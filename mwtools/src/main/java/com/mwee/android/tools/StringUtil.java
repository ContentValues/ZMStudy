package com.mwee.android.tools;

import android.text.TextUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

/**
 * String 工具类
 */
public class StringUtil {

    public static final String CHARSET_UTF8 = "utf-8";
    public static final String CHARSET_GB2312 = "GB2312";

    public static String bytes2String(byte[] inBytes) {
        return bytes2String(inBytes, CHARSET_GB2312);
    }

    public static String bytes2String(byte[] inBytes, String charset) {
        String output = null;
        if (inBytes != null && inBytes.length > 0) {
            try {
                output = new String(inBytes, charset);
            } catch (UnsupportedEncodingException e) {
                output = null;
            }
        }
        return output;
    }

    public static byte[] String2bytes(String inString) {
        byte[] output;
        try {
            output = inString.getBytes(CHARSET_UTF8);
        } catch (UnsupportedEncodingException e) {
            output = null;
        }
        return output;
    }

    /**
     * String转为int，如果异常， 则返回-1
     *
     * @param str String|待转换为int的字符串
     * @return int|转换后的value
     */
    public static int toInt(String str) {
        return toInt(str, -1);
    }

    /**
     * String转为int，如果异常， 则返回{@code #defaultValue}
     *
     * @param str          String|待转换为int的字符串
     * @param defaultValue int|如果出现异常，则返回该字段
     * @return int|转换后应该返回value
     */
    public static int toInt(String str, int defaultValue) {
        int value = defaultValue;
        if (!TextUtils.isEmpty(str)) {
            try {
                value = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                value = defaultValue;
                LogUtil.logError("originvalue=" + str, e);
            }
        }
        return value;
    }

    /**
     * String转为float
     *
     * @param str          String
     * @param defaultValue float
     * @return float
     */
    public static float toFloat(String str, float defaultValue) {
        float value = defaultValue;
        if (!TextUtils.isEmpty(str)) {
            try {
                value = Float.parseFloat(str);
            } catch (NumberFormatException e) {
                value = defaultValue;
                LogUtil.logError("originvalue=" + str, e);
            }
        }
        return value;
    }

    /**
     * String转为long
     *
     * @param str          String
     * @param defaultValue long
     * @return long
     */
    public static long toLong(String str, long defaultValue) {
        long value = defaultValue;
        if (!TextUtils.isEmpty(str)) {
            try {
                value = Long.parseLong(str);
            } catch (NumberFormatException e) {
                value = defaultValue;
                LogUtil.logError("originvalue=" + str, e);
            }
        }
        return value;
    }

    public static String subString(String content, int length) {
        return content.length() < length ? content : content.substring(0, length);
    }

    /**
     * 获取异常信息
     *
     * @param e Exception
     * @return String
     */
    public static String getExceptionInfo(Throwable e) {
        if(e==null){
            return null;
        }
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            //将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }

    /**
     * 从 Unicode 形式的字符串转换成对应的编码的特殊字符串。 如 "\u9EC4" to "黄".
     * Converts encoded \\uxxxx to unicode chars
     * and changes special saved chars to their original forms
     *
     * @param in  Unicode编码的字符数组。
     * @param off 转换的起始偏移量。
     * @param len 转换的字符长度。
     * @return 完成转换，返回编码前的特殊字符串。
     */
    public static String fromEncodedUnicode(char[] in, int off, int len) {
        char aChar;
        char[] out = new char[len]; // 只短不长
        int outLen = 0;
        int end = off + len;

        while (off < end) {
            aChar = in[off++];
            if (aChar == '\\') {
                aChar = in[off++];
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = in[off++];
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    out[outLen++] = (char) value;
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    out[outLen++] = aChar;
                }
            } else {
                out[outLen++] = (char) aChar;
            }
        }
        return new String(out, 0, outLen);
    }


    /**
     * info是空或者0
     *
     * @param info String
     * @return boolean | true:为空或者0;false,不为0的字符串
     */
    public static boolean emptyInt(String info) {
        return TextUtils.isEmpty(info) || TextUtils.equals("0", info);
    }

    public static boolean isMsgWeb(String msg) {
        return msg.contains("<!DOCTYPE html>") || msg.contains("<div") || msg.contains("<br") || msg.contains("<b");
    }

}
