package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbfilever")
public class FileverDBModel extends DBModel {
    @ColumnInf(name = "fsVer")
    public String fsVer = "";
    @ColumnInf(name = "fsDir")
    public String fsDir = "";
    @ColumnInf(name = "fsFileName", primaryKey = true)
    public String fsFileName = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";

    public FileverDBModel() {

    }

    @Override
    public FileverDBModel clone() {
        FileverDBModel cloneObj = null;
        try {
            cloneObj = (FileverDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}