package xdroid.mwee.com.zmstudy.business.processor;

import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.tools.encrypt.EncryptUtil;

import okhttp3.Call;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import xdroid.mwee.com.mwcommon.callback.ResultCallback;
import xdroid.mwee.com.zmstudy.business.db.AppConfiguration;
import xdroid.mwee.com.zmstudy.model.BindResponse;
import xdroid.mwee.com.zmstudy.model.GetDataResponse;
import xdroid.mwee.com.mwbase.okhttp.callback.EncryptJsonCallBack;
import xdroid.mwee.com.mwbase.okhttp.callback.JsonCallback;
import xdroid.mwee.com.zmstudy.net.NetApi;

/**
 * Created by zhangmin on 2018/6/15.
 */

public class NetDataProcessor implements INetInterface {


    private volatile static NetDataProcessor processor;

    //构造方法私有
    private NetDataProcessor() {
    }

    //获取单例
    public static NetDataProcessor getInstance() {
        if (processor == null) {
            synchronized (NetDataProcessor.class) {
                if (processor == null) {
                    processor = new NetDataProcessor();
                }
            }
        }
        return processor;
    }


    @Override
    public void loadActive(String shopid, ResultCallback<GetDataResponse> callback) {

        NetApi.bindShop(shopid, new JsonCallback<BindResponse>() {
            @Override
            public void onFail(Call call, String erroMsg, int id) {

                callback.onFailure(-1, erroMsg);
            }

            @Override
            public void onResponse(BindResponse response, int id) {

                String token = EncryptUtil.MD5Purity(response.data.fstoken + "TM18184V00014");
                AppConfiguration.saveTokenSeed(APPConfig.SHOP_ID, token, response.data.fsseed);

                NetApi.downLoad(new EncryptJsonCallBack<GetDataResponse>() {
                    @Override
                    public void onFail(Call call, String erroMsg, int id) {
                        callback.onFailure(-1, erroMsg);
                    }

                    @Override
                    public void onResponse(GetDataResponse response, int id) {

                        Observable.create(new Observable.OnSubscribe<Object>() {

                            @Override
                            public void call(Subscriber<? super Object> subscriber) {

                                //WriteJsonDataToDB.writeDataToDB(APPConfig.DB_MAIN, JSON.parseObject(response.data), response.tag);

                                subscriber.onNext(new Object());
                            }
                        }).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Action1<Object>() {
                                    @Override
                                    public void call(Object o) {
                                        callback.onSuccess(response);
                                    }
                                });
                    }
                });
            }
        });
    }


}
