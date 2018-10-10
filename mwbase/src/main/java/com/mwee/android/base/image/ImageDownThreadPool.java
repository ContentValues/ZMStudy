package com.mwee.android.base.image;


import com.mwee.android.base.task.WorkJob;
import com.mwee.android.tools.LogUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ImageDownThreadPool
 * Created by Virgil on 15/12/31.
 */
public class ImageDownThreadPool extends ThreadPoolExecutor {
    /**
     * 线程池实例
     */
    private final static ImageDownThreadPool instance = init();
    /**
     * 被拒绝的任务队列
     */
    private final BlockingQueue<Runnable> rejectedTask = new LinkedBlockingQueue<Runnable>();

    /**
     * 构造方法
     *
     * @param corePoolSize    int|核心线程数
     * @param maximumPoolSize int|最大线程数
     * @param keepAliveTime   long|存活时长
     * @param unit            TimeUnit|存活时长单位
     * @param workQueue       BlockingQueue<Runnable>|线程调度队列
     */
    private ImageDownThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread th = new Thread(r);
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
                    LogUtil.log("ThreadPool" + "_" + "RefusedTask_+" + "Insert" + "_" + key);
                rejectedTask.offer(r);
            }
        });
    }

    /**
     * 单例方法
     *
     * @return HJThreadPool
     */
    public static ImageDownThreadPool getInstance() {
        return instance;
    }

    /**
     * 实例构造器
     *
     * @return HJThreadPool
     */
    private static ImageDownThreadPool init() {
        ImageDownThreadPool i = instance;
        if (i == null) {
            int coreThreadNum = 0;
            int maxThreadNum = Runtime.getRuntime().availableProcessors() * 2;
            maxThreadNum = maxThreadNum > 4 ? 4 : maxThreadNum;
            int maxLiveTime = 60;
            i = new ImageDownThreadPool(coreThreadNum, maxThreadNum, maxLiveTime, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        }
        return i;
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
                LogUtil.log("ThreadPool" + "_" + "RefusedTask_+" + "Excute" + "_" + key);
            super.execute(task);
        }
    }

    /**
     * 将Task加入到线程队列里并执行。
     *
     * @param task WorkTask
     */
    public void joinTask(ImageDownJob task) {
        if (task != null) {
            super.execute(task);
        }
    }
}
