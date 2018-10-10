package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * 订单来源
 */
@TableInf(name = "tbBillSource")
public class BillSourceDBModel extends DBModel {
    @ColumnInf(name = "fsBillSourceId", primaryKey = true)
    public String fsBillSourceId = "1";  //--订单来源店铺代号

    @ColumnInf(name = "fsBillSourceName")
    public String fsBillSourceName = "";  //--订单来源店铺名称

    @ColumnInf(name = "fiBillSource")
    public int fiBillSource = 0;   //--订单来源类型;1自建(店内)/2微信  /3饿了么  …

    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";  //--修改用户名称

    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;  //--状态;1正常  / 9禁用  / 13删除

    @ColumnInf(name = "fiDataKind")
    public int fiDataKind = 0;  //--数据类型;1系统预设数据、2用户数据
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";  //--修改日期时间
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";  //--门店GUID
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";  //--修改用户代码

    public BillSourceDBModel() {

    }

    @Override
    public BillSourceDBModel clone() {
        BillSourceDBModel cloneObj = null;
        try {
            cloneObj = (BillSourceDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}