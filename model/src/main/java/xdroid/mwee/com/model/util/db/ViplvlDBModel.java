package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbviplvl")
public class ViplvlDBModel extends DBModel {
    @ColumnInf(name = "fsVIPLvlName")
    public String fsVIPLvlName = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fiSortOrder")
    public int fiSortOrder = 0;
    @ColumnInf(name = "fsCompanyGUID", primaryKey = true)
    public String fsCompanyGUID = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsVIPLvlId", primaryKey = true)
    public String fsVIPLvlId = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";

    public ViplvlDBModel() {

    }

    @Override
    public ViplvlDBModel clone() {
        ViplvlDBModel cloneObj = null;
        try {
            cloneObj = (ViplvlDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}