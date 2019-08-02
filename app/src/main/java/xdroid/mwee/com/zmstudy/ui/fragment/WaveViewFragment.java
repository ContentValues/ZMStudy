package xdroid.mwee.com.zmstudy.ui.fragment;

import android.graphics.Color;
import android.view.View;


import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.view.wave.TPAssortWaveView;

/**
 * 线圈比例
 */
public class WaveViewFragment extends BaseFragment {

    private TPAssortWaveView TPAssortWaveView;

    public static WaveViewFragment newInstance() {
        return new WaveViewFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wave;
    }

    @Override
    public void initView(View v) {
        TPAssortWaveView = v.findViewById(R.id.wave);
        TPAssortWaveView.setWaveColor(
                Color.rgb(198, 222, 246),
                Color.rgb(135, 184, 234),
                Color.rgb(19, 101, 209)
                );
        TPAssortWaveView.setInBorder(30,Color.rgb(252, 254, 255),Color.rgb(135, 184, 234));
        TPAssortWaveView.setOutBorder(20,Color.rgb(68, 222, 211));
        TPAssortWaveView.setSwipBorder(20,Color.rgb(19,98,208));

        TPAssortWaveView.start();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                waveView.start();
//            }
//        }, 1000);
    }




}
