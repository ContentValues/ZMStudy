package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 *  暂时用不到
 */
@TableInf(name = "tbhostmg")
public class HostMGDBModel extends DBModel {
    @ColumnInf(name = "Guid", primaryKey = true)
    public String Guid  = ""; //GUID

    @ColumnInf(name = "fiHostCls")
    public int fiHostCls = 0;  //站点类型;  11=点菜站点 \ 12=收银站点

    @ColumnInf(name = "fsShopGUID")
    public String fsShopGUID  = ""; //门店GUID

    @ColumnInf(name = "fiHostMax")
    public int fiHostMax = 0;   //站点最大数量

    @ColumnInf(name = "fsHostClsName")
    public String fsHostClsName  = ""; //分类名称

    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;

    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";

    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";

    public HostMGDBModel() {

    }

    @Override
    public HostMGDBModel clone() {
        HostMGDBModel cloneObj = null;
        try {
            cloneObj = (HostMGDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}