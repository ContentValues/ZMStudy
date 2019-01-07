package xdroid.mwee.com.zmstudy.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.zmstudy.R;

/**
 * Created by zhangmin on 2018/11/19.
 */

public class ViewFragment3 extends BaseFragment {


    private TextView mAnimatorViewTv;

    public static ViewFragment3 newInstance() {
        return new ViewFragment3();
    }


    @Override
    public void initView(View v) {

        mAnimatorViewTv = v.findViewById(R.id.mAnimatorViewTv);

        mAnimatorViewTv.setText("ViewFragment3 回退");



    }

    @Override
    public void initData() {
        super.initData();

        RxView.clicks(mAnimatorViewTv).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                getChildFragmentManager().popBackStack();
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_animatorview_layout;
    }
}
