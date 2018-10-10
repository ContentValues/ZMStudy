package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbreporttemplet")
public class ReporttempletDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fsRptFile")
    public String fsRptFile = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsReportTempletId", primaryKey = true)
    public String fsReportTempletId = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fiPaperSize")
    public int fiPaperSize = 0;
    @ColumnInf(name = "fsReportTempletName")
    public String fsReportTempletName = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsReportId", primaryKey = true)
    public String fsReportId = "";
    @ColumnInf(name = "fsReportTempletDesc")
    public String fsReportTempletDesc = "";

    public ReporttempletDBModel() {

    }

    @Override
    public ReporttempletDBModel clone() {
        ReporttempletDBModel cloneObj = null;
        try {
            cloneObj = (ReporttempletDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}