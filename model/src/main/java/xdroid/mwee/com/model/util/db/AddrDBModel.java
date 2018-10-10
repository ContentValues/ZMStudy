package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;

/**
 * Created by zhangmin on 2018/4/17.
 */

public class AddrDBModel extends DBModel {
    @ColumnInf(name = "fsProvinceId", primaryKey = true)
    public String fsProvinceId = "";
    @ColumnInf(name = "fsCityId", primaryKey = true)
    public String fsCityId = "";
    @ColumnInf(name = "fsProvinceName")
    public String fsProvinceName = "";
    @ColumnInf(name = "fsPostal")
    public String fsPostal = "";
    @ColumnInf(name = "fsCityName")
    public String fsCityName = "";
    @ColumnInf(name = "fsDistrictId", primaryKey = true)
    public String fsDistrictId = "";
    @ColumnInf(name = "fsDistrictName")
    public String fsDistrictName = "";

    public AddrDBModel() {

    }

    @Override
    public AddrDBModel clone() {
        AddrDBModel cloneObj = null;
        try {
            cloneObj = (AddrDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}
