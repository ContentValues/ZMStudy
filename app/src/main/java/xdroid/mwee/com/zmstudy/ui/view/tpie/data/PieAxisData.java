package xdroid.mwee.com.zmstudy.ui.view.tpie.data;
import android.graphics.RectF;
import java.util.Arrays;
import xdroid.mwee.com.zmstudy.ui.view.tpie.interfaces.IPieAxisData;

/**
 * Author：created by SugarT
 * Time：2019/7/23 15
 */
public class PieAxisData extends ChartData implements IPieAxisData {
    private float insideRadiusScale = 0.5F;
    private float outsideRadiusScale = 0.6F;
    private float offsetRadiusScale = 1.1F;
    private float startAngle = 0.0F;
    private float minAngle = 30.0F;
    private int decimalPlaces = 0;
    private float[] startAngles;
    private RectF[] rectFs;
    private RectF[] offsetRectFs;
    private float axisLength;

    public PieAxisData() {
    }

    @Override
    public float getInsideRadiusScale() {
        return this.insideRadiusScale;
    }

    @Override
    public void setInsideRadiusScale(float insideRadiusScale) {
        this.insideRadiusScale = insideRadiusScale;
    }

    @Override
    public float getOutsideRadiusScale() {
        return this.outsideRadiusScale;
    }

    @Override
    public void setOutsideRadiusScale(float outsideRadiusScale) {
        this.outsideRadiusScale = outsideRadiusScale;
    }

    @Override
    public float getOffsetRadiusScale() {
        return this.offsetRadiusScale;
    }

    @Override
    public void setOffsetRadiusScale(float offsetRadiusScale) {
        this.offsetRadiusScale = offsetRadiusScale;
    }

    @Override
    public float getStartAngle() {
        return this.startAngle;
    }

    @Override
    public void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
    }

    @Override
    public float getMinAngle() {
        return this.minAngle;
    }

    @Override
    public void setMinAngle(float minAngle) {
        this.minAngle = minAngle;
    }

    @Override
    public int getDecimalPlaces() {
        return this.decimalPlaces;
    }

    @Override
    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    @Override
    public float[] getStartAngles() {
        return this.startAngles;
    }

    @Override
    public void setStartAngles(float[] startAngles) {
        this.startAngles = startAngles;
    }

    @Override
    public RectF[] getRectFs() {
        return this.rectFs;
    }

    @Override
    public void setRectFs(RectF[] rectFs) {
        this.rectFs = rectFs;
    }

    @Override
    public RectF[] getOffsetRectFs() {
        return this.offsetRectFs;
    }

    @Override
    public void setOffsetRectFs(RectF[] offsetRectFs) {
        this.offsetRectFs = offsetRectFs;
    }

    @Override
    public float getAxisLength() {
        return this.axisLength;
    }

    @Override
    public void setAxisLength(float axisLength) {
        this.axisLength = axisLength;
    }

    @Override
    public String toString() {
        return "PieAxisData{insideRadiusScale=" + this.insideRadiusScale + ", outsideRadiusScale=" + this.outsideRadiusScale + ", offsetRadiusScale=" + this.offsetRadiusScale + ", startAngle=" + this.startAngle + ", minAngle=" + this.minAngle + ", decimalPlaces=" + this.decimalPlaces + ", startAngles=" + Arrays.toString(this.startAngles) + ", rectFs=" + Arrays.toString(this.rectFs) + ", offsetRectFs=" + Arrays.toString(this.offsetRectFs) + ", axisLength=" + this.axisLength + '}';
    }
}
