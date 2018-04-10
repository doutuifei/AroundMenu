package com.muzi.library;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by muzi on 2018/4/10.
 * 727784430@qq.com
 */

public class MenuOrientation {

    public static final int CENTENR = 0;
    public static final int TOP = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT_TOP = 3;
    public static final int LEFT_BOTTOM = 4;
    public static final int RIGHT_TOP = 5;
    public static final int RIGHT_BOTTOM = 6;

    @IntDef({CENTENR, TOP, BOTTOM, LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Orientation {
    }

}
