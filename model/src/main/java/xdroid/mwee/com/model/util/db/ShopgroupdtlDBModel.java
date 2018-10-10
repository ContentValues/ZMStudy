package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbshopgroupdtl")
public class ShopgroupdtlDBModel extends DBModel {
    @ColumnInf(name = "fiShopGroupId", primaryKey = true)
    public int fiShopGroupId = 0;
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsCompanyGUID", primaryKey = true)
    public String fsCompanyGUID = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    public ShopgroupdtlDBModel() {

    }

    @Override
    public ShopgroupdtlDBModel clone() {
        ShopgroupdtlDBModel cloneObj = null;
        try {
            cloneObj = (ShopgroupdtlDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}