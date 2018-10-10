package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbreport")
public class ReportDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsReportTempletId")
    public String fsReportTempletId = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fiReportCls")
    public int fiReportCls = 0;
    @ColumnInf(name = "fsReportName")
    public String fsReportName = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsReportId", primaryKey = true)
    public String fsReportId = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";

    public ReportDBModel() {

    }

    @Override
    public ReportDBModel clone() {
        ReportDBModel cloneObj = null;
        try {
            cloneObj = (ReportDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}