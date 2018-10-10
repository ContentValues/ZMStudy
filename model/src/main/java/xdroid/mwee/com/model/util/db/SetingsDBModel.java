package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbsetings")
public class SetingsDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fsShopGUID")
    public String fsShopGUID = "";
    @ColumnInf(name = "fskey", primaryKey = true)
    public String fskey = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsvalue")
    public String fsvalue = "";
    @ColumnInf(name = "fsreamark")
    public String fsreamark = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    public SetingsDBModel() {

    }

    @Override
    public SetingsDBModel clone() {
        SetingsDBModel cloneObj = null;
        try {
            cloneObj = (SetingsDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}