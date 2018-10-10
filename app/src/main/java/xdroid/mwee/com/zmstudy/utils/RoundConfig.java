package xdroid.mwee.com.zmstudy.utils;
import com.mwee.android.tools.StringUtil;

import java.math.RoundingMode;

/**
 * RoundConfig
 * Created by virgil on 16/7/25.
 */
public class RoundConfig {
    public static int ROUND_QUANTITY = 1;//'数量」圆整小数位数'
    public static int ROUND_QUANTITY_2 = 2;//'数量」圆整小数位数' 保留两位小数
    public static int ROUND_QUANTITY_3 = 3;//'数量」圆整小数位数' 保留三位小数
    public static int ROUND_SINGLE_PRICE = 2;//'「单价」圆整位数到','0=元/ 1=角/ 2=分'
    public static int ROUND_SUB_TOTAL = 2;// '「金额小计」圆整位数到','0=元/ 1=角/ 2=分'
    public static int ROUND_TOTAL = 2;//  '「总额」圆整位数计算到','0=元 / 1=角 / 2=分'
    public static RoundingMode Decimal_ROUND_Type = RoundingMode.HALF_UP;//  '小数位处理方式','1=四舍五入/ 2=舍去 / 3=进位'
    private static int ROUND_ORDER_TYPE = 1;//  '帐单圆整方式','1=按总额 / 2=按小计'

    public static void init(String shopID) {
//        ROUND_QUANTITY = StringUtil.toInt(CommonDBUtil.getConfig(DBOrderConfig.ROUND_QUANTITY, shopID), 2);
//        ROUND_SINGLE_PRICE = StringUtil.toInt(CommonDBUtil.getConfig(DBOrderConfig.ROUND_SINGLE_PRICE, shopID), 2);
        ROUND_SUB_TOTAL = StringUtil.toInt(CommonDBUtil.getConfig(DBOrderConfig.ROUND_SUB_TOTAL, shopID), 2);
        ROUND_TOTAL = StringUtil.toInt(CommonDBUtil.getConfig(DBOrderConfig.ROUND_TOTAL, shopID), 2);
        int roundType = StringUtil.toInt(CommonDBUtil.getConfig(DBOrderConfig.ROUND_COUNT, shopID), 2);
        switch (roundType) {
            case 1:
                Decimal_ROUND_Type = RoundingMode.HALF_UP;
                break;
            case 2:
                Decimal_ROUND_Type = RoundingMode.FLOOR;
                break;
            case 3:
                Decimal_ROUND_Type = RoundingMode.CEILING;
                break;
        }
        int order_roundType = StringUtil.toInt(CommonDBUtil.getConfig(DBOrderConfig.ROUND_ORDER_TYPE, shopID), 1);

        ROUND_TOTAL = order_roundType == 2 ? ROUND_SUB_TOTAL : ROUND_TOTAL;
    }
}
