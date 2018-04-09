package com.muzi.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by muzi on 2018/4/9.
 * 727784430@qq.com
 */
public class AroundMenuView extends View {

    private int widthMeasure, heightMeasure;
    private int minSize = 60;

    public AroundMenuView(Context context) {
        super(context);
    }

    public AroundMenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMeasure, heightMeasure;
        int widthMode, heightMode;
        widthMeasure = MeasureSpec.getSize(widthMeasureSpec);
        widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            if (widthMode == MeasureSpec.AT_MOST) {
                widthMeasure = minSize;
            }
        }

        heightMeasure = MeasureSpec.getSize(widthMeasureSpec);
        heightMode = MeasureSpec.getMode(widthMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            if (heightMode == MeasureSpec.AT_MOST) {
                heightMeasure = minSize;
            }
        }
        setMeasuredDimension(widthMeasure,heightMeasure);
    }
}
