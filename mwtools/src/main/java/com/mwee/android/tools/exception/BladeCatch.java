package com.mwee.android.tools.exception;

import android.content.Context;

/**
 * BladeCatch
 * Created by zhangmin on 16/7/21.
 */
public class BladeCatch {
    public static void init(Context context) {
        BladeExceptionHandler.getInstance().init();
    }
    public static void close(Context context) {
        BladeExceptionHandler.getInstance().close();
    }

    public static void setBusinessEnv(String info) {
        BladeExceptionHandler.getInstance().setBizInfo(info);
    }
}
