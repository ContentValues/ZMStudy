package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbnotice")
public class NoticeDBModel extends DBModel {
    @ColumnInf(name = "Id", primaryKey = true)
    public int id = 0;  //自增ID
    @ColumnInf(name = "fsTitle")
    public String fsTitle = "";//公告标题
    @ColumnInf(name = "fsContent")
    public String fsContent = "";//公告内容
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;//公告状态
    @ColumnInf(name = "fiType")
    public int fiType = 0;//公告来源
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";//更新时间
    @ColumnInf(name = "fsCreatUser")
    public String fsCreatUser = "";//公告创建人
    @ColumnInf(name = "fsCreatTime")
    public String fsCreatTime = "";//公告创建时间
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";

    @ColumnInf(name = "isRead", primaryKey = true)
    public int isRead = 0;//是否已读 0:未读 1、已读

    @ColumnInf(name = "alert", primaryKey = true)
    public int alert = 0;//是否提示 0:提示 1、不再提示

    public NoticeDBModel() {
    }

    @Override
    public NoticeDBModel clone() {
        NoticeDBModel cloneObj = null;
        try {
            cloneObj = (NoticeDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}