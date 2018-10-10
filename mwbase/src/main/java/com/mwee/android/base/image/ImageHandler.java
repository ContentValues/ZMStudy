package com.mwee.android.base.image;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * ImageHandler
 * Created by Virgil on 16/1/7.
 */
public class ImageHandler extends Handler {
    /**
     * 业务回调
     */
    private IDownloadImage mCallback = null;


    public ImageHandler(IDownloadImage callback) {
        this(Looper.myLooper(), callback);
    }

    public ImageHandler(Looper looper) {
        this(looper, null);
    }

    /**
     * 处理非UI线程之间的通讯
     *
     * @param looper   Looper|对应线程的Looper
     * @param callback IExecutorCallback
     */
    public ImageHandler(Looper looper, IDownloadImage callback) {
        super(looper);
        mCallback = callback;
    }

    @Override
    public void handleMessage(Message msg) {
//        if (Config.ENV == Environment.DEV) {
//            LogUtil.logNET("SyncHandler handleMessage,msg.obj=" + (msg.obj != null ? msg.obj.toString() : "msg.obj is null"));
//        }

        if (msg.obj != null && msg.obj instanceof ImageDownResult) {
            ImageDownResult bitmap = (ImageDownResult) msg.obj;
            if (mCallback != null) {
                if (bitmap.mBitmap != null) {
                    mCallback.success(bitmap);
                } else {
                    ImageDownResult re=new ImageDownResult();
                    re.url=bitmap.url;
                    mCallback.fail(re);
                }
            }
        } else if (mCallback != null) {
            ImageDownResult re=new ImageDownResult();
            re.url="";
            mCallback.fail(re);
        }
    }

}
