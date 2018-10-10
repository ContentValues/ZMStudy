package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbdiscountitem")
public class DiscountitemDBModel extends DBModel {
    @ColumnInf(name = "fiDiscountRate")
    public int fiDiscountRate = 0;
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fiOrderUintCd", primaryKey = true)
    public int fiOrderUintCd = 0;
    @ColumnInf(name = "fsDiscountId", primaryKey = true)
    public String fsDiscountId = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fiItemCd", primaryKey = true)
    public int fiItemCd = 0;
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    public DiscountitemDBModel() {

    }

    @Override
    public DiscountitemDBModel clone() {
        DiscountitemDBModel cloneObj = null;
        try {
            cloneObj = (DiscountitemDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}