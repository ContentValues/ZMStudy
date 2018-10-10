package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

/**
 * 支付分账时，查询的临时菜品Model
 * Created by virgil on 2018/1/11.
 *
 * @author virgil
 */
@TableInf(name = "tbSellOrderItem")
public class SellCouponTempMenuModel extends DBModel {
    @ColumnInf(name = "fsSeq", primaryKey = true)
    public String fsSeq;
    @ColumnInf(name = "fiOrderSeq")
    public int fiOrderSeq;
    /**
     * 原始结算总价
     */
    @ColumnInf(name = "fdOriginalAmt")
    public BigDecimal fdOriginalAmt = BigDecimal.ZERO;
    /**
     * 结算价
     */
    @ColumnInf(name = "fdSaleAmt")
    public BigDecimal fdSaleAmt = BigDecimal.ZERO;

    @ColumnInf(name = "fdDisAfterAmt")
    public BigDecimal fdDisAfterAmt = BigDecimal.ZERO;
    /**
     * 是否可折扣：0，不；1，可以
     */
    @ColumnInf(name = "fiIsDiscount")
    public int fiIsDiscount;

    public SellCouponTempMenuModel() {

    }
}
