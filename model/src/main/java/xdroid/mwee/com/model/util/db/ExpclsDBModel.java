package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbexpcls")
public class ExpclsDBModel extends DBModel {
    @ColumnInf(name = "fsExpClsName")
    public String fsExpClsName = "";
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
    @ColumnInf(name = "fsExpClsId", primaryKey = true)
    public String fsExpClsId = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    public ExpclsDBModel() {

    }

    @Override
    public ExpclsDBModel clone() {
        ExpclsDBModel cloneObj = null;
        try {
            cloneObj = (ExpclsDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

    @Override
    public String toString() {
        return "ExpclsDBModel{" +
                "fsExpClsName='" + fsExpClsName + '\'' +
                ", fsUpdateUserName='" + fsUpdateUserName + '\'' +
                ", fiStatus=" + fiStatus +
                ", fiSortOrder=" + fiSortOrder +
                ", fiDataKind=" + fiDataKind +
                ", fsUpdateTime='" + fsUpdateTime + '\'' +
                ", fsShopGUID='" + fsShopGUID + '\'' +
                ", fsExpClsId='" + fsExpClsId + '\'' +
                ", fsUpdateUserId='" + fsUpdateUserId + '\'' +
                '}';
    }
}