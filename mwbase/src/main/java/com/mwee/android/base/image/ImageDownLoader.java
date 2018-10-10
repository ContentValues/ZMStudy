package com.mwee.android.base.image;

import android.graphics.Bitmap;
import android.os.Looper;
import android.os.Message;


import com.mwee.android.base.net.Downloader;
import com.mwee.android.tools.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ImageDownLoader
 * Created by Virgil on 16/1/8.
 */
public class ImageDownLoader {

    public static String excute(IDownloadImage callBack, String... url) {
        if (url != null) {
            List<String> urlList = new ArrayList<String>();
            Collections.addAll(urlList, url);
            return excute(callBack, urlList);
        } else {
            return "";
        }
    }

    public static String excute(IDownloadImage callBack, List<String> url) {
        if (url != null && !url.isEmpty()) {
            ImageDownJob job = new ImageDownJob(url);
            if (callBack == null) {
                job.needsync = false;
            } else {
                job.needsync = true;
            }
            if (Looper.myLooper() != Looper.getMainLooper()) {
                doWithQueue(job, callBack);
            } else {
                doWithHandler(job, callBack);
            }
            return job.mKey;
        } else {
            return "";
        }
    }

    public static String excuteOnMain(IDownloadImage callBack, String... url) {
        if (url != null) {
            List<String> urlList = new ArrayList<String>();
            Collections.addAll(urlList, url);
            return excuteOnMain(callBack, urlList);
        } else {
            return "";
        }
    }

    public static String excuteOnMain(IDownloadImage callBack, List<String> url) {
        if (url != null && !url.isEmpty()) {

            ImageDownJob job = new ImageDownJob(url);
            if (callBack == null) {
                job.needsync = false;
            } else {
                job.needsync = true;
            }
            doWithHandler(job, callBack);
            return job.mKey;
        } else {
            return "";
        }
    }

    /**
     * 构建主线程的Handler并发起任务
     */
    private static void doWithHandler(final ImageDownJob mTask, IDownloadImage callBack) {
        if (mTask.needsync && callBack != null) {
            ImageHandler mHandler = new ImageHandler(Looper.getMainLooper(), callBack);
            mTask.setHandler(mHandler);
            mTask.setMessage(Message.obtain());
        }
        ImageDownThreadPool.getInstance().joinTask(mTask);
    }

    /**
     * 通过阻塞队列的形式,在调用着线程等过任务线程的结果
     */
    private static void doWithQueue(ImageDownJob mTask, IDownloadImage callBack) {
        BlockingQueue<ImageDownResult> workingQueue = null;
        if (mTask.needsync && callBack != null) {
            workingQueue = new ArrayBlockingQueue<ImageDownResult>(1);
            mTask.setQueue(workingQueue);
        }
        ImageDownThreadPool.getInstance().joinTask(mTask);
        if (workingQueue != null) {
            ImageDownResult responseData = null;
            try {
                responseData = workingQueue.poll(60, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                LogUtil.logError("", e);
                responseData = null;
            }
            if (responseData == null) {
                responseData = new ImageDownResult();
                responseData.mBitmap = null;
            }
            if (responseData.mBitmap != null) {
                callBack.success(responseData);
            } else {
                ImageDownResult re=new ImageDownResult();
                re.url="";
                callBack.fail(re);
            }
        }
    }


    /**
     * 重载方法see{@link #imgDownload(String, String)},默认图片的下载路径
     *
     * @param imgUrl String | 图片原始URL
     */
    protected static Bitmap imgDownload(String imgUrl) {
        return imgDownload(imgUrl, PictureManager.getDefaultImgPathByUrl(imgUrl));
    }

    /**
     * 下载图片.
     * 会首先判断本地缓存是否已经存在,如果已经存在,则不再进行下载.
     *
     * @param imgUrl   String | 图片原始URL
     * @param savePath String | 图片存储的目标文件
     */
    protected static Bitmap imgDownload(String imgUrl, String savePath) {
        return Downloader.downloadBitmap(imgUrl, savePath);
    }
}
