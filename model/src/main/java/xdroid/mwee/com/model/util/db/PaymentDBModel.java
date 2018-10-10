package xdroid.mwee.com.model.util.db;


import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbpayment")
public class PaymentDBModel extends DBModel {
    @ColumnInf(name = "fiIsForeign")
    public int fiIsForeign = 0;
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiIsCalcPaid")
    public int fiIsCalcPaid = 0;
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fiSortOrder")
    public int fiSortOrder = 0;
    @ColumnInf(name = "fiIsPremium")
    public int fiIsPremium = 0;
    @ColumnInf(name = "fiDataKind")
    public int fiDataKind = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fdExchangeRate")
    public BigDecimal fdExchangeRate = BigDecimal.ZERO;
    @ColumnInf(name = "fiIsCalcInvoice")
    public int fiIsCalcInvoice = 0;
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsPaymentId", primaryKey = true)
    public String fsPaymentId = "";
    @ColumnInf(name = "fdDefaultPrice")
    public BigDecimal fdDefaultPrice = BigDecimal.ZERO;
    @ColumnInf(name = "fsPaymentTypeId")
    public String fsPaymentTypeId = "";
    @ColumnInf(name = "fsPaymentName")
    public String fsPaymentName = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";

    @ColumnInf(name = "fscolor")
    public String fscolor = "";
    /**
     * 折扣率
     */
    @ColumnInf(name = "fdDiscountRate")
    public BigDecimal fdDiscountRate = BigDecimal.ZERO;
    /**
     * 折扣归属
     */
    @ColumnInf(name = "fsDiscountPaymentId")
    public String fsDiscountPaymentId = "";
    /**
     * 父类支付方式的名称，表结构中无此字段
     */
    @ColumnInf(name = "fsPaymentTypeName")
    public String fsPaymentTypeName = "";

    /**
     * 使用范围
     * 0: 所有菜品金额总和
     * 1:可打折菜品金额总和
     */
    @ColumnInf(name = "fiIsPartAmtDiscount")
    public int fiIsPartAmtDiscount = 0;

    @ColumnInf(name = "fsShortcutKey")
    public String fsShortcutKey = "";  //'快捷键',

    @ColumnInf(name = "fsHelpCode")
    public String fsHelpCode = "";   //'助记码',

    @ColumnInf(name = "fiIsEffectiveDate")
    public int fiIsEffectiveDate = 0;//'有效时间:0=无限制/1=固定时间',

    @ColumnInf(name = "fsStarDate")
    public String fsStarDate = "";//'开始时间',

    @ColumnInf(name = "fsEndDate")
    public String fsEndDate = "";//'结束时间',

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    /**
     * '验券平台1:美团点评',
     */
    @ColumnInf(name = "fiVoucherPlatform")
    public int fiVoucherPlatform = 0;

    public PaymentDBModel() {
    }

    @Override
    public PaymentDBModel clone() {
        PaymentDBModel cloneObj = null;
        try {
            cloneObj = (PaymentDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}