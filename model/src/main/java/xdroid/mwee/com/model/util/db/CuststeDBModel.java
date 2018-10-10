package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbcustste")
public class CuststeDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fsCustSteName")
    public String fsCustSteName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsCompanyGUID", primaryKey = true)
    public String fsCompanyGUID = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsCustSteId", primaryKey = true)
    public String fsCustSteId = "";

    public CuststeDBModel() {

    }

    @Override
    public CuststeDBModel clone() {
        CuststeDBModel cloneObj = null;
        try {
            cloneObj = (CuststeDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}