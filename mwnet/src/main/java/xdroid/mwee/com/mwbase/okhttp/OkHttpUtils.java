package xdroid.mwee.com.mwbase.okhttp;
import android.os.Looper;
import com.alibaba.fastjson.JSON;
import com.mwee.android.tools.LogUtil;
import java.io.IOException;
import java.util.concurrent.Executor;
import xdroid.mwee.com.mwbase.okhttp.builder.GetBuilder;
import xdroid.mwee.com.mwbase.okhttp.builder.HeadBuilder;
import xdroid.mwee.com.mwbase.okhttp.builder.OtherRequestBuilder;
import xdroid.mwee.com.mwbase.okhttp.builder.PostFileBuilder;
import xdroid.mwee.com.mwbase.okhttp.builder.PostFormBuilder;
import xdroid.mwee.com.mwbase.okhttp.builder.PostStringBuilder;
import xdroid.mwee.com.mwbase.okhttp.callback.Callback;
import xdroid.mwee.com.mwbase.okhttp.request.RequestCall;
import xdroid.mwee.com.mwbase.okhttp.utils.Platform;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class OkHttpUtils {
    public static final long DEFAULT_MILLISECONDS = 10_000L;
    private volatile static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Platform mPlatform;

    public OkHttpUtils(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        } else {
            mOkHttpClient = okHttpClient;
        }

        mPlatform = Platform.get();
    }


    public static OkHttpUtils initClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static OkHttpUtils getInstance() {
        return initClient(null);
    }


    public Executor getDelivery() {
        return mPlatform.defaultCallbackExecutor();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static GetBuilder get() {
        return new GetBuilder();
    }

    public static PostStringBuilder postString() {
        return new PostStringBuilder();
    }

    public static PostFileBuilder postFile() {
        return new PostFileBuilder();
    }

    public static PostFormBuilder post() {
        return new PostFormBuilder();
    }

    public static OtherRequestBuilder put() {
        return new OtherRequestBuilder(METHOD.PUT);
    }

    public static HeadBuilder head() {
        return new HeadBuilder();
    }

    public static OtherRequestBuilder delete() {
        return new OtherRequestBuilder(METHOD.DELETE);
    }

    public static OtherRequestBuilder patch() {
        return new OtherRequestBuilder(METHOD.PATCH);
    }

    public void execute(final RequestCall requestCall, Callback callback) {
        if (callback == null)
            callback = Callback.CALLBACK_DEFAULT;
        final Callback finalCallback = callback;
        final int id = requestCall.getOkHttpRequest().getId();
        final String url = requestCall.getOkHttpRequest().url;
        String requestStr = "";
        if (requestCall.getOkHttpRequest().params != null) {
            requestStr = JSON.toJSONString(JSON.toJSONString(requestCall.getOkHttpRequest().params));
        }
        String taskKey = "MWNET" + "_" + String.valueOf(System.currentTimeMillis());
        if (LogUtil.SHOW) {
            LogUtil.logNET(taskKey, "URL=" + url);
            LogUtil.logNET(taskKey, "RequestStr is :" + requestStr);
        }


//        if (Looper.getMainLooper() == Looper.myLooper()) {
//            LogUtil.logNET("enqueue onResponse 当前请求运行在主线中" + requestCall.getCall().request().url());
//        } else {
//            LogUtil.logNET("enqueue onResponse 当前请求运行在子线中" + requestCall.getCall().request().url());
//        }

        //同步请求
      /*  try {
            Response response = requestCall.getCall().execute();

            if (Looper.getMainLooper() == Looper.myLooper()) {
                LogUtil.logNET("enqueue2 onResponse 当前请求运行在主线中" + requestCall.getCall().request().url());
            } else {
                LogUtil.logNET("enqueue2 onResponse 当前请求运行在子线中" + requestCall.getCall().request().url());
            }


            if (requestCall.getCall().isCanceled()) {
                sendFailResultCallback(requestCall.getCall(), new IOException("Canceled"), finalCallback, id);
            } else {
                //callback.onResponse(requestCall.getCall(), response);
                if (!finalCallback.validateReponse(response, id)) {
                    sendFailResultCallback(requestCall.getCall(), new IOException("request failed , reponse's code is : " + response.code()), finalCallback, id);
                    return;
                }
                Object o = null;
                try {
                    o = finalCallback.parseNetworkResponse(response, id);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //解密数据
                String taskKey2 = "MWNET" + "_" + String.valueOf(System.currentTimeMillis());
                if (LogUtil.SHOW) {
                    LogUtil.logNET(taskKey2, "response=" + JSON.toJSONString(o));
                }
                sendSuccessResultCallback(o, finalCallback, id);
            }
        } catch (IOException e) {
            e.printStackTrace();
            sendFailResultCallback(requestCall.getCall(), e, finalCallback, id);
        }*/



        //异步线程执行耗时操作
        requestCall.getCall().enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

                sendFailResultCallback(call, e, finalCallback, id);
            }

            @Override
            public void onResponse(final Call call, final Response response) {

                //异步线程调用
                try {
                    if (call.isCanceled()) {
                        sendFailResultCallback(call, new IOException("Canceled!"), finalCallback, id);
                        return;
                    }

                    if (!finalCallback.validateReponse(response, id)) {
                        sendFailResultCallback(call, new IOException("request failed , reponse's code is : " + response.code()), finalCallback, id);
                        return;
                    }

                    Object o = finalCallback.parseNetworkResponse(response, id);

                    //解密数据
                    String taskKey = "MWNET" + "_" + String.valueOf(System.currentTimeMillis());
                    if (LogUtil.SHOW) {
                        LogUtil.logNET(taskKey, "response=" + JSON.toJSONString(o));
                    }
                    sendSuccessResultCallback(o, finalCallback, id);
                } catch (Exception e) {
                    sendFailResultCallback(call, e, finalCallback, id);
                } finally {
                    if (response.body() != null)
                        response.body().close();
                }

            }
        });
    }


    public void sendFailResultCallback(final Call call, final Exception e, final Callback callback, final int id) {
        if (callback == null) return;

        mPlatform.execute(new Runnable() {
            @Override
            public void run() {

                if (Looper.getMainLooper() == Looper.myLooper()) {
                    LogUtil.logNET("切换线程 sendFailResultCallback 当前请求运行在主线中");
                } else {
                    LogUtil.logNET("切换线程 sendFailResultCallback 当前请求运行在子线中");
                }


                callback.onError(call, e, id);
                callback.onAfter(id);
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final Callback callback, final int id) {
        if (callback == null) return;

        /**
         * 回调到主线程
         *
         */
        mPlatform.execute(new Runnable() {
            @Override
            public void run() {

                if (Looper.getMainLooper() == Looper.myLooper()) {
                    LogUtil.logNET("切换线程 sendSuccessResultCallback 当前请求运行在主线中");
                } else {
                    LogUtil.logNET("切换线程 sendSuccessResultCallback 当前请求运行在子线中");
                }

                callback.onResponse(object, id);
                callback.onAfter(id);
            }
        });
    }

    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    public static class METHOD {
        public static final String HEAD = "HEAD";
        public static final String DELETE = "DELETE";
        public static final String PUT = "PUT";
        public static final String PATCH = "PATCH";
    }
}

