package xdroid.mwee.com.mwbase.retrofit;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import org.json.JSONException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Subscriber;
import xdroid.mwee.com.mwbase.NetError;

/**
 * Created by zhangmin on 2018/10/26.
 *
 * 1.实现缓存机制
 * 2.实现重试机制
 *
 */

public abstract class ApiSubcriber<T> extends Subscriber<T> {


    @Override
    public void onError(Throwable e) {
        NetError error = null;
        if (e instanceof NetError) {
            error = (NetError) e;
        } else {

            if (e instanceof UnknownHostException) {
                error = new NetError(e, NetError.NoConnectError, "网络无连接");
            } else if (e instanceof JSONException
                    || e instanceof JsonParseException
                    || e instanceof JsonSyntaxException) {
                error = new NetError(e, NetError.ParseError, "数据解析异常");
            } else if (e instanceof SocketTimeoutException
                    || e instanceof ConnectException) {
                error = new NetError(e, NetError.NoConnectError, "网络中断,请检查您的网络状态");
            } else {
                error = new NetError(e, NetError.OtherError, "其他异常");
            }
        }
           /* if (useCommonErrorHandler()
                    && XApi.getCommonProvider() != null) {
                if (XApi.getCommonProvider().handleError(error)) {        //使用通用异常处理
                    return;
                }
            }*/
        onFail(error);

    }

    protected abstract void onFail(NetError error);

    @Override
    public void onCompleted() {

    }

}
