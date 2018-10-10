package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbmarea")
public class MareaDBModel extends DBModel {
    @ColumnInf(name = "fsMAreaId", primaryKey = true)
    public String fsMAreaId = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsMAreaName")
    public String fsMAreaName = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "wxMsgCount")
    public int wxMsgCount = 0;
    @ColumnInf(name = "fiSortOrder")
    public int fisortorder = 0;
    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 餐区对应的秒点打印机
     */
    @ColumnInf(name = "fsPrinterName")
    public String fsPrinterName = "";

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;
    /**
     * 服务费率
     */
    @ColumnInf(name = "fiServiceRate")
    public int fiServiceRate = 0;
    /**
     * 餐区服务费收取方式:①不收服务费；②整单金额比例（折前）；③整单金额比例（折后）；④菜品（已勾选服务费）⑤固定金额。
     * see{@link com.mwee.android.pos.db.business.pay.FeeAreaType}
     */
    @ColumnInf(name = "fiServiceType")
    public int fiServiceType = 0;
    /**
     * 餐区服务费的按固定金额收取时的金额值
     */
    @ColumnInf(name = "fdServiceAmt")
    public BigDecimal fdServiceAmt = BigDecimal.ZERO;
    public MareaDBModel() {

    }

    @Override
    public MareaDBModel clone() {
        MareaDBModel cloneObj = null;
        try {
            cloneObj = (MareaDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}