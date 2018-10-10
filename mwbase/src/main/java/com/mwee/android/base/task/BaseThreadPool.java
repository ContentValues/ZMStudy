package com.mwee.android.base.task;

import android.os.SystemClock;
import android.support.annotation.NonNull;

import com.mwee.android.base.net.ResponseData;
import com.mwee.android.tools.LogUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * BaseThreadPool
 */
public class BaseThreadPool extends ThreadPoolExecutor {
    private static int maxThreadNum = 0;
    /**
     * 线程池实例
     */
    private final static BaseThreadPool instance = init();
    /**
     * 被拒绝的任务队列
     */
    private final BlockingQueue<Runnable> rejectedTask = new LinkedBlockingQueue<>();
    /**
     * 线程结果同步队列
     */
    private Map<String, ArrayBlockingQueue<ResponseData>> threadQueue = new HashMap<>();

    /**
     * 构造方法
     *
     * @param corePoolSize    int|核心线程数
     * @param maximumPoolSize int|最大线程数
     * @param keepAliveTime   long|存活时长
     * @param unit            TimeUnit|存活时长单位
     * @param workQueue       BlockingQueue<Runnable>|线程调度队列
     */
    private BaseThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                Thread th = new Thread(r);
                th.setName("MYD_NetPool_" + SystemClock.elapsedRealtime());
                th.setPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                return th;
            }
        });
        super.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                //如果有任务被拒绝，则加入拒绝队列
                String key;
                if (r instanceof WorkJob) {
                    key = ((WorkJob) r).mKey;
                } else {
                    key = r.toString();
                }
                LogUtil.logBusiness("ThreadPool RefusedTask Insert" + "_" + key);
                rejectedTask.offer(r);
            }
        });
    }

    /**
     * 单例方法
     *
     * @return BaseThreadPool
     */
    public static BaseThreadPool getInstance() {
        return instance;
    }

    /**
     * 实例构造器
     *
     * @return BaseThreadPool
     */
    private static BaseThreadPool init() {
        BaseThreadPool i = instance;
        if (i == null) {
            int coreThreadNum = Runtime.getRuntime().availableProcessors();
            int maxThreadNum = Runtime.getRuntime().availableProcessors() * 10;
            if (maxThreadNum > 40) {
                maxThreadNum = 40;
            }
            int maxLiveTime = 65 * 2;
            i = new BaseThreadPool(coreThreadNum, maxThreadNum, maxLiveTime, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
            BaseThreadPool.maxThreadNum = maxThreadNum;
        }
        return i;
    }

    /**
     * 获取最大线程数
     *
     * @return int|最大线程数
     */
    public int getMaxThreadNum() {
        return maxThreadNum;
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        //线程执行结束，如果拒绝队列里有值，则拿出来进行执行
        Runnable task = rejectedTask.poll();
        if (task != null) {
            String key;
            if (r instanceof WorkJob) {
                key = ((WorkJob) r).mKey;
            } else {
                key = r.toString();
            }
            LogUtil.logBusiness("ThreadPool RefusedTask Excute " + key);
            super.execute(task);
        }
    }

    /**
     * 根据线程Token拿出同步队列
     *
     * @param key String|ThreadToken
     * @return ArrayBlockingQueue<ResponseData>
     */
    public ArrayBlockingQueue<ResponseData> getResultQueue(String key) {
        return threadQueue.get(key);
    }

    /**
     * Task执行完毕，remove掉线程队列的Task
     *
     * @param key String|ThreadToken
     */
    public void finishTask(String key) {
        if (threadQueue.containsKey(key)) {
            threadQueue.remove(key);
        }
    }

    /**
     * 取消任务,remove掉线程队列的Task
     *
     * @param key String
     */
    public void cancelTask(String key) {
        if (threadQueue.containsKey(key)) {
            threadQueue.remove(key);
        }
    }

    /**
     * 将Task加入到线程队列里并执行。
     *
     * @param task WorkTask
     */
    public void joinTask(WorkJob task) {
        if (task != null) {
            if (task.needSync) {
                threadQueue.put(task.mKey, new ArrayBlockingQueue<ResponseData>(1));
            }
            super.execute(task);
        }
    }
}
