package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 CREATE TABLE "businessCenterPerson"
 ("hostId" INTEGER PRIMARY KEY  NOT NULL ,
 "ip" VARCHAR,
 "mac" VARCHAR,
 "host" TEXT,
 "isConnect" INTEGER,
 "businessDate" VARCHAR,
 "lastOrder" VARCHAR, "updateTime" VARCHAR, "other1" TEXT, "other2" TEXT)

 当前连接到业务中心的站点及其各种状态
 */
@TableInf(name = "businessCenterPerson")
public class BusinessCenterPersonDBModel extends DBModel {
    @ColumnInf(name = "hostId", primaryKey = true)
    public String hostId = "";
    @ColumnInf(name = "ip")
    public String ip = "";
    @ColumnInf(name = "mac")
    public String mac = "";
    @ColumnInf(name = "host")
    public String host = "";
    @ColumnInf(name = "isConnect")
    public int isConnect = 0;
    @ColumnInf(name = "businessDate")
    public String businessDate = "";
    @ColumnInf(name = "lastOrder")
    public String lastOrder = "0";
    @ColumnInf(name = "updateTime")
    public String updateTime = "";
    @ColumnInf(name = "other1")
    public String other1 = "";
    @ColumnInf(name = "other2")
    public String other2 = "";

    public BusinessCenterPersonDBModel() {

    }

    @Override
    public BusinessCenterPersonDBModel clone() {
        BusinessCenterPersonDBModel cloneObj = null;
        try {
            cloneObj = (BusinessCenterPersonDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}