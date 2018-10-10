package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbmenuitemuint")
public class MenuItemUnitDBModel extends DBModel {
    /**
     * 修改用户名称
     */
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    /**
     * 门店的GUID
     */
    @ColumnInf(name = "fsShopGUID")
    public String fsShopGUID = "";
    /**
     * 数据状态;0未同步到店DB / 1正常 / 2临时沽清 / 3数量沽清 /13删除
     */
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    /**
     * 菜品ID
     */
    @ColumnInf(name = "fiItemCd")
    public int fiItemCd = 0;
    /**
     * 会员单价
     */
    @ColumnInf(name = "fdVIPPrice")
    public BigDecimal fdVIPPrice = BigDecimal.ZERO;

    /**
     * 规格ID
     */
    @ColumnInf(name = "fiOrderUintCd", primaryKey = true)
    public int fiOrderUintCd = 0;
    /**
     * 规格单位;份 只 盘
     */
    @ColumnInf(name = "fsOrderUint")
    public String fsOrderUint = "";
    /**
     * 销售单价
     */
    @ColumnInf(name = "fdSalePrice")
    public BigDecimal fdSalePrice = BigDecimal.ZERO;
    /**
     * 原始售卖价,和“fdSalePrice”一致，仅内存中使用
     */
    @ColumnInf(name = "fdSalePrice",ignoreWrite = true)
    public BigDecimal fdOriginPrice = BigDecimal.ZERO;
    /**
     * 库存数量
     */
    @ColumnInf(name = "fdInvQty")
    public BigDecimal fdInvQty = BigDecimal.ZERO;

    /**
     * 起点数量
     */
    @ColumnInf(name = "fiInitCount", primaryKey = true)
    public int fiInitCount = 0;

    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";

    @ColumnInf(name = "sync")
    public int sync = 0;
    @ColumnInf(name = "fiDefault")
    public int fiDefault;
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId;
    @ColumnInf(name = "fdCostPrice")
    public BigDecimal fdCostPrice;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    public MenuItemUnitDBModel() {

    }

    @Override
    public MenuItemUnitDBModel clone() {
        MenuItemUnitDBModel cloneObj = null;
        try {
            cloneObj = (MenuItemUnitDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}