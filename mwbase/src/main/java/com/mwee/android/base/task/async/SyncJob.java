package com.mwee.android.base.task.async;

import android.os.Handler;
import android.os.Message;

import com.mwee.android.base.task.ASyncExecute;
import com.mwee.android.base.task.WorkJob;
import com.mwee.android.tools.LogUtil;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * SyncJob,普通异步
 */
public class SyncJob<T> extends WorkJob {
    private ASyncExecute<T> mExcute;
    /**
     * 调用者线程的Handler
     */
    private Handler mHandler;

    /**
     * 调用者线程的Message
     */
    private Message mMessage;
    /**
     * 如果不使用Handler进行通讯，则可以使用这个queue
     */
    private ArrayBlockingQueue<T> mQueue;

    public SyncJob(ASyncExecute<T> excute, Message message) {
        this.mMessage = message;
        this.mExcute = excute;
        initConfig();
    }

    @Override
    public void initConfig() {
        needSync = true;
        mKey = String.valueOf(System.currentTimeMillis());
    }

    @Override
    public void execute() {
        T result = null;
        try {
            if (mExcute != null) {
                result = mExcute.execute();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if (mHandler != null) {
            mMessage.obj = result;
            mHandler.sendMessage(mMessage);
        } else if (mQueue != null) {
            try {
                if(result==null){
                    mQueue.put((T)new Object());
                }else {
                    mQueue.put(result);
                }
            } catch (InterruptedException e) {
                LogUtil.logError("", e);
            }

        }
    }

    public void setQueue(ArrayBlockingQueue<T> mQueue) {
        this.mQueue = mQueue;
    }

    public void setHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }
}
