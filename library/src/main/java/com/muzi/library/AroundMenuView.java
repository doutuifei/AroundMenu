package com.muzi.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by muzi on 2018/4/9.
 * 727784430@qq.com
 */
public class AroundMenuView extends View {

    private int circleSize;

    private int radius;//半径

    private int minSize = 120;//最小尺寸

    private Paint paint;

    public AroundMenuView(Context context) {
        this(context, null);
    }

    public AroundMenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMeasure, heightMeasure;
        int widthMode, heightMode;
        widthMeasure = MeasureSpec.getSize(widthMeasureSpec);
        widthMode = MeasureSpec.getMode(widthMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
            widthMeasure = com.muzi.library.utils.Display.dp2px(minSize);
        }

        heightMeasure = MeasureSpec.getSize(heightMeasureSpec);
        heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (heightMode == MeasureSpec.AT_MOST) {
            heightMeasure = com.muzi.library.utils.Display.dp2px(minSize);
        }

        circleSize = widthMeasure > heightMeasure ? heightMeasure : widthMeasure;

        radius = circleSize / 2;
        setMeasuredDimension(circleSize, circleSize);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawCircle(radius, radius, radius, paint);
    }
}
