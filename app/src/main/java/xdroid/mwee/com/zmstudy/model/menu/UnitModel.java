package xdroid.mwee.com.zmstudy.model.menu;

import com.mwee.android.tools.base.BusinessBean;

import java.math.BigDecimal;

import xdroid.mwee.com.model.util.db.MenuItemUnitDBModel;

/**
 * 菜品规格model
 * Created by qinwei on 2017/8/21.
 */

public class UnitModel extends BusinessBean {
    //门店的GUID
    public String fsShopGUID = "";
    //数据状态;0未同步到店DB / 1正常 / 2临时沽清 / 3数量沽清 /13删除
    public int fiStatus = 0;
    /**
     * 菜品ID
     */
    public int fiItemCd = 0;
    //会员单价
    public BigDecimal fdVIPPrice = BigDecimal.ZERO;

    /**
     * 规格ID
     */
    public int fiOrderUintCd = 0;
    /**
     * 规格单位;份 只 盘
     */
    public String fsOrderUint = "";
    //销售单价
    public BigDecimal fdSalePrice = BigDecimal.ZERO;
    //特价
    public BigDecimal fdBargainPrice = BigDecimal.ZERO;
    /**
     * 原始售卖价,和“fdSalePrice”一致，仅内存中使用
     */
    public BigDecimal fdOriginPrice = BigDecimal.ZERO;
    //库存数量
    public BigDecimal fdInvQty = BigDecimal.ZERO;

    //起点数量
    public int fiInitCount = 0;

    public UnitModel(){

    }

    public static UnitModel copyTo(MenuItemUnitDBModel model) {
        if (model == null) {
            return null;
        }
        UnitModel unitModel = new UnitModel();
        unitModel.fdInvQty = model.fdInvQty;
        unitModel.fdSalePrice = model.fdSalePrice;
        unitModel.fdVIPPrice = model.fdVIPPrice;
        unitModel.fdOriginPrice = model.fdOriginPrice;
        if (unitModel.fdOriginPrice == null || unitModel.fdOriginPrice.compareTo(BigDecimal.ZERO) <= 0) {
            unitModel.fdOriginPrice = model.fdSalePrice;
        }
        unitModel.fiInitCount = model.fiInitCount;
        unitModel.fiItemCd = model.fiItemCd;
        unitModel.fiOrderUintCd = model.fiOrderUintCd;

        unitModel.fiStatus = model.fiStatus;
        unitModel.fsOrderUint = model.fsOrderUint;
        unitModel.fsShopGUID = model.fsShopGUID;
        return unitModel;
    }

    @Override
    public UnitModel clone() throws CloneNotSupportedException {
        UnitModel cloneObj = null;
        try {
            cloneObj = (UnitModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}
