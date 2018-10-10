package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbuserdiscount")
public class UserdiscountDBModel extends DBModel {
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsUserId", primaryKey = true)
    public String fsUserId = "";
    @ColumnInf(name = "fsDiscountId", primaryKey = true)
    public String fsDiscountId = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    public UserdiscountDBModel() {

    }

    @Override
    public UserdiscountDBModel clone() {
        UserdiscountDBModel cloneObj = null;
        try {
            cloneObj = (UserdiscountDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}