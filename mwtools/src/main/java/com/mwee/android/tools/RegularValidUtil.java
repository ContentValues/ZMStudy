package com.mwee.android.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式验证类
 *
 * @Author chris
 */
public class RegularValidUtil {

    /**
     * 公用的验证方法
     *
     * @param regular 正则表达式
     * @param str     需验证的字符串
     * @return
     */
    public static boolean commonValid(String regular, String str) {
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 验证数字的方法
     *
     * @param str
     * @return
     */
    public static boolean validNumber(String str) {
        return commonValid("^[0-9]*$", str);
    }

    /**
     * 验证手机号码的方法
     * @param str
     * @return
     */
    public static boolean validPhoneNumber(String str) {
        return commonValid("[1][358]\\d{9}", str);
    }

}
