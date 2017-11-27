package com.example.xuqi.shiningtextviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;

/**
 * Created by xuqi on 17/11/27.
 * 跑马灯效果
 */

public class ShiningTextView extends android.support.v7.widget.AppCompatTextView {
    private int mWidth;
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mMatrix;
    private int mTranslate;

    public ShiningTextView(Context context) {
        super(context);
    }

    public ShiningTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShiningTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = getMeasuredWidth();
        if (mWidth > 0) {
            mPaint = getPaint();
            // 宽度与文字宽度一致
            mLinearGradient = new LinearGradient(0, 0, mWidth, 0, new int[]{Color.BLUE, Color.GREEN, Color.RED}, null, Shader.TileMode.CLAMP);
            mPaint.setShader(mLinearGradient);
            mMatrix = new Matrix();
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mLinearGradient != null) {
            // mTranslate为LinearGradient移动的距离
            mTranslate += mWidth / 10;
            // 从左往右移动到文字右边在移到最左边
            if (mTranslate > mWidth) {
                mTranslate = -mWidth;
            }
            // 移动LinearGradient
            mMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mMatrix);
            // 加一个延时使得颜色保留100ms，之后再改变颜色
            postInvalidateDelayed(100);
        }
    }
}
