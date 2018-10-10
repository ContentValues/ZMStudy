package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbaskgp")
public class AskgpDBModel extends DBModel {
    @ColumnInf(name = "fsAskGpId", primaryKey = true)
    public String fsAskGpId = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fiSortOrder")
    public int fiSortOrder = 0;
    @ColumnInf(name = "fiDataKind")
    //--数据类型;1系统预设数据、2用户建立数据
    public int fiDataKind = 1;
    @ColumnInf(name = "fsAskGpName")
    public String fsAskGpName = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    /**
     * 菜品要求是否显著显示 0:否,1:是
     */
    @ColumnInf(name = "fiIsShow")
    public int fiIsShow = 0;

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    /**
     * 是否适用于所有菜品分类：0:否 1：是'
     */
    @ColumnInf(name = "fiUseAllMenuCls", primaryKey = false)
    public int fiUseAllMenuCls = 0;

    public AskgpDBModel() {

    }

    @Override
    public AskgpDBModel clone() {
        AskgpDBModel cloneObj = null;
        try {
            cloneObj = (AskgpDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}