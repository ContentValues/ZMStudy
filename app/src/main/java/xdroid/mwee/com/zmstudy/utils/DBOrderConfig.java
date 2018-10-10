package xdroid.mwee.com.zmstudy.utils;

import android.text.TextUtils;

/**
 * Created by virgil on 16/7/19.
 * ---金额计算选项---
 */
public class DBOrderConfig {
    /**
     * 销售单号规则,0=日期+四位数顺序递增/1=日期+小时+分钟
     */
    public final static String ORDER_ID_TYPE = "023";

    public final static String ROUND_QUANTITY = "113";//'数量」圆整小数位数'
    public final static String ROUND_SINGLE_PRICE = "114";//'「单价」圆整位数到','0=元/ 1=角/ 2=分'
    public final static String ROUND_SUB_TOTAL = "115";// '「金额小计」圆整位数到','0=元/ 1=角/ 2=分'
    public final static String ROUND_TOTAL = "111";//  '「总额」圆整位数计算到','0=元 / 1=角 / 2=分'
    public final static String ROUND_COUNT = "112";//  '小数位处理方式','1=四舍五入/ 2=舍去 / 3=进位'
    public final static String ROUND_ORDER_TYPE = "110";//  '帐单圆整方式','1=按总额 / 2=按小计'

    /**
     * '收取服务费方式','1=不收服务费 / 2=按菜品销售金额比例 / 3=按桌号设定'
     * fiInt1,服务费比例
     */
    public final static String SERVICE_GET_WAY = "118";
    public final static String OPERATION_NEXT_STEP = "119";//  '点菜下单、查单:退菜、催单、起菜、转菜 完毕后做','1=登出回登入界面/ 2=回桌况管理'
    public final static String INVALID_ITEM_ORDER_TYPE = "120";//  '限制沽清菜品不能下单','0=false / 1=ture'
    public final static String TABLE_NAME_SET_TYPE = "121";//  '自动牌号或手输牌号','1=自动牌号(订单号后4位流水) /2=手输牌号'
    public final static String ORDER_CASH = "124";//  '快速结账-现金按钮','0=不使用/1=使用'
    public final static String ORDER_SCAR = "125";//  '快速结账-扫码支付按钮' '0=不使用/1=使用'
    public final static String CLEAR_TABLE_TYPE = "126";//  '自动清台','0=false / 1=ture'
    public final static String DIALOG_WINDOW = "127";//  '在点餐界面中点击桌台总是显示浮动功能窗' '0=false / 1=ture'

    public static final String STATEMENT_PRINT_COUNT = "121";  //结账单打印份数

    public static final String AUTOMATIC_ORDER = "134"; // 预点单自动下单厨房 0=false/1=ture

    public static final String MERGE_MENUITEM_EQUALS = "144";//相同菜品是否自动合并为一笔 0／false不合并    1/true合并 默认为不合并

}
