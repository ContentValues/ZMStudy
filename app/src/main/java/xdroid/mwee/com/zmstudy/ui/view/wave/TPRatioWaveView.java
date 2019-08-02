/*
 *  Copyright (C) 2015, gelitenight(gelitenight@gmail.com).
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package xdroid.mwee.com.zmstudy.ui.view.wave;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class TPRatioWaveView extends View {

    /**
     * Amplitude(振幅) - 波浪垂直振动时偏离水面的最大距离
     */
    private static final float DEFAULT_AMPLITUDE_RATIO = 0.05f;
    /**
     * 波浪静止时水面距离底部的高度
     */
    private static final float DEFAULT_WATER_LEVEL_RATIO = 0.5f;
    /**
     * 一个完整的波浪的水平长度
     */
    private static final float DEFAULT_WAVE_LENGTH_RATIO = 1.0f;
    /**
     * 波浪相对于初始位置的水平偏移
     */
    private static final float DEFAULT_WAVE_SHIFT_RATIO = 0.0f;


    private boolean mShowWave;

    private BitmapShader mWaveShader;
    private Matrix mShaderMatrix;
    private Paint mViewPaint;
    private Paint mInBorderPaint;

    //外边框的扫描色
    private Paint mSwipBorderPaint;
    //外边框扫描小圆点的画笔
    private Paint mSwipDotPaint;

    private float mDefaultAmplitude;
    private float mDefaultWaterLevel;
    private float mDefaultWaveLength;
    private double mDefaultAngularFrequency;

    private float mAmplitudeRatio = DEFAULT_AMPLITUDE_RATIO;
    private float mWaveLengthRatio = DEFAULT_WAVE_LENGTH_RATIO;
    private float mWaterLevelRatio = DEFAULT_WATER_LEVEL_RATIO;
    private float mWaveShiftRatio = DEFAULT_WAVE_SHIFT_RATIO;

    private int mBehindWaveColor;
    private int mCenterWaveColor;
    private int mFrontWaveColor;

    private Paint mPaintText;//动态文字画笔
    private Paint mSubPaintText;//下方固定文字画笔

    public TPRatioWaveView(Context context) {
        this(context, null);
    }

    public TPRatioWaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TPRatioWaveView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mShaderMatrix = new Matrix();
        mViewPaint = new Paint();
        mViewPaint.setAntiAlias(true);

        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(Color.BLACK);
        mPaintText.setFakeBoldText(true);
        mPaintText.setTextSize(dip2px(context, 25));

        mSubPaintText = new Paint();
        mSubPaintText.setAntiAlias(true);
        mSubPaintText.setColor(Color.GRAY);
        mSubPaintText.setTextSize(dip2px(context, 18));


        mSwipDotPaint = new Paint();
        mSwipDotPaint.setAntiAlias(true);
        mSwipDotPaint.setStyle(Style.STROKE);
        mSwipDotPaint.setColor(Color.BLACK);
        mSwipDotPaint.setStrokeWidth(dip2px(getContext(), 2));

    }

    public float getWaveShiftRatio() {
        return mWaveShiftRatio;
    }

    public void setWaveShiftRatio(float waveShiftRatio) {
        if (mWaveShiftRatio != waveShiftRatio) {
            mWaveShiftRatio = waveShiftRatio;
            invalidate();
        }
    }

    public float getWaterLevelRatio() {
        return mWaterLevelRatio;
    }

    public void setWaterLevelRatio(float waterLevelRatio) {
        if (mWaterLevelRatio != waterLevelRatio) {
            mWaterLevelRatio = waterLevelRatio;
            invalidate();
        }
    }

    public float getAmplitudeRatio() {
        return mAmplitudeRatio;
    }

    public void setAmplitudeRatio(float amplitudeRatio) {
        if (mAmplitudeRatio != amplitudeRatio) {
            mAmplitudeRatio = amplitudeRatio;
            invalidate();
        }
    }

    public float getWaveLengthRatio() {
        return mWaveLengthRatio;
    }

    public void setWaveLengthRatio(float waveLengthRatio) {
        mWaveLengthRatio = waveLengthRatio;
    }

    public boolean isShowWave() {
        return mShowWave;
    }

    public void setShowWave(boolean showWave) {
        mShowWave = showWave;
    }


    /**
     * 设置内边框的宽度 以及渐变色
     *
     * @param width
     * @param color0
     * @param color1
     */
    public void setInBorder(int width, int color0, int color1) {
        mInBorderPaint = new Paint();
        mInBorderPaint.setAntiAlias(true);
        mInBorderPaint.setStyle(Style.STROKE);
        //todo 效果不明显
        mInBorderPaint.setShader(new SweepGradient(getWidth() / 2f, getHeight() / 2f, color0, color1));
        mInBorderPaint.setStrokeWidth(width);

        invalidate();
    }

    /**
     * 设置外边框的扫描线圈
     *
     * @param width
     * @param color0
     * @param color1
     */
    public void setSwipBorder(int width, int color0, int color1) {
        mSwipBorderPaint = new Paint();
        mSwipBorderPaint.setAntiAlias(true);
        mSwipBorderPaint.setStyle(Style.STROKE);
        mSwipBorderPaint.setShader(new SweepGradient(getWidth() / 2f, getHeight() / 2f, color0, color1));
        mSwipBorderPaint.setStrokeWidth(width);
        invalidate();
    }


    /**
     * 设置水波的波浪
     *
     * @param behindWaveColor
     * @param mCenterWaveColor
     * @param frontWaveColor
     */
    public void setWaveColor(int behindWaveColor, int mCenterWaveColor, int frontWaveColor) {
        this.mBehindWaveColor = behindWaveColor;
        this.mCenterWaveColor = mCenterWaveColor;
        this.mFrontWaveColor = frontWaveColor;

        if (getWidth() > 0 && getHeight() > 0) {
            // need to recreate shader when color changed
            mWaveShader = null;
            createShader();
            invalidate();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        createShader();
    }

    /**
     * Create the shader with default waves which repeat horizontally, and clamp vertically
     */
    private void createShader() {
        mDefaultAngularFrequency = 2.0f * Math.PI / DEFAULT_WAVE_LENGTH_RATIO / getWidth();
        mDefaultAmplitude = getHeight() * DEFAULT_AMPLITUDE_RATIO;
        mDefaultWaterLevel = getHeight() * DEFAULT_WATER_LEVEL_RATIO;
        mDefaultWaveLength = getWidth();

        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint wavePaint = new Paint();
        wavePaint.setStrokeWidth(2);
        wavePaint.setAntiAlias(true);

        // Draw default waves into the bitmap
        // y=Asin(ωx+φ)+h
        final int endX = getWidth() + 1;
        final int endY = getHeight() + 1;

        float[] waveY = new float[endX];

        wavePaint.setColor(mBehindWaveColor);
        for (int beginX = 0; beginX < endX; beginX++) {
            double wx = beginX * mDefaultAngularFrequency;
            float beginY = (float) (mDefaultWaterLevel + mDefaultAmplitude * Math.sin(wx));
            canvas.drawLine(beginX, beginY, beginX, endY, wavePaint);

            waveY[beginX] = beginY;
        }

        //wavePaint.setColor(Color.rgb(135, 184, 234));
        wavePaint.setColor(mCenterWaveColor);
        final int wave3Shift = (int) (mDefaultWaveLength / 2);
        for (int beginX = 0; beginX < endX; beginX++) {
            canvas.drawLine(beginX, waveY[(beginX + wave3Shift) % endX], beginX, endY, wavePaint);
        }

        wavePaint.setColor(mFrontWaveColor);
        final int wave2Shift = (int) (mDefaultWaveLength / 4);
        for (int beginX = 0; beginX < endX; beginX++) {
            canvas.drawLine(beginX, waveY[(beginX + wave2Shift) % endX], beginX, endY, wavePaint);
        }
        // use the bitamp to create the shader
        mWaveShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        mViewPaint.setShader(mWaveShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mShowWave && mWaveShader != null) {
            if (mViewPaint.getShader() == null) {
                mViewPaint.setShader(mWaveShader);
            }
            mShaderMatrix.setScale(
                    mWaveLengthRatio / DEFAULT_WAVE_LENGTH_RATIO,
                    mAmplitudeRatio / DEFAULT_AMPLITUDE_RATIO,
                    0,
                    mDefaultWaterLevel);
            mShaderMatrix.postTranslate(mWaveShiftRatio * getWidth(), (DEFAULT_WATER_LEVEL_RATIO - mWaterLevelRatio) * getHeight());
            mWaveShader.setLocalMatrix(mShaderMatrix);

            //圆心X的坐标
            float centerX = getWidth() / 2f;
            //圆心Y的坐标
            float centerY = getHeight() / 2f;

            //画内边框
            float inBorderWidth = mInBorderPaint.getStrokeWidth();
            canvas.drawCircle(centerX, centerY, (getWidth() - inBorderWidth) / 2f, mInBorderPaint);


            float swipBorderWidth = mSwipBorderPaint.getStrokeWidth();


            //扫描开始的偏移量
            float startAngle = -0;

            //画扫描圆弧
            RectF rectF = new RectF(swipBorderWidth / 2
                    , swipBorderWidth / 2
                    , getWidth() - inBorderWidth / 2 + swipBorderWidth / 2
                    , getHeight() - inBorderWidth / 2 + swipBorderWidth / 2);
            canvas.drawArc(rectF, startAngle, mSweepAngle, false, mSwipBorderPaint);


            /**
             * 最里面的水波圆的绘制
             */
            float radius = centerX - 0 - inBorderWidth;
            canvas.drawCircle(centerX, centerY, radius, mViewPaint);


            //偏移的角度
            float offestDot = 1;
            //根据偏移的监督算出小圆的半径

            //描边小圆点的大小
            final float x = (float) (((getWidth() / 2f) * Math.cos(Math.toRadians(mSweepAngle - offestDot))));
            final float y = (float) (((getWidth() / 2f) * Math.sin(Math.toRadians(mSweepAngle - offestDot))));


            //算出小圆的半径
            float radiusOut = getWidth() / 2f;
            float radiusDot = (float) Math.hypot(radiusOut * Math.sin(Math.toRadians(offestDot)), radiusOut * (1 - Math.cos(Math.toRadians(offestDot))));
            canvas.drawCircle(centerX +x,centerY + y, dip2px(getContext(), radiusDot), mSwipDotPaint);

            //求小球圆心的位置


//            float offestDegree = (float) Math.toDegrees(Math.asin(Math.sin(y / (getWidth() / 2))));
//
//            System.out.println("offestDegree-->"+offestDegree);
//            System.out.println("Math.toRadians-->"+Math.toRadians( 90 - offestDegree));
//            System.out.println("Math.sin-->"+Math.sin(Math.toRadians( 90 - offestDegree)));
//
//            float dotX = (float) ((getWidth() / 2f) * Math.cos(Math.toRadians( 90 - offestDegree)));
//            float dotY = (float) ((getWidth() / 2f) * Math.sin(Math.toRadians( 90 - offestDegree)));
//
//            canvas.drawCircle(centerX + dotX, centerY + dotY, dip2px(getContext(), radiusDot), mSwipDotPaint);


            //画动态变化文字
            float mTetxWidth = mPaintText.measureText(mTitleText, 0, mTitleText.length());
            float dx = centerX - mTetxWidth / 2;
            Paint.FontMetricsInt fontMetricsInt = mPaintText.getFontMetricsInt();
            float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
            float baseLine = centerY + dy - dip2px(getContext(), 7);//向上位移7个dp
            canvas.drawText(mTitleText, dx, baseLine, mPaintText);

            //画文字
            float mSubTetxWidth = mSubPaintText.measureText(mSubText, 0, mSubText.length());
            Paint.FontMetricsInt fontMetricsIntSub = mSubPaintText.getFontMetricsInt();
            float dySub = (fontMetricsIntSub.bottom - fontMetricsIntSub.top) / 2 - fontMetricsIntSub.bottom;
            float baseLineSub = centerY + dySub + dy + dip2px(getContext(), 7);//向下位移7个dp
            canvas.drawText(mSubText, centerX - mSubTetxWidth / 2, baseLineSub, mSubPaintText);

        } else {
            mViewPaint.setShader(null);
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int mLoadingTime = 8 * 1000;//时间，单位秒
    private float mSweepAngle;//扫过的角度
    private String mTitleText = "";//要绘制的动态文字
    private String mSubText = "应出勤人数";//要提示的动态文字

    public void start() {
        this.setShowWave(true);
        /**
         * 水波动画
         */
        List<Animator> animators = new ArrayList<>();
        // horizontal animation.
        // wave waves infinitely.this
        ObjectAnimator waveShiftAnim = ObjectAnimator.ofFloat(
                this, "waveShiftRatio", 0f, 1f);
        waveShiftAnim.setRepeatCount(ValueAnimator.INFINITE);
        waveShiftAnim.setDuration(1000);
        waveShiftAnim.setInterpolator(new LinearInterpolator());
        animators.add(waveShiftAnim);
        // vertical animation.
        // water level increases from 0 to center of WaveView
        ObjectAnimator waterLevelAnim = ObjectAnimator.ofFloat(
                this, "waterLevelRatio", 0f, 0.5f);
        waterLevelAnim.setDuration(10000);
        waterLevelAnim.setInterpolator(new DecelerateInterpolator());
        animators.add(waterLevelAnim);
        // amplitude animation.
        // wave grows big then grows small, repeatedly
        ObjectAnimator amplitudeAnim = ObjectAnimator.ofFloat(
                this, "amplitudeRatio", 0.0001f, 0.05f);
        amplitudeAnim.setRepeatCount(ValueAnimator.INFINITE);
        amplitudeAnim.setRepeatMode(ValueAnimator.REVERSE);
        amplitudeAnim.setDuration(mLoadingTime);
        amplitudeAnim.setInterpolator(new LinearInterpolator());
        animators.add(amplitudeAnim);
        AnimatorSet mWaterAnimatorSet = new AnimatorSet();
        mWaterAnimatorSet.playTogether(animators);
        mWaterAnimatorSet.start();

        /**
         * 外层线圈绘制动画
         */
        ValueAnimator animator = ValueAnimator.ofFloat(0, 270);
        animator.setDuration(mLoadingTime);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mSweepAngle = (float) valueAnimator.getAnimatedValue();
                //获取到需要绘制的角度，重新绘制
                invalidate();
            }
        });
        animator.start();
        //这里是时间获取和赋值
        ValueAnimator animator1 = ValueAnimator.ofInt(mLoadingTime / 1000, 0);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int time = (int) valueAnimator.getAnimatedValue();
                mTitleText = time + "/" + mLoadingTime / 1000;
            }
        });
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator, animator1);
        set.setDuration(mLoadingTime);
        set.setInterpolator(new LinearInterpolator());
        set.start();
    }

}
