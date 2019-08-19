package xdroid.mwee.com.zmstudy.ui.fragment;

import android.view.View;
import android.widget.SeekBar;

import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.view.BenefitProgressView;

/**
 * 线圈比例
 */
public class BenefitProgressFragment extends BaseFragment {


    public static BenefitProgressFragment newInstance() {
        return new BenefitProgressFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_benefit_view;
    }

    @Override
    public void initView(View v) {

        BenefitProgressView mBenefitProgressView = v.findViewById(R.id.mBenefitProgressView);

        ((SeekBar) v.findViewById(R.id.mSeekBar)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBenefitProgressView.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
