package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * Created by Liming on 16/11/7.
 */
@TableInf(name = "dailyReport")
public class DailyReportDBModel extends DBModel {

    @ColumnInf(name = "businessdate",primaryKey = false)
    public String businessdate = "";//报表日期
    /**
     */
    @ColumnInf(name = "type",primaryKey = false)
    public String type = "";
    @ColumnInf(name = "value")
    public String value = "";
    @ColumnInf(name = "param1")
    public String param1 = "";
    @ColumnInf(name = "param2")
    public String param2 = "";
    @ColumnInf(name = "updateTime")
    public String updateTime = "";//更新时间
    @ColumnInf(name = "shopGUID")
    public String shopGUID = "";//门店ID

    public DailyReportDBModel(){

    }

    @Override
    public DailyReportDBModel clone() {
        DailyReportDBModel cloneObj = null;
        try {
            cloneObj = (DailyReportDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}
