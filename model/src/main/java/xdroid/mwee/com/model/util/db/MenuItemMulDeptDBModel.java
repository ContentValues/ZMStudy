package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * Created by lxx on 16/10/18.
 */
@TableInf(name = "tbMenuItemMulDept")
public class MenuItemMulDeptDBModel extends DBModel {
    @ColumnInf(name = "fiMulDeptCd", primaryKey = true)
    public int fiMulDeptCd = 0;

    @ColumnInf(name = "fiItemCd")
    public int fiItemCd = 0; //菜品內码
    @ColumnInf(name = "fsShopGUID")
    public String fsShopGUID = ""; //门店GUID

    @ColumnInf(name = "fsDeptId")
    public String fsDeptId = ""; //部门代码限制作部门

    @ColumnInf(name = "fsMAreaId")
    public String fsMAreaId = ""; //餐区代碼

    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0; //数据状态;/ 1正常 /13删除

    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = ""; //修改日期时间

    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = ""; //修改用户代码

    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = ""; //修改用户名称

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    public MenuItemMulDeptDBModel() {
    }

    @Override
    public MenuItemMulDeptDBModel clone() {
        MenuItemMulDeptDBModel cloneObj = null;
        try {
            cloneObj = (MenuItemMulDeptDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

}
