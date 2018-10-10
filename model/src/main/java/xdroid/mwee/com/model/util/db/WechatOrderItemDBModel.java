package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

/**
 * Created by lxx on 17/1/22.
 */

@TableInf(name = "tbwechatorderitem")
public class WechatOrderItemDBModel extends DBModel {

    @ColumnInf(name = "fsseqno")
    public String fsseqno = "";   // '订单明细编号',
    @ColumnInf(name = "fsorderno")
    public String fsorderno = "";   // '订单编号',
    @ColumnInf(name = "fsshopguid")
    public String fsshopguid = "";   //'门店id',
    @ColumnInf(name = "fsitemcode")
    public String fsitemcode = "";   // '菜品编码',
    @ColumnInf(name = "fsitemname")
    public String fsitemname = "";   // '菜品名称',
    @ColumnInf(name = "fiitemnum")
    public BigDecimal fiitemnum = BigDecimal.ZERO;   // '菜品数量',
    @ColumnInf(name = "fdmemberamount")
    public BigDecimal fdmemberamount = BigDecimal.ZERO;  // '会员价格',
    @ColumnInf(name = "fdsellamount")
    public BigDecimal fdsellamount = BigDecimal.ZERO;  // '销售价格',
    @ColumnInf(name = "fsactivityno")
    public String fsactivityno = "";   // '活动编号',
    @ColumnInf(name = "fddiscount")
    public BigDecimal fddiscount = BigDecimal.ZERO;  // '折前小计',
    @ColumnInf(name = "fddiscountrate")
    public BigDecimal fddiscountrate = BigDecimal.ZERO;  // '折扣率',
    @ColumnInf(name = "fddiscountamount")
    public BigDecimal fddiscountamount = BigDecimal.ZERO;  // '折扣金额',
    @ColumnInf(name = "fdsubtotal")
    public BigDecimal fdsubtotal = BigDecimal.ZERO;  // '小计',
    @ColumnInf(name = "fiactivitytype")
    public int fiactivitytype = 0;   // '活动类型',
    @ColumnInf(name = "fdrealamount")
    public BigDecimal fdrealamount = BigDecimal.ZERO;  // '结算价格',
    @ColumnInf(name = "fipresentflag")
    public int fipresentflag = 0;   // '是否赠送{0:否 1:是}',
    @ColumnInf(name = "fimenuflag")
    public int fimenuflag = 0;   // '是否套餐{0:否 1:套餐头2:套餐明细}',
    @ColumnInf(name = "fsmenuguid")
    public String fsmenuguid = "";   //'套餐ID',
    @ColumnInf(name = "fsmenuseqno")
    public String fsmenuseqno = "";   // '套餐编号',
    @ColumnInf(name = "fsmenuname")
    public String fsmenuname = "";   // '套餐名称',
    @ColumnInf(name = "fdaddprice")
    public BigDecimal fdaddprice = BigDecimal.ZERO;  // '加价',
    @ColumnInf(name = "fsstandardcode")
    public String fsstandardcode = "";   //
    @ColumnInf(name = "fsstandardname")
    public String fsstandardname = "";   //
    @ColumnInf(name = "fspracticecode")
    public String fspracticecode = "";   //
    @ColumnInf(name = "fspracticename")
    public String fspracticename = "";   //
    @ColumnInf(name = "fdpracticeamount")
    public BigDecimal fdpracticeamount = BigDecimal.ZERO;  //,
    @ColumnInf(name = "fsremark")
    public String fsremark = "";   // '备注',
    @ColumnInf(name = "fsupdatetime")
    public String fsupdatetime = "";   // '修改时间',
    @ColumnInf(name = "fscreatetime")
    public String fscreatetime = "";   // '创建时间',

}
