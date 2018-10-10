package com.mwee.android.base.task;

import android.os.Message;

import com.mwee.android.base.net.BaseRequest;
import com.mwee.android.base.task.async.SyncJob;
import com.mwee.android.base.task.async.SyncTask;
import com.mwee.android.base.task.async.UnSyncJob;
import com.mwee.android.base.task.callback.BusinessCallback;
import com.mwee.android.base.task.callback.IExecutorCallback;
import com.mwee.android.base.task.callback.SyncCallback;
import com.mwee.android.base.task.net.NetJob;
import com.mwee.android.base.task.net.NetTask;

import java.util.List;

/**
 * BusinessExecutor
 */
public class BusinessExecutor {

    /**
     * 发送服务,并正常回调
     *
     * @param entity   BusinessEntity
     * @param callback IExecutorCallback
     * @return String|本次网络会话key
     */
    public static String execute(BaseRequest entity, IExecutorCallback callback, boolean forceMainThread) {
        return execute(entity, callback, null, forceMainThread);

    }

    public static String execute(BaseRequest entity, IExecutorCallback callback) {
        return execute(entity, callback, null, true);
    }

    /**
     * 发送服务,并正常回调
     *
     * @param entity   BusinessEntity
     * @param callback IExecutorCallback
     * @return String|本次网络会话key
     */
    public static String execute(BaseRequest entity, IExecutorCallback callback, BusinessCallback serverCallback, boolean forceMainThread) {
        NetJob job = new NetJob(entity);
        job.setServerThreadCallback(serverCallback);
        NetTask task = new NetTask(job);
        if (forceMainThread) {
            task.executeSyncOnMainThread(callback);
        } else {
            task.executeSync(callback);
        }
        return job.mKey;
    }

    public static String execute(BaseRequest entity, IExecutorCallback callback, BusinessCallback serverCallback) {
        return execute(entity, callback, serverCallback, false);

    }

    /**
     * 发送服务,并正常回调
     *
     * @param entity   BusinessEntity
     * @param callback IExecutorCallback
     * @return String|本次网络会话key
     */
    public static String execute(List<BaseRequest> entity, IExecutorCallback callback, BusinessCallback serverCallback) {
        return execute(entity,callback,serverCallback,false);
    }

    /**
     * 发送服务，并正常回调
     * @param entity List<BaseRequest> | 请求列表
     * @param callback IExecutorCallback | 接收者线程回调
     * @param serverCallback BusinessCallback | 网络线程回调
     * @param forceMainThread  boolean | 是否强制在主线程
     * @return String
     */
    public static String execute(List<BaseRequest> entity, IExecutorCallback callback, BusinessCallback serverCallback,boolean forceMainThread) {
        NetJob job = new NetJob(entity);
        job.setServerThreadCallback(serverCallback);
        NetTask task = new NetTask(job);
        if (forceMainThread) {
            task.executeSyncOnMainThread(callback);
        } else {
            task.executeSync(callback);
        }
        return job.mKey;
    }
    /**
     * 执行普通异步任务,不需要回调
     *
     * @param excute ASyncExecute
     * @return String|本次会话key
     */
    public static String executeNoWait(ASyncExecute excute) {
        UnSyncJob job = new UnSyncJob(excute);
        BaseThreadPool.getInstance().joinTask(job);
        return job.mKey;
    }

    /**
     * 执行普通异步任务,需要回调
     *
     * @param excute   ASyncExecute
     * @param callBack IExecutorCallback
     * @return String|本次网络会话key
     */
    public static <T> String executeAsyncExcute(ASyncExecute<T> excute, SyncCallback<T> callBack) {
        SyncJob<T> job = new SyncJob<T>(excute, Message.obtain());

        SyncTask<T> task = new SyncTask<T>(job);
        task.executeSync(callBack);
        return job.mKey;
    }
    /**
     * 执行普通异步任务,需要回调,主线程回调
     *
     * @param excute   ASyncExecute
     * @param callBack IExecutorCallback
     * @return String|本次网络会话key
     */
    public static <T> String executeAsyncExcuteOnMain(ASyncExecute<T> excute, SyncCallback<T> callBack) {
        SyncJob<T> job = new SyncJob<T>(excute, Message.obtain());

        SyncTask<T> task = new SyncTask<T>(job);
        task.executeSyncOnMainthread(callBack);
        return job.mKey;
    }
}
