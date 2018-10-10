package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * 退菜权限/赠菜权限表
 * Created by virgil on 2017/1/17.
 */
@TableInf(name = "tbuserMenuItemRole")
public class UserMenuItemRoleDBModel extends DBModel {
    @ColumnInf(name = "fsUserId", primaryKey = true)
    public String fsUserId = "";//用户帐号
    @ColumnInf(name = "fiItemCd", primaryKey = true)
    public int fiItemCd = 0;//菜品內码
    @ColumnInf(name = "fiType")
    public int fiType = 0;//退菜/赠菜(0:退菜，1:赠菜)
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;//--数据状态;1正常、9禁用、13删除状态
    @ColumnInf(name = "fiOrderUintCd", primaryKey = true)
    public int fiOrderUintCd = 0;//规格ID
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";//门店GUID
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime = "";//建立日期时间
    @ColumnInf(name = "fsCreateUserId")
    public int fsCreateUserId = 0;//建立用户代码
    @ColumnInf(name = "fsCreateUserName")
    public String fsCreateUserName = "";//建立用户名称
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";//修改用户代码
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";//修改用户名称
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";//修改日期时间


    public UserMenuItemRoleDBModel() {

    }


    @Override
    public UserMenuItemRoleDBModel clone() {
        UserMenuItemRoleDBModel cloneObj = null;
        try {
            cloneObj = (UserMenuItemRoleDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}