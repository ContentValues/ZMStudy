package xdroid.mwee.com.zmstudy.ui.fragment;

import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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

public class ViewFragment2 extends BaseFragment {


    private TextView mAnimatorViewTv;

    public static ViewFragment2 newInstance() {
        return new ViewFragment2();
    }


    @Override
    public void initView(View v) {


        mAnimatorViewTv = v.findViewById(R.id.mAnimatorViewTv);

        mAnimatorViewTv.setText("ViewFragment2 回退");

        v.findViewById(R.id.mTimerImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                ViewFragment3 viewFragment3 = ViewFragment3.newInstance();
                fragmentTransaction.replace(android.R.id.content, viewFragment3);

                fragmentTransaction.addToBackStack("ViewFragment3");
                fragmentTransaction.commit();
            }
        });

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
