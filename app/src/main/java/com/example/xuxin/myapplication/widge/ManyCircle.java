package com.example.xuxin.myapplication.widge;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by xuxin on 16-11-29.
 */

public class ManyCircle extends View {
    private Paint mPaint;
    private boolean isInit;
    private ValueAnimator mAnimator;

    private int mWigth;
    private int mHeight;
    private float mMaxRadius = 16;
    private float mPi2;
    private float mR;
    private float mRadiu = 10;

    public ManyCircle(Context context) {
        super(context);
        init();
    }

    public ManyCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit) {
            isInit = true;
            start();
            mWigth = getWidth() / 2;
            mHeight = getHeight() / 2;

            mPi2 = 2 * (float) Math.PI;
            mR = mWigth - mMaxRadius;

        }
        canvas.drawCircle((float) (mWigth + mR * Math.sin(0)), (float) (mHeight + mR * Math.cos(0)), f(mRadiu+0), mPaint);
        canvas.drawCircle((float) (mWigth + mR * Math.sin(mPi2 /8)), (float) (mHeight + mR * Math.cos(mPi2 /8)), f(mRadiu+2), mPaint);
        canvas.drawCircle((float) (mWigth + mR * Math.sin(mPi2 /8*2)), (float) (mHeight + mR * Math.cos(mPi2 /8*2)), f(mRadiu+4), mPaint);
        canvas.drawCircle((float) (mWigth + mR * Math.sin(mPi2 /8*3)), (float) (mHeight + mR * Math.cos(mPi2 /8*3)), f(mRadiu+6), mPaint);

        canvas.drawCircle((float) (mWigth + mR * Math.sin(mPi2 /8*4)), (float) (mHeight + mR * Math.cos(mPi2 /8*4)), f(mRadiu+8), mPaint);
        canvas.drawCircle((float) (mWigth + mR * Math.sin(mPi2 /8*5)), (float) (mHeight + mR * Math.cos(mPi2 /8*5)), f(mRadiu+10), mPaint);
        canvas.drawCircle((float) (mWigth + mR * Math.sin(mPi2 /8*6)), (float) (mHeight + mR * Math.cos(mPi2 /8*6)), f(mRadiu+12), mPaint);
        canvas.drawCircle((float) (mWigth + mR * Math.sin(mPi2 /8*7)), (float) (mHeight + mR * Math.cos(mPi2 /8*7)), f(mRadiu+14), mPaint);

        if (mAnimator.isRunning()) {
            mRadiu = (float) mAnimator.getAnimatedValue();
            invalidate();
        }
    }

    private void start() {
        if (mAnimator == null) {
            mAnimator = ValueAnimator.ofFloat(0, mMaxRadius);
            mAnimator.setInterpolator(new LinearInterpolator());
            mAnimator.setDuration(1000);
            mAnimator.start();
        } else {
            mAnimator.start();
        }
        postDelayed(new Runnable() {
            @Override
            public void run() {
                start();
                invalidate();
            }
        }, mAnimator.getDuration());
        invalidate();
    }

    private float f(float x) {
        if (x <= mMaxRadius / 2) {
            return x;
        } else if(x < mMaxRadius){
            return mMaxRadius - x;
        }else
        if(x < mMaxRadius * 3 / 2)
        {
            return x - mMaxRadius;
        }else {
            return 2 * mMaxRadius - x;
        }
    }
}
