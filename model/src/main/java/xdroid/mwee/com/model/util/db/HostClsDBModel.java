package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * 暂时用不到
 */
@TableInf(name = "tbhostcls")
public class HostClsDBModel extends DBModel {
    @ColumnInf(name = "fiHostCls", primaryKey = true)
    public int fiHostCls = 0;  //站点类型;  11=点菜站点 \ 12=收银站点

    @ColumnInf(name = "fsHostClsName")
    public String fsHostClsName  = ""; //分类名称

    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;

    @ColumnInf(name = "fiDataKind")
    public int fiDataKind = 0;   //数据类型

    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";

    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";

    public HostClsDBModel() {

    }

    @Override
    public HostClsDBModel clone() {
        HostClsDBModel cloneObj = null;
        try {
            cloneObj = (HostClsDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}