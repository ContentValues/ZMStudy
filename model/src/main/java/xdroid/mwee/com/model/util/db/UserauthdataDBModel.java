package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbuserauthdata")
public class UserauthdataDBModel extends DBModel {
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsUserId", primaryKey = true)
    public String fsUserId = "";
    @ColumnInf(name = "fsDataKind", primaryKey = true)
    public String fsDataKind = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsValue", primaryKey = true)
    public String fsValue = "";

    public UserauthdataDBModel() {

    }

    @Override
    public UserauthdataDBModel clone() {
        UserauthdataDBModel cloneObj = null;
        try {
            cloneObj = (UserauthdataDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}