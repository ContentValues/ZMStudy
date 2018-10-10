package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbpaymentdetail")
public class PaymentdetailDBModel extends DBModel {
    @ColumnInf(name = "fisettlementmerchant")
    public int fisettlementmerchant = 0;
    @ColumnInf(name = "fipayee")
    public int fipayee = 0;
    @ColumnInf(name = "alipayemail")
    public String alipayemail = "";
    @ColumnInf(name = "fsmanageshopid")
    public String fsmanageshopid = "";
    @ColumnInf(name = "fitradetype")
    public int fitradetype = 0;
    @ColumnInf(name = "fssyspayid")
    public String fssyspayid = "";
    @ColumnInf(name = "fstransuserid")
    public String fstransuserid = "";
    @ColumnInf(name = "fisourceid")
    public int fisourceid = 0;
    @ColumnInf(name = "fipaymenttypeid")
    public int fipaymenttypeid = 0;
    @ColumnInf(name = "fsupdatetime")
    public String fsupdatetime = "";
    @ColumnInf(name = "fiisactive")
    public int fiisactive = 0;
    @ColumnInf(name = "fssourcename")
    public String fssourcename = "";
    @ColumnInf(name = "fsbanktype")
    public String fsbanktype = "";
    @ColumnInf(name = "fsshopid", primaryKey = true)
    public String fsshopid = "";
    @ColumnInf(name = "fstradeno")
    public String fstradeno = "";
    @ColumnInf(name = "fsorderid", primaryKey = true)
    public String fsorderid = "";
    @ColumnInf(name = "fsopenid")
    public String fsopenid = "";
    @ColumnInf(name = "fdamount")
    public BigDecimal fdamount = BigDecimal.ZERO;
    @ColumnInf(name = "fsshopname")
    public String fsshopname = "";
    @ColumnInf(name = "fsdesc")
    public String fsdesc = "";
    @ColumnInf(name = "fstradetime")
    public String fstradetime = "";
    @ColumnInf(name = "fscreatetime")
    public String fscreatetime = "";

    public PaymentdetailDBModel() {

    }

    @Override
    public PaymentdetailDBModel clone() {
        PaymentdetailDBModel cloneObj = null;
        try {
            cloneObj = (PaymentdetailDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}