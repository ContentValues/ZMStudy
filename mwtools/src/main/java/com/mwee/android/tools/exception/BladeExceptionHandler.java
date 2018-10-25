package com.mwee.android.tools.exception;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import com.mwee.android.tools.LogUtil;

import java.util.List;

/**
 * BladeExceptionHandler
 * Created by zhangmin on 16/7/21.
 */
public class BladeExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final static BladeExceptionHandler instance = new BladeExceptionHandler();
    private Thread.UncaughtExceptionHandler defaultHander;
    private String bizInfo = "";
    private String hardWareInfo = "";

    private BladeExceptionHandler() {

    }

    public static BladeExceptionHandler getInstance() {
        return instance;
    }

    public void init() {
//        defaultHander = Thread.getDefaultUncaughtExceptionHandler();
//        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void close(){
//        Thread.setDefaultUncaughtExceptionHandler(defaultHander);
    }
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        StringBuilder tag = new StringBuilder();
        tag.append("UnCaught").append("\n");
        if (!TextUtils.isEmpty(bizInfo)) {
            tag.append("bizInfo").append(":").append(bizInfo).append("\n");
        }
        LogUtil.logError(tag.toString(), throwable);
        defaultHander.uncaughtException(thread, throwable);

        ActivityManager am = (ActivityManager) LogUtil.context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> proList = am.getRunningAppProcesses();
        final String packageName = LogUtil.context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo temp : proList) {
            if (temp.processName.startsWith(packageName)) {
                android.os.Process.killProcess(temp.pid);
            }
        }
    }

    public void setBizInfo(String bizInfo) {
        this.bizInfo = bizInfo;
    }

}
