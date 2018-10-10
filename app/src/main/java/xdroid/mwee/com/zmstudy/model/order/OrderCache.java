package xdroid.mwee.com.zmstudy.model.order;

import android.text.TextUtils;

import com.mwee.android.tools.DateUtil;
import com.mwee.android.tools.base.BusinessBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import xdroid.mwee.com.model.util.db.DiscountDBModel;
import xdroid.mwee.com.model.util.db.UserDBModel;
import com.mwee.android.tools.ListUtil;
import xdroid.mwee.com.zmstudy.model.menu.MenuItem;
import xdroid.mwee.com.zmstudy.utils.Calc;
import xdroid.mwee.com.zmstudy.utils.RoundConfig;

/**
 * OrderCache
 * Created by virgil on 16/6/14.
 */
public class OrderCache extends BusinessBean {
    /**
     * 完整订单号
     */
    public String orderID;
    /**
     * 订单的单序列表
     */
    public List<OrderSeqModel> seqList = new ArrayList<>();
    /**
     * 当前单序
     */
    public int currentSeq = 1;

    /**
     * 桌台信息,--餐区代碼
     */
    public String fsmareaid = "";
    /**
     * 桌台信息,--餐区名称
     */
    public String areaName = "";
    /**
     * --餐桌代碼
     */
    public String fsmtableid = "";
    /**
     * --餐桌名稱
     */
    public String fsmtablename = "";
    /**
     * 订单营业日期
     */
    public String businessDate = "";
    /**
     * 订单创建时间,必须是HH:mm:ss格式
     */
    public String createTime = "";
    /**
     * 订单总价
     */
    public BigDecimal totalPrice = BigDecimal.ZERO;

    /**
     * 订单圆整
     */
    public BigDecimal totalRound = BigDecimal.ZERO;
    /**
     * 菜品总价，等于totalPrice-tempTotalService
     */
    public BigDecimal totalMenuPrice = BigDecimal.ZERO;

    /**
     * 销售金额,不包括折扣金额和赠送金额
     */
    public BigDecimal priceTotalOriginAfterGift = BigDecimal.ZERO;
    /**
     * 菜品总数量
     */
    public BigDecimal totalCount = BigDecimal.ZERO;
    /**
     * 已下单的菜品
     */
    public List<MenuItem> originMenuList = new ArrayList<>();

    /**
     * 临时选择的菜品
     */
    public List<MenuItem> tempMenuList = new ArrayList<>();
    /**
     * 用餐人数,默认1个人
     */
    public int personNum = 1;
    /**
     * 订单状态;see{@link OrderStatus}
     */
    public int orderStatus = OrderStatus.CREATED;

    /**
     * 是否需要圆整
     */
    public boolean whetherRound = true;

    /**
     * 整单备注
     */
    public String note = "";
    /**
     * 折扣方案，不使用
     */
    @Deprecated
    public DiscountDBModel selectDiscount = null;

    /**
     * 折扣免掉的金额
     */
    public BigDecimal totalDiscountAmount = BigDecimal.ZERO;

    /**
     * 当前订单对应的门店ID
     */
    public String shopID = "";


    /**
     * 重新计算订单的所有价格
     */
    public void reCalcAllByAll() {

        totalPrice = BigDecimal.ZERO;
        totalCount = BigDecimal.ZERO;
        priceTotalOriginAfterGift = BigDecimal.ZERO;
        totalDiscountAmount = BigDecimal.ZERO;

        if (!ListUtil.isEmpty(originMenuList)) {
            for (MenuItem item : originMenuList) {

                item.calcTotal();

                //更新折前价
                priceTotalOriginAfterGift = priceTotalOriginAfterGift.add(item.menuBiz.priceTotalOriginAfterGift);

                totalCount = totalCount.add(item.menuBiz.buyNum);
                totalDiscountAmount = totalDiscountAmount.add(item.menuBiz.discountAmount);
            }
        }


        //应收
        totalPrice = priceTotalOriginAfterGift.subtract(totalDiscountAmount);
        totalRound = totalPrice;
        totalMenuPrice = totalPrice;
        //对应收进行圆整
        if (whetherRound) {
            totalPrice = Calc.format(totalPrice, RoundConfig.ROUND_TOTAL);
        }
        totalRound = totalRound.subtract(totalPrice);

        //折前价-应收得到折扣金额
        totalDiscountAmount = priceTotalOriginAfterGift.subtract(totalMenuPrice);

    }


    /**
     * 将当前订单保存到DB的未完成列表里
     */
  /*  public void saveUnfinish() {
        if (!StringUtil.emptyInt(orderID)) {
            OrderSaveDBUtil.save(orderID, this, true);
        }
    }*/

    /**
     * 清除订单所有信息
     */
    public void clear() {
        personNum = 1;
        orderStatus = OrderStatus.CREATED;
        createTime = DateUtil.getCurrentDateTime("HH:mm:ss");
        clearAllMenu();
    }


    /**
     * 清除订单菜品部分的信息
     */
    public void clearAllMenu() {
        originMenuList.clear();
        tempMenuList.clear();
        seqList.clear();
        totalPrice = BigDecimal.ZERO;
        totalCount = BigDecimal.ZERO;
        priceTotalOriginAfterGift = BigDecimal.ZERO;
        note = "";
        selectDiscount = null;
        totalDiscountAmount = BigDecimal.ZERO;
    }

    /**
     * 清除掉所有折扣数据
     */
    public boolean clearAllMemberDiscount(boolean reCalc) {
        boolean hasDiscount = false;
        if (!originMenuList.isEmpty()) {
            for (MenuItem item : originMenuList) {
                if (item.menuBiz.selectDiscount != null) {
                    hasDiscount = true;
                }
            }
            if (selectDiscount != null) {
                hasDiscount = true;
                selectDiscount = null;
            }
        }
        if (reCalc) {
            reCalcAllByAll();
        }

        return hasDiscount;
    }


    /**
     * 更新单序的状态
     *
     * @param seqNO  int | 单序
     * @param status int | 状态
     */
    public void updateSeqStatus(int seqNO, int status, UserDBModel user, String hostID) {
        OrderSeqModel model = optSeqModel(seqNO);
        if (model == null) {
            model = new OrderSeqModel();
            model.createTime = DateUtil.getCurrentTime();

            model.seqNo = seqNO;
            seqList.add(model);
        }
        if (user != null && TextUtils.isEmpty(model.createWaiterID)) {
            model.createWaiterID = user.fsUserId;
            model.createWaiterName = user.fsUserName;
        }
        if (!TextUtils.isEmpty(hostID)) {
            model.createHostID = hostID;
        }
        model.seqStatus = status;
    }

    /**
     * 更新all单序的状态
     *
     * @param status int | 状态
     */
    public void updateAllSeqStatus(int status) {
        for (OrderSeqModel model : seqList) {
            if (model == null) {
                continue;
            }
            if (model.seqStatus == OrderSeqStatus.ORDERED) {
                model.seqStatus = status;
            }
        }
    }


    /**
     * 获取单序的状态
     *
     * @param seqNO
     * @return
     */
    public int optSeqStatus(int seqNO) {
        int status = 0;
        OrderSeqModel model = optSeqModel(seqNO);
        if (model != null) {
            status = model.seqStatus;
        }
        return status;
    }

    /**
     * 判断当前点菜批次是否是已点单状态（包括：已下单 和 反结账）
     *
     * @param seqNo 点菜批次
     * @return true代表已点单，false 未点单
     */
    public boolean isOrderedSeqNo(int seqNo) {
        int status = optSeqStatus(seqNo);
        return status == OrderSeqStatus.ORDERED || status == OrderSeqStatus.ANTIPAY;
    }

    public int optLastSeq() {
        int seqNo = 0;
        OrderSeqModel model = null;
        if (seqList != null && seqList.size() > 0) {
            model = seqList.get(seqList.size() - 1);
        }
        if (model != null) {
            seqNo = model.seqNo;
        }
        return seqNo;
    }

    public OrderSeqModel optSeqModel(int seqNO) {
        OrderSeqModel model = null;
        for (int i = 0; i < seqList.size(); i++) {
            OrderSeqModel temp = seqList.get(i);
            if (temp.seqNo == seqNO) {
                model = temp;
                break;
            }
        }
        return model;
    }


    @Override
    public OrderCache clone() {
        OrderCache cloneObj = null;
        try {
            cloneObj = (OrderCache) super.clone();
            if (originMenuList != null) {
                cloneObj.originMenuList = ListUtil.cloneList(originMenuList);
            }
            if (tempMenuList != null) {
                cloneObj.tempMenuList = ListUtil.cloneList(tempMenuList);
            }

            if (selectDiscount != null) {
                cloneObj.selectDiscount = selectDiscount.clone();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }


    @Override
    public String toString() {
        return "OrderCache{" +
                "orderID='" + orderID + '\'' +
                ", currentSeq=" + currentSeq +
                ", businessDate='" + businessDate + '\'' +
                ", createTime='" + createTime + '\'' +
                ", totalPrice=" + totalPrice +
                ", priceTotalOrigin=" + priceTotalOriginAfterGift +
                ", totalCount=" + totalCount +
                ", originMenuList=" + originMenuList +
                ", personNum=" + personNum +
                ", orderStatus=" + orderStatus +
                ", note='" + note + '\'' +
                ", selectDiscount=" + selectDiscount +
                ", totalDiscountAmount=" + totalDiscountAmount +
                ", shopID='" + shopID + '\'' +
                '}';
    }

}
