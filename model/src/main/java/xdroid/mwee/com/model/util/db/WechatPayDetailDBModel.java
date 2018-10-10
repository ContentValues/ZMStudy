package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

/**
 * Created by lxx on 17/1/22.
 */

@TableInf(name = "tbwechatpaydetail")
public class WechatPayDetailDBModel extends DBModel {
    @ColumnInf(name = "fsseqno")
    public String fsseqno = "";   // '主键编号',
    @ColumnInf(name = "fsorderno")
    public String fsorderno = "";   // '订单编号',
    @ColumnInf(name = "fstypeno")
    public String fstypeno = "";   // '支付类型编号',
    @ColumnInf(name = "fsshopguid")
    public String fsshopguid = "";   //'门店id',
    @ColumnInf(name = "fspayno")
    public String fspayno = "";   // '支付单号',
    @ColumnInf(name = "fdpayamount")
    public BigDecimal fdpayamount = BigDecimal.ZERO;  // '支付金额',
    @ColumnInf(name = "fdsettleamount")
    public BigDecimal fdsettleamount = BigDecimal.ZERO;  // '结算金额',
    @ColumnInf(name = "ficount")
    public int ficount = 0;   // '数量',
    @ColumnInf(name = "fscode")
    public String fscode = "";   //
    @ColumnInf(name = "firealflag")
    public int firealflag = 0;   //
    @ColumnInf(name = "fipaytype")
    public int fipaytype = 0;   // '支付类型{0：会员储值支付  1：微信支付  2：支付宝   3：百度钱包   4：银联卡  5：优惠券  6：积分}',
    @ColumnInf(name = "fipaystate")
    public int fipaystate = 0;   // '支付状态{0：待支付 1：已支付  2： 支付失败  3：已退款  4：退款失败  }',
    @ColumnInf(name = "fsupdatetime")
    public String fsupdatetime = "";   // '修改时间',
    @ColumnInf(name = "fscreatetime")
    public String fscreatetime = "";   // '创建时间',


    /**
     * 支付方式{1：微信支付 2：会员储值支付}",
     * "firealflag": "支付类型{0：会员储值支付  1：微信支付  2：支付宝   3：百度钱包 4：银联卡  5：优惠券  6：积分}",
     *
     * @return
     */
    public String getPayType() {
        switch (fipaytype) {
            case 0:
                return "会员储值支付";
            case 1:
                return "微信支付";
            case 2:
                return "支付宝";
            case 3:
                return "百度钱包";
            case 4:
                return "银联卡";
            case 5:
                return "优惠券";
            case 6:
                return "积分";
        }
        return "";
    }

}
