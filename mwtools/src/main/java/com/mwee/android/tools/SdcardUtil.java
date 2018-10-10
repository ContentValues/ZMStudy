package com.mwee.android.tools;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import java.io.File;

/**
 * SdcardUtil,SDcard相关逻辑的工具类
 */
public class SdcardUtil {

    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";

    private static final String ERROR_NO_PERMISSION = "没有SD卡的读写权限";
    private static final String ERROR_NO_SDCARD = "没有SD卡";
    private static final String ERROR_SDCARD_FREE_SPACE_NOT_ENOUGH = "SD卡可用空间不足";

    /**
     * SD卡是否可用,包括:
     * 1,SD卡的写权限,see{@link #hasExternalStoragePermission(Context)}
     * 2,SD卡是否已挂载,see{@link #isSDCARDReadyReadWrite()}
     * 3,SD卡的可用的存储空间是否足够,see{@link #isSDCardHasEnoughFreeSpace()}
     *
     * @return boolean | true:可用;false:不可用
     */
    public static boolean isSDCardUsable(Context context) {
        return SdcardUtil.hasExternalStoragePermission(context)
                && SdcardUtil.isSDCARDReadyReadWrite()
                && SdcardUtil.isSDCardHasEnoughFreeSpace();
    }

    /**
     * 检查SD卡可用状态,同时提供UI交互
     * 1,SD卡的写权限,see{@link #hasExternalStoragePermission(Context)},Toast文案为{@link #ERROR_NO_PERMISSION}
     * 2,SD卡是否已挂载,see{@link #isSDCARDReadyReadWrite()},Toast文案为{@link #ERROR_NO_SDCARD}
     * 3,SD卡的可用的存储空间是否足够,see{@link #isSDCardHasEnoughFreeSpace()},Toast文案为{@link #ERROR_SDCARD_FREE_SPACE_NOT_ENOUGH}
     *
     * @return boolean | true:可用;false:不可用
     */
    public static String checkSDCardUsableWithToast(Context context) {
        if (!SdcardUtil.hasExternalStoragePermission(context)) {
            return ERROR_NO_PERMISSION;
        }
        if (!SdcardUtil.isSDCARDReadyReadWrite()) {
            return ERROR_NO_SDCARD;
        }
        if (!SdcardUtil.isSDCardHasEnoughFreeSpace()) {
            return ERROR_SDCARD_FREE_SPACE_NOT_ENOUGH;
        }
        return "";
    }

    /**
     * SD卡是否已经挂载
     *
     * @return boolean
     */
    public static boolean isSDCARDReadyReadWrite() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            return true;
        } else
            LogUtil.log("SdcardUtil not Mounted");
        return false;
    }

    /**
     * 检查SD卡的剩余容量是否大于10MB
     *
     * @return boolean | true:大于10MB;false:小于10MB
     */
    public static boolean isSDCardHasEnoughFreeSpace() {
        File file = Environment.getExternalStorageDirectory();
        if (file == null) {
            LogUtil.log("SdcardUtil no Storage");
            return false;
        }
        long availableSizeInBytes = file.getFreeSpace();
        long megAvailable = availableSizeInBytes / 1048576;
        LogUtil.log("SdcardUtil isSDCardHasEnoughFreeSpace=" + megAvailable + "MB");

        if (megAvailable < 5) {
            return false;
        } else {
            return true;
        }
    }


    public static boolean hasExternalStoragePermission(Context context) {
        if(context==null){
            return false;
        }
        boolean result = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION) == PackageManager.PERMISSION_GRANTED;
        if (!result) {
            LogUtil.log("SdcardUtil no permission");
        }
        return result;
    }

}
