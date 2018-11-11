package xdroid.mwee.com.zmstudy.net.base;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import org.json.JSONException;
import java.net.UnknownHostException;
import rx.Subscriber;
import xdroid.mwee.com.zmstudy.net.NetError;

/**
 * Created by zhangmin on 2018/07/26.
 */

public abstract class ApiSubcriber<T> extends Subscriber<T> {


    @Override
    public void onError(Throwable e) {
        NetError error = null;
        if (e != null) {
            if (!(e instanceof NetError)) {
                if (e instanceof UnknownHostException) {
                    error = new NetError(e, NetError.NoConnectError);
                } else if (e instanceof JSONException
                        || e instanceof JsonParseException
                        || e instanceof JsonSyntaxException) {
                    error = new NetError(e, NetError.ParseError);
                } else {
                    error = new NetError(e, NetError.OtherError);
                }
            } else {
                error = (NetError) e;
            }
           /* if (useCommonErrorHandler()
                    && XApi.getCommonProvider() != null) {
                if (XApi.getCommonProvider().handleError(error)) {        //使用通用异常处理
                    return;
                }
            }*/
            onFail(error);
        }

    }

    protected abstract void onFail(NetError error);

    @Override
    public void onCompleted() {

    }

}
