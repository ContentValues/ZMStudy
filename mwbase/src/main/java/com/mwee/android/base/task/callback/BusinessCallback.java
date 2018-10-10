package com.mwee.android.base.task.callback;


import com.mwee.android.base.net.ResponseData;

/**
 * BusinessCallback
 */
public interface BusinessCallback {
    boolean success(int index, ResponseData responseData);

    boolean fail(int index, ResponseData responseData);
}
