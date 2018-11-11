package xdroid.mwee.com.zmstudy.model.bean;


import com.mwee.android.tools.base.BusinessBean;

/**
 * Created by zhangmin on 2018/5/24.
 */


public class KBTempDataBean extends BusinessBean {

    /**
     * 堂食，外卖，外带
     */
    public String fsDinnerType;

    @Deprecated
    public String fsNO;

    public String fsOrderId;
    public String fsPayAmount;
    public String fsStatus;
    public String fsTableNO;
    public String fsTableTime;
    public String fsUserMobile;
    public String fsCreateTime;
    public String fsMerchantId;
    public String fsShopGUID;
    public String fsUpdateTime;
    //美味订单号
    public String fsSellNo;

    /**
     * 业务类型： DINNER-正餐、SNACK-快餐
     */
    public String fsBusinessType;
    /**
     * PLATFORM——线上点，SCAN——扫码点
     */
    public String fsOrderStyle;
    /**
     * 取餐类型：TABLE-桌号，NO-取餐号，NO_BOOK-取餐号（预约），TABLE_BOOK-预约桌号
     */
    public String fsTakeStyle;
    /**
     * 就餐人数
     */
    public String fiPeopleNum;


    /**
     * 支付状态
     * APPLY_REFUND   -用户申请退款
     * REJECT_REFUND  -商家拒绝退款
     * ACCEPT_REFUND  -商家接受退款
     * REFUNDED       -商家同意退款
     */
    public String fsPayStatus;

    public KBTempDataBean() {

    }


}
