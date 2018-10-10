package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbtableinfo")
public class TableinfoDBModel extends DBModel {
    @ColumnInf(name = "fiSortOrder")
    public int fiSortOrder = 0;
    @ColumnInf(name = "fsKind")
    public String fsKind = "";
    @ColumnInf(name = "fsTableId", primaryKey = true)
    public String fsTableId = "";
    @ColumnInf(name = "fsTableName")
    public String fsTableName = "";

    public TableinfoDBModel() {

    }

    @Override
    public TableinfoDBModel clone() {
        TableinfoDBModel cloneObj = null;
        try {
            cloneObj = (TableinfoDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}