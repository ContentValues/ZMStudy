package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

/**
 * Created by lxx on 17/1/22.
 */
@TableInf(name = "tbwechatorder")
public class WechatOrderDBModel extends DBModel {
    @ColumnInf(name = "fsorderno", primaryKey = true)
    public String fsorderno = "";   // '订单编号',
    @ColumnInf(name = "fsopenid")
    public String fsopenid = "";   // '微信用户id',
    @ColumnInf(name = "fscompanyguid")
    public String fscompanyguid = "";   // '公司GUID',
    @ColumnInf(name = "fsshopguid")
    public String fsshopguid = "";   // '门店id',
    @ColumnInf(name = "fsshopname")
    public String fsshopname = "";   // '门店名称',
    @ColumnInf(name = "fisex")
    public int fisex = 0;   // '收货人性别',
    @ColumnInf(name = "fsname")
    public String fsname = "";   // '收货人名称',
    @ColumnInf(name = "fitag")
    public int fitag = 0;   // '标签',
    @ColumnInf(name = "fsaddress")
    public String fsaddress = "";   // '收货地址',
    @ColumnInf(name = "fsmobile")
    public String fsmobile = "";   // '手机号',
    @ColumnInf(name = "fidishwarenum")
    public int fidishwarenum = 0;   // '餐具数量',
    @ColumnInf(name = "fiinvoice")
    public int fiinvoice = 0;   // '是否需要发票',
    @ColumnInf(name = "fiinvoicetype")
    public int fiinvoicetype = 0;   // '发票类型',
    @ColumnInf(name = "fsinvoicename")
    public String fsinvoicename = "";   // '发票抬头名称',
    @ColumnInf(name = "fdcoupon")
    public BigDecimal fdcoupon = BigDecimal.ZERO;   // '优惠券抵扣',
    @ColumnInf(name = "fdintegral")
    public BigDecimal fdintegral = BigDecimal.ZERO;   // '积分抵扣',
    @ColumnInf(name = "fddistribution")
    public BigDecimal fddistribution = BigDecimal.ZERO;   // '配送费',
    @ColumnInf(name = "fdboxamount")
    public BigDecimal fdboxamount = BigDecimal.ZERO;   // '餐盒费',
    @ColumnInf(name = "fsarrivetime")
    public String fsarrivetime = "";   // '送达时间',
    @ColumnInf(name = "fspaytype")
    public String fspaytype = "";   // '支付方式{1：微信支付 2：会员储值支付}',
    @ColumnInf(name = "fdsellamount")
    public BigDecimal fdsellamount = BigDecimal.ZERO;   // '销售金额',
    @ColumnInf(name = "fdrealamount")
    public BigDecimal fdrealamount = BigDecimal.ZERO;   // '实际金额',
    @ColumnInf(name = "fistatus")
    public int fistatus = 0;   //'订单状态{0待接单，1：接单，2：完成，3：取消，4：商家取消，5退款中，6：退款成功 ，7：退款失败，11：待支付，12：支付失败}',
    @ColumnInf(name = "fsremark")
    public String fsremark = "";   // '备注',
    @ColumnInf(name = "fsupdatetime")
    public String fsupdatetime = "";   // '修改时间',
    @ColumnInf(name = "fscreatetime")
    public String fscreatetime = "";   // '创建时间',
    @ColumnInf(name = "localOrderId")
    public String localOrderId = "";   // 本地订单号
    @ColumnInf(name = "fssettlement")
    public String fssettlement = "";   // 拉订单标志
}
