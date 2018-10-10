package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbauthoritydtl")
public class AuthoritydtlDBModel extends DBModel {
    @ColumnInf(name = "fsProgId", primaryKey = true)
    public String fsProgId = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsProgDtlId", primaryKey = true)
    public String fsProgDtlId = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsRoleId", primaryKey = true)
    public String fsRoleId = "";
    @ColumnInf(name = "fiUsable")
    public int fiUsable = 0;
    @ColumnInf(name = "fsProjId", primaryKey = true)
    public String fsProjId = "";

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    public AuthoritydtlDBModel() {

    }

    @Override
    public AuthoritydtlDBModel clone() {
        AuthoritydtlDBModel cloneObj = null;
        try {
            cloneObj = (AuthoritydtlDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}