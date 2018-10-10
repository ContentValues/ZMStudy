package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbcusttype")
public class CusttypeDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsCompanyGUID", primaryKey = true)
    public String fsCompanyGUID = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsCustTypeId", primaryKey = true)
    public String fsCustTypeId = "";
    @ColumnInf(name = "fsCustTypeName")
    public String fsCustTypeName = "";

    public CusttypeDBModel() {

    }

    @Override
    public CusttypeDBModel clone() {
        CusttypeDBModel cloneObj = null;
        try {
            cloneObj = (CusttypeDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}