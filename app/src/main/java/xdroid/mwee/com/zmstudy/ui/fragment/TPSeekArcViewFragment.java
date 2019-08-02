package xdroid.mwee.com.zmstudy.ui.fragment;

import android.view.View;

import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.zmstudy.R;

/**
 * 线圈比例
 */
public class TPSeekArcViewFragment extends BaseFragment {


    public static TPSeekArcViewFragment newInstance() {
        return new TPSeekArcViewFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_seekarc_view;
    }

    @Override
    public void initView(View v) {


        SeekArc mSeekArc = (SeekArc) v.findViewById(R.id.seekArc);

//        mSeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener()
//        {
//
//            @Override
//            public void onStopTrackingTouch(SeekArc seekArc)
//            {
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekArc seekArc)
//            {
//            }
//
//            @Override
//            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser)
//            {
//                //mSeekArcProgress.setText(String.valueOf(progress));
//            }
//        });

    }
}
