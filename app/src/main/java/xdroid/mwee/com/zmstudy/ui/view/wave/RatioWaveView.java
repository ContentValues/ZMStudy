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
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.mwee.android.tools.DM;

import java.util.ArrayList;
import java.util.List;

import xdroid.mwee.com.zmstudy.ui.fragment.TextDrawable;

/**
 * Author：created by SugarT
 * Time：2019/7/31 11
 */
public class RatioWaveView extends View {

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

    private float mDefaultAmplitude;
    private float mDefaultWaterLevel;
    private float mDefaultWaveLength;
    private double mDefaultAngularFrequency;

    private float mAmplitudeRatio = DEFAULT_AMPLITUDE_RATIO;
    private float mWaveLengthRatio = DEFAULT_WAVE_LENGTH_RATIO;
    private float mWaterLevelRatio = DEFAULT_WATER_LEVEL_RATIO;
    private float mWaveShiftRatio = DEFAULT_WAVE_SHIFT_RATIO;

    private int mBehindWaveColor = Color.rgb(198, 222, 246);
    private int mCenterWaveColor = Color.rgb(135, 184, 234);
    private int mFrontWaveColor = Color.rgb(19, 101, 209);

    private Paint mCenterPaintText;//动态文字画笔
    private Paint mCenterSubPaintText;//下方固定文字画笔

    ////总人数
    private float centerPersonTotal = 8;

    ////开始的人数
    private float centerPensonStart = 6;

    public RatioWaveView(Context context) {
        this(context, null);
    }

    public RatioWaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioWaveView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mShaderMatrix = new Matrix();
        mViewPaint = new Paint();
        mViewPaint.setAntiAlias(true);

        mInBorderPaint = new Paint();
        mInBorderPaint.setAntiAlias(true);
        mInBorderPaint.setStyle(Paint.Style.STROKE);
        //todo 效果不明显
        mInBorderPaint.setShader(new SweepGradient(getWidth() / 2f, getHeight() / 2f, Color.rgb(252, 254, 255), Color.rgb(135, 184, 234)));
        mInBorderPaint.setStrokeWidth(DM.dip2px(getContext(), 16));


        mSwipBorderPaint = new Paint();
        mSwipBorderPaint.setAntiAlias(true);
        mSwipBorderPaint.setStyle(Paint.Style.STROKE);
        mSwipBorderPaint.setShader(new SweepGradient(getWidth() / 2f, getHeight() / 2f, Color.rgb(198, 222, 246), Color.BLUE));
        mSwipBorderPaint.setStrokeWidth(DM.dip2px(getContext(), 8));


        mCenterPaintText = new Paint();
        mCenterPaintText.setAntiAlias(true);
        mCenterPaintText.setFakeBoldText(true);
        mCenterPaintText.setColor(Color.BLACK);
        mCenterPaintText.setTextSize(DM.dip2px(context, 25));

        mCenterSubPaintText = new Paint();
        mCenterSubPaintText.setAntiAlias(true);
        mCenterSubPaintText.setColor(Color.GRAY);
        mCenterSubPaintText.setTextSize(DM.dip2px(context, 18));


        int arcColor = getResources().getColor(android.R.color.darker_gray);
        mThumb = TextDrawable.builder()
                .beginConfig()
                .boardColor(arcColor)
                .textColor(arcColor)
                .withBorder(3).endConfig()
                .round()
                .build("0", getResources()
                        .getColor(android.R.color.holo_blue_bright));
        mThumb.setBounds(-36, -36, 36, 36);

    }


    private Drawable mThumb;



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



    private int mTranslateX;
    private int mTranslateY;


    private int mThumbXPos;
    private int mThumbYPos;

    private final int mAngleOffset = -90;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /**
         * 确定圆弧范围
         */
        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
//        final int min = Math.min(width, height);
//        float top = 0;
//        float left = 0;
//        int arcDiameter = 0;

        mTranslateX = (int) (width * 0.5f);
        mTranslateY = (int) (height * 0.5f);

//        arcDiameter = min - getPaddingLeft();
//        mArcRadius = arcDiameter / 2;
//        top = height / 2 - (arcDiameter / 2);
//        left = width / 2 - (arcDiameter / 2);
//        mArcRect.set(left, top, left + arcDiameter, top + arcDiameter);

        //int arcStart = (int) mProgressSweep + 0 + 0 + 90;
//        mThumbXPos = (int) (mArcRadius * Math.cos(Math.toRadians(arcStart)));
//        mThumbYPos = (int) (mArcRadius * Math.sin(Math.toRadians(arcStart)));
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

            //容许的最大半径
            float allowMaxRadius = (float) (centerX * 0.8);

            //画内边框
            float inBorderWidth = mInBorderPaint.getStrokeWidth();
            canvas.drawCircle(centerX, centerY, (2 * allowMaxRadius - inBorderWidth) / 2f, mInBorderPaint);

            float swipBorderWidth = mSwipBorderPaint.getStrokeWidth();


            float swipBorderRadius = (2 * allowMaxRadius - swipBorderWidth) / 2f;

            RectF rectF = new RectF();
            rectF.set(centerX - swipBorderRadius, centerY - swipBorderRadius, centerX + swipBorderRadius, centerY + swipBorderRadius);
            canvas.drawArc(rectF, mAngleOffset, mProgressSweep, false, mSwipBorderPaint);

            /**
             * 最里面的水波圆的绘制
             */
            float radius = allowMaxRadius - 0 - inBorderWidth;
            canvas.drawCircle(centerX, centerY, radius, mViewPaint);

            String mCenterText = (int)centerPensonStart + "/" + (int)centerPersonTotal;

            //画动态变化文字
            float mTetxWidth = mCenterPaintText.measureText(mCenterText, 0, mCenterText.length());
            float dx = centerX - mTetxWidth / 2;
            Paint.FontMetricsInt fontMetricsInt = mCenterPaintText.getFontMetricsInt();
            float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
            float baseLine = centerY + dy - DM.dip2px(getContext(), 7);//向上位移7个dp
            canvas.drawText(mCenterText, dx, baseLine, mCenterPaintText);

            //画文字
            float mSubTetxWidth = mCenterSubPaintText.measureText(mCenterSubText, 0, mCenterSubText.length());
            Paint.FontMetricsInt fontMetricsIntSub = mCenterSubPaintText.getFontMetricsInt();
            float dySub = (fontMetricsIntSub.bottom - fontMetricsIntSub.top) / 2 - fontMetricsIntSub.bottom;
            float baseLineSub = centerY + dySub + dy + DM.dip2px(getContext(), 7);//向下位移7个dp
            canvas.drawText(mCenterSubText, centerX - mSubTetxWidth / 2, baseLineSub, mCenterSubPaintText);


            int thumbAngle = (int) (0 + mProgressSweep + 0 + 90);
            mThumbXPos = (int) (swipBorderRadius * Math.cos(Math.toRadians(thumbAngle)));
            mThumbYPos = (int) (swipBorderRadius * Math.sin(Math.toRadians(thumbAngle)));
            canvas.translate(mTranslateX - mThumbXPos, mTranslateY - mThumbYPos);
            mThumb.draw(canvas);

        } else {
            mViewPaint.setShader(null);
        }
    }



    private int mLoadingTime = 8 * 1000;//时间，单位秒
    private float mProgressSweep;//扫过的角度
    private String mCenterSubText = "应出勤人数";//要提示的动态文字

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

        //总共扫描的角度
        float swipFinishAngel = centerPensonStart / centerPersonTotal * 360;
        /**
         * 外层线圈绘制动画
         */
        ValueAnimator animator = ValueAnimator.ofFloat(0, swipFinishAngel);

        //动画时间 单位秒
        animator.setDuration(mLoadingTime);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mProgressSweep = (float) valueAnimator.getAnimatedValue();
                //获取到需要绘制的角度，重新绘制
                invalidate();
            }
        });
        animator.start();
        //这里是时间获取和赋值
    }
}
