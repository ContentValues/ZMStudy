package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

/**
 * Created by virgil on 2018/1/10.
 *
 * @author virgil
 */
@TableInf(name = "tbSellCoupon")
public class SellCouponDBModel extends DBModel {
    @ColumnInf(name = "fsSellDate", primaryKey = true)
    public String fsSellDate = "";
    @ColumnInf(name = "fiPaySeq", primaryKey = true)
    public int fiPaySeq = 0;
    @ColumnInf(name = "fsSellNo", primaryKey = true)
    public String fsSellNo = "";
    @ColumnInf(name = "fsSeq", primaryKey = true)
    public String fsSeq;
    @ColumnInf(name = "fsCheckBillNo", primaryKey = true)
    public String fsCheckBillNo = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsshopguid = "";
    @ColumnInf(name = "fiOrderSeq")
    public int fiOrderSeq;
    @ColumnInf(name = "fsPaymentId")
    public String fsPaymentId = "";
    /**
     * 支付组ID
     */
    @ColumnInf(name = "fsPaymentTypeId")
    public String fsPaymentTypeId = "";

    @ColumnInf(name = "fspaymenttypename")
    public String fsPaymentTypeName = "";
    @ColumnInf(name = "fdSpliteAmt")
    public BigDecimal fdSpliteAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fspaymentname")
    public String fsPaymentName = "";
    @ColumnInf(name = "fiIsCalcPaid")
    public int fiIsCalcPaid = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime = "";
    @ColumnInf(name = "lver")
    public int lver = 0;
    @ColumnInf(name = "pver")
    public int pver = 0;

    public SellCouponDBModel() {

    }

    @Override
    public SellCouponDBModel clone() {
        SellCouponDBModel cloneObj = null;
        try {
            cloneObj = (SellCouponDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}
