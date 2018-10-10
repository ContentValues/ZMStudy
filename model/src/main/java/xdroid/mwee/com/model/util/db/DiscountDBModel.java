package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbdiscount")
public class DiscountDBModel extends DBModel {
    @ColumnInf(name = "fiDiscountRate")
    public int fiDiscountRate = 0;
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fiIsVIPUse")
    public int fiIsVIPUse = 0;
    @ColumnInf(name = "fsDiscountId", primaryKey = true)
    public String fsDiscountId = "";
    @ColumnInf(name = "fsDiscountName")
    public String fsDiscountName = "";
    @ColumnInf(name = "fiDataKind")
    public int fiDataKind = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    /**
     * '0=部分菜品打折,1=整单立减,2=整单打折)',
     */
    @ColumnInf(name = "ficouponid")
    public int ficouponid = 0;
    @ColumnInf(name = "fdddv")
    public BigDecimal fdddv = BigDecimal.ZERO; // '减扣金额',

    @ColumnInf(name = "fiVIPId")
    public int fiVIPId = 0; //'会员级别id',
    @ColumnInf(name = "fiDiscReason")
    public int fiDiscReason = 0; //'打折理由是否必选(0:否;1:是)'

    @ColumnInf(name = "fiIsEffectiveDate")
    public int fiIsEffectiveDate = 0;//时效限制 0不限制 1限制
    @ColumnInf(name = "fsStarDate")
    public String fsStarDate = "";//开始时间
    @ColumnInf(name = "fsEndDate")
    public String fsEndDate = "";//结束时间

    /**
     * 开始时间戳
     */
    public long startTime = 0;
    /**
     * 结束时间戳
     */
    public long endTime = 0;

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;


    @Override
    public DiscountDBModel clone() {
        DiscountDBModel cloneObj = null;
        try {
            cloneObj = (DiscountDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}