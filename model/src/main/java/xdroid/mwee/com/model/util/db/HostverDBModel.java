package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbhostver")
public class HostverDBModel extends DBModel {
    @ColumnInf(name = "fsVer")
    public String fsVer = "";
    @ColumnInf(name = "fsDateTime")
    public String fsDateTime = "";
    @ColumnInf(name = "fsHost", primaryKey = true)
    public String fsHost = "";
    @ColumnInf(name = "fsFileName", primaryKey = true)
    public String fsFileName = "";

    public HostverDBModel() {

    }

    @Override
    public HostverDBModel clone() {
        HostverDBModel cloneObj = null;
        try {
            cloneObj = (HostverDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}