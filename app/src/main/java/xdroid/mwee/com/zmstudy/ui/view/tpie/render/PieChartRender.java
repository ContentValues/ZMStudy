package xdroid.mwee.com.zmstudy.ui.view.tpie.render;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

import java.text.NumberFormat;

import xdroid.mwee.com.zmstudy.ui.view.tpie.interfaces.TouchListener;
import xdroid.mwee.com.zmstudy.ui.view.tpie.interfaces.IPieAxisData;
import xdroid.mwee.com.zmstudy.ui.view.tpie.interfaces.IPieData;

/**
 * Author：created by SugarT
 * Time：2019/7/23 15
 */
public class PieChartRender extends ChartRender implements TouchListener {
    private IPieAxisData pieAxisData;
    private IPieData pieData;
    private Paint mPaint = new Paint();
    private Paint paintText = new Paint();
    private Path outPath = new Path();
    private Path midPath = new Path();
    private Path inPath = new Path();
    private Path outMidPath = new Path();
    private Path midInPath = new Path();
    private float drawAngle;
    private NumberFormat numberFormat;
    private PointF mPointF = new PointF();
    private boolean touchFlag = false;

    public PieChartRender(IPieAxisData pieAxisData, IPieData pieData) {
        this.pieAxisData = pieAxisData;
        this.pieData = pieData;
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.paintText.setAntiAlias(true);
        this.paintText.setDither(true);
        this.paintText.setColor(pieAxisData.getColor());
        this.paintText.setTextSize(pieAxisData.getTextSize());
        this.paintText.setTextAlign(Paint.Align.CENTER);
        this.paintText.setStrokeWidth(pieAxisData.getPaintWidth());
        this.numberFormat = NumberFormat.getPercentInstance();
        this.numberFormat.setMinimumFractionDigits(pieAxisData.getDecimalPlaces());
    }

    public void drawGraph(Canvas canvas, float animatedValue) {
        if (Math.min(this.pieData.getAngle() - 1.0F, animatedValue - this.pieData.getCurrentAngle()) >= 0.0F) {
            //this.drawAngle = Math.min(this.pieData.getAngle() - 1.0F, animatedValue - this.pieData.getCurrentAngle());
            this.drawAngle = Math.min(this.pieData.getAngle(), animatedValue - this.pieData.getCurrentAngle());
        } else {
            this.drawAngle = 0.0F;
        }

        if (this.touchFlag) {///触摸的时候 进行重绘 等比例放大绘制的大小
            this.drawArc(canvas, this.pieData.getCurrentAngle(), this.drawAngle, this.pieData, this.pieAxisData.getOffsetRectFs()[0], this.pieAxisData.getOffsetRectFs()[1], this.pieAxisData.getOffsetRectFs()[2], this.mPaint);
        } else {
            this.drawArc(canvas, this.pieData.getCurrentAngle(), this.drawAngle, this.pieData, this.pieAxisData.getRectFs()[0], this.pieAxisData.getRectFs()[1], this.pieAxisData.getRectFs()[2], this.mPaint);
        }

    }

    public void drawGraphText(Canvas canvas, float animatedValue) {
        if (this.pieAxisData.getIsTextSize() && animatedValue > this.pieData.getCurrentAngle() + this.pieData.getAngle() / 2.0F) {
            if (this.touchFlag) {
                this.drawText(canvas, this.pieData, this.pieData.getCurrentAngle() + this.pieAxisData.getStartAngle(), this.numberFormat, true);
            } else if (this.pieData.getAngle() > this.pieAxisData.getMinAngle()) {
                this.drawText(canvas, this.pieData, this.pieData.getCurrentAngle() + this.pieAxisData.getStartAngle(), this.numberFormat, false);
            }
        }

    }

    private void drawArc(Canvas canvas, float currentStartAngle, float drawAngle, IPieData pie, RectF outRectF, RectF midRectF, RectF inRectF, Paint paint) {
        this.outPath.moveTo(0.0F, 0.0F);
        this.outPath.arcTo(outRectF, currentStartAngle, drawAngle);
        this.midPath.moveTo(0.0F, 0.0F);
        this.midPath.arcTo(midRectF, currentStartAngle, drawAngle);
        this.inPath.moveTo(0.0F, 0.0F);
        this.inPath.arcTo(inRectF, currentStartAngle, drawAngle);
        this.outMidPath.op(this.outPath, this.midPath, Path.Op.DIFFERENCE);
        this.midInPath.op(this.midPath, this.inPath, Path.Op.DIFFERENCE);
        paint.setColor(pie.getColor());
        canvas.drawPath(this.outMidPath, paint);
        paint.setAlpha(128);
        canvas.drawPath(this.midInPath, paint);
        this.outPath.reset();
        this.midPath.reset();
        this.inPath.reset();
        this.outMidPath.reset();
        this.midInPath.reset();
    }

    private void drawText(Canvas canvas, IPieData pie, float currentStartAngle, NumberFormat numberFormat, boolean flag) {
        int textPathX = (int)(Math.cos(Math.toRadians((double)(currentStartAngle + pie.getAngle() / 2.0F))) * (double)this.pieAxisData.getAxisLength() * (double)(1.0F + this.pieAxisData.getOutsideRadiusScale()) / 2.0D);
        int textPathY = (int)(Math.sin(Math.toRadians((double)(currentStartAngle + pie.getAngle() / 2.0F))) * (double)this.pieAxisData.getAxisLength() * (double)(1.0F + this.pieAxisData.getOutsideRadiusScale()) / 2.0D);
        this.mPointF.x = (float)textPathX;
        this.mPointF.y = (float)textPathY;
        String[] strings;
        if (flag) {
            strings = new String[]{pie.getName() + "", numberFormat.format((double)pie.getPercentage()) + ""};
        } else {
            strings = new String[]{numberFormat.format((double)pie.getPercentage()) + ""};
        }

        this.textCenter(strings, this.paintText, canvas, this.mPointF, Paint.Align.CENTER);
    }

    public void setTouchFlag(boolean touchFlag) {
        this.touchFlag = touchFlag;
    }
}
