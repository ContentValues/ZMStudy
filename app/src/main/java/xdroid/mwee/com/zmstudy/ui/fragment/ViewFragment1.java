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
 * Created by zhangmin on 2018/10/16.
 */

public class ViewFragment1 extends BaseFragment {


    private TextView mAnimatorViewTv;

    public static ViewFragment1 newInstance() {
        return new ViewFragment1();
    }


    @Override
    public void initView(View v) {

        mAnimatorViewTv = v.findViewById(R.id.mAnimatorViewTv);

        mAnimatorViewTv.setText("ViewFragment1 回退");


        v.findViewById(R.id.mTimerImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                ViewFragment2 viewFragment2 = ViewFragment2.newInstance();
                fragmentTransaction.replace(android.R.id.content, viewFragment2);

                fragmentTransaction.addToBackStack("ViewFragment2");
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
                //getChildFragmentManager().popBackStack();
                getFragmentManager().popBackStack();
            }
        });


    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_animatorview_layout;
    }
}
