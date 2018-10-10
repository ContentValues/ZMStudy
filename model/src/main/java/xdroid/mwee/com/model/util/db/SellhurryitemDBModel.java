package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbsellhurryitem")
public class SellhurryitemDBModel extends DBModel {
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime = "";
    @ColumnInf(name = "fsCreateUserName")
    public String fsCreateUserName = "";
    @ColumnInf(name = "fsSellNo", primaryKey = true)
    public String fsSellNo = "";
    @ColumnInf(name = "fiHurrySeq", primaryKey = true)
    public int fiHurrySeq = 0;
    @ColumnInf(name = "fsSourceSeq", primaryKey = true)
    public String fsSourceSeq = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";

    public SellhurryitemDBModel() {

    }

    @Override
    public SellhurryitemDBModel clone() {
        SellhurryitemDBModel cloneObj = null;
        try {
            cloneObj = (SellhurryitemDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}