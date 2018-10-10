package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * Created by Liming on 17/2/9.
 */
@TableInf(name = "tbOpenParam")
public class OpenParamDBModel extends DBModel {
    @ColumnInf(name = "fiId",primaryKey = true)
    public int fiId;                   //自增ID
    @ColumnInf(name = "fsMAreaId",primaryKey = true)
    public String fsMAreaId;           // 区域ID
    @ColumnInf(name = "fiOrderUintCd",primaryKey = true)
    public int fiOrderUintCd;         //菜品规格内码
    @ColumnInf(name = "fiItemCd",primaryKey = true)
    public int fiItemCd;              // 菜品内码ID
    @ColumnInf(name = "fiNumberType",primaryKey = true)
    public int fiNumberType;         // '数量类型(1:固定数量，0:按人数)',
    @ColumnInf(name = "fiSaleQty",primaryKey = true)
    public int fiSaleQty;            //'固定数量',
    @ColumnInf(name = "fiIsGift",primaryKey = true)
    public int fiIsGift;              //'赠送(1:勾选，0:不勾选)',
    @ColumnInf(name = "fiStatus",primaryKey = true)
    public int fiStatus;               //'状态',
    @ColumnInf(name = "fsShopGUID",primaryKey = true)
    public String fsShopGUID;          //'门店shopGUID',
    @ColumnInf(name = "fsUpdateTime",primaryKey = true)
    public String fsUpdateTime;       //修改日期时间',
    @ColumnInf(name = "fsUpdateUserId",primaryKey = true)
    public String fsUpdateUserId;    // '修改用户代码',
    @ColumnInf(name = "fsUpdateUserName",primaryKey = true)
    public String fsUpdateUserName;    //'修改用户名称',


}
