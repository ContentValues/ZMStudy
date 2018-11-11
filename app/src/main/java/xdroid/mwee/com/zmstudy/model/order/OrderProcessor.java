package xdroid.mwee.com.zmstudy.model.order;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBSimpleUtil;
import com.mwee.android.tools.DateUtil;
import com.mwee.android.tools.LogUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import xdroid.mwee.com.model.util.db.MenuclsDBModel;
import xdroid.mwee.com.model.util.db.MenuitemDBModel;
import xdroid.mwee.com.model.util.db.SellDBModel;
import xdroid.mwee.com.model.util.db.SellOrderDBModel;
import xdroid.mwee.com.model.util.db.SellOrderItemDBModel;
import xdroid.mwee.com.model.util.db.SellcheckDBModel;
import xdroid.mwee.com.model.util.db.UserDBModel;

import com.mwee.android.tools.ListUtil;

import xdroid.mwee.com.zmstudy.db.client.ClientMenuDBUtil;
import xdroid.mwee.com.zmstudy.db.client.HostDBUtil;
import xdroid.mwee.com.zmstudy.db.client.MenuClsDBUtils;
import xdroid.mwee.com.zmstudy.model.menu.MenuItem;
import xdroid.mwee.com.zmstudy.utils.Calc;
import xdroid.mwee.com.zmstudy.utils.RoundConfig;

/**
 * Created by zhangmin on 2018/6/30.
 */

public class OrderProcessor {

    public static void saveOrder(OrderCache orderCache, SellcheckDBModel sellDBModel) {


        List<SellOrderItemDBModel> orderItemList = new ArrayList<>();
        for (MenuItem menuItem : orderCache.originMenuList) {
            //构建明细
            SellOrderItemDBModel sellOrderItemDBModel = createSellOrderItemDBModel(orderCache, ClientMenuDBUtil.queryMenuDBModel(menuItem.itemID), menuItem,
                    0, null, orderCache.optSeqModel(menuItem.menuBiz.orderSeqID));
            sellOrderItemDBModel.replaceNoTrans();
            orderItemList.add(sellOrderItemDBModel);
        }

        //构建Order
        SellDBModel dbOrder = OrderProcessor.createSellDBModel(orderCache, orderCache.shopID, orderItemList, sellDBModel);
        dbOrder.replaceNoTrans();
        //构建订单单序
        //修改seq的生成形式
        if (orderCache.seqList != null && orderCache.seqList.size() > 0) {
            for (int i = 0; i < orderCache.seqList.size(); i++) {
                OrderSeqModel model = orderCache.seqList.get(i);
                if (model.seqStatus == OrderSeqStatus.NORMAL) {
                    //未下单单序不保存
                    continue;
                }
                OrderProcessor.createOrderSeq(orderCache, model, dbOrder);
            }
        }

    }

    /**
     * 创建SellOrderItemDBModel
     *
     * @param menuItem    MenuitemDBModel
     * @param temp
     * @param fiSetFoodCd
     * @param fatherMenu
     * @param seqModel
     * @return SellOrderItemDBModel
     */
    public static SellOrderItemDBModel createSellOrderItemDBModel(OrderCache orderCache, MenuitemDBModel menuItem, MenuItem temp, int fiSetFoodCd, MenuItem fatherMenu, OrderSeqModel seqModel) {
        SellOrderItemDBModel sellOrderItemDBModel = new SellOrderItemDBModel();
        sellOrderItemDBModel.fsSellNo = orderCache.orderID;
        sellOrderItemDBModel.fiOrderSeq = temp.menuBiz.orderSeqID;
        sellOrderItemDBModel.fsseq = temp.menuBiz.uniq;
        if (TextUtils.isEmpty(sellOrderItemDBModel.fsseq)) {
            sellOrderItemDBModel.fsseq = UUID.randomUUID().toString();
        }
        //	--销售日期(开台日期)(营业日期)
        sellOrderItemDBModel.fsSellDate = orderCache.businessDate.contains("-") ? orderCache.businessDate : DateUtil.formartDateStrToTarget(orderCache.businessDate, "yyyyMMdd", "yyyy-MM-dd");

        if (fatherMenu != null) {
            //--套餐头商品的点单序号;Link套餐头
            sellOrderItemDBModel.fsSeq_M = fatherMenu.menuBiz.uniq;
        }
        //sellOrderItemDBModel.fsMSectionId = orderCache.currentSectionID;

        if (fatherMenu != null) {
            sellOrderItemDBModel.fiOrderMode = fatherMenu.menuBiz.menuSellType;
        } else {
            sellOrderItemDBModel.fiOrderMode = temp.menuBiz.menuSellType;
        }

        //sellOrderItemDBModel.fiIsBonus = menuItem.fiIsBonus;
        sellOrderItemDBModel.fiItemCd = menuItem.fiItemCd;
        sellOrderItemDBModel.fsItemId = menuItem.fsItemId;
        sellOrderItemDBModel.fsItemName = temp.name;
      /*  sellOrderItemDBModel.fsItemName2 = (TextUtils.isEmpty(temp.name2) ? menuItem.fsItemName2 : temp.name2);
        if (!NetOrderType.isShareShopPrePay(orderCache.thirdOrderType) && temp.isMenuTemporary()) {
            //共享餐厅不需要拼接[临]字样
            sellOrderItemDBModel.fsItemName = "[临]" + sellOrderItemDBModel.fsItemName;
            sellOrderItemDBModel.fsItemName2 = "[Temp]" + sellOrderItemDBModel.fsItemName2;
        }*/

        //规格
        sellOrderItemDBModel.fsOrderUint = temp.currentUnit.fsOrderUint;
        //销售单价;商品规格表的销售价(牌价);可改的单价
        sellOrderItemDBModel.fdSalePrice = temp.currentUnit.fdSalePrice;
        if (temp.currentUnit.fdOriginPrice == null || temp.currentUnit.fdOriginPrice.compareTo(BigDecimal.ZERO) <= 0) {
            temp.currentUnit.fdOriginPrice = sellOrderItemDBModel.fdSalePrice;
        }
       /* if (temp.supportTimes() || temp.isMenuTemporary()) {
            sellOrderItemDBModel.fdOriginalAmt = temp.currentUnit.fdSalePrice;
        } else {
        }*/
        sellOrderItemDBModel.fdOriginalAmt = temp.currentUnit.fdOriginPrice;
        //会员价格;商品规格表的会员价
        if (temp.menuBiz.checkMemberPrice()) {
            sellOrderItemDBModel.fdVIPPrice = temp.currentUnit.fdVIPPrice;
        }
        //--规格单位号;	对应商品规格单位表
        sellOrderItemDBModel.fiOrderUintCd = temp.currentUnit.fiOrderUintCd;

        sellOrderItemDBModel.fsSpecialNote = temp.menuBiz.fsSpecialNote;
        //关联要求
        sellOrderItemDBModel.fsNote = !TextUtils.isEmpty(temp.menuBiz.selectedExtraStr) ? (temp.menuBiz.selectedExtraStr + ";" + temp.menuBiz.note) : temp.menuBiz.note;
        sellOrderItemDBModel.fsGeneralNote = !TextUtils.isEmpty(temp.menuBiz.selectedExtraStr) ? (temp.menuBiz.selectedExtraStr + ";" + temp.menuBiz.fsGeneralNote) : temp.menuBiz.fsGeneralNote;
        /*-商品表参考带过来-*/
        //是否可打折;0/1
        //sellOrderItemDBModel.fiIsDiscount = temp.supportDiscount() ? 1 : 0;
        //是否收服务费;0/1
        //sellOrderItemDBModel.fiIsServiceFee = temp.supportServiceFee() ? 1 : 0;
        //是否可改价;0/1
        //sellOrderItemDBModel.fiIsEditPrice = temp.supportTimes() ? 1 : 0;
        //是否可改数(称重);0/1;为1=ture时,份数为1
        //sellOrderItemDBModel.fiIsEditQty = temp.supportWeight() ? 1 : 0;
        //是否可赠送;0/1
        //sellOrderItemDBModel.fiIsGift = temp.supportGift() ? 1 : 0;
        //是否打印;0/1
        //sellOrderItemDBModel.fiIsPrn = menuItem.fiIsPrn;
        //是否为数量沽清菜品;0/1
        //sellOrderItemDBModel.fiIsQtyAssess = temp.isOutOfStock() ? 1 : 0;
        //sellOrderItemDBModel.fiIsNew = temp.supportNew() ? 1 : 0;
        //sellOrderItemDBModel.fiIsSpecialty = temp.supportSpeciality() ? 1 : 0;
        //sellOrderItemDBModel.fiIsHot = temp.supportHot() ? 1 : 0;
        //打印--是否多制作部门
        //sellOrderItemDBModel.fiIsMulDept = menuItem.fiIsMulDept;

        /**
         * 如果菜品是套餐菜，看套餐头的打印配置，如果套餐头的打印配置是不打印则直接不打印，
         * 如果套餐头的配置是按照头打印，菜品明细的打印部门指向套餐头的打印, 并且是单部门打印
         * 如果套餐头的配置是按照套餐明细打印，菜品明细的打印部门指向自己的打印部门
         *
         * 如果菜品是配料菜，
         *          打印部门指向菜品头部的打印部门,如果菜品头部设置不打印，则配料也不打印，如果菜品头部是多部门打印，配料也是多部门打印
         */

        MenuitemDBModel fatherDBModel = null;
        if (fatherMenu != null) {
            fatherDBModel = DBSimpleUtil.query(APPConfig.DB_MAIN, "select * from tbmenuitem where fiItemCd='" + fatherMenu.itemID + "'", MenuitemDBModel.class);
        }

        if (menuItem.fiItemKind == 4 && TextUtils.isEmpty(menuItem.fsDeptId) && fatherMenu != null && fatherDBModel != null) {
            //配料菜
            if (fatherDBModel.fiIsPrn == 0) {
                //不打印制作单
                sellOrderItemDBModel.fsDeptId = "";
                sellOrderItemDBModel.fiIsPrn = 0;
                sellOrderItemDBModel.fiIsMulDept = 0;
            } else if (fatherDBModel.fiIsPrn == 1) {
                sellOrderItemDBModel.fiIsPrn = 1;
                //制作部门;部门会对应出菜打印机
                sellOrderItemDBModel.fsDeptId = fatherDBModel.fsDeptId;
                sellOrderItemDBModel.fiIsMulDept = fatherDBModel.fiIsMulDept;
            }
        } else if (fatherDBModel != null) {
            //套餐菜
            if (fatherDBModel.fiIsPrn == 0) {
                //不打印制作单
                sellOrderItemDBModel.fsDeptId = "";
                sellOrderItemDBModel.fiIsPrn = 0;
                sellOrderItemDBModel.fiIsMulDept = 0;
            } else if (fatherDBModel.fiIsPrn == 1 && fatherDBModel.fiIsSetDtlPrn == 1) {
                //制作部门;部门会对应出菜打印机
                sellOrderItemDBModel.fsDeptId = fatherDBModel.fsDeptId;
                sellOrderItemDBModel.fiIsMulDept = 0;
                sellOrderItemDBModel.fiIsPrn = 1;
            } else {
                //制作部门;部门会对应出菜打印机
                sellOrderItemDBModel.fiIsPrn = 1;
                sellOrderItemDBModel.fsDeptId = menuItem.fsDeptId;
                sellOrderItemDBModel.fiIsMulDept = menuItem.fiIsMulDept;
            }
        } else {
            //制作部门;部门会对应出菜打印机
            sellOrderItemDBModel.fsDeptId = menuItem.fsDeptId;
            sellOrderItemDBModel.fiIsMulDept = menuItem.fiIsMulDept;
            sellOrderItemDBModel.fiIsPrn = menuItem.fiIsPrn;
        }

        if (fatherMenu != null) {
            if (menuItem.fiItemKind == 4) {
                sellOrderItemDBModel.fiOrderItemKind = 4;
                //配料处理多部门
                //sellOrderItemDBModel.fiIsMulDept = StringUtil.toInt(DBSimpleUtil.queryString(APPConfig.DB_MAIN, "select fiIsMulDept from tbmenuitem where fiItemCd='" + fatherMenu.itemID + "'"), 0);
            } else {
                //点单商品种类；1单品 2套餐头 3套餐明细
                sellOrderItemDBModel.fiOrderItemKind = !TextUtils.isEmpty(fatherMenu.menuBiz.uniq) ? 3 : menuItem.fiItemKind;
            }
        } else {
            sellOrderItemDBModel.fiOrderItemKind = menuItem.fiItemKind;
        }
        //菜单分类代码
        sellOrderItemDBModel.fsMenuClsId = menuItem.fsMenuClsId;
        //销售分类代码
        sellOrderItemDBModel.fsExpClsId = menuItem.fsExpClsId;
        //收入分类代码
        sellOrderItemDBModel.fsRevenueTypeId = menuItem.fsRevenueTypeId;

        MenuclsDBModel clsModel = MenuClsDBUtils.queryMenuClsByID(menuItem.fsShopGUID, menuItem.fsMenuClsId);
        if (clsModel != null) {
            if (TextUtils.isEmpty(sellOrderItemDBModel.fsExpClsId)) {
                //销售分类代码
                sellOrderItemDBModel.fsExpClsId = clsModel.fsExpClsId;
            }
            if (TextUtils.isEmpty(sellOrderItemDBModel.fsRevenueTypeId)) {
                //收入分类代码
                sellOrderItemDBModel.fsRevenueTypeId = clsModel.fsRevenueTypeId;
            }
            sellOrderItemDBModel.fsmenuclsname = clsModel.fsMenuClsName;
            //sellOrderItemDBModel.fsexpclsname = MenuDBUtil.getExpClsByID(orderCache.shopID, sellOrderItemDBModel.fsExpClsId);
            //sellOrderItemDBModel.fsrevenuetypename = MenuDBUtil.getRevenueByID(orderCache.shopID, sellOrderItemDBModel.fsRevenueTypeId);
        }
        //--组成项內码;套餐明细用
        sellOrderItemDBModel.fiSetFoodCd = fiSetFoodCd;


        //--优惠价格标签;0=正常/1=减价/2=特价/3=会员价
        //sellOrderItemDBModel.fiPriceTag = temp.useMemberPrice ? 3 : 0;
        //上菜状态;默认1; 1一般即起 / 2等叫(叫起) / 3起菜 / 4划菜 / 8打包 /9取消(退菜数量=销售数量，消除此菜)
        sellOrderItemDBModel.fiItemMakeSte = temp.menuBiz.fiItemMakeState;
        //催菜次数
        sellOrderItemDBModel.fiHurryTimes = temp.menuBiz.fiHurryTimes;

        /*--退菜数据用字段，关联加菜记录--*/
        //源序号；加菜的那笔序号
        sellOrderItemDBModel.fsSourceSeq = "";
        //源加菜记录下单时间
        sellOrderItemDBModel.fsSourceOrderTime = "";

        /*--退菜授权相关字段,暂不处理--*/
        //退菜授权用户代码
        sellOrderItemDBModel.fsBackUserId = temp.menuBiz.voidUserID;
        //退菜授权人
        sellOrderItemDBModel.fsBackUserName = temp.menuBiz.voidUserName;
        //退菜理由
        sellOrderItemDBModel.fsBackReason = temp.menuBiz.voidReson;

        /*--赠送授权相关字段,暂不处理--*/
        //赠送授权用户代码
        sellOrderItemDBModel.fsGiftUserId = temp.menuBiz.giftUserID;
        //赠送授权人
        sellOrderItemDBModel.fsGiftUserName = temp.menuBiz.giftUserName;
        //赠送理由
        sellOrderItemDBModel.fsGiftReason = temp.menuBiz.giftReason;

        /*--折扣授权相关字段,暂不处理--*/
        //折扣授权用户代码
        sellOrderItemDBModel.fsDiscountUserId = temp.menuBiz.discountUserID;
        //折扣授权人
        sellOrderItemDBModel.fsDiscountUserName = temp.menuBiz.discountUserName;
        //折扣理由
        sellOrderItemDBModel.fsDiscountReason = temp.menuBiz.discountReason;

        /*--改价授权相关字段,暂不处理--*/
        //改价授权用户代码
        sellOrderItemDBModel.fsEditPirceUserId = "";
        //改价授权人
        sellOrderItemDBModel.fsEditPirceUserName = "";
        //改价理由
        sellOrderItemDBModel.fsEditPirceReason = "";

        /*--暂不处理--*/
        sellOrderItemDBModel.fiindex = 0;
        sellOrderItemDBModel.fsSourceSellNo = "";
        //上菜顺序;1 2 3 4 ...
        sellOrderItemDBModel.fiItemMakeOrder = 0;
        //服务费比例;由参数表取
        //sellOrderItemDBModel.fiServiceRate = orderCache.serviceRate;
        //服务费金额;=销售金额*服务费比例*0.01	;(赠送菜品，服务费金额=0)
        sellOrderItemDBModel.fdServiceAmt = temp.menuBiz.priceService;
        //划菜号;4码，号码每天从1计数，用於出品划菜
        sellOrderItemDBModel.fiEraseNo = 0;
        //划菜日期时间; 2014-03-23 12:40:00  (营业日期+当前时间)
        sellOrderItemDBModel.fsEraseTime = "";

        //sellOrderItemDBModel.fsmsectionname = DBSimpleUtil.queryString(APPConfig.DB_MAIN,"select fsMSectionName from tbmsection where fsMSectionId='" + orderCache.currentSectionID + "'");
        //sellOrderItemDBModel.fsdeptname = CommonDBUtil.getDeptNameByDeptID(menuItem.fsDeptId);


       /* if (temp.menuBiz.menuSellType == MenuSellType.GIFT) {
            sellOrderItemDBModel.fsGifttime = temp.menuBiz.giftTime;
            if (TextUtils.isEmpty(temp.menuBiz.giftTime)) {
                sellOrderItemDBModel.fsGifttime = temp.menuBiz.menuSellTime;
            }
            sellOrderItemDBModel.fsGiftReason = temp.menuBiz.giftReason;

        }*/
        if (temp.menuBiz.voidNum.compareTo(BigDecimal.ZERO) > 0) {
            sellOrderItemDBModel.fsBacktime = temp.menuBiz.voidTime;
            if (TextUtils.isEmpty(temp.menuBiz.voidTime)) {
                sellOrderItemDBModel.fsBacktime = temp.menuBiz.menuSellTime;
            }
        }
        // 价格计算
        if (temp.menuBiz.checkSpecialPrice() && !temp.menuBiz.checkMemberPrice()) {
            //特价/折算单价;促销活动价格/套餐明细折算价/活动减价
            sellOrderItemDBModel.fdBargainPrice = temp.currentUnit.fdBargainPrice;
            sellOrderItemDBModel.fdSalePrice = temp.currentUnit.fdOriginPrice;

        }

        //加价
        sellOrderItemDBModel.fdAddPrice = temp.menuBiz.priceExtraTotal;

        //销售数量/重量;
        sellOrderItemDBModel.fdSaleQty = temp.menuBiz.buyNum;
        if (temp.menuBiz.selectDiscount != null) {
            //折扣率%	; (20 , 20%off=打8折  )
            sellOrderItemDBModel.fiDiscountRate = temp.menuBiz.selectDiscount.fiDiscountRate;
            sellOrderItemDBModel.fsDiscountId = temp.menuBiz.selectDiscount.fsDiscountId;
            sellOrderItemDBModel.fsDiscountName = temp.menuBiz.selectDiscount.fsDiscountName;
            sellOrderItemDBModel.fiMemDiscount = temp.menuBiz.selectDiscount.fiIsVIPUse;

        } else {
            sellOrderItemDBModel.fiDiscountRate = 0;
        }
        //折扣金额;=销售金额*折扣率*0.01	;(赠送菜品不参与打折，所以折扣金额=0)
        sellOrderItemDBModel.fdDiscountAmt = temp.menuBiz.discountAmount;
        if (sellOrderItemDBModel.fiMemDiscount == 1) {
            sellOrderItemDBModel.fdDiscountMemAmt = sellOrderItemDBModel.fdDiscountAmt;
        }
        /*if (temp.menuBiz.selectDiscount != null && TextUtils.equals(temp.menuBiz.selectDiscount.fsDiscountId, DiscountType.CASH)) {
            sellOrderItemDBModel.fdfixedamount = temp.menuBiz.discountAmount;
        }*/

        sellOrderItemDBModel.fdGiftqty = temp.menuBiz.giftNum;

        //退货数量
        sellOrderItemDBModel.fdBackQty = temp.menuBiz.voidNum;

        sellOrderItemDBModel.fsBackremark = temp.menuBiz.voidReson;
/*

        if (temp.supportWeight()) {
            //结算单价; = (销售单价 or 会员价 or 特价)+加价;
            sellOrderItemDBModel.fdSettlePrice = temp.price;
            sellOrderItemDBModel.fdSubTotal = sellOrderItemDBModel.fdSaleQty.multiply(sellOrderItemDBModel.fdSettlePrice).add(sellOrderItemDBModel.fdAddPrice);

            if (temp.menuBiz.checkSpecialPrice()) {
                sellOrderItemDBModel.fdCouponSpecialAmt = sellOrderItemDBModel.fdSaleQty.subtract(sellOrderItemDBModel.fdBackQty).multiply(sellOrderItemDBModel.fdBargainPrice).add(sellOrderItemDBModel.fdAddPrice);
            }
            if (temp.menuBiz.checkMemberPrice() && temp.price.compareTo(temp.currentUnit.fdSalePrice) != 0 && temp.price.compareTo(sellOrderItemDBModel.fdVIPPrice) == 0) {
                sellOrderItemDBModel.fdCouponVipAmt = sellOrderItemDBModel.fdSaleQty.subtract(sellOrderItemDBModel.fdBackQty).multiply(sellOrderItemDBModel.fdVIPPrice).add(sellOrderItemDBModel.fdAddPrice);
            }
            if (sellOrderItemDBModel.fdBackQty.compareTo(BigDecimal.ZERO) > 0) {
                sellOrderItemDBModel.fdSaleAmt = BigDecimal.ZERO;
                //退货金额
                sellOrderItemDBModel.fdBackAmt = (sellOrderItemDBModel.fdOriginalAmt.multiply(temp.menuBiz.voidNum)).add(temp.menuBiz.priceExtraTotal);
            } else if (sellOrderItemDBModel.fdGiftqty.compareTo(BigDecimal.ZERO) > 0) {
                sellOrderItemDBModel.fdSaleAmt = BigDecimal.ZERO;
                //赠送金额;
                sellOrderItemDBModel.fdGiftAmt = (sellOrderItemDBModel.fdOriginalAmt.multiply(temp.menuBiz.giftNum)).add(temp.menuBiz.priceExtraTotal);
                sellOrderItemDBModel.fdCouponVipAmt = BigDecimal.ZERO;//菜品是赠送的话，那就不属于特价和会员价优惠
                sellOrderItemDBModel.fdCouponSpecialAmt = BigDecimal.ZERO;
            } else {
                BigDecimal quantity = sellOrderItemDBModel.fdSaleQty.subtract(sellOrderItemDBModel.fdGiftqty).subtract(sellOrderItemDBModel.fdBackQty);
                if (quantity.compareTo(BigDecimal.ZERO) > 0) {
                    sellOrderItemDBModel.fdSaleAmt = (quantity).multiply(sellOrderItemDBModel.fdSettlePrice).add(sellOrderItemDBModel.fdAddPrice);
                } else {
                    sellOrderItemDBModel.fdSaleAmt = BigDecimal.ZERO;
                }
            }
            //原始售卖价
            sellOrderItemDBModel.fdOriginalAmt = sellOrderItemDBModel.fdSaleQty.subtract(sellOrderItemDBModel.fdBackQty).multiply(sellOrderItemDBModel.fdOriginalAmt).add(sellOrderItemDBModel.fdAddPrice);
        } else {

        }
*/

        //结算单价; = (销售单价 or 会员价 or 特价)+加价;
        sellOrderItemDBModel.fdSettlePrice = temp.price.add(temp.menuBiz.priceExtraTotal);
        //赠送金额;
        sellOrderItemDBModel.fdGiftAmt = (sellOrderItemDBModel.fdOriginalAmt.add(temp.menuBiz.priceExtraTotal)).multiply(temp.menuBiz.giftNum);
        //退货金额
        sellOrderItemDBModel.fdBackAmt = temp.menuBiz.voidNum.multiply(sellOrderItemDBModel.fdOriginalAmt.add(temp.menuBiz.priceExtraTotal));
        sellOrderItemDBModel.fdSubTotal = sellOrderItemDBModel.fdSaleQty.multiply(sellOrderItemDBModel.fdSettlePrice);
        sellOrderItemDBModel.fdSaleAmt = (sellOrderItemDBModel.fdSaleQty.subtract(sellOrderItemDBModel.fdGiftqty).subtract(sellOrderItemDBModel.fdBackQty)).multiply(sellOrderItemDBModel.fdSettlePrice);
        //原始售卖价
        sellOrderItemDBModel.fdOriginalAmt = (sellOrderItemDBModel.fdSaleQty.subtract(sellOrderItemDBModel.fdBackQty)).multiply(sellOrderItemDBModel.fdOriginalAmt.add(temp.menuBiz.priceExtraTotal));
        if (sellOrderItemDBModel.fdGiftqty.compareTo(BigDecimal.ZERO) > 0) {
            sellOrderItemDBModel.fdCouponVipAmt = BigDecimal.ZERO;//菜品是赠送的话，那就不属于特价和会员价优惠
            sellOrderItemDBModel.fdCouponSpecialAmt = BigDecimal.ZERO;
        } else {
            if (temp.menuBiz.checkSpecialPrice()) {
                sellOrderItemDBModel.fdCouponSpecialAmt = (sellOrderItemDBModel.fdSaleQty.subtract(sellOrderItemDBModel.fdGiftqty).subtract(sellOrderItemDBModel.fdBackQty)).multiply(sellOrderItemDBModel.fdBargainPrice.add(temp.menuBiz.priceExtraTotal));
            }
            if (temp.menuBiz.checkMemberPrice() && temp.price.compareTo(temp.currentUnit.fdSalePrice) != 0 && temp.price.compareTo(sellOrderItemDBModel.fdVIPPrice) == 0) {
                sellOrderItemDBModel.fdCouponVipAmt = (sellOrderItemDBModel.fdSaleQty.subtract(sellOrderItemDBModel.fdGiftqty).subtract(sellOrderItemDBModel.fdBackQty)).multiply(sellOrderItemDBModel.fdVIPPrice.add(temp.menuBiz.priceExtraTotal));
            }
        }
        //套餐明细的增菜数量和退菜数量设置为0
        if (sellOrderItemDBModel.fiOrderItemKind == 3) {
            sellOrderItemDBModel.fdBackAmt = BigDecimal.ZERO;
            sellOrderItemDBModel.fdGiftAmt = BigDecimal.ZERO;
        }
        sellOrderItemDBModel.fdGiftAmt = sellOrderItemDBModel.fdGiftAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
        sellOrderItemDBModel.fdBackAmt = sellOrderItemDBModel.fdBackAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
        sellOrderItemDBModel.fdSubTotal = sellOrderItemDBModel.fdSubTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
        sellOrderItemDBModel.fdCouponVipAmt = sellOrderItemDBModel.fdCouponVipAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
        sellOrderItemDBModel.fdCouponSpecialAmt = sellOrderItemDBModel.fdCouponSpecialAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
        sellOrderItemDBModel.fdSaleAmt = sellOrderItemDBModel.fdSaleAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
        ////折后金额;=销售金额-折扣金额
        sellOrderItemDBModel.fdDisAfterAmt = sellOrderItemDBModel.fdSaleAmt.subtract(sellOrderItemDBModel.fdDiscountAmt);
        if (sellOrderItemDBModel.fdSaleAmt.compareTo(BigDecimal.ZERO) == 0 && sellOrderItemDBModel.fdfixedamount.compareTo(BigDecimal.ZERO) > 0) {
            sellOrderItemDBModel.fdDisAfterAmt = BigDecimal.ZERO;
        }
        sellOrderItemDBModel.fdSubTotal2 = sellOrderItemDBModel.fdDisAfterAmt;

        //套餐分账的相关字段
        //sellOrderItemDBModel.fiSplitStatus = temp.menuBiz.readSplite() ? 1 : 0;
        sellOrderItemDBModel.fdSplitDisBeforeAmt = temp.menuBiz.packageSplitOrigin;
        sellOrderItemDBModel.fdSplitDisAfterAmt = temp.menuBiz.packageSplitPrice;
        sellOrderItemDBModel.fdSplitDisAmt = temp.menuBiz.packageSplitDiscount;
        if (sellOrderItemDBModel.fiOrderItemKind == 3 && sellOrderItemDBModel.fiSplitStatus == 1) {
            sellOrderItemDBModel.fdOriginalAmt = sellOrderItemDBModel.fdSplitDisBeforeAmt;
        }

        //基础数据
        JSONObject localDBModel = DBSimpleUtil.queryJson(APPConfig.DB_MAIN, "select fsCreateTime,fsCreateUserId,fsCreateUserName,lver,pver from tbSellOrderItem  where fsSeq='" + temp.menuBiz.uniq + "'");
        UserDBModel userDBModel = HostDBUtil.getUserDBModel();
        String waiterID = userDBModel.fsUserId;
        String waiterName = userDBModel.fsUserName;
        String createTime = temp.menuBiz.createTime;
        if (seqModel != null) {
            if (!TextUtils.isEmpty(seqModel.createWaiterID)) {
                waiterID = seqModel.createWaiterID;
                waiterName = seqModel.createWaiterName;
            }
            if (TextUtils.isEmpty(createTime)) {
                createTime = seqModel.createTime;
            }
        }
        if (localDBModel == null || localDBModel.size() <= 0) {
            localDBModel = new JSONObject();
            localDBModel.put("fsCreateTime", temp.menuBiz.createTime);
            localDBModel.put("fsCreateUserId", waiterID);
            localDBModel.put("fsCreateUserName", waiterName);
            localDBModel.put("lver", 0);
            localDBModel.put("pver", 0);
        }
        //建立站点
        if (seqModel != null && !TextUtils.isEmpty(seqModel.createHostID)) {
            sellOrderItemDBModel.fsHostId = seqModel.createHostID;
        } else {
            sellOrderItemDBModel.fsHostId = HostDBUtil.getHostDBModel().fsHostId;
        }
        //建立日期时间;system time
        sellOrderItemDBModel.fsCreateTime = localDBModel.getString("fsCreateTime");
        //建立用户代码
        sellOrderItemDBModel.fsCreateUserId = localDBModel.getString("fsCreateUserId");
        //建立用户名称(开台人员)(服务员)
        sellOrderItemDBModel.fsCreateUserName = localDBModel.getString("fsCreateUserName");
        //修改日期时间
        sellOrderItemDBModel.fsUpdateTime = DateUtil.getCurrentDateTime(DateUtil.DATE_VISUAL14FORMAT);
        //门店GUID
        sellOrderItemDBModel.fsshopguid = menuItem.fsShopGUID;
        sellOrderItemDBModel.hassub = ListUtil.isEmpty(temp.menuBiz.selectedModifier) || ListUtil.isEmpty(temp.menuBiz.selectedPackageItems) ? 1 : 0;
        sellOrderItemDBModel.lver = localDBModel.getInteger("lver") + 3;
        sellOrderItemDBModel.pver = localDBModel.getInteger("pver");

        //--下单日期时间; 2014-03-23 12:30:49  (营业日期+当前时间)
        sellOrderItemDBModel.fsOrderTime = sellOrderItemDBModel.fsSellDate + " " + orderCache.createTime;
        if (sellOrderItemDBModel.fiOrderMode == 2) {
            sellOrderItemDBModel.fiOrderMode = 1;
        }
        if (sellOrderItemDBModel.fiOrderMode == 4) {
            sellOrderItemDBModel.fiOrderMode = 3;
        }

        sellOrderItemDBModel.changePriceTimes = temp.menuBiz.currentPriceTimes;
        //转菜信息
        //sellOrderItemDBModel.mergedItemInfo = temp.mergedItemInfo;
        //临时菜信息
        //sellOrderItemDBModel.fiIsTemporaryMenu = temp.isMenuTemporary() ? 1 : 0;
        return sellOrderItemDBModel;
    }


    /**
     * 创建订单SellDBModel
     *
     * @param orderCache OrderCache
     * @param fsShopGUID String
     * @return SellDBModel
     */
    public static SellDBModel createSellDBModel(OrderCache orderCache, String fsShopGUID, List<SellOrderItemDBModel> orderItemList, SellcheckDBModel payBillModel) {
        SellDBModel sellDBModel = new SellDBModel();
        //sellDBModel.fsSoftVersion = BizConstant.VERSION_NAME;
        sellDBModel.fiVersion = 4;
        sellDBModel.fssellno = orderCache.orderID;
        sellDBModel.fsSellDate = orderCache.businessDate.contains("-") ? orderCache.businessDate : DateUtil.formartDateStrToTarget(orderCache.businessDate, "yyyyMMdd", "yyyy-MM-dd");

        //	--销售时间(开台时间)
        sellDBModel.fsSellTime = orderCache.createTime;
        //	--餐段代码
        sellDBModel.fsMSectionId = "";

        //	--牌号;取餐号;
        sellDBModel.fsMealNumber = "";
        //	--订单类型 0： 正餐； 1：快餐
        sellDBModel.fiselltype = 1;

        //	--来客数
        sellDBModel.fiCustSum = orderCache.personNum;


        sellDBModel.fiBillKind = 1;

        sellDBModel.fsBillSourceId = "";
        sellDBModel.fsBillSourceName = "";
        sellDBModel.thirdOrderId = "";
        sellDBModel.thirdOrderType = 0;
        //	--来源单号
        sellDBModel.fsBillSourceNo = "";
        //第三方订单类型,see{@link com.mwee.android.pos.business.rapid.api.bean.model.NetOrderType}
        sellDBModel.fiBillSource = 0;
        //处理打赏
        sellDBModel.fsrewardinfo = "";

        sellDBModel.fiBillStatus = orderCache.orderStatus;

        //赠送金额
        BigDecimal fdGiftAmt = BigDecimal.ZERO;
        //退菜金额
        BigDecimal fdBackAmt = BigDecimal.ZERO;
        //折扣金额
        BigDecimal fdDiscountAmt = BigDecimal.ZERO;
        //服务费
        BigDecimal fdServiceAmt = BigDecimal.ZERO;
        //折前应收
        BigDecimal fdSaleAmt = BigDecimal.ZERO;
        //赠送数量
        BigDecimal fdGiftQty = BigDecimal.ZERO;
        //退菜数量
        BigDecimal fdBackQty = BigDecimal.ZERO;
        BigDecimal fdDisExpAmt = BigDecimal.ZERO;
        //原始售卖价
        BigDecimal fdOriginAmt = BigDecimal.ZERO;
        for (SellOrderItemDBModel temp : orderItemList) {
            if (temp.fiOrderItemKind == 3) {
                //如果是套餐明细,则不计入总价
                continue;
            }
            fdGiftAmt = fdGiftAmt.add(temp.fdGiftAmt);
            fdBackAmt = fdBackAmt.add(temp.fdBackAmt);
            fdDiscountAmt = fdDiscountAmt.add(temp.fdDiscountAmt);
            fdServiceAmt = fdServiceAmt.add(temp.fdServiceAmt);
            fdSaleAmt = fdSaleAmt.add(temp.fdSaleAmt);
            fdGiftQty = fdGiftQty.add(temp.fdGiftqty);
            fdBackQty = fdBackQty.add(temp.fdBackQty);
            fdOriginAmt = fdOriginAmt.add(temp.fdOriginalAmt);
            sellDBModel.fdDiscountMemAmt = sellDBModel.fdDiscountMemAmt.add(temp.fdDiscountMemAmt);
            sellDBModel.fdCouponVipAmt = sellDBModel.fdCouponVipAmt.add(temp.fdCouponVipAmt);

            sellDBModel.fdCouponSpecialAmt = sellDBModel.fdCouponSpecialAmt.add(temp.fdCouponSpecialAmt);

            if (temp.fiOrderItemKind != 3 && temp.fiIsDiscount == 1) {
                fdDisExpAmt = fdDisExpAmt.add(temp.fdSubTotal2);
                sellDBModel.fdOriginalAmtDis = sellDBModel.fdOriginalAmtDis.add(temp.fdOriginalAmt);
            }
        }
        fdServiceAmt = BigDecimal.ZERO;
        BigDecimal fdDiscountRoundAmt = BigDecimal.ZERO;
//
        BigDecimal fdDisExpRoundAmt = fdDisExpAmt;
        //	--免菜品服务费金额
        sellDBModel.fdFreeSveAmt = fdServiceAmt;
        //	--免服务费授权用户代码
        sellDBModel.fsFreeSveAmtUserId = "";
        //	--免服务费人
        sellDBModel.fsFreeSveAmtUserName = "";
        fdServiceAmt = BigDecimal.ZERO;

        BigDecimal fdExpAmtOrigin = fdSaleAmt.subtract(fdDiscountAmt);
        BigDecimal fdExpAmt = BigDecimal.ZERO;
        if (orderCache.whetherRound) {
            fdExpAmt = Calc.format(fdExpAmtOrigin, RoundConfig.ROUND_TOTAL);
        } else {
            fdExpAmt = fdExpAmtOrigin;
        }
        sellDBModel.fdRoundAmt = fdExpAmt.subtract(fdExpAmtOrigin);
        fdExpAmt = fdExpAmt.add(fdServiceAmt);
       /* if (orderCache.thirdOrder()) {
            fdExpAmt = fdExpAmtOrigin;
        }*/


        fdDisExpRoundAmt = fdDisExpRoundAmt.subtract(fdDisExpAmt);

        sellDBModel.fdGiftAmt = fdGiftAmt;
        sellDBModel.fdBackAmt = fdBackAmt;
        sellDBModel.fdDiscountAmt = fdDiscountAmt;
        sellDBModel.fdServiceAmt = fdServiceAmt;
        sellDBModel.fdSaleAmt = fdSaleAmt;
        sellDBModel.fdExpAmt = fdExpAmt;
        sellDBModel.fdDisExpAmt = fdDisExpAmt;
        sellDBModel.fdGiftRoundAmt = BigDecimal.ZERO;
        sellDBModel.fdBackRoundAmt = BigDecimal.ZERO;
        sellDBModel.fdDiscountRoundAmt = BigDecimal.ZERO;
        sellDBModel.fdServiceRoundAmt = BigDecimal.ZERO;
        sellDBModel.fdSaleRoundAmt = BigDecimal.ZERO;
        sellDBModel.fdDisExpRoundAmt = fdDisExpRoundAmt;
        sellDBModel.fdGiftQty = fdGiftQty;
        sellDBModel.fdBackQty = fdBackQty;
        if (fdOriginAmt.compareTo(BigDecimal.ZERO) <= 0) {
            fdOriginAmt = sellDBModel.fdSaleAmt;
        }
        sellDBModel.fdOriginalAmt = fdOriginAmt;

        sellDBModel.locked = 0;
        if (orderCache.selectDiscount != null) {
            sellDBModel.fsDiscountId = orderCache.selectDiscount.fsDiscountId;//	--折扣方案代号;由整单打折写入的
            sellDBModel.fsDiscountName = orderCache.selectDiscount.fsDiscountName;//	--折扣方案名称
        } else {
            List<JSONObject> discounNameList = DBSimpleUtil.queryJsonList(APPConfig.DB_MAIN, "select fsDiscountId,fsDiscountName from tbSellOrderItem where fsSellNo='" + sellDBModel.fssellno + "' and fsDiscountId<>'' group by fsDiscountId");
            if (discounNameList.size() > 1) {
                sellDBModel.fsDiscountId = "-1";//	--折扣方案代号;由整单打折写入的
                sellDBModel.fsDiscountName = "组合折扣";//	--折扣方案名称
            } else if (discounNameList.size() == 1) {
                sellDBModel.fsDiscountId = discounNameList.get(0).getString("fsDiscountId");//	--折扣方案代号;由整单打折写入的
                sellDBModel.fsDiscountName = discounNameList.get(0).getString("fsDiscountName");//	--折扣方案名称
            }
        }

        sellDBModel.fsCreditAccountId = "";//	--挂账对象代号
        sellDBModel.fsCreditAccountName = "";//	--挂账对象名称
        sellDBModel.fdDebtAmt = BigDecimal.ZERO;//	--挂账金额
        sellDBModel.fdDiffAmt = BigDecimal.ZERO;
        if (payBillModel != null) {
            //--已付金额;
            sellDBModel.fdPayAmt = payBillModel.fdReceAmt;
            //	--差額(未付溢收);=已付金额-应收金额，<0为未付、>0为溢收(长款)
            //	--实收金额;营业金额净值; 由付款方式.是否计入实收=1 算出
            sellDBModel.fdRealAmt = payBillModel.fdRealAmt;
            sellDBModel.locked = payBillModel.locked;
            sellDBModel.fsCreditAccountId = payBillModel.fsCreditAccountId;//	--挂账对象代号
            sellDBModel.fsCreditAccountName = payBillModel.fsCreditAccountName;//	--挂账对象名称
            sellDBModel.fdDebtAmt = payBillModel.fdDebtAmt;//	--挂账金额
        }

        sellDBModel.fsNote = orderCache.note;//	--销售单备注/外送备注;当为拆分餐单时，备注写 由原单1501080001拆出;当为合併餐单时，原桌单备注写 併入1501080002

        sellDBModel.fsPrintExpTime = "";//	--打印消费单时间
        if (payBillModel != null && !TextUtils.isEmpty(payBillModel.fsCheckTime)) {
            sellDBModel.fsCheckEndTime = DateUtil.formartDateStrToTarget(payBillModel.fsCheckTime, DateUtil.DATE_VISUAL14FORMAT, "HH:mm");//	--结帐完成时间
        } else {
            sellDBModel.fsCheckEndTime = "";
        }


        /*- 反结账授权信息 -*/
        sellDBModel.fsReCheckUserId = "";//	--反结授权用户代码
        sellDBModel.fsReCheckUserName = "admin";//	--反结授权人
        sellDBModel.fsReCheckReason = "";//	--反结理由

        /*- 销售人员信息,暂不处理 -*/
        sellDBModel.fsStaffId = "";//	--销售人员代碼;客户经理
        sellDBModel.fsStaffName = "";//	--销售人员名称;UserName

        /*- 关联信息,暂不处理 -*/
        sellDBModel.fsMasterSellNo = "";//	--主销售单号;用於 关联桌、并桌、拆分餐单
        sellDBModel.fsRelationMTable = "";//	--关联桌号全名; 主、副桌餐桌号列表，小票打印用，ex:C001sellDBModel.C002sellDBModel.C003

        /*- 发票信息,暂不处理 -*/
        sellDBModel.fiIsInvoice = 0;//	--是否已开发票;0=false/1=ture
        sellDBModel.fsInvoiceCls = "";//	--开票类别;個人/公司
        sellDBModel.fsInvoiceTitle = "";//	--发票抬头
        sellDBModel.fdInvoiceAmt = BigDecimal.ZERO;//	--发票开出金额

        sellDBModel.fsVIPSource = "";//	--会员来源
        sellDBModel.fiIsVIP = 0;//	--是否为会员;0=false/1=ture

        sellDBModel.fsCardNo = "";
        /*- 外送信息,暂不处理 -*/
        sellDBModel.fsSendAddr = "";//	--送餐地址
        sellDBModel.fsSendOnTime = "";//	--期望送达时间
        sellDBModel.fsSendUserId = "";//	--派送人员代码;fsDelivererId
        sellDBModel.fsSendUserName = "";//	--派送人员名称;fsDelivererName
        sellDBModel.fsPutUpTime = "";//	--挂单时间(食堂单挂单、外送下单)
        sellDBModel.fsAssignTime = "";//	--派单时间

        sellDBModel.fsMAreaId = orderCache.fsmareaid;//	--餐区代碼
        if (orderCache.orderStatus == OrderStatus.PAIED) {
            //sellDBModel.fsMTableId = FormatUtil.restoreTableID(orderCache.fsmtableid);//	--餐桌代碼
        } else {
            sellDBModel.fsMTableId = orderCache.fsmtableid;//	--餐桌代碼
        }
        sellDBModel.fsMTableName = orderCache.fsmtablename;//	--餐桌名稱


        /*- 客户信息,暂不处理 -*/
        sellDBModel.fsCustGUID = "";//	--客户Guid
        sellDBModel.fsCustId = "";//	--客户号;微信号
        //sellDBModel.fsCustName = orderCache.fsCustName;//	--客户名称
        //sellDBModel.fsCustMobile = orderCache.fsCustMobile;//	--客户手机号

        //别的信息
        sellDBModel.fiPrintTimes_Op = 0;//	--开台单打印次数
        //sellDBModel.fiPrintTimes_Exp = orderCache.printPre;//	--消费单打印次数

        sellDBModel.fiIsVerify = 0;//	--是否审核;0=false/1=ture
        sellDBModel.fsVerifyUserName = "";//	--审核用户名称
        sellDBModel.fsVerifyTime = "";//	--审核日期时间;system time

        sellDBModel.fiModifyTag = 0;//	--是否改单(改价、改数)标签;0=false/1=ture
        sellDBModel.fiReCheckTag = sellDBModel.fiBillStatus == OrderStatus.ANTI_BACK ? 1 : 0;//	--是否反结标签;0=false/1=ture
        sellDBModel.voidreason = "";//退单原因
        sellDBModel.fsHostId = "";//	--建立站点
        sellDBModel.mergedOrderID = "";
        sellDBModel.mergedOrderInfo = "";
        sellDBModel.fiIsInvoice = 0;//需要开发票
        sellDBModel.fsInvoiceTitle = "";
        sellDBModel.fsInvoiceCls = "";
        sellDBModel.fsdutyparagraph = "";
        sellDBModel.fiIsInvoice = 0;
        //备用字段
        sellDBModel.fsbackup0 = "0";

        String time = DateUtil.getCurrentDateTime(DateUtil.DATE_VISUAL14FORMAT);
        JSONObject localDBModel = DBSimpleUtil.queryJson(APPConfig.DB_MAIN, "select fsCreateTime,fsCreateUserId,fsCreateUserName,lver,pver from tbSell where fsSellNo='" + sellDBModel.fssellno + "'");

        if (localDBModel == null || localDBModel.size() <= 0) {
            localDBModel = new JSONObject();
            localDBModel.put("fsCreateTime", sellDBModel.fsSellDate + " " + orderCache.createTime);
            //localDBModel.put("fsCreateUserId", orderCache.waiterID);
            //localDBModel.put("fsCreateUserName", orderCache.waiterName);
            localDBModel.put("lver", 0);
            localDBModel.put("pver", 0);
        }
        sellDBModel.fsCreateTime = localDBModel.getString("fsCreateTime");//	--建立日期时间;system time
        sellDBModel.fsCreateUserId = localDBModel.getString("fsCreateUserId");//	--建立用户代码
        sellDBModel.fsCreateUserName = localDBModel.getString("fsCreateUserName");//	--建立用户名称(开台人员)(服务员)
        sellDBModel.fsUpdateTime = time;//	--修改日期时间
        sellDBModel.fsshopguid = fsShopGUID;//	--门店GUID
        sellDBModel.lver = localDBModel.getInteger("lver") + 3;
        sellDBModel.pver = localDBModel.getInteger("pver");

        //订单是否显示
        sellDBModel.fiSelected = 0;
        //客户端类型
        //sellDBModel.clientType = APPConfig.getClientType();


        //区域名称AreaName
       /* if (TextUtils.isEmpty(orderCache.areaName)) {
            sellDBModel.areaName = DBSimpleUtil.queryString(APPConfig.DB_MAIN, "select fsMAreaName from tbmarea where fsMAreaId = '" + orderCache.fsmareaid + "'");
        } else {
            sellDBModel.areaName = orderCache.areaName;
        }
        //总店Id
        sellDBModel.companyGuid = DBSimpleUtil.queryString(APPConfig.DB_MAIN, "select fsCompanyGUID from tbShop where fsShopGUID = '" + fsShopGUID + "'");
        //餐段名称SectionName
        sellDBModel.sectionName = DBSimpleUtil.queryString(APPConfig.DB_MAIN, "select fsMSectionName from tbmsection where fsMSectionId='" + orderCache.currentSectionID + "'");
        //开台站点名称HostName  站点名字
        sellDBModel.hostName = orderCache.currentHostID;
        sellDBModel.clientType = APPConfig.getClientType();
*/
        sellDBModel.replaceNoTrans();
        return sellDBModel;
    }


    /**
     * 创建OrderSeq
     *
     * @param seqModel    int
     * @param sellDBModel SellDBModel
     * @return SellOrderDBModel
     */
    public static SellOrderDBModel createOrderSeq(OrderCache orderCache, OrderSeqModel seqModel, SellDBModel sellDBModel) {
        SellOrderDBModel sellOrderDBModel = new SellOrderDBModel();
        sellOrderDBModel.fssellno = sellDBModel.fssellno;
        sellOrderDBModel.fiorderseq = seqModel.seqNo;
        sellOrderDBModel.fiOrderCls = 1;
        sellOrderDBModel.fsSellDate = orderCache.businessDate.contains("-") ? orderCache.businessDate : DateUtil.formartDateStrToTarget(orderCache.businessDate, "yyyyMMdd", "yyyy-MM-dd");//	--销售日期(开台日期)(营业日期)
        sellOrderDBModel.fiOrderSte = seqModel.seqStatus;
        JSONObject localDBModel = DBSimpleUtil.queryJson(APPConfig.DB_MAIN, "select fsCreateTime,fsCreateUserId,fsCreateUserName,lver,pver from tbSellOrder where fsSellNo='" + sellDBModel.fssellno + "' and fiOrderSeq='" + sellOrderDBModel.fiorderseq + "'");
        String time = DateUtil.getCurrentDateTime(DateUtil.DATE_VISUAL14FORMAT);
        if (localDBModel == null || localDBModel.size() <= 0) {
            localDBModel = new JSONObject();
            if (!TextUtils.isEmpty(seqModel.createTime)) {
                localDBModel.put("fsCreateTime", seqModel.createTime);
            }
            if (!TextUtils.isEmpty(seqModel.createWaiterID)) {
                localDBModel.put("fsCreateUserId", seqModel.createWaiterID);
                localDBModel.put("fsCreateUserName", seqModel.createWaiterName);
            } else {
                UserDBModel userDBModel = HostDBUtil.getUserDBModel();
                localDBModel.put("fsCreateUserId", userDBModel.fsUserId);
                localDBModel.put("fsCreateUserName", userDBModel.fsUserName);
            }
            localDBModel.put("lver", 0);
            localDBModel.put("pver", 0);
        }
        sellOrderDBModel.fsCreateTime = localDBModel.getString("fsCreateTime");
        sellOrderDBModel.fsCreateUserId = localDBModel.getString("fsCreateUserId");
        sellOrderDBModel.fsCreateUserName = localDBModel.getString("fsCreateUserName");
        sellOrderDBModel.fsUpdateTime = time;
        sellOrderDBModel.fsshopguid = sellDBModel.fsshopguid;
        sellOrderDBModel.lver = localDBModel.getInteger("lver") + 3;
        sellOrderDBModel.pver = localDBModel.getInteger("pver");
        sellOrderDBModel.replaceNoTrans();
        LogUtil.log("orderPaySuccess", JSON.toJSONString(sellDBModel));
        return sellOrderDBModel;
    }

}
