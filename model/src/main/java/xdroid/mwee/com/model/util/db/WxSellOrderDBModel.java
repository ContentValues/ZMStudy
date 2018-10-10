package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbWxSellOrder")
public class WxSellOrderDBModel extends DBModel {
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime = "";
    @ColumnInf(name = "fiMsgNo")
    public int fiMsgNo = 0;
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fdOrderAmt")
    public BigDecimal fdOrderAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fsWxID")
    public String fsWxID = "";
    @ColumnInf(name = "fsDiscountId")
    public String fsDiscountId = "";
    @ColumnInf(name = "fsDiscountName")
    public String fsDiscountName = "";
    @ColumnInf(name = "fsCommitTime")
    public String fsCommitTime = "";
    @ColumnInf(name = "fsMTableName")
    public String fsMTableName = "";
    @ColumnInf(name = "fsOrderNo", primaryKey = true)
    public String fsOrderNo = "";
    @ColumnInf(name = "fsMTableId")
    public String fsMTableId = "";
    @ColumnInf(name = "fsSendOnTime")
    public String fsSendOnTime = "";
    @ColumnInf(name = "fsSellNo")
    public String fsSellNo = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsSendAddr")
    public String fsSendAddr = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";

    public WxSellOrderDBModel() {

    }

    @Override
    public WxSellOrderDBModel clone() {
        WxSellOrderDBModel cloneObj = null;
        try {
            cloneObj = (WxSellOrderDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}