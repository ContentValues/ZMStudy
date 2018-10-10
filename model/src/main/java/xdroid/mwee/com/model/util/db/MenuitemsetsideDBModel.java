package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbmenuitemsetside")
public class MenuitemsetsideDBModel extends DBModel {
    @ColumnInf(name = "fsSetFoodName2")
    public String fsSetFoodName2 = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fsShopGUID")
    public String fsShopGUID = "";
    /**
     * 是否必选项目;0/1
     */
    @ColumnInf(name = "fiIsRequired")
    public int fiIsRequired = 0;
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fiSortOrder")
    public int fiSortOrder = 0;
    @ColumnInf(name = "fiItemCd_M", primaryKey = true)
    public int fiItemCd_M = 0;
    @ColumnInf(name = "fiSetFoodCd", primaryKey = true)
    public int fiSetFoodCd = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fiSetFoodQty")
    public int fiSetFoodQty = 0;
    @ColumnInf(name = "fsSetFoodName")
    public String fsSetFoodName = "";
    @ColumnInf(name = "fiSetFoodType")
    public int fiSetFoodType = 0;//配菜项类型(0=固定/1=可选)
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    /**
     * 分账百分比
     */
    @ColumnInf(name = "fiSplitPrice")
    public BigDecimal fiSplitPrice = BigDecimal.ZERO;

    /**
     * 套餐分账折扣金额，计算用，非表结构
     */
    public BigDecimal packageSplitDiscount = BigDecimal.ZERO;
    /**
     * 套餐分账折前金额，计算用，非表结构
     */
    public BigDecimal packageSplitOrigin = BigDecimal.ZERO;
    /**
     * 套餐分账的最终价格，计算用，非表结构
     */
    public BigDecimal packageSplitPrice = BigDecimal.ZERO;

    public MenuitemsetsideDBModel() {

    }

    @Override
    public MenuitemsetsideDBModel clone() {
        MenuitemsetsideDBModel cloneObj = null;
        try {
            cloneObj = (MenuitemsetsideDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}