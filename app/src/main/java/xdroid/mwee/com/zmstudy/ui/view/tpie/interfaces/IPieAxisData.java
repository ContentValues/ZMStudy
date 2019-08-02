package xdroid.mwee.com.zmstudy.ui.view.tpie.interfaces;

import android.graphics.RectF;


/**
 * Author：created by SugarT
 * Time：2019/7/23 15
 */
public interface IPieAxisData extends IChartData {
    float getInsideRadiusScale();

    void setInsideRadiusScale(float var1);

    float getOutsideRadiusScale();

    void setOutsideRadiusScale(float var1);

    float getOffsetRadiusScale();

    void setOffsetRadiusScale(float var1);

    float getStartAngle();

    void setStartAngle(float var1);

    float getMinAngle();

    void setMinAngle(float var1);

    int getDecimalPlaces();

    void setDecimalPlaces(int var1);

    float[] getStartAngles();

    void setStartAngles(float[] var1);

    RectF[] getRectFs();

    void setRectFs(RectF[] var1);

    RectF[] getOffsetRectFs();

    void setOffsetRectFs(RectF[] var1);

    float getAxisLength();

    void setAxisLength(float var1);
}
