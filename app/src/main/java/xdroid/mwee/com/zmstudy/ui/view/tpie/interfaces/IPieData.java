package xdroid.mwee.com.zmstudy.ui.view.tpie.interfaces;


/**
 * Author：created by SugarT
 * Time：2019/7/23 15
 */
public interface IPieData extends IChartData {
    void setValue(float var1);

    float getValue();

    void setPercentage(float var1);

    float getPercentage();

    void setAngle(float var1);

    float getAngle();

    float getCurrentAngle();

    void setCurrentAngle(float var1);
}
