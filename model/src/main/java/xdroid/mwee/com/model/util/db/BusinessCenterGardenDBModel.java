package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * CREATE TABLE "businessCenterGarden"
 * ("id" INTEGER PRIMARY KEY  NOT NULL ,
 * "key" VARCHAR NOT NULL ,
 * "value" TEXT NOT NULL ,
 * "other1" TEXT,
 * "other2" TEXT,
 * "other3" TEXT,
 * "other4" TEXT,
 * "updateTime" VARCHAR)
 * 业务中心拉取的各种数据
 */
@TableInf(name = "businessCenterGarden")
public class BusinessCenterGardenDBModel extends DBModel {
    @ColumnInf(name = "key")
    public String key = "";
    @ColumnInf(name = "value")
    public String value = "";
    @ColumnInf(name = "other1")
    public String other1 = "";
    @ColumnInf(name = "other2")
    public String other2 = "";
    @ColumnInf(name = "other3")
    public String other3 = "";
    @ColumnInf(name = "other4")
    public String other4 = "";
    @ColumnInf(name = "updateTime")
    public String updateTime = "";

    public BusinessCenterGardenDBModel() {

    }

    @Override
    public BusinessCenterGardenDBModel clone() {
        BusinessCenterGardenDBModel cloneObj = null;
        try {
            cloneObj = (BusinessCenterGardenDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}