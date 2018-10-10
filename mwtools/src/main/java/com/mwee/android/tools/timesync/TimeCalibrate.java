package com.mwee.android.tools.timesync;

import android.os.SystemClock;

import java.util.Date;

/**
 * TimeCalibrate 时间校准机制
 */
public class TimeCalibrate {
    private static long currentTag; //本地时间
    private static long serviceTime;//服务器时间
    private static boolean succedFetchedTime = false; //是否成功获取过服务器时间
    private static boolean localTimeIsOK = true;  //本地时间是否有效,默认是有效的

    public TimeCalibrate() {
    }

    /**
     * 确定服务器时间和本地时间是否一致，60秒内认为一致
     *
     * @param inputTime long
     */
    public static void initServerTime(long inputTime) {
        if (!succedFetchedTime || !localTimeIsOK) {
            serviceTime = inputTime;
            succedFetchedTime = true;
            currentTag = SystemClock.elapsedRealtime();
            localTimeIsOK = (Math.abs(System.currentTimeMillis() - serviceTime) < 60000);
        }
    }

    public static boolean isSuccedFetchedTime() {
        return succedFetchedTime;
    }

    public static Date getCurrentCalendar() {
        Date s = new Date();
//        Calendar currentCalendar = DateUtil.getLocalCalendar();
        if (!localTimeIsOK) {
            s.setTime(serviceTime + SystemClock.elapsedRealtime() - currentTag);
        }
        return s;
    }
}
