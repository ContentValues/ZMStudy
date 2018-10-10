package com.mwee.android.base.net;

import com.mwee.android.base.net.component.Interceptor;

/**
 * 通讯配合
 * Created by virgil on 2016/11/10.
 */

public class NetConfig {
    /**
     * 添加拦截器
     *
     * @param logInterceptor
     */
    public static void addInterceptor(Interceptor logInterceptor) {
        ServiceConnector.logInterceptor = logInterceptor;
    }

    public static void addFinalInterceptor(Interceptor finalInterceptor) {
        ServiceConnector.finalInterceptor = finalInterceptor;
    }
}
