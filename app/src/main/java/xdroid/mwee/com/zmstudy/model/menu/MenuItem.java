package xdroid.mwee.com.zmstudy.model.menu;

import com.mwee.android.tools.DateUtil;
import com.mwee.android.tools.base.BusinessBean;

import java.math.BigDecimal;

import com.mwee.android.tools.ToastUtil;
import xdroid.mwee.com.model.util.db.AskDBModel;
import com.mwee.android.tools.ListUtil;
import xdroid.mwee.com.zmstudy.utils.Calc;
import xdroid.mwee.com.zmstudy.utils.RoundConfig;

/**
 * MenuItem
 * Created by virgil on 16/6/15.
 *
 * @author Virgil
 */
public class MenuItem extends BusinessBean {

        /*=======================基础数据=======================*/
    /**
     * 菜品唯一标示符
     */
    public int itemID = 0;

    /**
     * 名称
     */
    public String name = "";

    /**
     * 结算单价
     */
    public BigDecimal price = BigDecimal.ZERO;

    /**
     * 是否是品类
     */
    public boolean isCategory = false;
    /**
     * 品类代码
     */
    public String categoryCode = "";

    /*======================= 业务变化数据=======================*/
    /**
     * 初始化为null，提高clone性能
     */
    public MenuBiz menuBiz = null;


    /*=======================DB原始信息=======================*/
    /**
     * 当前规格信息
     */
    public UnitModel currentUnit = null;

    /**
     * 正餐的做法信息,快餐的做法字段参见{@link MenuBiz#selectProcedure}
     */
    @Deprecated
    public AskDBModel currentPractice = null;


    /**
     * 菜品编号匹配
     */
    public String fsItemId = "";


    /**
     * 配置项， 位操作
     * 1，是否必选做法
     * 2，是否多规格
     * 4，是否多做法
     * 8，是否可赠送
     * 16,是否支持打折
     * 32，是否支持自定义称重
     * 64，是否招牌菜
     * 128，是否新菜
     * 256，是否是套餐
     * 512，是否是时价菜
     * 1024, 是否有配料
     * 2048，是套餐时，需要分账
     * 4096，套餐明细时，是否默认选中
     * 8192，收取服务费
     * 16384,是否外卖
     * 32768,是否打印
     * 65536,是否做法多选
     * 131072,是否是热门菜
     */
    public int config = 0;


    /**
     * 计算当前菜品的总价
     */
    public void calcTotal() {
        if (currentUnit == null) {
            ToastUtil.showToast("当前菜品没有配置规格");
            return;
        }

        menuBiz.totalPrice = BigDecimal.ZERO;
        menuBiz.priceExtraTotal = BigDecimal.ZERO;
        menuBiz.priceTotalOriginAfterGift = BigDecimal.ZERO;
        menuBiz.priceService = BigDecimal.ZERO;
        menuBiz.decreasConfig(16);

        //计算做法的加价
        BigDecimal extraPrice = BigDecimal.ZERO;
        //正餐的做法
        /*if (currentPractice != null) {
            extraPrice = extraPrice.add(currentPractice.fdAddPrice);
        }*/
        for (AskDBModel askDBModel : menuBiz.selectMulProcedure) {
            extraPrice = extraPrice.add(askDBModel.fdAddPrice);
        }

        if (menuBiz.selectNote != null && menuBiz.selectNote.size() > 0) {
            for (NoteItemModel temp : menuBiz.selectNote) {
                //目前套餐仅支持固定价格
                if (temp != null) {
                    if (temp.selected && temp.totalPrice.compareTo(BigDecimal.ZERO) != 0) {
                        extraPrice = extraPrice.add(temp.totalPrice);
                    }
                }
            }
        }
        if (menuBiz.selectOrderNote != null && menuBiz.selectOrderNote.size() > 0) {
            for (NoteItemModel temp : menuBiz.selectOrderNote) {
                if (temp != null) {
                    //目前套餐仅支持固定价格
                    if (temp.selected && temp.totalPrice.compareTo(BigDecimal.ZERO) != 0) {
                        extraPrice = extraPrice.add(temp.totalPrice);
                    }
                }
            }
        }

        BigDecimal singlePrice;

        singlePrice = currentUnit.fdSalePrice;
        menuBiz.decreasConfig(16);

        singlePrice = Calc.format(singlePrice, RoundConfig.ROUND_SINGLE_PRICE);
        price = singlePrice;

        //处理菜品要求
        if (extraPrice.compareTo(BigDecimal.ZERO) < 0 && extraPrice.add(singlePrice).compareTo(BigDecimal.ZERO) < 0) {
            BigDecimal chajia = extraPrice.add(singlePrice).abs();
            //加价导致菜品总价格为负数 将溢出的价格填到某个菜品要求上
            if (!ListUtil.isEmpty(menuBiz.selectNote)) {
                menuBiz.selectNote.get(0).totalPrice = menuBiz.selectNote.get(0).totalPrice.add(chajia);
            } else if (!ListUtil.isEmpty(menuBiz.selectOrderNote)) {
                menuBiz.selectOrderNote.get(0).totalPrice = menuBiz.selectOrderNote.get(0).totalPrice.add(chajia);
            }
            extraPrice = BigDecimal.ZERO.subtract(singlePrice);
        }

        menuBiz.priceExtraTotal = extraPrice;

        menuBiz.priceTotalOriginAfterGift = (singlePrice.add(extraPrice)).multiply(menuBiz.buyNum.subtract(menuBiz.voidNum).subtract(menuBiz.giftNum));
        menuBiz.priceTotalOriginAfterGift = menuBiz.priceTotalOriginAfterGift.setScale(2, BigDecimal.ROUND_HALF_UP);

        menuBiz.totalPrice = menuBiz.priceTotalOriginAfterGift;
        menuBiz.discountAmount = BigDecimal.ZERO;
        menuBiz.totalPrice = menuBiz.totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        if (menuBiz.menuSellType == MenuSellType.GIFT) {
            //如果是手动免单
            menuBiz.totalPrice = BigDecimal.ZERO;
        }
        if (menuBiz.menuSellType != MenuSellType.ADD) {
            menuBiz.priceTotalOriginAfterGift = BigDecimal.ZERO;
            menuBiz.totalPrice = BigDecimal.ZERO;
        }

        if (!ListUtil.isEmpty(menuBiz.selectedModifier)) {
            for (MenuItem menuItem : menuBiz.selectedModifier) {
                //配料不使用会员价
                menuItem.calcTotal();
            }
        }
    }


    /**
     * 更新规格单位信息
     *
     * @param currentUnit MenuItemUnitDBModel
     */
    public void updateCurrentUnit(UnitModel currentUnit) {
        this.currentUnit = currentUnit;
        refreshUnit();
    }

    /**
     * 还原为默认单位
     */
    public void restoreCurrentUnit() {
        //this.currentUnit = defaultUnit;
        refreshUnit();
    }

    /**
     * 根据单位信息,刷新相关数据
     */
    private void refreshUnit() {
        if (currentUnit != null) {
            this.price = Calc.format(currentUnit.fdSalePrice, RoundConfig.ROUND_SINGLE_PRICE);
        }
    }

    /**
     * 更新单序
     *
     * @param orderSeq int
     */
    public void updateOrderSeq(int orderSeq) {
        this.menuBiz.orderSeqID = orderSeq;
        if (!ListUtil.isEmpty(this.menuBiz.selectedModifier)) {
            for (MenuItem modifier : this.menuBiz.selectedModifier) {
                modifier.menuBiz.orderSeqID = orderSeq;
            }
        }
    }

    @Override
    public MenuItem clone() {
        MenuItem cloneObj = null;
        try {
            cloneObj = (MenuItem) super.clone();
            if (menuBiz != null) {
                cloneObj.menuBiz = menuBiz.clone();
            }
            if (currentPractice != null) {
                cloneObj.currentPractice = currentPractice.clone();
            }
            if (currentUnit != null) {
                cloneObj.currentUnit = currentUnit.clone();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

    @Override
    public String toString() {
        return "[" + name + " " + menuBiz.buyNum + currentUnit.fsOrderUint + "(" + itemID + ")]";
    }


    /**
     * 初始化点菜信息
     *
     * @param currentSeq
     */
    public void init(int currentSeq) {
        if (menuBiz == null) {
            menuBiz = new MenuBiz();
        }
        //改菜品点餐单序
        updateOrderSeq(currentSeq);
        //改菜品唯一标示
        updateUniq();
        //代表点菜时间 此字段在业务中心下单时候会重新设置
        menuBiz.createTime = DateUtil.getCurrentTime();
        if (currentUnit.fiInitCount > 0) {
            menuBiz.buyNum = new BigDecimal(currentUnit.fiInitCount);
        } else {
            menuBiz.buyNum = BigDecimal.ONE;
        }
        calcTotal();
    }

    public void updateUniq() {
        menuBiz.generateUniq();
        if (!ListUtil.isEmpty(menuBiz.selectedModifier)) {
            for (MenuItem item : menuBiz.selectedModifier) {
                item.menuBiz.generateUniq();
            }
        }
        if (!ListUtil.isEmpty(menuBiz.selectedPackageItems)) {
            for (MenuItem menuItem : menuBiz.selectedPackageItems) {
                menuItem.menuBiz.generateUniq();
            }
        }
    }

}
