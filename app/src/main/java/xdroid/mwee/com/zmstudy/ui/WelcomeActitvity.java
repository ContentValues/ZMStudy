package xdroid.mwee.com.zmstudy.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.mwee.android.sqlite.META;
import com.mwee.android.sqlite.base.ClientMetaUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import xdroid.mwee.com.mwcommon.base.XActivity;
import xdroid.mwee.com.mwcommon.callback.ResultCallback;
import xdroid.mwee.com.mwcommon.view.XStateController;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.cache.AppCache;
import xdroid.mwee.com.zmstudy.model.GetDataResponse;
import xdroid.mwee.com.zmstudy.processor.NetDataProcessor;
import xdroid.mwee.com.zmstudy.router.Router;

/**
 * Created by zhangmin on 2018/5/10.
 */

public class WelcomeActitvity extends XActivity {

    private XStateController xStateController;

    @Override
    public void initData(Bundle savedInstanceState) {
        xStateController = findViewById(R.id.contentLayout);
        xStateController.showContent();

        findViewById(R.id.iv_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = ClientMetaUtil.getSettingsValueByKey(META.TOKEN);
                String seed = ClientMetaUtil.getSettingsValueByKey(META.SEED);
                String shopid = ClientMetaUtil.getSettingsValueByKey(META.SHOPID);
                boolean isLogin = !TextUtils.isEmpty(token) && !TextUtils.isEmpty(seed) && !TextUtils.isEmpty(shopid);
                if (isLogin) {
                    jumptoMain();
                } else {
                    loadActive();
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.cashier_activity_welcome;
    }


    private void loadActive() {
        xStateController.showLoading();
        NetDataProcessor.getInstance().loadActive("202385", new ResultCallback<GetDataResponse>() {
            @Override
            public void onSuccess(GetDataResponse data) {
                xStateController.showContent();
                jumptoMain();
            }

            @Override
            public void onFailure(int code, String msg) {
                super.onFailure(code, msg);
                xStateController.showError();
            }
        });
    }


    private void jumptoMain() {
        xStateController.showLoading();
        Observable.create(new Observable.OnSubscribe<Object>() {

            @Override
            public void call(Subscriber<? super Object> subscriber) {

                AppCache.getInstance().refresh();

                subscriber.onNext(new Object());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        xStateController.showContent();
                        Router.newIntent(WelcomeActitvity.this).to(MainActivity.class).launch();
                    }
                });

    }


}
