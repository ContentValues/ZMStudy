package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbbargain")
public class BargainDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    /**
     * 计划类型
     * 1，按起止天
     * 2，按星期
     * 3，指定天
     */
    @ColumnInf(name = "fiPlanType")
    public int fiPlanType = 0;
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    /**
     * 计划类型指定的Value
     * 1，按起止天：
     * 2，按星期：1,2,3
     * 3，指定天：1220,1219,1221,1222
     */
    @ColumnInf(name = "fsPlanTypeValue")
    public String fsPlanTypeValue = "";
    /**
     * 1=特价/2=菜品买减/3、18003满减
     */
    @ColumnInf(name = "fiBargainCls")
    public int fiBargainCls = 0;
    /**
     * 结束日期:yyyy-MM-dd
     */
    @ColumnInf(name = "fsEndTime")
    public String fsEndTime = "";
    @ColumnInf(name = "fsBargainId", primaryKey = true)
    public String fsBargainId = "";
    /**
     * 开始日期：yyyy-MM-dd
     */
    @ColumnInf(name = "fsBeginTime")
    public String fsBeginTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsBargainName")
    public String fsBargainName = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    /**
     * 餐段ID：10,11,12,2,3,4,5,6,7,8,9
     */
    @ColumnInf(name = "fsMSectionIdList")
    public String fsMSectionIdList = "";
    /**
     * 自定义开始时间
     */
    @ColumnInf(name = "fscustomendtime")
    public String fscustomendtime = "";
    /**
     * 自定义结束时间
     */
    @ColumnInf(name = "fscustomstarttime")
    public String fscustomstarttime = "";

    public BargainDBModel() {

    }

    @Override
    public BargainDBModel clone() {
        BargainDBModel cloneObj = null;
        try {
            cloneObj = (BargainDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}