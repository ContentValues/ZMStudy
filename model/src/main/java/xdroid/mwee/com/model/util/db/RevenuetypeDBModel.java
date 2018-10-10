package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbrevenuetype")
public class RevenuetypeDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fiSortOrder")
    public int fiSortOrder = 0;
    @ColumnInf(name = "fiDataKind")
    public int fiDataKind = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsRevenueTypeId", primaryKey = true)
    public String fsRevenueTypeId = "";
    @ColumnInf(name = "fsRevenueTypeName")
    public String fsRevenueTypeName = "";

    public RevenuetypeDBModel() {

    }

    @Override
    public RevenuetypeDBModel clone() {
        RevenuetypeDBModel cloneObj = null;
        try {
            cloneObj = (RevenuetypeDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

    @Override
    public String toString() {
        return "RevenuetypeDBModel{" +
                "fsUpdateUserName='" + fsUpdateUserName + '\'' +
                ", fiStatus=" + fiStatus +
                ", fiSortOrder=" + fiSortOrder +
                ", fiDataKind=" + fiDataKind +
                ", fsUpdateTime='" + fsUpdateTime + '\'' +
                ", fsShopGUID='" + fsShopGUID + '\'' +
                ", fsUpdateUserId='" + fsUpdateUserId + '\'' +
                ", fsRevenueTypeId='" + fsRevenueTypeId + '\'' +
                ", fsRevenueTypeName='" + fsRevenueTypeName + '\'' +
                '}';
    }
}