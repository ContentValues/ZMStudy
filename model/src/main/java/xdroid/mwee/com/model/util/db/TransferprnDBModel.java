package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * 传菜部门打印哪些制作部门
 */
@TableInf(name = "tbtransferprn")
public class TransferprnDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fsDeptId_Make", primaryKey = true)
    public String fsDeptId_Make = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsDeptId_Transfer", primaryKey = true)
    public String fsDeptId_Transfer = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    public TransferprnDBModel() {

    }

    @Override
    public String toString() {
        return "TransferprnDBModel{" +
                "fsUpdateUserName='" + fsUpdateUserName + '\'' +
                ", fsDeptId_Make='" + fsDeptId_Make + '\'' +
                ", fsUpdateTime='" + fsUpdateTime + '\'' +
                ", fsDeptId_Transfer='" + fsDeptId_Transfer + '\'' +
                ", fsShopGUID='" + fsShopGUID + '\'' +
                ", fsUpdateUserId='" + fsUpdateUserId + '\'' +
                '}';
    }

    @Override
    public TransferprnDBModel clone() {
        TransferprnDBModel cloneObj = null;
        try {
            cloneObj = (TransferprnDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}