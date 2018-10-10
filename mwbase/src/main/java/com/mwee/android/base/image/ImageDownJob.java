package com.mwee.android.base.image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.mwee.android.tools.FileUtil;
import com.mwee.android.tools.LogUtil;

/**
 * ImageDownJob
 * Created by Virgil on 15/11/13.
 */
public class ImageDownJob implements Runnable {
    public String mKey;

    public List<String> urlList = new ArrayList<String>();
    public boolean needsync = false;

    public static HashMap<String, List<Object>> loadingQueue = new HashMap<String, List<Object>>();
    private Handler mHandler;
    private Message mMessage;
    private BlockingQueue<ImageDownResult> mQueue;

    public ImageDownJob(String... url) {
        setUrl(url);
        generateKey();
    }

    public ImageDownJob(List<String> urlList) {
        setUrl(urlList);
        generateKey();
    }

    public void generateKey() {
        mKey = "ImageDownJob-" + String.valueOf(System.currentTimeMillis());
    }

    @Override
    public void run() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }

        setName();
        Bitmap bitmap = null;
        try {
            for (String url : urlList) {
                if (TextUtils.isEmpty(url)) {
                    continue;
                }
                if (ImageDownJob.isWaiting(url)) {
                    Object lock = new Object();
                    ImageDownJob.pushLoadingQueue(url, lock);

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    ImageDownJob.initWaiting(url);

                    bitmap = PictureManager.gitBitmapFromDisk(url);
                    if (bitmap != null) {
//                        PictureManager.pushBitmapToMemCache(url, bitmap);
                            LogUtil.log("ImageDownload getbitmap From IO-Disk=" + url);
                    } else {
                        LogUtil.log("ImageDownload getbitmap From IO-Net=" + url);
                        bitmap = retryDownload(url, 3);
                    }
                    ImageDownJob.popAllLoadingQueue(url);
                }
                if (bitmap == null && !TextUtils.isEmpty(url)) {
                    FileUtil.deleteAllFile(PictureManager.getDefaultImgPathByUrl(url));
                }
            }
        } catch (Exception e) {
            LogUtil.logError("", e);
        }

        doCallback(bitmap);
    }

    private void setName() {
        String originName = Thread.currentThread().getName();
        String subFix = this.getClass().getSimpleName();
        StringBuilder name = new StringBuilder("");
        if (originName.contains("|-")) {
            name.append(originName.substring(0, originName.indexOf("|-")));
        } else {
            name.append(originName);
        }
        name.append("|-").append(subFix);
        Thread.currentThread().setName(name.toString());
    }

    /**
     * 执行回调
     *
     * @param bitmap
     */
    private void doCallback(Bitmap bitmap) {
        ImageDownResult result = new ImageDownResult();
        result.mBitmap = bitmap;
        result.key = mKey;
        result.url = urlList.toString();

        if (mHandler != null && mMessage != null) {
            mMessage.obj = result;
            mHandler.sendMessage(mMessage);
        } else if (mQueue != null) {
            try {
                mQueue.put(result);
            } catch (InterruptedException e) {
                LogUtil.logError("", e);
            }
        }
    }

    /**
     * 循环重试下载图片
     *
     * @param url       String
     * @param retryTime int
     * @return int | see {@link com.mwee.android.base.net.component.NetResultType}
     */
    private Bitmap retryDownload(String url, int retryTime) {

        Bitmap tempResult = ImageDownLoader.imgDownload(url);
        if (tempResult == null) {
            retryTime--;
                LogUtil.log("ImageDownload retryTime=" + retryTime + ";downLoadNow=" + url);
            if (retryTime < 0) {
                return tempResult;
            } else {
                return retryDownload(url, retryTime);
            }
        } else {
            return tempResult;
        }
    }

    public void setUrl(String... url) {
        Collections.addAll(urlList, url);
    }

    public void setUrl(List<String> urlList) {
        this.urlList.clear();
        this.urlList.addAll(urlList);
    }


    /**
     * 当前key是否已经有等待队列了.
     *
     * @param key String
     * @return boolean
     */
    public synchronized static boolean isWaiting(String key) {
        return loadingQueue != null
                && loadingQueue.containsKey(key)
                && loadingQueue.get(key) != null
                && !loadingQueue.get(key).isEmpty();
    }

    /**
     * 初始化整个队列
     */
    public synchronized static void initQueue() {
        if (loadingQueue == null) {
            loadingQueue = new HashMap<String, List<Object>>();
        }
    }

    /**
     * 初始化一个key的队列
     *
     * @param key String
     */
    public synchronized static void initWaiting(String key) {
        if (loadingQueue == null) {
            ImageDownJob.initQueue();
        }
        loadingQueue.put(key, new ArrayList<Object>());
    }

    /**
     * 压入一个等待锁
     *
     * @param key  String
     * @param lock Object
     */
    public synchronized static void pushLoadingQueue(String key, Object lock) {
        if (!TextUtils.isEmpty(key)) {
            if (loadingQueue == null) {
                ImageDownJob.initQueue();
            }
            if (loadingQueue.containsKey(key)) {
                loadingQueue.get(key).add(lock);
            } else {
                ArrayList<Object> objects = new ArrayList<Object>();
                if (lock != null) {
                    objects.add(lock);
                }
                loadingQueue.put(key, objects);
            }
        }
            LogUtil.log("ImageDownloadQueue push,key=" + key + ";loadingQueue size=" + loadingQueue.get(key).size());
    }

    /**
     * 唤醒所有等待者
     *
     * @param key String
     */
    public synchronized static void popAllLoadingQueue(String key) {
        if (loadingQueue != null) {
            if (loadingQueue.containsKey(key)) {

                List<Object> objects = loadingQueue.get(key);
                    if (!objects.isEmpty()) {
                        LogUtil.log("ImageDownloadQueue pop,key=" + key + ";loadingQueue size=" + loadingQueue.get(key).size());
                }
                for (Object temp : objects) {
                    temp.notifyAll();
                }
                loadingQueue.remove(key);
            }
        }
    }

    public void setHandler(Handler handler) {
        mHandler = handler;
    }

    public void setMessage(Message message) {
        mMessage = message;
    }

    public void setQueue(BlockingQueue<ImageDownResult> queue) {
        mQueue = queue;
    }
}
