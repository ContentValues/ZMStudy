package com.mwee.android.print.printer;

import android.util.Log;

import com.mwee.android.tools.LogUtil;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by virgil on 2018/3/28.
 */

public class PrintLog {
    /**
     * 是否需要打印日志
     */
    protected static boolean showLog = true;

    /**
     * 设置是否打印日志
     *
     * @param show boolean | true:打印;false:不打印
     */
    public static void setShowLog(boolean show) {
        showLog = show;
    }

    public static void e(String tag, String msg) {
        LogUtil.logBusiness(tag, msg);
        if (showLog) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable e) {
        LogUtil.logBusiness(tag, msg + " " + e.getMessage());
        LogUtil.logError(e);
        try {
            CrashReport.postCatchedException(e);
            return;
        } catch (Throwable e2) {
        }
        if (showLog) {
            Log.e(tag, msg, e);
        }

    }

    public static void i(String tag, String msg) {
        if (showLog) {
            Log.i(tag, msg);
        }
    }

    public static String i(String tag, byte[] msg) {
        StringBuilder sb = new StringBuilder("[");
        if (msg != null) {
            for (byte b : msg) {
                sb.append(String.format("0x%2x", b)).append(",");
            }
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        if (showLog) {

//            ToastUtil.showToast(tag+" "+sb.toString());
            Log.i(tag, sb.toString());
        }
        return sb.toString();
    }

    public static void w(String tag, String msg) {
        if (showLog) {
            Log.w(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (showLog) {
            Log.d(tag, msg);
        }
    }
}
