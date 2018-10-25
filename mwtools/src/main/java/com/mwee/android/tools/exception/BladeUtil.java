package com.mwee.android.tools.exception;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;

import com.mwee.android.tools.log.LogUpload;

/**
 * BladeUtil
 * Created by zhangmin on 16/7/21.
 */
public class BladeUtil {

    public static String buildHardwareInfo(Context context) {

        StringBuilder sb = new StringBuilder();
        sb.append("============AppEnvironment-Begin============").append("\n");
        if (context != null) {
            sb.append("PackageName").append(":").append(context.getPackageName()).append("\n");
            PackageInfo info = null;
            try {
                info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (info != null) {
                sb.append("AppVersionName").append(":").append(info.versionName).append("\n");
                sb.append("AppVersionCode").append(":").append(info.versionCode).append("\n");
            }
        }
        sb.append("FingerPrint").append(":").append(Build.FINGERPRINT).append("\n");
        sb.append("应用启动时间").append(":").append(LogUpload.BOOT_TIME).append("\n");

        sb.append("============AppEnvironment-End============").append("\n");
        return sb.toString();
    }
}
