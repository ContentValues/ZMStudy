package xdroid.mwee.com.zmstudy.ui.fragment;

import android.view.View;
import android.widget.Button;
import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.view.tpie.CountDownView;
/**
 * 线圈比例
 */
public class PieRatioFragment extends BaseFragment {

    public static PieRatioFragment newInstance() {
        return new PieRatioFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pieratio;
    }

    @Override
    public void initView(View v) {
        CountDownView countDownView = v.findViewById(R.id.countDownView);
        Button btnStart = v.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownView.start();
            }
        });

    }

    @Override
    public void initData() {
        super.initData();

    }
}
