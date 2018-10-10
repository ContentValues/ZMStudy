package com.mwee.android.tools;

import java.util.Random;
import java.util.UUID;

/**
 * Created by liuxiuxiu on 2018/3/5.
 */

public class UUIDUtil {

    /**
     * 为降低UUID重复概率，我们在原有UUID基础上增加十二位的时间戳再加一位随机数
     *--时间戳我们只去年份的后两位
     * @return
     */
    public static String optUUID() {
        return UUID.randomUUID().toString() + //UUID--36位
                DateUtil.getCurrentDateTime("yyMMddHHmmss") + //时间戳--12位
                (new Random()).nextInt(10); //一位随机数-- 一位
    }
}
