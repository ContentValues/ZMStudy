package com.mwee.android.base.task.net;

import android.os.Looper;
import android.os.Message;

import com.mwee.android.base.net.ResponseData;
import com.mwee.android.base.net.component.Result;
import com.mwee.android.base.task.BaseThreadPool;
import com.mwee.android.base.task.callback.IExecutorCallback;
import com.mwee.android.tools.LogUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * NetTask
 */
public class NetTask {
    private NetJob mTask;
    private NetHandler mHandler;
    private IExecutorCallback mCallback;

    public NetTask(NetJob task) {
        mTask = task;
    }

    /**
     * 执行CallBack
     *
     * @param callBack     IExecutorCallback
     * @param responseData ResponseData
     */
    public static void executeCallBack(IExecutorCallback callBack, ResponseData responseData) {
        if (callBack != null && responseData != null) {
            switch (responseData.result) {
                case Result.SUCCESS:
                    callBack.success(responseData);
                    break;
                case Result.FAIL:
                    callBack.fail(responseData);
                    break;
                case Result.BUSINESS_FAIL:
                    callBack.fail(responseData);
                    break;
                default:
                    callBack.fail(responseData);
                    break;
            }
        }
    }

    /**
     * 提交，调用者线程调用callback
     *
     * @param callback CTPAdapterResultBlock
     */

    public void executeSync(IExecutorCallback callback) {
        this.mCallback = callback;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            doWithQueue();
        } else {
            doWithHandler();
        }
    }

    /**
     * 提交，强制在主线程调用callback
     *
     * @param callback CTPAdapterResultBlock
     */
    public void executeSyncOnMainThread(IExecutorCallback callback) {
        this.mCallback = callback;
        doWithHandler();
    }

    /**
     * 构建主线程的Handler并发起任务
     */
    private void doWithHandler() {
//        if (Config.ENV == Environment.DEV) {
//            LogUtil.log("NetTask doWithHandler");
//        }

        if (mCallback == null) {
            mTask.needSync = false;
        }
        if (mHandler == null && mTask.needSync) {
            mHandler = new NetHandler(Looper.getMainLooper(), mCallback);
        }
        BaseThreadPool.getInstance().joinTask(mTask);
        int timeOut = 60;
        if (mTask.timeOut > 0) {
            timeOut = mTask.timeOut;
        }
        if (mTask.needSync) {
            mTask.mHandler=mHandler;
        }
    }

    /**
     * 通过阻塞队列的形式,在调用者线程等过任务线程的结果
     */
    private void doWithQueue() {
        if (mCallback == null) {
            mTask.needSync = false;
        }
        ArrayBlockingQueue<ResponseData> queue = new ArrayBlockingQueue<ResponseData>(1);
        BaseThreadPool.getInstance().joinTask(mTask);
        ResponseData responseData = null;
        if (mTask.needSync) {
            int timeOut = 60;
            if (mTask.timeOut > 0) {
                timeOut = mTask.timeOut;
            }
            mTask.mQueue=queue;
            try {
                responseData = queue.poll(timeOut, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                LogUtil.logError("", e);
                responseData = null;
            } catch (Exception e) {
                LogUtil.logError("", e);
                responseData = null;
            }
        }
        if (responseData == null) {
            responseData = NetJob.buildTimeOutModel();
        }
        if (mCallback != null) {
            NetTask.executeCallBack(mCallback, responseData);
        } else {
            IExecutorCallback callback = ExecutorResult.pollWaitingCallback(mTask.mKey);
            if (callback != null) {
                NetTask.executeCallBack(callback, responseData);
            } else {
                if (ExecutorResult.waitingKeyIsRegisted(mTask.mKey)) {
                    ExecutorResult.putRepsoneDataToReulst(mTask.mKey, responseData);
                }
            }
        }
    }


    public void executeUnSync() {
        BaseThreadPool.getInstance().joinTask(mTask);
    }


}
