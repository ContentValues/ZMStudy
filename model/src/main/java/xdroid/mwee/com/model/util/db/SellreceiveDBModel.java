package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbSellReceive")
public class SellreceiveDBModel extends DBModel {
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime = "";
    @ColumnInf(name = "fsCreateUserName")
    public String fsCreateUserName = "";
    @ColumnInf(name = "fsPaymentId", primaryKey = true)
    public String fspaymentid = "";
    /**
     * 支付组ID
     */
    @ColumnInf(name = "fsPaymentTypeId", primaryKey = true)
    public String fsPaymentTypeId = "";

    @ColumnInf(name = "fsCreateUserId")
    public String fsCreateUserId = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fiIsCalcPaid")
    public int fiIsCalcPaid = 0;
    @ColumnInf(name = "fsSellDate")
    public String fsSellDate = "";
    @ColumnInf(name = "fdForeignMoney")
    public BigDecimal fdForeignMoney = BigDecimal.ZERO;
    @ColumnInf(name = "fdReceMoney")
    public BigDecimal fdReceMoney = BigDecimal.ZERO;
    @ColumnInf(name = "fsMiscno")
    public String fsMiscno = "";
    @ColumnInf(name = "fsHostId")
    public String fsHostId = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fiSeq", primaryKey = true)
    public int fiseq = 0;

    @ColumnInf(name = "fdExchangeRate")
    public BigDecimal fdExchangeRate = BigDecimal.ZERO;
    @ColumnInf(name = "fiIsCalcInvoice")
    public int fiIsCalcInvoice = 0;
    @ColumnInf(name = "fiindex")
    public long fiindex = 0;
    @ColumnInf(name = "fsSellNo", primaryKey = true)
    public String fssellno = "";
    @ColumnInf(name = "fiPaymentType", primaryKey = true)
    public int fipaymenttype = 0;
    @ColumnInf(name = "fsCheckBillNo", primaryKey = true)
    public String fscheckbillno = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsshopguid = "";
    @ColumnInf(name = "fdPayMoney")
    public BigDecimal fdPayMoney = BigDecimal.ZERO;
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";
    @ColumnInf(name = "lver")
    public int lver = 0;
    @ColumnInf(name = "pver")
    public int pver = 0;

    @ColumnInf(name = "fspaymenttypename")
    public String fspaymenttypename = "";

    @ColumnInf(name = "fspaymentname")
    public String fspaymentname = "";

    /**
     * 会员卡号
     */
    @ColumnInf(name = "fsbackup0")
    public String fsbackup0 = "";
    /**
     * 会员消费订单号
     */
    @ColumnInf(name = "fsbackup1")
    public String fsbackup1 = "";
    /**
     * 会员积分抵扣
     */
    @ColumnInf(name = "fsbackup2")
    public String fsbackup2 = "";
    /**
     * 是否秒付：1，是秒付；其他，不是秒付
     */
    @ColumnInf(name = "isRapidPay")
    public int isRapidPay = 0;
    /**
     * 是否满减：1，是；其他，不是
     */
    @ColumnInf(name = "isCouponMoney")
    public int isCouponMoney = 0;
    /**
     * '第三方属性，位操作：1，押金; 2: 预付金'
     */
    @ColumnInf(name = "fiThirdType")
    public int fiThirdType = 0;
    /**
     * 关联支付方式序号
     */
    @ColumnInf(name = "fiSeq_M")
    public int fiSeq_M = -1;
    /**
     * 仅用于可折扣菜品
     */
    @ColumnInf(name = "fiIsPartAmtDiscount")
    public int fiIsPartAmtDiscount = 0;
    /**
     * 挂账账户名称
     */
    @ColumnInf(name = "fsCreditAccountName")
    public String fsCreditAccountName = "";
    /**
     * 挂账账户ID
     */
    @ColumnInf(name = "fsCreditAccountId")
    public String fsCreditAccountId = "";
    /**
     * 第三方订单号，例如：1,秒付订单号2,扫码订单号3,会员储值订单号
     */
    @ColumnInf(name = "fsThirdOrder")
    public String fsThirdOrder = "";
    /**
     * 第三方账户名称，打印时需要做敏感处理，例如：1，会员名称2，扫码支付账户昵称
     */
    @ColumnInf(name = "fsThirdAccountName")
    public String fsThirdAccountName = "";
    /**
     * 支付来源：1，秒付；2，整单立减；3，满减；4，外卖
     */
    @ColumnInf(name = "fiSourceType")
    public int fiSourceType = 0;
    /**
     * 会员卡号
     */
    @ColumnInf(name = "fsMemCardNo")
    public String fsMemCardNo = "";
    /**
     * 券号
     */
    @ColumnInf(name = "fsTicketCode")
    public String fsTicketCode = "";


    public SellreceiveDBModel() {

    }

    @Override
    public SellreceiveDBModel clone() {
        SellreceiveDBModel cloneObj = null;
        try {
            cloneObj = (SellreceiveDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

    @Override
    public String toString() {
        return "SellreceiveDBModel{" +
                "fsCreateTime='" + fsCreateTime + '\'' +
                ", fsCreateUserName='" + fsCreateUserName + '\'' +
                ", fsPaymentId='" + fspaymentid + '\'' +
                ", fsCreateUserId='" + fsCreateUserId + '\'' +
                ", fsUpdateUserName='" + fsUpdateUserName + '\'' +
                ", fiStatus=" + fiStatus +
                ", fiIsCalcPaid=" + fiIsCalcPaid +
                ", fdForeignMoney=" + fdForeignMoney +
                ", fdReceMoney=" + fdReceMoney +
                ", fsMiscno='" + fsMiscno + '\'' +
                ", fsHostId='" + fsHostId + '\'' +
                ", fsUpdateTime='" + fsUpdateTime + '\'' +
                ", fiSeq=" + fiseq +
                ", fdExchangeRate=" + fdExchangeRate +
                ", fiIsCalcInvoice=" + fiIsCalcInvoice +
                ", fiindex=" + fiindex +
                ", fsSellNo='" + fssellno + '\'' +
                ", fiPaymentType=" + fipaymenttype +
                ", fsCheckBillNo='" + fscheckbillno + '\'' +
                ", fsShopGUID='" + fsshopguid + '\'' +
                ", fdPayMoney=" + fdPayMoney +
                ", fsUpdateUserId='" + fsUpdateUserId + '\'' +
                ", fsNote='" + fsNote + '\'' +
                ", lver=" + lver +
                ", pver=" + pver +
                '}';
    }
}