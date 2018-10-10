package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbaskgpmenucls")
public class AskgpMenuClsDBModel extends DBModel {

    @ColumnInf(name = "fsGuid", primaryKey = true)
    public String fsGuid = "";

    /**
     * '菜单分类代码'
     */
    @ColumnInf(name = "fsMenuClsId", primaryKey = false)
    public String fsMenuClsId = "";

    @ColumnInf(name = "fsAskGpId", primaryKey = false)
    public String fsAskGpId = "";

    @ColumnInf(name = "fsUpdateUserId", primaryKey = false)
    public String fsUpdateUserId = "";

    @ColumnInf(name = "fsUpdateUserName", primaryKey = false)
    public String fsUpdateUserName = "";

    @ColumnInf(name = "fsShopGUID", primaryKey = false)
    public String fsShopGUID = "";

    @ColumnInf(name = "fiStatus", primaryKey = false)
    public int fiStatus = 0;

    @ColumnInf(name = "fsUpdateTime", primaryKey = false)
    public String fsUpdateTime = "";

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource", primaryKey = false)
    public int fiDataSource = 0;

    @ColumnInf(name = "sync", primaryKey = false)
    public int sync = 0;

    public AskgpMenuClsDBModel() {

    }

    @Override
    public AskgpMenuClsDBModel clone() {
        AskgpMenuClsDBModel cloneObj = null;
        try {
            cloneObj = (AskgpMenuClsDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}