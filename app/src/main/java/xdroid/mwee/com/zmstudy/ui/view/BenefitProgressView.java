package xdroid.mwee.com.zmstudy.ui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mwee.android.tools.DM;

import java.math.BigDecimal;


/**
 * Author：created by SugarT
 * Time：2019/8/16 14
 */
public class BenefitProgressView extends View {


    private BigDecimal mMaxProgress = new BigDecimal(100);
    private BigDecimal mCurrentProgress = BigDecimal.ZERO;
    private float mProgress = 0;
    private int mAnimDuration = 1000;
    private int mWidth;


    private int mHeight;

    //基准线 实线
    private Paint mPaintDatum;

    //基准线 虚线
    private Paint mPaintDatumDash;

    //进度线
    private Paint mPaintSchedule;

    //进度线文字描述
    private Paint mPaintScheduleText;


    /**
     * 权益类的比例范围是70%~90%
     */
    private Paint mPaintText;

    private float mRatioBegin = (float) 0.7;
    private float mRratioFinish = (float) 0.9;


    public BenefitProgressView(Context context) {
        this(context, null);
    }

    public BenefitProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BenefitProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        mPaintDatum = new Paint();
        mPaintDatum.setColor(Color.GRAY);
        mPaintDatum.setAntiAlias(true);
        mPaintDatum.setStyle(Paint.Style.STROKE);
        mPaintDatum.setStrokeWidth(DM.dip2px(getContext(), 2));


        mPaintText = new Paint();
        mPaintText.setColor(Color.GRAY);
        mPaintText.setAntiAlias(true);
        mPaintText.setTextSize(DM.dip2px(getContext(), 12));
        mPaintText.setStyle(Paint.Style.STROKE);
        mPaintText.setStrokeWidth(DM.dip2px(getContext(), 1));


        mPaintDatumDash = new Paint();
        mPaintDatumDash.setColor(Color.GRAY);
        mPaintDatumDash.setAntiAlias(true);
        mPaintDatumDash.setStyle(Paint.Style.STROKE);
        mPaintDatumDash.setStrokeWidth(DM.dip2px(getContext(), 2));
        PathEffect pathEffect = new DashPathEffect(new float[]{10, 6, 10, 6}, 0);
        mPaintDatumDash.setPathEffect(pathEffect);


        mPaintSchedule = new Paint();
        mPaintSchedule.setColor(Color.BLUE);
        mPaintSchedule.setAntiAlias(true);
        mPaintSchedule.setStyle(Paint.Style.STROKE);
        mPaintSchedule.setStrokeWidth(DM.dip2px(getContext(), 18));


        mPaintScheduleText = new Paint();
        mPaintScheduleText.setAntiAlias(true);
        mPaintScheduleText.setColor(Color.BLUE);
        mPaintScheduleText.setTextSize(DM.dip2px(getContext(), 12));

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {


        BigDecimal currentPercent = mCurrentProgress.divide(mMaxProgress);

        /**
         * 画虚线
         */
        canvas.drawLine(0, mHeight / 2, mWidth * mRratioFinish, mHeight / 2, mPaintDatumDash);
        canvas.drawPath(createLinePath(mWidth * mRratioFinish, mHeight / 2, true), mPaintText);
        //虚线权益类的比例 描述文字
        String ratioFinishText = mRratioFinish * 100 + "%";
        canvas.drawText(ratioFinishText, mWidth * mRratioFinish - mPaintText.measureText(ratioFinishText) / 2, mHeight / 2 - DM.dip2px(getContext(), 20), mPaintText);


        /**
         * 画实线
         */
        canvas.drawLine(0, mHeight / 2, mWidth * mRatioBegin, mHeight / 2, mPaintDatum);
        canvas.drawPath(createLinePath(mWidth * mRatioBegin, mHeight / 2, true), mPaintText);
        //实线权益类的比例 描述文字
        String ratioBeginhText = mRatioBegin * 100 + "%";
        canvas.drawText(ratioBeginhText, mWidth * mRatioBegin - mPaintText.measureText(ratioBeginhText) / 2, mHeight / 2 - DM.dip2px(getContext(), 20), mPaintText);


        /**
         * 画动态输入比率
         */
        if (mCurrentProgress.compareTo(BigDecimal.ZERO) > 0) {
            String text = mCurrentProgress + "%";
            Paint.FontMetricsInt fontMetricsInt = mPaintScheduleText.getFontMetricsInt();
            float dy = fontMetricsInt.bottom - fontMetricsInt.top;
            int offest = DM.dip2px(getContext(), 12);
            float scheduletextX = mWidth * currentPercent.floatValue() - mPaintScheduleText.measureText(text) / 2;
            float scheduletextY = mHeight / 2 + dy + offest;

            if (mCurrentProgress.compareTo(new BigDecimal(mProgress)) == 0) {//文字描绘等动画结束
                canvas.drawText(text, scheduletextX, scheduletextY + DM.dip2px(getContext(), 4), mPaintScheduleText);
                canvas.drawPath(createLinePath(mWidth * currentPercent.floatValue(), mHeight / 2 + DM.dip2px(getContext(), 10), false), mPaintText);
            }

            canvas.drawLine(0, mHeight / 2, mWidth * currentPercent.floatValue(), mHeight / 2, mPaintSchedule);
        }
    }


    /**
     * 控制文字描述的折线路径
     *
     * @param x
     * @param y
     * @param top
     * @return
     */
    private Path createLinePath(float x, float y, boolean top) {

        Path linePath = new Path();
        int offest = 4;
        if (top) {
            //空出4个DP的偏移量
            linePath.moveTo(x, y - DM.dip2px(getContext(), offest));
            linePath.rLineTo(0, -DM.dip2px(getContext(), offest));
            linePath.rLineTo(-DM.dip2px(getContext(), offest), -DM.dip2px(getContext(), offest));
        } else {
            linePath.moveTo(x, y + DM.dip2px(getContext(), offest / 2));
            linePath.rLineTo(0, DM.dip2px(getContext(), offest));
            linePath.rLineTo(-DM.dip2px(getContext(), offest), DM.dip2px(getContext(), offest));
        }

        return linePath;
    }


    private void startProgressWithAnim() {
        ValueAnimator valueAnim = ValueAnimator.ofFloat(mCurrentProgress.floatValue(), mProgress);
        valueAnim.setDuration(mAnimDuration);
        valueAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentProgress = new BigDecimal((float) animation.getAnimatedValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
                Log.d("valueAnim", "onAnimationUpdate -->" + mCurrentProgress);
                invalidate();
            }
        });
        valueAnim.start();
    }

    /**
     * 设置当前进度值
     *
     * @param progress
     */
    public void setProgress(float progress) {
        mProgress = progress;
        startProgressWithAnim();
    }

}
