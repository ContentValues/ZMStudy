package com.mwee.android.base.image;


import com.mwee.android.base.task.callback.ICallback;

/**
 * IDownloadImageCallback
 * Created by Virgil on 15/12/31.
 */
public interface IDownloadImage extends ICallback<ImageDownResult> {
    @Override
    void success(ImageDownResult result);

    @Override
    boolean fail(ImageDownResult url);
}
