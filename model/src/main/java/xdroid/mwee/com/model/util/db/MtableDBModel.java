package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbmtable")
public class MtableDBModel extends DBModel {
    /**
     * 桌台属性
     */
    @ColumnInf(name = "fsmtableid", primaryKey = true)
    public String fsmtableid = "";  //餐桌代码

    @ColumnInf(name = "fsmtablename")
    public String fsmtablename = ""; //餐桌名称

    @ColumnInf(name = "fsmtableclsid")
    public String fsmtableclsid = ""; //餐桌类别代码

    @ColumnInf(name = "fiseats")
    public int fiseats = 0;  //座位数

    @ColumnInf(name = "fdmtableprice")
    public BigDecimal fdmtableprice = BigDecimal.ZERO; //餐桌价格

    @ColumnInf(name = "fsmareaid")
    public String fsmareaid = "";  //餐区代码

    @ColumnInf(name = "fiSortOrder")
    public int fiSortOrder = 0;//顺序

    @ColumnInf(name = "fistatus")
    public int fistatus = 0;  //数据状态;1=正常 / 2=临时用(搭台、拆分餐单、反结…的＋1桌) / 9=禁用/ 13删除

    @ColumnInf(name = "fsupdatetime")
    public String fsupdatetime = "";  //修改日期时间

    @ColumnInf(name = "fsupdateuserid")
    public String fsupdateuserid = "";   //修改用户代码

    @ColumnInf(name = "fsupdateusername")
    public String fsupdateusername = "";  //修改用户名称

    @ColumnInf(name = "fsshopguid", primaryKey = true)
    public String fsshopguid = "";  //门店GUID

    //TODO 暂时没用到的业务字段

    /*- 预订信息 -*/
    @ColumnInf(name = "fscustguid")
    public String fscustguid = "";  //客户GUID

    @ColumnInf(name = "fscustname")
    public String fscustname = "";  //客户名称

    @ColumnInf(name = "fscustmobile")
    public String fscustmobile = "";  //客户手机

    @ColumnInf(name = "fsbookingtime")
    public String fsbookingtime = "";   //预订抵达时间(当前餐段)

    /* 点单信息 */
    @ColumnInf(name = "fiorderjob")
    public int fiorderjob = 0;  //点单中状态;0=false/1=true

    @ColumnInf(name = "fsorderusername")
    public String fsorderusername = "";  //站点名+用户名

    @ColumnInf(name = "fsorderhstime")
    public String fsorderhstime = "";  //点单握手时间;心跳

    /* 结账信息 */
    @ColumnInf(name = "ficheckjob")
    public int ficheckjob = 0;  //结账中状态;0=false/1=true

    @ColumnInf(name = "fscheckusername")
    public String fscheckusername = "";  //站点名+用户名

    @ColumnInf(name = "fscheckhstime")
    public String fscheckhstime = "";  //结账握手时间;心跳

    /* 换桌信息 */
    @ColumnInf(name = "fichangejob")
    public int fichangejob = 0;  //换桌中状态;0=false/1=true

    @ColumnInf(name = "fschangeusername")
    public String fschangeusername = "";  //站点名+用户名

    @ColumnInf(name = "fsOrderJob")
    public String fsOrderJob = "0";  //站点名+用户名

    @ColumnInf(name = "fschangehstime")
    public String fschangehstime = "";  //换桌握手时间;心跳

    //TODO 业务相关
    @ColumnInf(name = "fssellno")
    public String fssellno = "";   //销售单号

    @ColumnInf(name = "fsmtablesteid")
    public String fsmtablesteid = "1";  //餐桌狀態代號;1空闲 / 2开台 / 3占用 / 8预订 / 9停用

    @ColumnInf(name = "fshint")
    public String fshint = "";  //餐桌关联备注; 主桌桌号

    @ColumnInf(name = "fisharebills")
    public int fisharebills = 0;  //「拼台」账单数;默认0; 一桌多单,产生桌号+1，只要拼台账单数>0，此桌就不能清台; 拆分餐单、反结账 +1桌不用

    @ColumnInf(name = "fiopenjob")
    public int fiopenjob = 0;//开台中状态;0=false/1=true

    @ColumnInf(name = "fsopenusername")
    public String fsopenusername = "";//站点名+用户名

    @ColumnInf(name = "fsopenhstime")
    public String fsopenhstime = "";//开台握手时间;心跳


    @ColumnInf(name = "fioccupyflag")
    public int fioccupyflag = 0; //占用标识; 1一般 / 2印单完成 /  3结账完成(收银结) / 4线上支付完成(客人结)

    @ColumnInf(name = "fiwxmsgflag")
    public int fiwxmsgflag = 0; //云端交互信息标识; 0无   / 1点菜铃图 / 2结账铃图 /  3开票铃图 / 4 服务铃图


    /**
     * POS端新增
     */
    @ColumnInf(name = "prestatmentstatus")
    public int prestatmentstatus = 0;

    @ColumnInf(name = "ordersource")
    public int ordersource = 0;

    /**
     * 标志位
     * 2. 是否服务铃
     * 4. 是否未读秒点单
     */
    @ColumnInf(name = "flag")
    public int flag = 0;
    /**
     * 额外存储的order信息(json, 秒点订单用)
     */
    @ColumnInf(name = "extra_order")
    public String extra_order = "";


    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    public MtableDBModel() {

    }

    @Override
    public MtableDBModel clone() {
        MtableDBModel cloneObj = null;
        try {
            cloneObj = (MtableDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}