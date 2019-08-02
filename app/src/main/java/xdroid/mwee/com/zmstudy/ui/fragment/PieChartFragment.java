package xdroid.mwee.com.zmstudy.ui.fragment;

import android.graphics.Color;
import android.view.View;

import com.mwee.android.tools.DM;

import java.util.ArrayList;

import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.view.tpie.interfaces.IPieData;
import xdroid.mwee.com.zmstudy.ui.view.tpie.chart.PieChart;
import xdroid.mwee.com.zmstudy.ui.view.tpie.data.PieData;

public class PieChartFragment extends BaseFragment {

    protected int[] mColors = { 0xFF6495ED,
            0xFFE6B800, 0xFFE32636, 0xFF800000, 0xFF7CFC00, 0xFF808000, 0xFFFF8C69, 0xFF808080,0xFFCCFF00};


    public static PieChartFragment newInstance() {
        return new PieChartFragment();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_piechart;
    }


    @Override
    public void initView(View v) {
        ArrayList<IPieData> mPieDataList = new ArrayList<>();

        PieChart pieChart = v.findViewById(R.id.pieChart);
        for (int i = 0; i < 4; i++) {
            PieData pieData = new PieData();
            pieData.setName("区域" + i);
            pieData.setValue((float) i + 1);
            pieData.setColor(mColors[i]);
            mPieDataList.add(pieData);
        }
        pieChart.setDataList(mPieDataList);
        pieChart.setAxisColor(Color.WHITE);
        pieChart.setAxisTextSize(DM.dpToPx(15));

    }

    @Override
    public void initData() {
        super.initData();

    }
}
