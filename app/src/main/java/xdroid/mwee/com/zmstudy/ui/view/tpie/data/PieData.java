package xdroid.mwee.com.zmstudy.ui.view.tpie.data;


import xdroid.mwee.com.zmstudy.ui.view.tpie.interfaces.IPieData;

/**
 * Author：created by SugarT
 * Time：2019/7/23 15
 */
public class PieData extends ChartData implements IPieData {
    /**
     * 占据的值 例如有四个圆弧 1 2 3 4
     *   float sumValue += pie.getValue() = 1+2+3+4
     *   那么 百分比 float percentage = pie.getValue() / sumValue;
     */
    private float value;
    /**
     * 百分比
     */
    private float percentage;
    /**
     * 需要绘制圆弧的角度
     */
    private float angle = 0.0F;
    /**
     * 起始点的圆弧角度
     */
    private float currentAngle;

    public PieData() {
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return this.value;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public float getPercentage() {
        return this.percentage;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAngle() {
        return this.angle;
    }

    public float getCurrentAngle() {
        return this.currentAngle;
    }

    public void setCurrentAngle(float currentAngle) {
        this.currentAngle = currentAngle;
    }
}