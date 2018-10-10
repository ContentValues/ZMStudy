package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbuser")
public class UserDBModel extends DBModel {
    @ColumnInf(name = "fsCellphone")
    public String fsCellphone = "";
    @ColumnInf(name = "fsPID")
    public String fsPID = "";
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime = "";
    @ColumnInf(name = "fsCreateUserName")
    public String fsCreateUserName = "";
    @ColumnInf(name = "fsDeptId")
    public String fsDeptId = "";
    @ColumnInf(name = "fiIsSales")
    public int fiIsSales = 0;
    @ColumnInf(name = "fsCreateUserId")
    public String fsCreateUserId = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsAuthCode")
    public String fsAuthCode = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsCompanyGUID")
    public String fsCompanyGUID = "";
    @ColumnInf(name = "fsUserId")
    public String fsUserId = "";
    @ColumnInf(name = "fsUserName")
    public String fsUserName = "";
    /**
     * 已废弃，请使用fsUserId字段
     */
    @Deprecated
    @ColumnInf(name = "fsStaffId", primaryKey = true)
    public String fsStaffId = "";

    @ColumnInf(name = "fsPwd")
    public String fsPwd = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";
    /**
     * 绑定的ID卡的卡号
     */
    @ColumnInf(name = "fsiccardcode")
    public String fsiccardcode = "";


    /**
     * 登录的会话Token
     */
    @ColumnInf(name = "loginToken")
    public String loginToken = "";
    /**
     * 登录的站点
     */
    @ColumnInf(name = "loginHost")
    public String loginHost = "";


    /**
     * 允许退菜菜品 0： 不限 1： 指定
     */
    @ColumnInf(name = "fiIsRetreatFood")
    public int fiIsRetreatFood = 1;

    /**
     * 允许赠送菜品 0： 不限   1： 指定
     */
    @ColumnInf(name = "fiisgift")
    public int fiisgift = 1;

    /**
     * 允许折扣菜品 0： 不限 1： 指定
     */
    @ColumnInf(name = "fiIsDiscount")
    public int fiIsDiscount = 1;


    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    /**
     * 账单优化权限 0:是,1:否
     */
    @ColumnInf(name = "fiBillAuthority")
    public int fiBillAuthority = 1;

    /**
     * 允许查看账单分类 0:A类,1:B类
     */
    @ColumnInf(name = "fiBillClass")
    public int fiBillClass = 1;

    /**
     * 允许的最大折扣
     */
    @ColumnInf(name = "fiUserDiscount")
    public int fiUserDiscount = 0;

    public UserDBModel() {

    }


    @Override
    public UserDBModel clone() {
        UserDBModel cloneObj = null;
        try {
            cloneObj = (UserDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}