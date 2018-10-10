package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbauthority")
public class AuthorityDBModel extends DBModel {
    @ColumnInf(name = "fsProgId", primaryKey = true)
    public String fsProgId = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsRoleId", primaryKey = true)
    public String fsRoleId = "";
    @ColumnInf(name = "fsProjId", primaryKey = true)
    public String fsProjId = "";

    public AuthorityDBModel() {

    }

    @Override
    public AuthorityDBModel clone() {
        AuthorityDBModel cloneObj = null;
        try {
            cloneObj = (AuthorityDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}