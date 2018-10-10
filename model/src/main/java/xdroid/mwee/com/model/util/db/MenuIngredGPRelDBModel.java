package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * Created by liuxiuxiu on 2017/3/8.
 *  菜品与配料分组关联关系表
 */
 @TableInf(name = "tbmenuingredgprel")
public class MenuIngredGPRelDBModel extends DBModel {
    @ColumnInf(name = "fsMenuClsId")
    public String fsMenuClsId = ""; //'配料分组代码'

    @ColumnInf(name = "fiItemCd")
    public int fiItemCd = 0; //'菜品內码'
    @ColumnInf(name = "fiStatus", primaryKey = true)
    public int fiStatus = 0; //'状态'
    @ColumnInf(name = "fsShopGUID")
    public String fsShopGUID = ""; //'门店shopGUID',
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = ""; //'修改日期时间',
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = ""; //'修改用户代码',
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = ""; //'修改用户名称',

    public MenuIngredGPRelDBModel(){}
}