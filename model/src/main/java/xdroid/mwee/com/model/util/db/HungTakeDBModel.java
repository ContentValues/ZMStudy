package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbHungTake")
public class HungTakeDBModel extends DBModel {
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsOrderSite")
    public String fsOrderSite = "";
    @ColumnInf(name = "fsOrder")
    public String fsOrder = "";
    @ColumnInf(name = "fsGrade")
    public String fsGrade = "";
    @ColumnInf(name = "fsTimeStamp")
    public int fsTimeStamp = 0;
    @ColumnInf(name = "fsDescribe")
    public String fsDescribe = "";
    @ColumnInf(name = "fiId", primaryKey = true)
    public long fiId = 0;
    @ColumnInf(name = "fsOrderTime")
    public String fsOrderTime = "";
    @ColumnInf(name = "fsOrderName")
    public String fsOrderName = "";

    public HungTakeDBModel() {

    }

    @Override
    public HungTakeDBModel clone() {
        HungTakeDBModel cloneObj = null;
        try {
            cloneObj = (HungTakeDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}