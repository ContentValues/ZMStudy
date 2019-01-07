package xdroid.mwee.com.mwbase;

import com.mwee.android.tools.base.BaseResponse;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import xdroid.mwee.com.mwbase.NetError;

/**
 * Created by zhangmin on 2018/11/24.
 */

public class RXUtils {

    /**
     * 线程切换
     *
     * @return
     */
    public static <T> Observable.Transformer<T, T> getScheduler() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**
     * 统一网络请求 一个App中返回数据类型应该一致
     * 异常处理变换
     *
     * @return
     */
    public static <T extends BaseResponse> Observable.Transformer<T, T> getApiTransformer() {
        Observable.Transformer<T, T> transformer = new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {

                return observable.flatMap(new Func1<T, Observable<T>>() {
                    @Override
                    public Observable<T> call(T model) {
                        if (model == null) {
                            return Observable.error(new NetError(NetError.NoDataError, model.errmsg));
                        } else if (model.errno != 0) {
                            return Observable.error(new NetError(model.errno, model.errmsg));
                        } /*else if (model.isBizError()) {
                            return Observable.error(new NetError(model.getErrorMsg(), NetError.BusinessError));
                        } */ else {
                            return Observable.just(model);
                        }
                    }

                });
            }
        };
        return transformer;
    }


}
