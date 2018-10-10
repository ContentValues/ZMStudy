package xdroid.mwee.com.zmstudy.utils;


import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Calc
 * Created by virgil on 16/7/15.
 */
public class Calc {

    /**
     * 格式化,精确到小数点位数
     *
     * @param originValue BigDecimal
     * @param floatCount  int | 小数点位数
     * @return BigDecimal
     */
    public static BigDecimal format(BigDecimal originValue, int floatCount) {
        return originValue.setScale(floatCount, RoundConfig.Decimal_ROUND_Type);
    }

    /**
     * 指定四舍五入的形式格式化
     *
     * @param originValue BigDecimal
     * @param floatCount  int | 小数点位数
     * @param roundInfo   int
     * @return BigDecimal
     */
    public static BigDecimal format(BigDecimal originValue, int floatCount, RoundingMode roundInfo) {
        return originValue.setScale(floatCount, roundInfo);
    }

    /**
     * 格式化显示,默认显示两位小数
     *
     * @param originValue BigDecimal
     * @return String
     */
    public static String formatShow(BigDecimal originValue) {
        return formatShow(originValue, 2, RoundConfig.Decimal_ROUND_Type);
    }

    /**
     * 格式化显示,指定精确的位数
     *
     * @param originValue BigDecimal
     * @param floatCount  int
     * @return String
     */
    public static String formatShow(BigDecimal originValue, int floatCount) {
        return formatShow(originValue, floatCount, RoundConfig.Decimal_ROUND_Type);
    }

    /**
     * 格式化显示,指定位数和四舍五入的形式
     *
     * @param originValue BigDecimal
     * @param floatCount  int
     * @param roundInfo   int
     * @return String
     */
    public static String formatShow(BigDecimal originValue, int floatCount, RoundingMode roundInfo) {
        return originValue.setScale(floatCount, roundInfo).toPlainString();
    }


}
