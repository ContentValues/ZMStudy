package xdroid.mwee.com.zmstudy.ui.fragment;

import android.graphics.Color;
import android.view.View;

import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.view.wave.RatioWaveView;
import xdroid.mwee.com.zmstudy.ui.view.wave.TPAssortWaveView;
import xdroid.mwee.com.zmstudy.ui.view.wave.TPRatioWaveView;

/**
 * 线圈比例
 */
public class RatioWaveViewFragment extends BaseFragment {

    //private TPRatioWaveView mRatioWave;
    private RatioWaveView mRatioWave;

    public static RatioWaveViewFragment newInstance() {
        return new RatioWaveViewFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ratio_wave;
    }

    @Override
    public void initView(View v) {
        mRatioWave = v.findViewById(R.id.mRatioWave);
//        mRatioWave.setWaveColor(
//                Color.rgb(198, 222, 246),
//                Color.rgb(135, 184, 234),
//                Color.rgb(19, 101, 209)
//        );
//        mRatioWave.setInBorder(50, Color.rgb(252, 254, 255), Color.rgb(135, 184, 234));
//        mRatioWave.setSwipBorder(20, Color.rgb(198, 222, 246), Color.BLUE);
        mRatioWave.start();
    }
}
