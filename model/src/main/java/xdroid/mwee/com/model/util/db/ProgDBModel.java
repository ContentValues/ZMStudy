package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbprog")
public class ProgDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fsProgId", primaryKey = true)
    public String fsProgId = "";
    @ColumnInf(name = "fiSortOrder")
    public int fiSortOrder = 0;
    @ColumnInf(name = "fsProgDesc")
    public String fsProgDesc = "";
    @ColumnInf(name = "fsProgLvl")
    public String fsProgLvl = "";
    @ColumnInf(name = "fsProgName")
    public String fsProgName = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    public ProgDBModel() {

    }

    @Override
    public ProgDBModel clone() {
        ProgDBModel cloneObj = null;
        try {
            cloneObj = (ProgDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}