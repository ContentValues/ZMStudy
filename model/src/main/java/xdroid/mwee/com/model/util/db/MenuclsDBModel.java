package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbmenucls")
public class MenuclsDBModel extends DBModel {
    @ColumnInf(name = "fsExpClsId")
    public String fsExpClsId = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fiMenuClsKind")
    public int fiMenuClsKind = 0;
    @ColumnInf(name = "fiSortOrder")
    public int fiSortOrder = 0;
    @ColumnInf(name = "fsMenuClsFullName")
    public String fsMenuClsFullName = "";
    @ColumnInf(name = "fiDataKind")
    public int fiDataKind = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fiLevel")
    public int fiLevel = 0;
    @ColumnInf(name = "fiDtlLvl")
    public int fiDtlLvl = 0;
    @ColumnInf(name = "fsMenuClsId", primaryKey = true)
    public String fsMenuClsId = "";
    @ColumnInf(name = "fsMenuClsName")
    public String fsMenuClsName = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsMenuClsId_P")
    public String fsMenuClsId_P = "";
    @ColumnInf(name = "fsRevenueTypeId")
    public String fsRevenueTypeId = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    @ColumnInf(name = "sync")
    public int sync = 0;
    /**
     * '是否后厨打印:0无后厨打印,1单制作部门,2多制作部门';
     */
    @ColumnInf(name = "fiIsPrn")
    public int fiIsPrn = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    public MenuclsDBModel() {

    }

    @Override
    public MenuclsDBModel clone() {
        MenuclsDBModel cloneObj = null;
        try {
            cloneObj = (MenuclsDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}