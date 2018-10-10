package com.mwee.android.tools;

import android.text.TextUtils;

/**
 * 格式化工具类
 * Created by virgil on 16/8/23.
 */
public class FormatUtil {
    /**
     * 格式化会员订单号,只保留后5位
     *
     * @param cardNo String
     * @return String
     */
    public static String formatMemberCardNo(String cardNo) {
        if(TextUtils.isEmpty(cardNo)){
            return "";
        }
        StringBuilder sb = new StringBuilder(cardNo);
        int legth = sb.length() - (sb.length() > 5 ? 5 : 2);
        StringBuilder sym = new StringBuilder();
        for (int i = 0; i < legth; i++) {
            sym.append("*");
        }
        sb.replace(0, legth, sym.toString());
        return sb.toString();
    }

}
