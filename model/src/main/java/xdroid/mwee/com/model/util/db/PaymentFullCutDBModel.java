package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

/**
 * '满减券明细表'
 */
@TableInf(name = "tbpayment")
public class PaymentFullCutDBModel extends DBModel {

    /**
     * '折价对应的结算方式代号'
     */
    @ColumnInf(name = "fsPaymentId", primaryKey = true)
    public String fsPaymentId = "";

    /**
     * '满价'
     */
    @ColumnInf(name = "fdFullmoney")
    public BigDecimal fdFullmoney = BigDecimal.ZERO;
    /**
     * '减价'
     */
    @ColumnInf(name = "fdCutmoney")
    public BigDecimal fdCutmoney = BigDecimal.ZERO;

    @ColumnInf(name = "fsShopGUID")
    public String fsShopGUID = "";  //'门店shopGUID'
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";//'修改日期时间',
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";//'修改用户代码',
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = ""; //'修改用户名称',

    public PaymentFullCutDBModel() {
    }
}
