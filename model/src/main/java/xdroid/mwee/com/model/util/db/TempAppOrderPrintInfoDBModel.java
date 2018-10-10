package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * Created by liuxiuxiu on 2018/3/12.
 * 外卖小票打印Model
 */

@TableInf(name = "TempAppOrderPrintInfo")
public class TempAppOrderPrintInfoDBModel extends DBModel {
    /**
     * ID
     */
    @ColumnInf(name = "guid", primaryKey = true)
    public String guid = "";
    /**
     * 订单号
     */
    @ColumnInf(name = "orderId")
    public String orderId = "";

    /**
     * 打印时间
     */
    @ColumnInf(name = "printTime")
    public String printTime = "";

    /**
     * 打印类型：0：POS正常接单打印；1：补单；
     */
    @ColumnInf(name = "printType")
    public int printTyp = 0;

    /**
     * 小票类型0：制作单；1：结账单
     */
    @ColumnInf(name = "receptType")
    public int receptType = 0;

    /**
     * 服务员ID;如果是补单：-1
     */
    @ColumnInf(name = "userId")
    public String userId = "";

    /**
     * 服务员名称；如果是补单：第三方平台
     */
    @ColumnInf(name = "UserName")
    public String UserName = "";

    /**
     * 门店GUID
     */
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID;

    public TempAppOrderPrintInfoDBModel() {
    }
}
