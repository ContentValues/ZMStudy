package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbSell")
public class SellDBModel extends DBModel {
    @ColumnInf(name = "fsCreateUserName")
    public String fsCreateUserName;
    @ColumnInf(name = "fiCustSum")
    public int fiCustSum;
    @ColumnInf(name = "fdFreeSveAmt")
    public BigDecimal fdFreeSveAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fsInvoiceCls")
    public String fsInvoiceCls;
    @ColumnInf(name = "fsMealNumber")
    public String fsMealNumber;
    @ColumnInf(name = "fsDiscountName")
    public String fsDiscountName;
    @ColumnInf(name = "fiReCheckTag")
    public int fiReCheckTag;
    @ColumnInf(name = "fsMTableName")
    public String fsMTableName;
    @ColumnInf(name = "fsVerifyTime")
    public String fsVerifyTime;
    @ColumnInf(name = "fiPrintTimes_Exp")
    public int fiPrintTimes_Exp;
    @ColumnInf(name = "fdInvoiceAmt")
    public BigDecimal fdInvoiceAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fdServiceAmt")
    public BigDecimal fdServiceAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fsMSectionId")
    public String fsMSectionId;
    @ColumnInf(name = "fsPutUpTime")
    public String fsPutUpTime;
    @ColumnInf(name = "fsCreditAccountId")
    public String fsCreditAccountId;
    @ColumnInf(name = "fsFreeSveAmtUserId")
    public String fsFreeSveAmtUserId;
    @ColumnInf(name = "fsSellDate")
    public String fsSellDate;
    @ColumnInf(name = "fiBillStatus")
    public int fiBillStatus;
    @ColumnInf(name = "fiBillSource")
    public int fiBillSource;
    @ColumnInf(name = "fiPrintTimes_Op")
    public int fiPrintTimes_Op;
    @ColumnInf(name = "fsVerifyUserName")
    public String fsVerifyUserName;
    @ColumnInf(name = "fiModifyTag")
    public int fiModifyTag;
    @ColumnInf(name = "fdDebtAmt")
    public BigDecimal fdDebtAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fdDiffAmt")
    public BigDecimal fdDiffAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fdGiftAmt")
    public BigDecimal fdGiftAmt = BigDecimal.ZERO;

    @ColumnInf(name = "fdGiftQty")
    public BigDecimal fdGiftQty = BigDecimal.ZERO;
    @ColumnInf(name = "fdGiftRoundAmt")
    public BigDecimal fdGiftRoundAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fsInvoiceTitle")
    public String fsInvoiceTitle;
    @ColumnInf(name = "fiIsVIP")
    public int fiIsVIP;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime;
    @ColumnInf(name = "fsVIPSource")
    public String fsVIPSource;
    @ColumnInf(name = "fsSellNo", primaryKey = true)
    public String fssellno;
    @ColumnInf(name = "fsSendOnTime")
    public String fsSendOnTime;
    @ColumnInf(name = "fsSendUserName")
    public String fsSendUserName;
    @ColumnInf(name = "fsPrintExpTime")
    public String fsPrintExpTime;
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsshopguid;
    @ColumnInf(name = "fsMAreaId")
    public String fsMAreaId;
    @ColumnInf(name = "fsCreditAccountName")
    public String fsCreditAccountName;
    @ColumnInf(name = "fdRealAmt")
    public BigDecimal fdRealAmt = BigDecimal.ZERO;

    @ColumnInf(name = "fdServiceRoundAmt")
    public BigDecimal fdServiceRoundAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fdSaleRoundAmt")
    public BigDecimal fdSaleRoundAmt = BigDecimal.ZERO;

    @ColumnInf(name = "fsCardNo")
    public String fsCardNo;
    @ColumnInf(name = "fsStaffId")
    public String fsStaffId;
    @ColumnInf(name = "fsReCheckReason")
    public String fsReCheckReason;
    @ColumnInf(name = "fsNote")
    public String fsNote;
    @ColumnInf(name = "fsReCheckUserName")
    public String fsReCheckUserName;
    @ColumnInf(name = "fiIsVerify")
    public int fiIsVerify;
    @ColumnInf(name = "fsCreateUserId")
    public String fsCreateUserId;
    @ColumnInf(name = "fiBillKind")
    public int fiBillKind;
    @ColumnInf(name = "fsCustMobile")
    public String fsCustMobile;
    @ColumnInf(name = "fsCustName")
    public String fsCustName;
    @ColumnInf(name = "fiIsInvoice")
    public int fiIsInvoice;
    @ColumnInf(name = "fdDiscountAmt")
    public BigDecimal fdDiscountAmt = BigDecimal.ZERO;

    @ColumnInf(name = "fdDiscountRoundAmt")
    public BigDecimal fdDiscountRoundAmt = BigDecimal.ZERO;


    @ColumnInf(name = "fiindex")
    public long fiindex;
    @ColumnInf(name = "fdSaleAmt")
    public BigDecimal fdSaleAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fsAssignTime")
    public String fsAssignTime;
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime;
    @ColumnInf(name = "fsSellTime")
    public String fsSellTime;
    @ColumnInf(name = "fsFreeSveAmtUserName")
    public String fsFreeSveAmtUserName;
    @ColumnInf(name = "fsCheckEndTime")
    public String fsCheckEndTime;
    @ColumnInf(name = "fsMasterSellNo")
    public String fsMasterSellNo;
    @ColumnInf(name = "fsBillSourceId")
    public String fsBillSourceId;
    @ColumnInf(name = "fsBillSourceName")
    public String fsBillSourceName;
    @ColumnInf(name = "fsBillSourceNo")
    public String fsBillSourceNo;
    @ColumnInf(name = "fsRelationMTable")
    public String fsRelationMTable;
    @ColumnInf(name = "fsHostId")
    public String fsHostId;
    @ColumnInf(name = "fsSendUserId")
    public String fsSendUserId;
    @ColumnInf(name = "fsDiscountId")
    public String fsDiscountId;
    @ColumnInf(name = "fsCustId")
    public String fsCustId;
    @ColumnInf(name = "fdExpAmt")
    public BigDecimal fdExpAmt = BigDecimal.ZERO;

    @ColumnInf(name = "fdBackAmt")
    public BigDecimal fdBackAmt = BigDecimal.ZERO;

    @ColumnInf(name = "fdBackQty")
    public BigDecimal fdBackQty = BigDecimal.ZERO;

    @ColumnInf(name = "fdBackRoundAmt")
    public BigDecimal fdBackRoundAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fdDisExpAmt")
    public BigDecimal fdDisExpAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fdDisExpRoundAmt")
    public BigDecimal fdDisExpRoundAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fsMTableId")
    public String fsMTableId;
    @ColumnInf(name = "fsCustGUID")
    public String fsCustGUID;
    @ColumnInf(name = "fdPayAmt")
    public BigDecimal fdPayAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fsSendAddr")
    public String fsSendAddr;
    @ColumnInf(name = "fsStaffName")
    public String fsStaffName;
    @ColumnInf(name = "fsReCheckUserId")
    public String fsReCheckUserId;
    //备用字段
    @ColumnInf(name = "fsbackup0")
    public String fsbackup0;
    //备用字段
    @ColumnInf(name = "fsbackup1")
    public String fsbackup1;

    @ColumnInf(name = "fsbackup2")
    public String fsbackup2;
    @ColumnInf(name = "locked")
    public int locked;
    @ColumnInf(name = "lver")
    public int lver;
    @ColumnInf(name = "pver")
    public int pver;
    /**
     * 退单原因
     */
    @ColumnInf(name = "voidreason")
    public String voidreason;
    /**
     * 合桌的订单号
     */
    @ColumnInf(name = "mergedOrderID")
    public String mergedOrderID;


    //合桌信息、菜品换桌导致结账等信息---主要记录操作员
    @ColumnInf(name = "mergedOrderInfo")
    public String mergedOrderInfo = "";

    /**
     * 订单类型
     * 正餐 0 快餐1 ；2:外卖
     */
    @ColumnInf(name = "fiSellType")
    public int fiselltype;
    /**
     * 打赏信息
     */
    @ColumnInf(name = "fsrewardinfo")
    public String fsrewardinfo;
    /**
     * 公司税号
     */
    @ColumnInf(name = "fsdutyparagraph")
    public String fsdutyparagraph;

    /**
     * 是否显示(显示:0 隐藏1)
     */
    @ColumnInf(name = "fiSelected")
    public int fiSelected = 0;

    /**
     * 账单上传状态(0, 未上传; 1, 成功; 2, 失败)
     */
    @ColumnInf(name = "fiUploadStatus")
    public int fiUploadStatus = 0;

    /**
     * 账单上传结果消息
     */
    @ColumnInf(name = "fsUploadMsg")
    public String fsUploadMsg = "";

    /**
     * 账单上传时间
     */
    @ColumnInf(name = "fsUploadTime")
    public String fsUploadTime = "";
    /**
     * 原始结算总价
     */
    @ColumnInf(name = "fdOriginalAmt")
    public BigDecimal fdOriginalAmt = BigDecimal.ZERO;

    /**
     * 外部订单ID
     */
    @ColumnInf(name = "thirdOrderId")
    public String thirdOrderId = "";
    /**
     * 第三方订单类型,see{@link com.mwee.android.pos.business.rapid.api.bean.model.NetOrderType}
     */
    @ColumnInf(name = "thirdOrderType")
    public int thirdOrderType = 0;
    /**
     * 可折扣菜品的原始总金额
     */
    @ColumnInf(name = "fdOriginalAmtDis")
    public BigDecimal fdOriginalAmtDis = BigDecimal.ZERO;
    /**
     * 折后圆整
     */
    @ColumnInf(name = "fdRoundAmt")
    public BigDecimal fdRoundAmt = BigDecimal.ZERO;
    /**
     * 特价优惠结算金额
     */
    @ColumnInf(name = "fdCouponSpecialAmt")
    public BigDecimal fdCouponSpecialAmt = BigDecimal.ZERO;
    /**
     * 会员优惠计算金额
     */
    @ColumnInf(name = "fdCouponVipAmt")
    public BigDecimal fdCouponVipAmt = BigDecimal.ZERO;
    /**
     * 会员折扣金额
     */
    @ColumnInf(name = "fdDiscountMemAmt")
    public BigDecimal fdDiscountMemAmt = BigDecimal.ZERO;
    /**
     * 服务费分摊的优惠金额
     */
    @ColumnInf(name = "fdDiscountMemAmt")
    public BigDecimal fdServiceSplitCouponAmt = BigDecimal.ZERO;
    /**
     * 服务费分摊的实收金额
     */
    @ColumnInf(name = "fdDiscountMemAmt")
    public BigDecimal fdServiceSplitCalcAmt = BigDecimal.ZERO;
    /**
     * 软件版本号
     */
    @ColumnInf(name = "fsSoftVersion")
    public String fsSoftVersion = "";
    /**
     * 数据版本号
     */
    @ColumnInf(name = "fiVersion")
    public int fiVersion = 0;

    public SellDBModel() {

    }


    @Override
    public String toString() {
        return "SellDBModel{" +
                "fsCreateUserName='" + fsCreateUserName + '\'' +
                ", fiCustSum=" + fiCustSum +
                ", fdFreeSveAmt=" + fdFreeSveAmt +
                ", fsInvoiceCls='" + fsInvoiceCls + '\'' +
                ", fsMealNumber='" + fsMealNumber + '\'' +
                ", fsDiscountName='" + fsDiscountName + '\'' +
                ", fiReCheckTag=" + fiReCheckTag +
                ", fsMTableName='" + fsMTableName + '\'' +
                ", fsVerifyTime='" + fsVerifyTime + '\'' +
                ", fiPrintTimes_Exp=" + fiPrintTimes_Exp +
                ", fdInvoiceAmt=" + fdInvoiceAmt +
                ", fdServiceAmt=" + fdServiceAmt +
                ", fsMSectionId='" + fsMSectionId + '\'' +
                ", fsPutUpTime='" + fsPutUpTime + '\'' +
                ", fsCreditAccountId='" + fsCreditAccountId + '\'' +
                ", fsFreeSveAmtUserId='" + fsFreeSveAmtUserId + '\'' +
                ", fsSellDate='" + fsSellDate + '\'' +
                ", fiBillStatus=" + fiBillStatus +
                ", fiBillSource=" + fiBillSource +
                ", fiPrintTimes_Op=" + fiPrintTimes_Op +
                ", fsVerifyUserName='" + fsVerifyUserName + '\'' +
                ", fiModifyTag=" + fiModifyTag +
                ", fdDebtAmt=" + fdDebtAmt +
                ", fdDiffAmt=" + fdDiffAmt +
                ", fdGiftAmt=" + fdGiftAmt +
                ", fsInvoiceTitle='" + fsInvoiceTitle + '\'' +
                ", fiIsVIP=" + fiIsVIP +
                ", fsUpdateTime='" + fsUpdateTime + '\'' +
                ", fsVIPSource='" + fsVIPSource + '\'' +
                ", fsSellNo='" + fssellno + '\'' +
                ", fsSendOnTime='" + fsSendOnTime + '\'' +
                ", fsSendUserName='" + fsSendUserName + '\'' +
                ", fsPrintExpTime='" + fsPrintExpTime + '\'' +
                ", fsShopGUID='" + fsshopguid + '\'' +
                ", fsMAreaId='" + fsMAreaId + '\'' +
                ", fsCreditAccountName='" + fsCreditAccountName + '\'' +
                ", fdRealAmt=" + fdRealAmt +
                ", fsCardNo='" + fsCardNo + '\'' +
                ", fsStaffId='" + fsStaffId + '\'' +
                ", fsReCheckReason='" + fsReCheckReason + '\'' +
                ", fsNote='" + fsNote + '\'' +
                ", fsReCheckUserName='" + fsReCheckUserName + '\'' +
                ", fiIsVerify=" + fiIsVerify +
                ", fsCreateUserId='" + fsCreateUserId + '\'' +
                ", fiBillKind=" + fiBillKind +
                ", fsCustMobile='" + fsCustMobile + '\'' +
                ", fsCustName='" + fsCustName + '\'' +
                ", fiIsInvoice=" + fiIsInvoice +
                ", fdDiscountAmt=" + fdDiscountAmt +
                ", fiindex=" + fiindex +
                ", fdSaleAmt=" + fdSaleAmt +
                ", fsAssignTime='" + fsAssignTime + '\'' +
                ", fsCreateTime='" + fsCreateTime + '\'' +
                ", fsSellTime='" + fsSellTime + '\'' +
                ", fsFreeSveAmtUserName='" + fsFreeSveAmtUserName + '\'' +
                ", fsCheckEndTime='" + fsCheckEndTime + '\'' +
                ", fsMasterSellNo='" + fsMasterSellNo + '\'' +
                ", fsBillSourceId='" + fsBillSourceId + '\'' +
                ", fsBillSourceName='" + fsBillSourceName + '\'' +
                ", fsBillSourceNo='" + fsBillSourceNo + '\'' +
                ", fsRelationMTable='" + fsRelationMTable + '\'' +
                ", fsHostId='" + fsHostId + '\'' +
                ", fsSendUserId='" + fsSendUserId + '\'' +
                ", fsDiscountId='" + fsDiscountId + '\'' +
                ", fsCustId='" + fsCustId + '\'' +
                ", fdExpAmt=" + fdExpAmt +
                ", fdBackAmt=" + fdBackAmt +
                ", fddisexpamt=" + fdDisExpAmt +
                ", fsMTableId='" + fsMTableId + '\'' +
                ", fsCustGUID='" + fsCustGUID + '\'' +
                ", fdPayAmt=" + fdPayAmt +
                ", fsSendAddr='" + fsSendAddr + '\'' +
                ", fsStaffName='" + fsStaffName + '\'' +
                ", fsReCheckUserId='" + fsReCheckUserId + '\'' +
                ", lver=" + lver +
                ", pver=" + pver +
                ", fsSoftVersion=" + fsSoftVersion +
                '}';
    }

    @Override
    public SellDBModel clone() {
        SellDBModel cloneObj = null;
        try {
            cloneObj = (SellDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}