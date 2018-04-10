package com.muzi.library;

import android.graphics.Rect;

/**
 * Created by muzi on 2018/4/10.
 * 727784430@qq.com
 */

public class AroundMenuHelp {

    /**
     * 计算中间按钮初始位置
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
            case MenuOrientation.BOTTOM:
                rect.left = radius / 2 - childWidth / 2;
                rect.top = radius - childWidth;
                rect.right = radius / 2 + childWidth / 2;
                rect.bottom = radius;
                break;
            case MenuOrientation.TOP:
                rect.left = radius / 2 - childWidth / 2;
                rect.top = 0;
                rect.right = radius / 2 + childWidth / 2;
                rect.bottom = childWidth;
                break;
            case MenuOrientation.LEFT:
                rect.left = 0;
                rect.top = radius / 2 - childWidth / 2;
                rect.right = childWidth;
                rect.bottom = radius / 2 + childWidth / 2;
                break;
            case MenuOrientation.RIGHT:
                rect.left = radius - childWidth;
                rect.top = radius / 2 - childWidth / 2;
                rect.right = radius;
                rect.bottom = radius / 2 + childWidth / 2;
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

    /**
     * 计算位置距离
     *
     * @param orientation
     * @param index
     * @param total
     * @param radius
     */
    public static int[] calculateTranslation(@MenuOrientation.Orientation int orientation,
                                             int index, int total, int radius) {
        double degree, degree90;
        int[] translation = new int[2];
        switch (orientation) {
            case MenuOrientation.TOP:
                degree = Math.toRadians(180) / (total - 1) * index;
                degree90 = Math.toRadians(90) / (total - 1) * index;
                if (degree < degree90) {
                    translation[1] = -(int) (radius / 2 * Math.sin(degree));
                } else {
                    translation[1] = (int) (radius / 2 * Math.sin(degree));
                }
                translation[0] = -(int) (radius / 2 * Math.cos(degree));
                break;
            case MenuOrientation.BOTTOM:
                degree = Math.toRadians(180) / (total - 1) * index;
                degree90 = Math.toRadians(90) / (total - 1) * index;
                if (degree < degree90) {
                    translation[1] = (int) (radius / 2 * Math.sin(degree));
                } else {
                    translation[1] = -(int) (radius / 2 * Math.sin(degree));
                }
                translation[0] = -(int) (radius / 2 * Math.cos(degree));
                break;
            case MenuOrientation.LEFT:
                degree = Math.toRadians(180) / (total - 1) * index;
                degree90 = Math.toRadians(90) / (total - 1) * index;
                if (degree < degree90) {
                    translation[0] = -(int) (radius / 2 * Math.sin(degree));
                } else {
                    translation[0] = (int) (radius / 2 * Math.sin(degree));
                }
                translation[1] = -(int) (radius / 2 * Math.cos(degree));
                break;
            case MenuOrientation.RIGHT:
                degree = Math.toRadians(180) / (total - 1) * index;
                degree90 = Math.toRadians(90) / (total - 1) * index;
                if (degree < degree90) {
                    translation[0] = (int) (radius / 2 * Math.sin(degree));
                } else {
                    translation[0] = -(int) (radius / 2 * Math.sin(degree));
                }
                translation[1] = -(int) (radius / 2 * Math.cos(degree));
                break;
            case MenuOrientation.RIGHT_BOTTOM:
                degree = Math.toRadians(90) / (total - 1) * index;
                translation[0] = -(int) (radius * Math.sin(degree));
                translation[1] = -(int) (radius * Math.cos(degree));
                break;
            case MenuOrientation.RIGHT_TOP:
                degree = (Math.toRadians(90) / (total - 1) * index);
                translation[0] = -(int) (radius * Math.sin(degree));
                translation[1] = (int) (radius * Math.cos(degree));
                break;
            case MenuOrientation.LEFT_BOTTOM:
                degree = (Math.toRadians(90) / (total - 1) * index);
                translation[0] = (int) (radius * Math.sin(degree));
                translation[1] = -(int) (radius * Math.cos(degree));
                break;
            case MenuOrientation.LEFT_TOP:
                degree = Math.toRadians(90) / (total - 1) * index;
                translation[0] = (int) (radius * Math.sin(degree));
                translation[1] = (int) (radius * Math.cos(degree));
                break;
        }
        return translation;
    }

}
