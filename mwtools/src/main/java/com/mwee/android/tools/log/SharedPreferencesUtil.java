package com.mwee.android.tools.log;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

/**
 * 缓存工具类
 *
 * @author longtao.li
 */
class SharedPreferencesUtil {
    // 缓存文件名
    private static final String PREFERENCE_FILE_NAME = "mwtools";


    /**
     * 读取一个对象
     */
    public static String readStrng(Context context, String key) {
        if (context == null) {
            return null;
        }
        SharedPreferences pre = context.getSharedPreferences(
                PREFERENCE_FILE_NAME, 0);
        String json = pre.getString(key, "");
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        return json;
    }

    /**
     * 读取一个对象
     */
    public static int readInt(Context context, String key) {
        if (context == null) {
            return -1;
        }
        SharedPreferences pre = context.getSharedPreferences(
                PREFERENCE_FILE_NAME, 0);
        int json = pre.getInt(key, 0);
        return json;
    }

    /**
     * 存储一个对象
     */
    public static void saveString(Context context, String key, String obj) {
        if (context == null) {
            return;
        }
        SharedPreferences spf = context.getSharedPreferences(
                PREFERENCE_FILE_NAME, 0);
        Editor editor = spf.edit();
        editor.putString(key, obj);
        editor.apply();
    }

    /**
     * 存储一个对象
     */
    public static void saveInt(Context context, String key, int obj) {
        if (context == null) {
            return;
        }
        SharedPreferences spf = context.getSharedPreferences(
                PREFERENCE_FILE_NAME, 0);
        Editor editor = spf.edit();
        editor.putInt(key, obj);
        editor.apply();
    }

    /**
     * 清除某个key对应的缓存
     */
    public static void clearByKey(String key, Context context) {
        if (context == null) {
            return;
        }
        SharedPreferences spf = context.getSharedPreferences(
                PREFERENCE_FILE_NAME, 0);
        Editor editor = spf.edit();
        editor.remove(key);
        editor.apply();
    }


}
