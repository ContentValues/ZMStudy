package com.mwee.android.base.net;

/**
 * IHttpResultCallBack
 */
public interface IHttpResultCallBack {
    void onResult(int resultCode, byte[] infoBytes, int httpStatus,Exception e);
}
