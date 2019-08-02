package xdroid.mwee.com.zmstudy.ui.view.tpie.data;


import xdroid.mwee.com.zmstudy.ui.view.tpie.interfaces.IChartData;

/**
 * Author：created by SugarT
 * Time：2019/7/23 15
 */
public class ChartData extends BaseData implements IChartData {
    protected String name = "";

    public ChartData() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
