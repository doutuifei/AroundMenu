package com.muzi.library;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * Created by muzi on 2018/4/10.
 * 727784430@qq.com
 */

public class AroundMenuHelp {

    /**
     * 计算初始位置
     *
     * @param orientation-中心按钮的位置
     * @param radius
     * @param childWidth
     * @return
     */
    public static Rect calculateLayout(@MenuOrientation.Orientation int orientation,
                                       int radius, int childWidth) {
        Rect rect = new Rect();
        switch (orientation) {
            case MenuOrientation.CENTENR:
                rect.left = radius / 2 - childWidth / 2;
                rect.top = radius / 2 - childWidth / 2;
                rect.right = radius / 2 + childWidth / 2;
                rect.bottom = radius / 2 + childWidth / 2;
                break;
            case MenuOrientation.TOP:
                rect.left = radius / 2 - childWidth / 2;
                rect.top = radius / 2;
                rect.right = radius / 2 + childWidth / 2;
                rect.bottom = radius / 2 + childWidth;
                break;
            case MenuOrientation.BOTTOM:
                rect.left = radius / 2 - childWidth / 2;
                rect.top = radius / 2 - childWidth;
                rect.right = radius / 2 + childWidth / 2;
                rect.bottom = radius / 2;
                break;
            case MenuOrientation.RIGHT_BOTTOM:
                rect.left = radius - childWidth;
                rect.top = radius - childWidth;
                rect.right = radius;
                rect.bottom = radius;
                break;
            case MenuOrientation.RIGHT_TOP:
                rect.left = radius - childWidth;
                rect.top = 0;
                rect.right = radius;
                rect.bottom = childWidth;
                break;
            case MenuOrientation.LEFT_BOTTOM:
                rect.left = 0;
                rect.top = radius - childWidth;
                rect.right = childWidth;
                rect.bottom = radius;
                break;
            case MenuOrientation.LEFT_TOP:
                rect.left = 0;
                rect.top = 0;
                rect.right = childWidth;
                rect.bottom = childWidth;
                break;
        }
        return rect;
    }

    private void openAnimator(final View view, int index, int total, int radius) {
        view.setVisibility(View.VISIBLE);
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

    /**
     * 计算位置距离
     *
     * @param orientation
     * @param index
     * @param total
     * @param radius
     */
    public static void calculateTranslation(@MenuOrientation.Orientation int orientation,
                                            int index, int total, int radius) {
        double degree;
        int[] translation = new int[2];
        switch (orientation) {
            case MenuOrientation.CENTENR:

                break;
            case MenuOrientation.TOP:

                break;
            case MenuOrientation.BOTTOM:

                break;
            case MenuOrientation.RIGHT_BOTTOM:
                degree = Math.toRadians(90) / (total - 1) * index;
                translation[0] = -(int) (radius * Math.sin(degree));
                translation[1] = -(int) (radius * Math.cos(degree));
                break;
            case MenuOrientation.RIGHT_TOP:

                break;
            case MenuOrientation.LEFT_BOTTOM:
                degree = Math.toRadians(90) / (total - 1) * index;
                translation[0] = (int) (radius * Math.sin(degree));
                translation[1] = (int) (radius * Math.cos(degree));
                break;
            case MenuOrientation.LEFT_TOP:

                break;
        }
    }

}
