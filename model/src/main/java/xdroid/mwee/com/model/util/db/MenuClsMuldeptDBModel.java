package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * Created by qinwei on 2017/12/19.
 */
@TableInf(name = "tbmenuClsMuldept")
public class MenuClsMuldeptDBModel extends DBModel {
    @ColumnInf(name = "fsGuid")
    public String fsGuid = "";
    @ColumnInf(name = "fsMenuClsId")
    public String fsMenuClsId = "";
    @ColumnInf(name = "fsShopGUID")
    public String fsShopGUID = "";
    @ColumnInf(name = "fsDeptId")
    public String fsDeptId = "";
    @ColumnInf(name = "fsMAreaId")
    public String fsMAreaId = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource;

    @ColumnInf(name = "sync")
    public int sync;

    @Override
    public MenuClsMuldeptDBModel clone() {
        MenuClsMuldeptDBModel cloneObj = null;
        try {
            cloneObj = (MenuClsMuldeptDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

    @Override
    public String toString() {
        return "MenuClsMuldeptDBModel{" +
                "fsGuid='" + fsGuid + '\'' +
                ", fsMenuClsId='" + fsMenuClsId + '\'' +
                ", fsShopGUID='" + fsShopGUID + '\'' +
                ", fsDeptId='" + fsDeptId + '\'' +
                ", fsMAreaId='" + fsMAreaId + '\'' +
                ", fiStatus=" + fiStatus +
                ", fsUpdateTime='" + fsUpdateTime + '\'' +
                ", fsUpdateUserId='" + fsUpdateUserId + '\'' +
                ", fsUpdateUserName='" + fsUpdateUserName + '\'' +
                ", fiDataSource=" + fiDataSource +
                '}';
    }
}

