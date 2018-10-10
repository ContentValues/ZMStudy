package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbchangetable")
public class ChangetableDBModel extends DBModel {
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime = "";
    @ColumnInf(name = "fsCreateUserName")
    public String fsCreateUserName = "";
    @ColumnInf(name = "fsShopGUID")
    public String fsShopGUID = "";
    @ColumnInf(name = "fsMTableId")
    public String fsMTableId = "";
    @ColumnInf(name = "fiTimes")
    public int fiTimes = 0;
    @ColumnInf(name = "fsSellNo")
    public String fsSellNo = "";
    @ColumnInf(name = "fsMTableId_new")
    public String fsMTableId_new = "";
    @ColumnInf(name = "fsChangeReason")
    public String fsChangeReason = "";

    public ChangetableDBModel() {

    }

    @Override
    public ChangetableDBModel clone() {
        ChangetableDBModel cloneObj = null;
        try {
            cloneObj = (ChangetableDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}