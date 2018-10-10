package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * -- 折扣方案分类关联表
 */
@TableInf(name = "tbdiscountmenucls")
public class DiscountMenuClsDBModel extends DBModel {

    @ColumnInf(name = "fsGuid", primaryKey = true)
    public String fsGuid = "";

    /**
     * 门店GUID
     */
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";

    /**
     * 折扣方案代号
     */
    @ColumnInf(name = "fsDiscountId", primaryKey = true)
    public String fsDiscountId = "";

    /**
     * 折扣方案代号
     */
    @ColumnInf(name = "fsMenuClsId", primaryKey = true)
    public String fsMenuClsId = "";

    /**
     * 折扣率%
     */
    @ColumnInf(name = "fiDiscountRate")
    public int fiDiscountRate = 0;

    /**
     * 修改用户名称
     */
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";

    /**
     * 状态，1:启用,9:禁用,13:删除
     */
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    /**
     * 修改日期时间
     */
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";

    /**
     * 修改用户代码
     */
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    /**
     * 是否已同步
     */
    @ColumnInf(name = "sync")
    public int sync = 0;

    public DiscountMenuClsDBModel() {

    }

    @Override
    public DiscountMenuClsDBModel clone() {
        DiscountMenuClsDBModel cloneObj = null;
        try {
            cloneObj = (DiscountMenuClsDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}