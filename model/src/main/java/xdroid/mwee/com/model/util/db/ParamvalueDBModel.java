package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * 店铺配置
 * 001 -- 营业日期
 * 020 -- 中控账号
 */
@TableInf(name = "tbParamValue")
public class ParamvalueDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";


    @ColumnInf(name = "fsParamId", primaryKey = true)
    public String fsparamid = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsshopguid = "";

    @ColumnInf(name = "fsParamValue")
    public String fsParamValue = "";//设定值

    @ColumnInf(name = "fsStr1")
    public String fsStr1 = "";
    @ColumnInf(name = "fsStr2")
    public String fsStr2 = "";
    @ColumnInf(name = "fsStr3")
    public String fsStr3 = "";
    @ColumnInf(name = "fsStr4")
    public String fsStr4 = "";
    @ColumnInf(name = "fsStr5")
    public String fsStr5 = "";

    @ColumnInf(name = "fiInt2")
    public int fiInt2 = 0;
    @ColumnInf(name = "fiInt1")
    public int fiInt1 = 0;
    @ColumnInf(name = "fiInt3")
    public int fiInt3 = 0;
    @ColumnInf(name = "fiInt4")
    public int fiInt4 = 0;
    @ColumnInf(name = "fiInt5")
    public int fiInt5 = 0;


    @ColumnInf(name = "lver")
    public int lver = 0;
    @ColumnInf(name = "pver")
    public int pver = 0;

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    public ParamvalueDBModel() {

    }

    @Override
    public ParamvalueDBModel clone() {
        ParamvalueDBModel cloneObj = null;
        try {
            cloneObj = (ParamvalueDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}