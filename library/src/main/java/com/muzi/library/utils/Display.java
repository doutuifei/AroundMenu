package com.muzi.library.utils;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by muzi on 2018/4/10.
 * 727784430@qq.com
 */

public class Display {

    /**
     * dp转px
     */
    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, Resources.getSystem().getDisplayMetrics());
    }

}
