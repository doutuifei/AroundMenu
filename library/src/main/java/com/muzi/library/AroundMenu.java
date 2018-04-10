package com.muzi.library;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by muzi on 2018/4/10.
 * 727784430@qq.com
 */

public class AroundMenu extends FrameLayout implements View.OnClickListener {

    private List<MenuButton> buttonList;
    private int count = 5;//数量
    private int radius = 500;//半径
    private int childWidth;
    private boolean isShowing;

    public AroundMenu(Context context) {
        this(context, null);
    }

    public AroundMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        MenuButton centerButton = new MenuButton(context);
        centerButton.setId(-1);
        centerButton.setOnClickListener(this);
        addView(centerButton);

        for (int i = 0; i < count; i++) {
            MenuButton menuButton = new MenuButton(context);
            menuButton.setVisibility(GONE);
            menuButton.setOnClickListener(this);
            menuButton.setId(i);
            addView(menuButton);
        }
    }

    public void setButtonList(List<MenuButton> buttonList) {
        this.buttonList = buttonList;
        if (buttonList == null && buttonList.size() < 1) {
            return;
        }
        count = buttonList.size();
        for (MenuButton menuButton : buttonList) {
            addView(menuButton);
        }

        invalidate();
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


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        for (int i = 1; i <= count; i++) {
            View view = getChildAt(i);
            view.layout(radius - childWidth, radius - childWidth, radius, radius);
        }
        childWidth = getChildAt(0).getMeasuredWidth();
        getChildAt(0).layout(radius - childWidth, radius - childWidth, radius, radius);
    }

    @Override
    public void onClick(View v) {
        Log.d("AroundMenu", "v.getId():" + v.getId());
        if (!isShowing) {
            openMenu();
        } else {
            closeMenu();
        }
    }

    private void openMenu() {
        isShowing = true;
        for (int i = 0; i <= count; i++) {
            View view = getChildAt(i + 1);
            openAnimator(view, i, count, radius - childWidth);
        }
    }

    private void closeMenu() {
        isShowing = false;
        for (int i = 0; i <= count; i++) {
            View view = getChildAt(i + 1);
            closeAnimator(view, i, count, radius - childWidth);
        }
    }

    private void openAnimator(View view, int index, int total, int radius) {
        if (view == null) {
            return;
        }
        if (view.getVisibility() == GONE) {
            view.setVisibility(VISIBLE);
        }
        double degree = Math.toRadians(90) / (total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1));
        set.setInterpolator(new OvershootInterpolator());
        set.setDuration(800).start();
    }

    private void closeAnimator(final View view, int index, int total, int radius) {
        if (view == null) {
            return;
        }
        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0.1f));
        set.setDuration(800).start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public boolean isShowing() {
        return isShowing;
    }
}
