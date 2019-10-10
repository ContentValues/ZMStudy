package com.sugart.composition.welcome;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import xdroid.mwee.com.mwcommon.base.BaseActivity;
import xdroid.mwee.com.mwcommon.view.XStateController;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.activity.MainActivity;

import com.mwee.android.tools.router.Router;

/**
 * Created by zhangmin on 2018/5/10.
 */

public class WelcomeActitvity extends BaseActivity implements WelcomeContract.View {

    private XStateController xStateController;
    private ImageView iv_icon;

    private WelcomPresenter welcomPresenter;

    @Override
    public void initView() {
        iv_icon = findViewById(R.id.iv_icon);
        xStateController = findViewById(R.id.contentLayout);

        welcomPresenter = new WelcomPresenter();
        welcomPresenter.attachView(this);

        //loadingView和contentView并存默认先开启loadingView
        xStateController.showContent();


    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        RxView.clicks(iv_icon)
                .throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
//                        Router.newIntent(WelcomeActitvity.this).to(MainActivity.class).launch();
                        welcomPresenter.doWelcome("福利", 10, 1);
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.cashier_activity_welcome;
    }


    private final static String TAG = WelcomeActitvity.class.getSimpleName();

    @Override
    public void doMakeLoving(String data) {

        Log.d(TAG, "doMakeLoving  " + data);
    }

    @Override
    public void showLoading() {
        Log.d(TAG, "showLoading  ");
        xStateController.showLoading();
    }

    @Override
    public void hideLoading() {
        Log.d(TAG, "hideLoading  ");
        xStateController.showContent();
    }

    @Override
    public void onError(String message) {
        Log.d(TAG, "doMakeLoving  " + message);
    }


}
