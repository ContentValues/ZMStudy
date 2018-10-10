package com.mwee.android.base.task.net;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import com.mwee.android.base.net.ResponseData;
import com.mwee.android.base.task.callback.IExecutorCallback;

/**
 * NetHandler
 */
public class NetHandler extends Handler {
    /**
     * 业务回调
     */
    private IExecutorCallback mCallback = null;

    private ArrayMap<String, IExecutorCallback> waitingCallBack = new ArrayMap<>();


    /**
     * 处理非UI线程之间的通讯
     *
     * @param looper   Looper|对应线程的Looper
     * @param callback IExecutorCallback
     */
    public NetHandler(Looper looper, IExecutorCallback callback) {
        super(looper);
        mCallback = callback;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.obj == null) {
            msg.obj = NetJob.buildTimeOutModel();
        }
        if (msg.obj != null && msg.obj instanceof ResponseData) {
            ResponseData responseData = (ResponseData) msg.obj;
            if (TextUtils.isEmpty(responseData.key)) {
                return;
            }
            if (mCallback == null) {
                mCallback = waitingCallBack.get(responseData.key);
            }
            if (mCallback != null) {
                NetTask.executeCallBack(mCallback, responseData);
                waitingCallBack.remove(responseData.key);
            } else {
                String key = responseData.key;
                /**
                 * 如果发起服务调用者本身没有Callback,则需要查找接收者在哪里.
                 * 目前,接收者统一被注册在{@link ExecutorResult.waitingCallbackMap}
                 */
                IExecutorCallback callback = ExecutorResult.pollWaitingCallback(key);
                if (callback != null) {
                    NetTask.executeCallBack(callback, responseData);
                } else {
                    if (ExecutorResult.waitingKeyIsRegisted(key)) {
                        ExecutorResult.putRepsoneDataToReulst(key, responseData);
                    }
                }

            }
        }
    }

    public void registCallBack(String key, IExecutorCallback callback) {
        waitingCallBack.put(key, callback);
    }

    public void setmCallback(IExecutorCallback mCallback) {
        this.mCallback = mCallback;
    }
}
