package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbSellCheck")
public class SellcheckDBModel extends DBModel {
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime = "";
    @ColumnInf(name = "fsCreateUserName")
    public String fsCreateUserName = "";
    @ColumnInf(name = "fiPrintTimes")
    public int fiPrintTimes = 0;
    @ColumnInf(name = "fsSellDate")
    public String fsSellDate = "";
    @ColumnInf(name = "fsCreateUserId")
    public String fsCreateUserId = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fdReceAmt")
    public BigDecimal fdReceAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fsCheckTime")
    public String fsCheckTime = "";
    @ColumnInf(name = "fsHostId")
    public String fsHostId = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsVerifyTime")
    public String fsVerifyTime = "";
    @ColumnInf(name = "fiCheckTimes")
    public int fiCheckTimes = 0;
    @ColumnInf(name = "fiindex")
    public long fiindex = 0;
    @ColumnInf(name = "fdInvoiceAmt")
    public BigDecimal fdInvoiceAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fsSellNo", primaryKey = true)
    public String fssellno = "";
    @ColumnInf(name = "fsCheckBillNo", primaryKey = true)
    public String fscheckbillno = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsshopguid = "";
    @ColumnInf(name = "fsShiftId")
    public String fsShiftId = "";
    /**
     * --班次名称
     */
    @ColumnInf(name = "fsshiftname")
    public String fsshiftname = "";
    @ColumnInf(name = "fdRealAmt")
    public BigDecimal fdRealAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fsVerifyUserId")
    public String fsVerifyUserId = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";
    @ColumnInf(name = "fsbackup0")
    public String fsbackup0 = "";
    @ColumnInf(name = "fsbackup1")
    public String fsbackup1 = "";
    @ColumnInf(name = "fsbackup2")
    public String fsbackup2 = "";

    @ColumnInf(name = "locked")
    public int locked = 0;

    @ColumnInf(name = "lver")
    public int lver = 0;
    @ColumnInf(name = "pver")
    public int pver = 0;

    public BigDecimal fdDebtAmt = BigDecimal.ZERO;
    public String fsCreditAccountName;
    public String fsCreditAccountId;

    public SellcheckDBModel() {

    }

    @Override
    public SellcheckDBModel clone() {
        SellcheckDBModel cloneObj = null;
        try {
            cloneObj = (SellcheckDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}