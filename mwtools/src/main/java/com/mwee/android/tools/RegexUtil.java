package com.mwee.android.tools;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则的Util
 * Created by virgil on 2017/11/13.
 *
 * @author virgil
 */

public class RegexUtil {
    /**
     * 菜品名称的正则校验，允许：字母、数字、中文、各种全角符号、"._-,"、"()"
     */
    private static Pattern patternName = Pattern.compile("^[a-zA-Z0-9\\u4e00-\\u9fa5._,()\\-\\uFF00-\\uFFFF]*$", Pattern.CASE_INSENSITIVE);
    /**
     * 输入内容校验，允许更多的符号
     */
    private static Pattern patternContent = Pattern.compile("^[a-zA-Z0-9\\s\\u4e00-\\u9fa5._,%@*!&/$()\\-\\uFF00-\\uFFFF]*$", Pattern.CASE_INSENSITIVE);

    /**
     * 校验是否是纯数字
     */
    private static Pattern patternNumber = Pattern.compile("^[0-9]*$", Pattern.CASE_INSENSITIVE);

    /**
     * 匹配名称
     *
     * @param inputStr String
     * @return boolean | true:没有问题；false：不符合规则
     */
    public static boolean checkName(String inputStr) {
        //过滤null字符
        if ("null".equalsIgnoreCase(inputStr)) {
            return false;
        }
        Matcher m = patternName.matcher(inputStr.trim());
        return m.matches();
    }

    /**
     * 匹配内容
     *
     * @param inputStr String
     * @return boolean | true:没有问题；false：不符合规则
     */
    public static boolean checkContent(String inputStr) {
        //过滤null字符
        if ("null".equalsIgnoreCase(inputStr)) {
            return false;
        }
        Matcher m = patternContent.matcher(inputStr.trim());
        return m.matches();
    }

    /**
     * 判断是否为金额
     *
     * @return true 是，false 否
     */
    public static boolean isReallyMoney(String money) {
        return money.matches("^(([1-9]\\d{0,5})|0)(\\.\\d{0,2})?$");
    }

    /**
     * 校验是否是纯数字
     *
     * @param paranStr String
     * @return boolean
     */
    public static boolean checkNumber(String paranStr) {
        Matcher m = patternNumber.matcher(paranStr.trim());
        return m.matches();
    }

    /**
     * 格式化数字长度
     *
     * @param paramStr
     * @param count
     * @return
     */
    public static String formatNumber(int paramStr, int count) {
        return String.format(Locale.SIMPLIFIED_CHINESE, "%0" + count + "d", paramStr);
    }

    public static Matcher buildMatcher(String content, String regEx) {
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(content);
        return m;
    }
}
