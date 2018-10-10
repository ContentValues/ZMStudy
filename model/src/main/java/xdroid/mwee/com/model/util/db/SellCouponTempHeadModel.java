package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

/**
 * 订单头
 * Created by virgil on 2018/1/11.
 *
 * @author virgil
 */
@TableInf(name = "tbSell")
public class SellCouponTempHeadModel extends DBModel {
    /**
     * 原始结算总价
     */
    @ColumnInf(name = "fdOriginalAmt")
    public BigDecimal fdOriginalAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fdDisExpAmt")
    public BigDecimal fdDisExpAmt = BigDecimal.ZERO;
    /**
     * 服务费
     */
    @ColumnInf(name = "fdServiceAmt")
    public BigDecimal fdServiceAmt = BigDecimal.ZERO;

    public SellCouponTempHeadModel() {

    }
}
