package xdroid.mwee.com.zmstudy.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.jakewharton.rxbinding.view.RxView;
import com.mwee.android.sqlite.META;
import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.ClientMetaUtil;
import com.mwee.android.tools.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import xdroid.mwee.com.mwcommon.base.BaseActivity;
import xdroid.mwee.com.mwcommon.callback.ResultCallback;
import xdroid.mwee.com.mwcommon.view.XStateController;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.business.cache.AppCache;
import xdroid.mwee.com.zmstudy.model.GetDataResponse;
import xdroid.mwee.com.mwbase.NetError;
import xdroid.mwee.com.mwbase.retrofit.ApiSubcriber;
import xdroid.mwee.com.zmstudy.net.service.HttpService;
import xdroid.mwee.com.mwbase.retrofit.XRetrofit;
import xdroid.mwee.com.zmstudy.business.processor.NetDataProcessor;
import xdroid.mwee.com.mwbase.RXUtils;

import com.mwee.android.tools.router.Router;

/**
 * Created by zhangmin on 2018/5/10.
 */

public class WelcomeActitvity extends BaseActivity {

    private XStateController xStateController;
    private ImageView iv_icon;

    @Override
    public void initView() {
        iv_icon = findViewById(R.id.iv_icon);
        xStateController = findViewById(R.id.contentLayout);
        xStateController.showContent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        RxView.clicks(iv_icon).throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Router.newIntent(WelcomeActitvity.this).to(MainActivity.class).launch();
//                        String token = ClientMetaUtil.getSettingsValueByKey(META.TOKEN);
//                        String seed = ClientMetaUtil.getSettingsValueByKey(META.SEED);
//                        String shopid = ClientMetaUtil.getSettingsValueByKey(META.SHOPID);
//                        boolean isLogin = !TextUtils.isEmpty(token) && !TextUtils.isEmpty(seed) && !TextUtils.isEmpty(shopid);
//                        if (isLogin) {
//                            jumptoMain();
//                        } else {
//                            loadActive();
//                        }

                        //loadActive222();
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.cashier_activity_welcome;
    }


    private void loadActive() {
        xStateController.showLoading();
        NetDataProcessor.getInstance().loadActive(APPConfig.SHOP_ID, new ResultCallback<GetDataResponse>() {
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


    private void loadActive222() {
//        xStateController.showLoading();
//        NetDataProcessor.getInstance().loadActive("202385", new ResultCallback<GetDataResponse>() {
//            @Override
//            public void onSuccess(GetDataResponse data) {
//                xStateController.showContent();
//                jumptoMain();
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//                super.onFailure(code, msg);
//                xStateController.showError();
//            }
//        });


        String tag = "2018-11-14 14:00:40";
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), tag);
        XRetrofit.create(HttpService.class).downLoad(requestBody)
                .compose(RXUtils.getScheduler())
                .compose(RXUtils.getApiTransformer())
                .subscribe(new ApiSubcriber<GetDataResponse>() {
                    @Override
                    protected void onFail(NetError error) {
                        LogUtil.log("error-->" + JSON.toJSONString(error));
                    }

                    @Override
                    public void onNext(GetDataResponse response) {
                        LogUtil.log("onNext-->" + JSON.toJSONString(response));
                    }
                });


        /*HashMap<String, String> params = new HashMap();
        params.put("deviceId", "TM18184V00014");
        params.put("shopType", "1");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(params));

        XApi.getInstance().getRetrofit(HttpService.MWEE_BASE_URL, false)
                .create(HttpService.class)
                .bindShop(requestBody)
                .flatMap(new Func1<BindResponse, Observable<GetDataResponse>>() {
                    @Override
                    public Observable<GetDataResponse> call(BindResponse response) {
                        String token = EncryptUtil.MD5Purity(response.data.fstoken + "TM18184V00014");
                        AppConfiguration.saveTokenSeed(APPConfig.SHOP_ID, token, response.data.fsseed);
                        //String value = "-1";
                        String value = "2018-11-08 17:22:01";
                        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), value);
                        return XApi.getInstance()
                                .getRetrofit(HttpService.MWEE_BASE_URL, true)
                                .create(HttpService.class)
                                .downLoad(requestBody);
                    }
                }).compose(RXUtils.getScheduler())
                .subscribe(new Observer<GetDataResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.log("测试2 onError", e.getMessage());
                    }

                    @Override
                    public void onNext(GetDataResponse response) {

                        LogUtil.log("测试2 onNext", JSON.toJSONString(response));

                    }
                });*/


    }


    private void jumptoMain() {
        xStateController.showLoading();
        Observable.create(new Observable.OnSubscribe<Object>() {

            @Override
            public void call(Subscriber<? super Object> subscriber) {
                AppCache.getInstance().refresh();
                subscriber.onNext(new Object());
            }
        }).compose(RXUtils.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        xStateController.showContent();
                        Router.newIntent(WelcomeActitvity.this).to(MainActivity.class).launch();
                    }
                });
    }
}
