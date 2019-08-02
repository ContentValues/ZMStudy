package xdroid.mwee.com.zmstudy.ui.view.tpie.chart;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import xdroid.mwee.com.zmstudy.ui.view.tpie.interfaces.IPieAxisData;
import xdroid.mwee.com.zmstudy.ui.view.tpie.render.PieChartRender;
import xdroid.mwee.com.zmstudy.ui.view.tpie.animation.ChartAnimator;
import xdroid.mwee.com.zmstudy.ui.view.tpie.compute.ComputePie;
import xdroid.mwee.com.zmstudy.ui.view.tpie.data.PieAxisData;
import xdroid.mwee.com.zmstudy.ui.view.tpie.interfaces.IPieChart;
import xdroid.mwee.com.zmstudy.ui.view.tpie.interfaces.IPieData;
import xdroid.mwee.com.zmstudy.ui.view.tpie.render.ChartRender;

/**
 * Author：created by SugarT
 * Time：2019/7/23 15
 */
public class PieChart extends Chart<IPieData> implements IPieChart {
    private IPieAxisData mPieAxisData = new PieAxisData();
    private ComputePie mComputePie;
    private RectF rectF;
    private RectF rectFOut;
    private RectF rectFIn;
    private float radius;
    private float radiusOut;
    private float radiusIn;
    private RectF offsetRectF;
    private RectF offsetRectFOut;
    private RectF offsetRectFIn;
    private float offsetRadius;
    private float offsetRadiusOut;
    private float offsetRadiusIn;
    private RectF[] mRectFs;
    private RectF[] mOffsetRectFs;
    private float animatedValue;
    private ChartAnimator mChartAnimator;
    private PieChartRender mPieChartRender;
    public boolean isAnimated;
    public boolean isTouch;
    private int angleId;

    public PieChart(Context context) {
        this(context,null);
    }

    public PieChart(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PieChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mComputePie = new ComputePie(this.mPieAxisData);
        this.rectF = new RectF();
        this.rectFOut = new RectF();
        this.rectFIn = new RectF();
        this.offsetRectF = new RectF();
        this.offsetRectFOut = new RectF();
        this.offsetRectFIn = new RectF();
        this.mRectFs = new RectF[3];
        this.mOffsetRectFs = new RectF[3];
        this.isAnimated = true;
        this.isTouch = true;
        this.angleId = -1;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.radius = (float)Math.min(this.mWidth, this.mHeight) * 0.4F;
        if (this.radius * 2.0F > (float)Math.min(this.mWidth, this.mHeight)) {
            this.radius = 0.0F;
        }

        this.mPieAxisData.setAxisLength(this.radius);
        this.rectF.left = -this.radius;
        this.rectF.top = -this.radius;
        this.rectF.right = this.radius;
        this.rectF.bottom = this.radius;
        this.mRectFs[0] = this.rectF;
        this.radiusOut = this.radius * this.mPieAxisData.getOutsideRadiusScale();
        this.rectFOut.left = -this.radiusOut;
        this.rectFOut.top = -this.radiusOut;
        this.rectFOut.right = this.radiusOut;
        this.rectFOut.bottom = this.radiusOut;
        this.mRectFs[1] = this.rectFOut;
        this.radiusIn = this.radius * this.mPieAxisData.getInsideRadiusScale();
        this.rectFIn.left = -this.radiusIn;
        this.rectFIn.top = -this.radiusIn;
        this.rectFIn.right = this.radiusIn;
        this.rectFIn.bottom = this.radiusIn;
        this.mRectFs[2] = this.rectFIn;


        this.offsetRadius = this.radius * this.mPieAxisData.getOffsetRadiusScale();
        this.offsetRectF.left = -this.offsetRadius;
        this.offsetRectF.top = -this.offsetRadius;
        this.offsetRectF.right = this.offsetRadius;
        this.offsetRectF.bottom = this.offsetRadius;
        this.mOffsetRectFs[0] = this.offsetRectF;
        this.offsetRadiusOut = this.offsetRadius * this.mPieAxisData.getOutsideRadiusScale();
        this.offsetRectFOut.left = -this.offsetRadiusOut;
        this.offsetRectFOut.top = -this.offsetRadiusOut;
        this.offsetRectFOut.right = this.offsetRadiusOut;
        this.offsetRectFOut.bottom = this.offsetRadiusOut;
        this.mOffsetRectFs[1] = this.offsetRectFOut;
        this.offsetRadiusIn = this.offsetRadius * this.mPieAxisData.getInsideRadiusScale();
        this.offsetRectFIn.left = -this.offsetRadiusIn;
        this.offsetRectFIn.top = -this.offsetRadiusIn;
        this.offsetRectFIn.right = this.offsetRadiusIn;
        this.offsetRectFIn.bottom = this.offsetRadiusIn;
        this.mOffsetRectFs[2] = this.offsetRectFIn;


        this.mPieAxisData.setRectFs(this.mRectFs);
        this.mPieAxisData.setOffsetRectFs(this.mOffsetRectFs);


        this.animated();
        this.chartRenderList.clear();

        for(int i = 0; i < this.mDataList.size(); ++i) {
            this.mPieChartRender = new PieChartRender(this.mPieAxisData, (IPieData)this.mDataList.get(i));
            this.chartRenderList.add(this.mPieChartRender);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate((float)(this.mViewWidth / 2), (float)(this.mViewHeight / 2));
        canvas.save();

        ///绘制圆弧
        canvas.rotate(this.mPieAxisData.getStartAngle());
        Iterator<ChartRender> var2 = this.chartRenderList.iterator();

        while(var2.hasNext()) {
            ChartRender  chartRender = var2.next();
            chartRender.drawGraph(canvas, this.animatedValue);
        }

        //绘制文字
        canvas.restore();
        var2 = this.chartRenderList.iterator();
        while(var2.hasNext()) {
            ChartRender chartRender = var2.next();
            PieChartRender pieChartRender = (PieChartRender)chartRender;
            pieChartRender.drawGraphText(canvas, this.animatedValue);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if (this.isTouch && this.mDataList.size() > 0) {
//            PieChartRender pieChartRender;
//            switch(event.getAction()) {
//                case 0:
//                    float x = event.getX() - (float)(this.mWidth / 2);
//                    float y = event.getY() - (float)(this.mHeight / 2);
//                    float touchAngle = 0.0F;
//                    if (x < 0.0F && y < 0.0F) {
//                        touchAngle += 180.0F;
//                    } else if (y < 0.0F && x > 0.0F) {
//                        touchAngle += 360.0F;
//                    } else if (y > 0.0F && x < 0.0F) {
//                        touchAngle += 180.0F;
//                    }
//
//                    touchAngle = (float)((double)touchAngle + Math.toDegrees(Math.atan((double)(y / x))));
//                    touchAngle -= this.mPieAxisData.getStartAngle();
//                    if (touchAngle < 0.0F) {
//                        touchAngle += 360.0F;
//                    }
//
//                    float touchRadius = (float)Math.sqrt((double)(y * y + x * x));
//                    if (this.radiusOut < touchRadius && touchRadius < this.radius) {
//                        this.angleId = -Arrays.binarySearch(this.mPieAxisData.getStartAngles(), touchAngle) - 1;
//                        pieChartRender = (PieChartRender)this.chartRenderList.get(this.angleId);
//                        pieChartRender.setTouchFlag(true);
//                        this.invalidate();
//                    }
//
//                    return true;
//                case 1:
//                    pieChartRender = (PieChartRender)this.chartRenderList.get(this.angleId);
//                    pieChartRender.setTouchFlag(false);
//                    this.invalidate();
//                    return true;
//            }
//        }

        return super.onTouchEvent(event);
    }

    protected void animated() {
        if (!this.isAnimated) {
            this.animatedValue = 360.0F;
        } else {
            this.mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    animatedValue = (Float)animation.getAnimatedValue();
                    PieChart.this.invalidate();
                }
            };
            this.mChartAnimator = new ChartAnimator(this.mAnimatorUpdateListener);
            this.mChartAnimator.animatedY(2000L, 360.0F);
        }

    }

    /**
     * 添加单个数据
     * @param chartData
     */
    @Override
    public void setData(IPieData chartData) {
        this.mDataList.clear();
        this.mDataList.add(chartData);
        this.computePie();
    }

    /**
     * 添加数组
     * @param chartDataList
     */
    @Override
    public void setDataList(ArrayList<IPieData> chartDataList) {
        this.mDataList = chartDataList;
        this.computePie();
    }

    @Override
    public int getCurrentWidth() {
        int wrapSize;
        if (this.mDataList != null && this.mDataList.size() > 1) {
            NumberFormat numberFormat = NumberFormat.getPercentInstance();
            numberFormat.setMinimumFractionDigits(this.mPieAxisData.getDecimalPlaces());
            this.paintText.setTextSize(this.mPieAxisData.getTextSize());
            this.paintText.setStrokeWidth(this.mPieAxisData.getPaintWidth());
            float percentWidth = this.paintText.measureText(numberFormat.format(10L));
            float nameWidth = this.paintText.measureText(this.mPieAxisData.getName());
            wrapSize = (int)(((double)(percentWidth * 4.0F) + (double)nameWidth * 1.1D) * (double)this.mPieAxisData.getOffsetRadiusScale());
        } else {
            wrapSize = 0;
        }

        return wrapSize;
    }

    @Override
    public int getCurrentHeight() {
        int wrapSize;
        if (this.mDataList != null && this.mDataList.size() > 1) {
            NumberFormat numberFormat = NumberFormat.getPercentInstance();
            numberFormat.setMinimumFractionDigits(this.mPieAxisData.getDecimalPlaces());
            this.paintText.setTextSize(this.mPieAxisData.getTextSize());
            this.paintText.setStrokeWidth(this.mPieAxisData.getPaintWidth());
            float percentWidth = this.paintText.measureText(numberFormat.format(10L));
            float nameWidth = this.paintText.measureText(this.mPieAxisData.getName());
            wrapSize = (int)(((double)(percentWidth * 4.0F) + (double)nameWidth * 1.1D) * (double)this.mPieAxisData.getOffsetRadiusScale());
        } else {
            wrapSize = 0;
        }

        return wrapSize;
    }

    @Override
    public void computePie() {
        this.mComputePie.computePie(this.mDataList);
    }

    @Override
    public void setAxisTextSize(float axisTextSize) {
        this.mPieAxisData.setTextSize(axisTextSize);
    }

    @Override
    public void setAxisColor(int axisColor) {
        this.mPieAxisData.setColor(axisColor);
    }

    @Override
    public void setAxisWidth(float axisWidth) {
        this.mPieAxisData.setPaintWidth(axisWidth);
    }

    @Override
    public void setInsideRadiusScale(float insideRadiusScale) {
        this.mPieAxisData.setInsideRadiusScale(insideRadiusScale);
    }

    @Override
    public void setOutsideRadiusScale(float outsideRadiusScale) {
        this.mPieAxisData.setOutsideRadiusScale(outsideRadiusScale);
    }

    @Override
    public void setOffsetRadiusScale(float offsetRadiusScale) {
        this.mPieAxisData.setOffsetRadiusScale(offsetRadiusScale);
    }

    @Override
    public void setStartAngle(float startAngle) {
        while(startAngle < 0.0F) {
            startAngle += 360.0F;
        }

        while(startAngle > 360.0F) {
            startAngle -= 360.0F;
        }

        this.mPieAxisData.setStartAngle(startAngle);
    }

    @Override
    public void setMinAngle(float minAngle) {
        this.mPieAxisData.setMinAngle(minAngle);
    }

    @Override
    public void setDecimalPlaces(int decimalPlaces) {
        this.mPieAxisData.setDecimalPlaces(decimalPlaces);
    }
}
