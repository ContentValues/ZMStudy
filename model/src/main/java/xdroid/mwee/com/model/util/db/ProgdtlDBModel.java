package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbprogdtl")
public class ProgdtlDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fsProgId", primaryKey = true)
    public String fsProgId = "";
    @ColumnInf(name = "fiSortOrder")
    public int fiSortOrder = 0;
    @ColumnInf(name = "fsProglName")
    public String fsProglName = "";
    @ColumnInf(name = "fsProgDtlId", primaryKey = true)
    public String fsProgDtlId = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    public ProgdtlDBModel() {

    }

    @Override
    public ProgdtlDBModel clone() {
        ProgdtlDBModel cloneObj = null;
        try {
            cloneObj = (ProgdtlDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}