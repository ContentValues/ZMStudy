package xdroid.mwee.com.zmstudy.ui.view.tpie.compute;


import java.util.ArrayList;

import xdroid.mwee.com.zmstudy.ui.view.tpie.interfaces.IPieAxisData;
import xdroid.mwee.com.zmstudy.ui.view.tpie.interfaces.IPieData;

/**
 * Author：created by SugarT
 * Time：2019/7/23 15
 */
public class ComputePie {
    private IPieAxisData pieAxisData;
    private float[] startAngles;

    public ComputePie(IPieAxisData pieAxisData) {
        this.pieAxisData = pieAxisData;
    }

    /**
     * 数据源包装
     * @param pieDataList
     */
    public void computePie(ArrayList<IPieData> pieDataList) {
        if (pieDataList != null && pieDataList.size() != 0) {
            this.startAngles = new float[pieDataList.size()];
            float sumValue = 0.0F;

            for(int i = 0; i < pieDataList.size(); ++i) {
                IPieData pie = (IPieData)pieDataList.get(i);
                sumValue += pie.getValue();
            }

            float sumAngle = 0.0F;

            for(int i = 0; i < pieDataList.size(); ++i) {
                IPieData pie = (IPieData)pieDataList.get(i);
                float percentage = pie.getValue() / sumValue;
                float angle = percentage * 360.0F;
                pie.setPercentage(percentage);
                pie.setAngle(angle);
                pie.setCurrentAngle(sumAngle);
                sumAngle += angle;
                this.startAngles[i] = sumAngle;
            }

            this.pieAxisData.setStartAngles(this.startAngles);
        }
    }
}