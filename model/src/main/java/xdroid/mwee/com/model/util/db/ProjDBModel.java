package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbproj")
public class ProjDBModel extends DBModel {
    @ColumnInf(name = "fsProjName")
    public String fsProjName = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fsProjId", primaryKey = true)
    public String fsProjId = "";

    public ProjDBModel() {

    }

    @Override
    public ProjDBModel clone() {
        ProjDBModel cloneObj = null;
        try {
            cloneObj = (ProjDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}