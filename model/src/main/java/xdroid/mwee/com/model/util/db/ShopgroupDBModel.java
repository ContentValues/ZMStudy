package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbshopgroup")
public class ShopgroupDBModel extends DBModel {
    @ColumnInf(name = "fsShopGroupName")
    public String fsShopGroupName = "";
    @ColumnInf(name = "fiShopGroupId", primaryKey = true)
    public int fiShopGroupId = 0;
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fiSortOrder")
    public int fiSortOrder = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsCompanyGUID")
    public String fsCompanyGUID = "";
    @ColumnInf(name = "fsShopGroupKind")
    public String fsShopGroupKind = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";

    public ShopgroupDBModel() {

    }

    @Override
    public ShopgroupDBModel clone() {
        ShopgroupDBModel cloneObj = null;
        try {
            cloneObj = (ShopgroupDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}