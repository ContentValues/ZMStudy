package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * Created by liuxiuxiu on 2017/6/13.
 * 门店服务关联表
 *
 */

@TableInf(name = "tbshopservice")
public class ShopServiceDBModel extends DBModel {


    @ColumnInf(name = "fsGuid")
    public String fsGuid = ""; //fsGuid

    @ColumnInf(name = "fiServiceId")
    public int fiServiceId = 0;  //'服务ID',


    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;  //'服务状态'  1:启用；9：禁用

    /**
     * 门店ID
     */
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";

    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";  // '修改日期时间',

    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";  //'修改用户代码',

    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";  // '修改用户名称',

    public ShopServiceDBModel() {

    }

    @Override
    public ShopServiceDBModel clone() {
        ShopServiceDBModel cloneObj = null;
        try {
            cloneObj = (ShopServiceDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}
