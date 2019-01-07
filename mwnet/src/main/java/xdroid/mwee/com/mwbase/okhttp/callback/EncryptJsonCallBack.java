package xdroid.mwee.com.mwbase.okhttp.callback;

import com.alibaba.fastjson.JSON;
import com.mwee.android.sqlite.base.ClientMetaUtil;
import com.mwee.android.tools.encrypt.EncryptUtil;
import org.json.JSONException;
import java.lang.reflect.ParameterizedType;
import java.net.UnknownHostException;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import xdroid.mwee.com.mwbase.NetError;

import com.mwee.android.sqlite.META;

/**
 * Created by zhangmin on 2018/6/14.
 */

public abstract class EncryptJsonCallBack<T> extends Callback<T> {
    private long expireTime = -1L;        //缓存时间
    private Class<T> entityClass;

    public EncryptJsonCallBack() {
        this(-1L);
    }

    public EncryptJsonCallBack(long expireTime) {
        this.expireTime = expireTime;
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
        //todo 主线程做处理 可以作为加密处理吗？
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        NetError error = null;
        if (e != null) {
           /* if (e instanceof UnknownHostException
                    && expireTime > -1) {
                *//**
             * 无网络连接的时候  读取DisKCache中的数据
             *//*
                String cacheKey = getCacheKey(call.request());
                String cacheContent = DiskCache.getInstance(ClientApplication.getContext()).get(cacheKey);

                if (!TextUtils.isEmpty(cacheContent)) {
                    try {
                        if (entityClass == String.class) {
                            onResponse((T) cacheContent, id);
                            return;
                        }
                        T model = GsonProvider.getInstance().getGson().fromJson(cacheContent, entityClass);
                        onResponse(model, id);
                        return;

                    } catch (Exception exception) {
                    }
                }
            }*/
            if (!(e instanceof NetError)) {
                if (e instanceof UnknownHostException) {
                    error = new NetError(e, NetError.NoConnectError,"网络无连接");
                } else if (e instanceof JSONException
                        || e instanceof com.alibaba.fastjson.JSONException) {
                    error = new NetError(e, NetError.ParseError,"数据解析异常");
                } else {
                    error = new NetError(e, NetError.OtherError,"其他异常");
                }
            } else {
                error = (NetError) e;
            }
        }
        //String errorMsg = NetErrorProcessor.queryErrorMsg(error);
        onFail(call, error.errorMsg, id);
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {


        String result = "";
        try {
            result = EncryptUtil.MwDecryptaut(ClientMetaUtil.getSettingsValueByKey(META.SHOPID)
                    , ClientMetaUtil.getSettingsValueByKey(META.TOKEN)
                    , ClientMetaUtil.getSettingsValueByKey(META.SEED)
                    , response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Cache
        /*if (expireTime > -1 && !TextUtils.isEmpty(result)) {
            *//**
             * 网络数据解析成功 DiskCache缓存数据
             *//*
            if (ClientApplication.getContext() != null) {
                String cacheKey = getCacheKey(response.request());
                DiskCache.getInstance(ClientApplication.getContext()).put(cacheKey, result, expireTime);
            }
        }*/

        if (entityClass == String.class) {
            return (T) result;
        }

        return JSON.parseObject(result, entityClass);
    }

    //public abstract void onFail(Call call, NetError e, int id);
    public abstract void onFail(Call call, String erroMsg, int id);

    /**
     * 获取缓存的key
     *
     * @param request
     * @return
     */
    /*private String getCacheKey(Request request) {
        String url = request.url().toString();
        RequestBody requestBody = request.body();
        if (requestBody != null) {
            MediaType mediaType = requestBody.contentType();
            if (mediaType != null) {
                if (AppKit.isText(mediaType)) {
                    String bodyStr = AppKit.bodyToString(request);
                    return new StringBuilder(url).append(bodyStr).toString();
                }
            }
        }
        return url;
    }*/
}
