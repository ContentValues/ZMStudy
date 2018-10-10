package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbvipdiscount")
public class VipdiscountDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fiDiscountType")
    public int fiDiscountType = 0;
    @ColumnInf(name = "fsDiscountId")
    public String fsDiscountId = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsVIPLvlId", primaryKey = true)
    public String fsVIPLvlId = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    public VipdiscountDBModel() {

    }

    @Override
    public VipdiscountDBModel clone() {
        VipdiscountDBModel cloneObj = null;
        try {
            cloneObj = (VipdiscountDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}