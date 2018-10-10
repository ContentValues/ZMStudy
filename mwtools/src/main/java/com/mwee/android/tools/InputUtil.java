package com.mwee.android.tools;

import android.content.Context;

import java.io.InputStream;

/**
 * Created by virgil on 2017/6/22.
 */

public class InputUtil {

    /**
     * 读取Assets的文件
     *
     * @param context  Context
     * @param fileName String
     * @return String | 读取的内容
     */
    public static String readFromAssets(Context context, String fileName) {
        String result = "";
        try {
            InputStream in = context.getAssets().open(fileName);
            //获取文件的字节数
            int lenght = in.available();
            //创建byte数组
            byte[] buffer = new byte[lenght];
            //将文件中的数据读到byte数组中
            int readLength = in.read(buffer);
            if (readLength <= 5) {
                return "";
            }
            result = new String(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
