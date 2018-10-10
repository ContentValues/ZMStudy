package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbsellhurry")
public class SellhurryDBModel extends DBModel {
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime = "";
    @ColumnInf(name = "fsCreateUserName")
    public String fsCreateUserName = "";
    @ColumnInf(name = "fsSellNo", primaryKey = true)
    public String fsSellNo = "";
    @ColumnInf(name = "fiHurrySeq", primaryKey = true)
    public int fiHurrySeq = 0;
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";

    public SellhurryDBModel() {

    }

    @Override
    public SellhurryDBModel clone() {
        SellhurryDBModel cloneObj = null;
        try {
            cloneObj = (SellhurryDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}