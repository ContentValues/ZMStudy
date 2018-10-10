package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbcustlink")
public class CustlinkDBModel extends DBModel {
    @ColumnInf(name = "fsLinkCustGUID", primaryKey = true)
    public String fsLinkCustGUID = "";
    @ColumnInf(name = "fsCustGUID", primaryKey = true)
    public String fsCustGUID = "";

    public CustlinkDBModel() {

    }

    @Override
    public CustlinkDBModel clone() {
        CustlinkDBModel cloneObj = null;
        try {
            cloneObj = (CustlinkDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}