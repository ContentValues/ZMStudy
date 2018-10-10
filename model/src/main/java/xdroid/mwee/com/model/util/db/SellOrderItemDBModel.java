package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

/**
 * 菜品表
 *
 * @author virgil
 */
@TableInf(name = "tbSellOrderItem")
public class SellOrderItemDBModel extends DBModel {
    @ColumnInf(name = "fiOrderMode")
    public int fiOrderMode;
    @ColumnInf(name = "fiIsEditQty")
    public int fiIsEditQty;//是否可改数(称重);0/1;为1=ture时,份数为1
    @ColumnInf(name = "fiHurryTimes")
    public int fiHurryTimes;
    @ColumnInf(name = "fsCreateUserName")
    public String fsCreateUserName;
    @ColumnInf(name = "fsDiscountUserName")
    public String fsDiscountUserName;
    @ColumnInf(name = "fiSetFoodCd")
    public int fiSetFoodCd;
    @ColumnInf(name = "fsSellDate")
    public String fsSellDate;
    @ColumnInf(name = "fdVIPPrice")
    public BigDecimal fdVIPPrice = BigDecimal.ZERO;
    @ColumnInf(name = "fsItemId")
    public String fsItemId;
    @ColumnInf(name = "fsSourceOrderTime")
    public String fsSourceOrderTime;
    @ColumnInf(name = "fsItemName")
    public String fsItemName;
    @ColumnInf(name = "fsGiftUserName")
    public String fsGiftUserName;
    @ColumnInf(name = "fdServiceAmt")
    public BigDecimal fdServiceAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fsSeq", primaryKey = true)
    public String fsseq;
    @ColumnInf(name = "fsMSectionId")
    public String fsMSectionId;
    @ColumnInf(name = "fdSalePrice")
    public BigDecimal fdSalePrice = BigDecimal.ZERO;
    @ColumnInf(name = "fsOrderTime")
    public String fsOrderTime;
    @ColumnInf(name = "fsItemName2")
    public String fsItemName2;
    @ColumnInf(name = "fsDeptId")
    public String fsDeptId;
    @ColumnInf(name = "fiDiscountRate")
    public int fiDiscountRate;
    @ColumnInf(name = "fiIsPrn")
    public int fiIsPrn;
    @ColumnInf(name = "fiIsDiscount")
    public int fiIsDiscount;
    @ColumnInf(name = "fiIsGift")
    public int fiIsGift;
    @ColumnInf(name = "fdBargainPrice")
    public BigDecimal fdBargainPrice = BigDecimal.ZERO;
    @ColumnInf(name = "fdSettlePrice")
    public BigDecimal fdSettlePrice = BigDecimal.ZERO;  //--结算单价; = (销售单价 or 会员价 or 特价)+加价;
    @ColumnInf(name = "fdGiftAmt")
    public BigDecimal fdGiftAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fsSeq_M")
    public String fsSeq_M;
    @ColumnInf(name = "fsDiscountReason")
    public String fsDiscountReason;
    @ColumnInf(name = "fiIsEditPrice")//时价菜
    public int fiIsEditPrice;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime;
    @ColumnInf(name = "fsMenuClsId")
    public String fsMenuClsId;
    @ColumnInf(name = "fsGiftUserId")
    public String fsGiftUserId;
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsshopguid;
    @ColumnInf(name = "fsSellNo")
    public String fsSellNo;
    @ColumnInf(name = "fsBackUserId")
    public String fsBackUserId;
    @ColumnInf(name = "fsEditPirceUserId")
    public String fsEditPirceUserId;
    @ColumnInf(name = "fdAddPrice")
    public BigDecimal fdAddPrice = BigDecimal.ZERO;
    @ColumnInf(name = "fdDisAfterAmt")
    public BigDecimal fdDisAfterAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fsEraseTime")
    public String fsEraseTime;

    //做法+要求
    @ColumnInf(name = "fsNote")
    public String fsNote;

    //显著要求
    @ColumnInf(name = "fsSpecialNote")
    public String fsSpecialNote;

    //做法+普通要求
    @ColumnInf(name = "fsGeneralNote")
    public String fsGeneralNote;

    @ColumnInf(name = "fiOrderUintCd")
    public int fiOrderUintCd;
    @ColumnInf(name = "fiIsQtyAssess")
    public int fiIsQtyAssess;
    @ColumnInf(name = "fsCreateUserId")
    public String fsCreateUserId;
    @ColumnInf(name = "fdSubTotal")
    public BigDecimal fdSubTotal = BigDecimal.ZERO;
    @ColumnInf(name = "fsBackUserName")
    public String fsBackUserName;
    @ColumnInf(name = "fsEditPirceUserName")
    public String fsEditPirceUserName;
    @ColumnInf(name = "fsGiftReason")
    public String fsGiftReason;
    @ColumnInf(name = "fdDiscountAmt")
    public BigDecimal fdDiscountAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fiOrderItemKind")
    public int fiOrderItemKind;  //1单品；2、套餐头；3、套餐明细； 4、配料
    @ColumnInf(name = "fdSaleQty")
    public BigDecimal fdSaleQty = BigDecimal.ZERO;
    @ColumnInf(name = "fdBackQty")
    public BigDecimal fdBackQty = BigDecimal.ZERO;
    @ColumnInf(name = "fiindex")
    public long fiindex;
    @ColumnInf(name = "fdSaleAmt")
    public BigDecimal fdSaleAmt = BigDecimal.ZERO;  //--销售金额(折前金额);=结算单价*销售数量
    @ColumnInf(name = "fsOrderUint")
    public String fsOrderUint;
    @ColumnInf(name = "fsRevenueTypeId")
    public String fsRevenueTypeId;
    @ColumnInf(name = "fsDiscountUserId")
    public String fsDiscountUserId;
    @ColumnInf(name = "fiOrderSeq")
    public int fiOrderSeq;
    @ColumnInf(name = "fsExpClsId")
    public String fsExpClsId;
    @ColumnInf(name = "fiEraseNo")
    public int fiEraseNo;
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime;
    @ColumnInf(name = "fiPriceTag")
    public int fiPriceTag;
    @ColumnInf(name = "fiIsServiceFee")
    public int fiIsServiceFee;
    @ColumnInf(name = "fsSourceSeq")
    public String fsSourceSeq;
    @ColumnInf(name = "fiItemCd")
    public int fiItemCd;
    @ColumnInf(name = "fdSubTotal2")
    public BigDecimal fdSubTotal2 = BigDecimal.ZERO;

    @ColumnInf(name = "fsBackReason")
    public String fsBackReason;
    @ColumnInf(name = "fsEditPirceReason")
    public String fsEditPirceReason;
    @ColumnInf(name = "fsHostId")
    public String fsHostId;
    @ColumnInf(name = "fsSourceSellNo")
    public String fsSourceSellNo;
    @ColumnInf(name = "fiItemMakeSte")
    public int fiItemMakeSte;
    @ColumnInf(name = "fiItemMakeOrder")
    public int fiItemMakeOrder;
    @ColumnInf(name = "fiServiceRate")
    public int fiServiceRate;
    @ColumnInf(name = "fdBackAmt")
    public BigDecimal fdBackAmt = BigDecimal.ZERO;
    @ColumnInf(name = "lver")
    public int lver;
    @ColumnInf(name = "pver")
    public int pver;
    @ColumnInf(name = "fsmsectionname")
    public String fsmsectionname = "";
    @ColumnInf(name = "fsdeptname")
    public String fsdeptname = "";

    @ColumnInf(name = "fsmenuclsname")
    public String fsmenuclsname = "";

    @ColumnInf(name = "fsexpclsname")
    public String fsexpclsname = "";

    @ColumnInf(name = "fsrevenuetypename")
    public String fsrevenuetypename;

    @ColumnInf(name = "fdGiftqty")
    public BigDecimal fdGiftqty = BigDecimal.ZERO;

    @ColumnInf(name = "fsGifttime")
    public String fsGifttime = "";

    @ColumnInf(name = "fsBacktime")
    public String fsBacktime = "";

    @ColumnInf(name = "fsBackremark")
    public String fsBackremark = "";


    @ColumnInf(name = "fdfixedamount")
    public BigDecimal fdfixedamount = BigDecimal.ZERO;

    @ColumnInf(name = "fiIsMulDept")
    public int fiIsMulDept;//是否为多制作部门(0=否/1=是)

    @ColumnInf(name = "fiIsSetDtlPrn")
    public int fiIsSetDtlPrn;//是否套餐头部门打印(1=套餐头部门/2=明细部门)
    @ColumnInf(name = "fsDiscountName")
    public String fsDiscountName = "";
    @ColumnInf(name = "fsDiscountId")
    public String fsDiscountId = "";
    @ColumnInf(name = "hassub")
    public int hassub;

    //菜品换桌信息
    @ColumnInf(name = "mergedItemInfo")
    public String mergedItemInfo = "";

    /**
     * 套餐时，是否需要套餐分账：1，开启分账
     */
    @ColumnInf(name = "fiSplitStatus")
    public int fiSplitStatus = 0;

    //分账折前金额
    @ColumnInf(name = "fdSplitDisBeforeAmt")
    public BigDecimal fdSplitDisBeforeAmt = BigDecimal.ZERO;
    //分账折扣金额
    @ColumnInf(name = "fdSplitDisAmt")
    public BigDecimal fdSplitDisAmt = BigDecimal.ZERO;
    //分账折后金额
    @ColumnInf(name = "fdSplitDisAfterAmt")
    public BigDecimal fdSplitDisAfterAmt = BigDecimal.ZERO;
    /**
     * 原始结算总价
     */
    @ColumnInf(name = "fdOriginalAmt")
    public BigDecimal fdOriginalAmt = BigDecimal.ZERO;

    /**
     * 改价次数
     */
    @ColumnInf(name = "changePriceTimes")
    public int changePriceTimes = 0;
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
     * 是否是会员折扣：0：不是1：是
     */
    @ColumnInf(name = "fiMemDiscount")
    public int fiMemDiscount = 0;
    /**
     * 是否是招牌菜：0，不是；1，是
     */
    @ColumnInf(name = "fiIsSpecialty")
    public int fiIsSpecialty = 0;
    /**
     * 是否是新菜：0，不是；1，是
     */
    @ColumnInf(name = "fiIsNew")
    public int fiIsNew = 0;
    /**
     * 是否是热门菜：0，不是；1，是
     */
    @ColumnInf(name = "fiIsHot")
    public int fiIsHot = 0;

    /**
     * 是否是临时菜：0=false/1=true
     */
    @ColumnInf(name = "fiIsTemporaryMenu")
    public int fiIsTemporaryMenu = 0;

    public SellOrderItemDBModel() {

    }

    @Override
    public SellOrderItemDBModel clone() {
        SellOrderItemDBModel cloneObj = null;
        try {
            cloneObj = (SellOrderItemDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

    @Override
    public String toString() {
        return "SellOrderItemDBModel{" +
                "fiOrderMode=" + fiOrderMode +
                ", fiIsEditQty=" + fiIsEditQty +
                ", fiHurryTimes=" + fiHurryTimes +
                ", fsCreateUserName='" + fsCreateUserName + '\'' +
                ", fsDiscountUserName='" + fsDiscountUserName + '\'' +
                ", fiSetFoodCd=" + fiSetFoodCd +
                ", fdVIPPrice=" + fdVIPPrice +
                ", fsItemId='" + fsItemId + '\'' +
                ", fsSourceOrderTime='" + fsSourceOrderTime + '\'' +
                ", fsItemName='" + fsItemName + '\'' +
                ", fsGiftUserName='" + fsGiftUserName + '\'' +
                ", fdServiceAmt=" + fdServiceAmt +
                ", fsSeq='" + fsseq + '\'' +
                ", fsMSectionId='" + fsMSectionId + '\'' +
                ", fdSalePrice=" + fdSalePrice +
                ", fsOrderTime='" + fsOrderTime + '\'' +
                ", fsItemName2='" + fsItemName2 + '\'' +
                ", fsDeptId='" + fsDeptId + '\'' +
                ", fiDiscountRate=" + fiDiscountRate +
                ", fiIsPrn=" + fiIsPrn +
                ", fiIsDiscount=" + fiIsDiscount +
                ", fiIsGift=" + fiIsGift +
                ", fdBargainPrice=" + fdBargainPrice +
                ", fdSettlePrice=" + fdSettlePrice +
                ", fdGiftAmt=" + fdGiftAmt +
                ", fsSeq_M='" + fsSeq_M + '\'' +
                ", fsDiscountReason='" + fsDiscountReason + '\'' +
                ", fiIsEditPrice=" + fiIsEditPrice +
                ", fsUpdateTime='" + fsUpdateTime + '\'' +
                ", fsMenuClsId='" + fsMenuClsId + '\'' +
                ", fsGiftUserId='" + fsGiftUserId + '\'' +
                ", fsShopGUID='" + fsshopguid + '\'' +
                ", fsSellNo='" + fsSellNo + '\'' +
                ", fsBackUserId='" + fsBackUserId + '\'' +
                ", fsEditPirceUserId='" + fsEditPirceUserId + '\'' +
                ", fdAddPrice=" + fdAddPrice +
                ", fdDisAfterAmt=" + fdDisAfterAmt +
                ", fsEraseTime='" + fsEraseTime + '\'' +
                ", fsNote='" + fsNote + '\'' +
                ", fiOrderUintCd=" + fiOrderUintCd +
                ", fiIsQtyAssess=" + fiIsQtyAssess +
                ", fsCreateUserId='" + fsCreateUserId + '\'' +
                ", fdSubTotal=" + fdSubTotal +
                ", fsBackUserName='" + fsBackUserName + '\'' +
                ", fsEditPirceUserName='" + fsEditPirceUserName + '\'' +
                ", fsGiftReason='" + fsGiftReason + '\'' +
                ", fdDiscountAmt=" + fdDiscountAmt +
                ", fiOrderItemKind=" + fiOrderItemKind +
                ", fdSaleQty=" + fdSaleQty +
                ", fdBackQty=" + fdBackQty +
                ", fiindex=" + fiindex +
                ", fdSaleAmt=" + fdSaleAmt +
                ", fsOrderUint='" + fsOrderUint + '\'' +
                ", fsRevenueTypeId='" + fsRevenueTypeId + '\'' +
                ", fsDiscountUserId='" + fsDiscountUserId + '\'' +
                ", fiOrderSeq=" + fiOrderSeq +
                ", fsExpClsId='" + fsExpClsId + '\'' +
                ", fiEraseNo=" + fiEraseNo +
                ", fsCreateTime='" + fsCreateTime + '\'' +
                ", fiPriceTag=" + fiPriceTag +
                ", fiIsServiceFee=" + fiIsServiceFee +
                ", fsSourceSeq='" + fsSourceSeq + '\'' +
                ", fiItemCd=" + fiItemCd +
                ", fdSubTotal2=" + fdSubTotal2 +
                ", fsBackReason='" + fsBackReason + '\'' +
                ", fsEditPirceReason='" + fsEditPirceReason + '\'' +
                ", fsHostId='" + fsHostId + '\'' +
                ", fsSourceSellNo='" + fsSourceSellNo + '\'' +
                ", fiItemMakeSte=" + fiItemMakeSte +
                ", fiItemMakeOrder=" + fiItemMakeOrder +
                ", fiServiceRate=" + fiServiceRate +
                ", fdBackAmt=" + fdBackAmt +
                ", lver=" + lver +
                ", pver=" + pver +
                '}';
    }
}