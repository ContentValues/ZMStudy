package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbmenuitemaskgp")
public class MenuitemaskgpDBModel extends DBModel {
    @ColumnInf(name = "fsAskGpId", primaryKey = true)
    public String fsAskGpId = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fiItemCd", primaryKey = true)
    public int fiItemCd = 0;
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    /**
     * 关系类型1:全部 , 2:菜品分类，3:菜品',
     */
    @ColumnInf(name = "fiRelationtype")
    public int fiRelationtype = 3;

    /**
     * '菜单分类代码'
     */
    @ColumnInf(name = "fsMenuClsId")
    public String fsMenuClsId = "";

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    public MenuitemaskgpDBModel() {

    }

    @Override
    public MenuitemaskgpDBModel clone() {
        MenuitemaskgpDBModel cloneObj = null;
        try {
            cloneObj = (MenuitemaskgpDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}