package com.muzi.library;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by muzi on 2018/4/10.
 * 727784430@qq.com
 */

public class MenuOrientation {

    public static final int TOP = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    public static final int LEFT_TOP = 5;
    public static final int LEFT_BOTTOM = 6;
    public static final int RIGHT_TOP = 7;
    public static final int RIGHT_BOTTOM = 8;

    @IntDef({TOP, BOTTOM, LEFT, RIGHT, LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Orientation {
    }

}
