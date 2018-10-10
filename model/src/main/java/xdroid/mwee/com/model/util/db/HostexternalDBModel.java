package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbhostexternal")
public class HostexternalDBModel extends DBModel {
    @ColumnInf(name = "fiInt2")
    public int fiInt2 = 0;
    @ColumnInf(name = "fsStr2")
    public String fsStr2 = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiInt1")
    public int fiInt1 = 0;
    @ColumnInf(name = "fiInt3")
    public int fiInt3 = 0;
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fiInt4")
    public int fiInt4 = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsParamValue")
    public String fsParamValue = "";
    @ColumnInf(name = "fiInt5")
    public int fiInt5 = 0;
    @ColumnInf(name = "fiCls", primaryKey = true)
    public int fiCls = 0;
    @ColumnInf(name = "fsHostId", primaryKey = true)
    public String fsHostId = "";
    @ColumnInf(name = "fsStr5")
    public String fsStr5 = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsStr4")
    public String fsStr4 = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsStr1")
    public String fsStr1 = "";
    @ColumnInf(name = "fsStr3")
    public String fsStr3 = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    public HostexternalDBModel() {

    }

    @Override
    public HostexternalDBModel clone() {
        HostexternalDBModel cloneObj = null;
        try {
            cloneObj = (HostexternalDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}