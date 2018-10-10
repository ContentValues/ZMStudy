package com.mwee.android.base.task.net;

import com.mwee.android.base.net.ResponseData;
import com.mwee.android.base.task.callback.IExecutorCallback;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * ExecutorResult
 */
public class ExecutorResult {
    private static List<String> waitingKeyList = new ArrayList<String>();
    /**
     * 服务结果的容器
     */
    private static LinkedHashMap<String, ResponseData> resultMap = new LinkedHashMap<String, ResponseData>();
    /**
     * 服务结果接收者的监听集合
     */
    private static LinkedHashMap<String, IExecutorCallback> waitingCallbackMap = new LinkedHashMap<String, IExecutorCallback>();

    /**
     * 服务结果接收者注册监听器进来
     *
     * @param key      String
     * @param callback IExecutorCallback
     */
    public static synchronized void registWaitingQueue(String key, IExecutorCallback callback) {
        if (key != null) {

            if (resultMap.containsKey(key)) {
                /**
                 * 如果服务结果容器里已经有了对应的结果,则直接通知.
                 */
                if (callback != null) {
                    NetTask.executeCallBack(callback, resultMap.get(key));
                }
                /**
                 * 如果有别的页面也在等待相同服务的结果,则进行相同的通知.
                 * 注意,这里只是个兼容性处理,不应该依赖于这里的处理结果.
                 */
                if (waitingCallbackMap.containsKey(key)) {
                    NetTask.executeCallBack(waitingCallbackMap.get(key), resultMap.get(key));
                    waitingCallbackMap.remove(key);
                }
                resultMap.remove(key);
                removeWaitingKey(key);
            } else {
                /**
                 * 否则,则注册进去
                 */
                waitingCallbackMap.put(key, callback);
            }
        }
    }

    /**
     * 将服务结果放入容器
     *
     * @param key          String
     * @param responseData ResponseData
     */
    public static synchronized void putRepsoneDataToReulst(String key, ResponseData responseData) {
        resultMap.put(key, responseData);
    }

    /**
     * 取出对应的接收者CallBack并移除出等待队列
     *
     * @param key String
     * @return IExecutorCallback
     */
    public static synchronized IExecutorCallback pollWaitingCallback(String key) {
        IExecutorCallback callback = null;
        callback = waitingCallbackMap.get(key);
        if (callback != null) {
            waitingCallbackMap.remove(key);
            removeWaitingKey(key);
        }
        return callback;
    }

    public static synchronized void registWaitingKeyList(String key) {
        if (waitingKeyList == null) {
            waitingKeyList = new ArrayList<String>();
        }
        waitingKeyList.add(key);
    }

    public static synchronized boolean waitingKeyIsRegisted(String key) {
        if (waitingKeyList == null) {
            return false;
        } else {
            return waitingKeyList.indexOf(key) >= 0;
        }
    }

    public static synchronized void removeWaitingKey(String key) {
        if (waitingKeyList.contains(key)) {
            waitingKeyList.remove(key);
        }
    }


}
