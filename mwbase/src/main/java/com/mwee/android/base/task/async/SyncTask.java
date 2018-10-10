package com.mwee.android.base.task.async;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.mwee.android.base.task.BaseThreadPool;
import com.mwee.android.base.task.callback.SyncCallback;
import com.mwee.android.tools.LogUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * SyncTask
 */
public class SyncTask<T> {
    private SyncJob<T> mTask;
    private Handler mHandler;
    private SyncCallback<T> mCallback;

    public SyncTask(SyncJob<T> task) {
        mTask = task;
    }


    /**
     * 提交，调用者线程调用callback
     *
     * @param callback CTPAdapterResultBlock
     */

    public void executeSync(SyncCallback<T> callback) {
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
    public void executeSyncOnMainthread(SyncCallback<T> callback) {
        this.mCallback = callback;
        doWithHandler();
    }

    /**
     * 构建主线程的Handler并发起任务
     */
    private void doWithHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                        if (mCallback != null) {
                            T value = null;
                            if(msg.obj!=null) {
                                try {
                                    value = (T) msg.obj;
                                } catch (Exception e) {
                                    LogUtil.logError(e);
                                }
                            }
                            mCallback.callback(value);
                        }
                }
            };
        }
        mTask.setHandler(mHandler);
        BaseThreadPool.getInstance().joinTask(mTask);
    }

    /**
     * 通过阻塞队列的形式,在调用着线程等过任务线程的结果
     */
    private void doWithQueue() {
        ArrayBlockingQueue<T> queue = new ArrayBlockingQueue<T>(1);
        mTask.setQueue(queue);
        BaseThreadPool.getInstance().joinTask(mTask);
        T result = null;
        try {
            result = queue.take();
        } catch (InterruptedException e) {
            LogUtil.logError("", e);
            result = null;
        } catch (Exception e) {
            LogUtil.logError("", e);
            result = null;
        }
        if (mCallback != null) {
            mCallback.callback(result);
        }
    }
}
