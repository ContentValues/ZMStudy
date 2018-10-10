package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbmenuitemsetsidedtl")
public class MenuitemsetsidedtlDBModel extends DBModel {
    @ColumnInf(name = "fiDefault")
    public int fiDefault = 0;
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fdBargainPrice")
    public BigDecimal fdBargainPrice = BigDecimal.ZERO;  //折算单价

    @ColumnInf(name = "fiSortOrder")
    public int fiSortOrder = 0;
    @ColumnInf(name = "fiSetFoodCd", primaryKey = true)
    public int fiSetFoodCd = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fiItemCd", primaryKey = true)
    public int fiItemCd = 0;
    @ColumnInf(name = "fdSaleQty")
    public BigDecimal fdSaleQty = BigDecimal.ZERO;
    @ColumnInf(name = "fiItemCd_M", primaryKey = true)
    public int fiItemCd_M = 0;
    @ColumnInf(name = "fiOrderUintCd", primaryKey = true)
    public int fiOrderUintCd = 0;
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    /**
     * 可选套餐加价
     */
    @ColumnInf(name = "fdIncrease")
    public BigDecimal fdIncrease = BigDecimal.ZERO;

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    public MenuitemsetsidedtlDBModel() {

    }
    /**
     * 套餐会员价加价
     */
    @ColumnInf(name = "fdVIPDifference")
    public BigDecimal fdVIPDifference = BigDecimal.ZERO;


    @Override
    public MenuitemsetsidedtlDBModel clone() {
        MenuitemsetsidedtlDBModel cloneObj = null;
        try {
            cloneObj = (MenuitemsetsidedtlDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}