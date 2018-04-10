package com.muzi.library;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by muzi on 2018/4/10.
 * 727784430@qq.com
 */

public class AroundMenu<T extends View> extends FrameLayout implements View.OnClickListener {

    private List<T> buttonList;//view

    private int count;//数量

    private int radius = 500;//半径

    private int childWidth;//按钮的宽度

    private boolean isShowing;//状态

    private @MenuOrientation.Orientation
    int menuOrientation = MenuOrientation.RIGHT_BOTTOM;//方向

    public AroundMenu(Context context) {
        this(context, null);
    }

    public AroundMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        //默认添加中间按钮
        addCenterBtn();
    }

    public void setMenuOrientation(@MenuOrientation.Orientation int menuOrientation) {
        this.menuOrientation = menuOrientation;
        requestLayout();
    }

    /**
     * 添加菜单按钮
     *
     * @param buttonList
     */
    public void setMenuList(List<T> buttonList) {
        this.buttonList = buttonList;
        if (buttonList == null && buttonList.size() < 1) {
            return;
        }
        initView();
        requestLayout();
    }

    /**
     * 初始化菜单数量
     * 添加中间按钮
     */
    private void initView() {
        removeAllViews();
        count = buttonList.size();

        //默认添加中间按钮
        addCenterBtn();

        for (int i = 0; i < count; i++) {
            View view = buttonList.get(i);
            view.setVisibility(GONE);
            view.setOnClickListener(this);
            view.setId(i);
            addView(view);
        }
    }

    /**
     * 添加中间按钮
     */
    private void addCenterBtn() {
        MenuButton centerButton = new MenuButton(getContext());
        centerButton.setId(-1);
        centerButton.setOnClickListener(this);
        addView(centerButton);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSize, heightSize;
        int widthMode, heightMode;
        widthSize = MeasureSpec.getSize(widthMeasureSpec);
        widthMode = MeasureSpec.getMode(widthMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = radius;
        }

        heightSize = MeasureSpec.getSize(heightMeasureSpec);
        heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = radius;
        }

        radius = widthSize > heightSize ? heightSize : widthSize;

        setMeasuredDimension(widthSize, heightSize);
    }


    /**
     * 设置初始位置
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Rect rect = AroundMenuHelp.calculateLayout(menuOrientation, radius, childWidth);
        if (count > 0) {
            for (int i = 1; i <= count; i++) {
                View view = getChildAt(i);
                view.layout(rect.left, rect.top, rect.right, rect.bottom);
            }
        }
        childWidth = getChildAt(0).getMeasuredWidth();
        getChildAt(0).layout(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case -1:
                //中间按钮
                if (count < 1) {
                    return;
                }
                if (!isShowing) {
                    openMenu();
                } else {
                    closeMenu();
                }
                break;
            default:
                //点击menu
                if (isShowing) {
                    closeMenu();
                }
                if (onAroundMenuClick != null) {
                    onAroundMenuClick.onMenuClick(v.getId());
                }
                break;
        }
    }

    /**
     * 打开menu
     */
    public void openMenu() {
        if (isShowing) {
            return;
        }
        isShowing = true;
        for (int i = 0; i <= count; i++) {
            View view = getChildAt(i + 1);
            if (view != null) {
                openAnimator(view, i, count, radius - childWidth);
            }
        }
        if (onAroundMenuClick != null) {
            onAroundMenuClick.onCenterClick(isShowing);
        }
    }

    /**
     * 关闭menu
     */
    public void closeMenu() {
        if (!isShowing) {
            return;
        }
        isShowing = false;
        for (int i = 0; i <= count; i++) {
            View view = getChildAt(i + 1);
            if (view != null) {
                closeAnimator(view, i, count, radius - childWidth);
            }
        }
        if (onAroundMenuClick != null) {
            onAroundMenuClick.onCenterClick(isShowing);
        }
    }

    /**
     * 开启动画
     *
     * @param view
     * @param index
     * @param total
     * @param radius
     */
    private void openAnimator(final View view, int index, int total, int radius) {
        view.setVisibility(View.VISIBLE);
        int[] translation = AroundMenuHelp.calculateTranslation(menuOrientation, index, total, radius);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translation[0]),
                ObjectAnimator.ofFloat(view, "translationY", 0, translation[1]),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1));
        set.setInterpolator(new OvershootInterpolator());
        set.setDuration(800).start();
    }

    /**
     * 关闭动画
     *
     * @param view
     * @param index
     * @param total
     * @param radius
     */
    private void closeAnimator(final View view, int index, int total, int radius) {
        int[] translation = AroundMenuHelp.calculateTranslation(menuOrientation, index, total, radius);
        final AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translation[0], 0),
                ObjectAnimator.ofFloat(view, "translationY", translation[1], 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0.1f));
        set.setDuration(800).start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
                set.removeAllListeners();
            }
        });

    }

    /**
     * 返回当前状态
     *
     * @return
     */
    public boolean isShowing() {
        return isShowing;
    }

    /**
     * 回调
     *
     * @param onAroundMenuClick
     */
    public void setOnAroundMenuClick(OnAroundMenuClick onAroundMenuClick) {
        this.onAroundMenuClick = onAroundMenuClick;
    }

    private OnAroundMenuClick onAroundMenuClick;

    public interface OnAroundMenuClick {
        void onCenterClick(boolean isShowing);

        void onMenuClick(int position);
    }

}
