package com.mwee.android.tools;

import android.text.TextUtils;

import com.mwee.android.tools.LogUtil;

import java.math.BigDecimal;

/**
 * 数字处理工具类
 *
 * @author chris
 */
public class NumberUtils {

    /**
     * 对数字进行四舍五入取两位
     *
     * @param numberStr
     * @return
     */
    public static float roundHalfUp(String numberStr) {
        if (TextUtils.isEmpty(numberStr)) {
            return 0;
        }
        BigDecimal bigDecimal = new BigDecimal(numberStr);
        bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.floatValue();
    }

    /**
     * 对数字进行四舍五入取两位
     *
     * @param number
     * @return
     */
    public static float round(float number, int scale, int roundingMode) {
        BigDecimal bigDecimal = new BigDecimal(Float.toString(number));
        return bigDecimal.setScale(scale, roundingMode).floatValue();
    }

    public static float parseStrToFloat(String numberStr) {
        if (!TextUtils.isEmpty(numberStr)) {
            try {
                BigDecimal bigDecimal = new BigDecimal(numberStr);
                return bigDecimal.floatValue();
            } catch (Exception e) {
                LogUtil.logError(e);
            }
        }
        return 0f;
    }


    public static double parseStrToDouble(String numberStr) {
        if (!TextUtils.isEmpty(numberStr)) {
            BigDecimal bigDecimal = new BigDecimal(numberStr);
            return bigDecimal.doubleValue();
        }
        return 0d;
    }

    public static String valueIntShow(float value) {
        Float aFloat = new Float(value);
        int valueInt = aFloat.intValue();
        return String.valueOf(value == valueInt ? valueInt : value);
    }

    public static String showBigDecimal(BigDecimal val) {
        String show;
        if (val.intValue() == val.doubleValue()) {
            show = String.valueOf(val.intValue());
        } else {
            show = String.valueOf(val.doubleValue());
        }
        return show;
    }

    public static String flashBenefitsPayMoneyShow(float value, String preStr) {
        StringBuilder str = new StringBuilder();
        if (!TextUtils.isEmpty(preStr)) {
            str.append(preStr);
        }
        str.append(valueIntShow(value));
        return str.toString();
    }

    public static int getStrAfterPointLength(String str) {
        int pointIndex = str.indexOf(".");
        if (pointIndex != -1 && pointIndex != str.length() - 1) {
            return str.substring(pointIndex + 1, str.length()).length();
        }
        return 0;
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    public static String subZeroAndDot(BigDecimal s) {
        return subZeroAndDot(s.toPlainString());
    }

}
