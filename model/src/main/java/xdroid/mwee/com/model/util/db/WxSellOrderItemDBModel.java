package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbWxSellOrderItem")
public class WxSellOrderItemDBModel extends DBModel {
    @ColumnInf(name = "fiOrderUintCd")
    public int fiOrderUintCd = 0;
    @ColumnInf(name = "fiOrderItemSte")
    public int fiOrderItemSte = 0;
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime = "";
    @ColumnInf(name = "fiItemCd")
    public int fiItemCd = 0;
    @ColumnInf(name = "fdSettlePrice")
    public BigDecimal fdSettlePrice = BigDecimal.ZERO;
    @ColumnInf(name = "fsTaste")
    public String fsTaste = "";
    @ColumnInf(name = "fsSeq_M")
    public String fsSeq_M = "";
    @ColumnInf(name = "fsCuisineName")
    public String fsCuisineName = "";
    @ColumnInf(name = "fsItemId")
    public String fsItemId = "";
    @ColumnInf(name = "fsItemName")
    public String fsItemName = "";
    @ColumnInf(name = "fiOrderItemKind")
    public int fiOrderItemKind = 0;
    @ColumnInf(name = "fdSaleQty")
    public BigDecimal fdSaleQty = BigDecimal.ZERO;
    @ColumnInf(name = "fdSaleAmt")
    public BigDecimal fdSaleAmt = BigDecimal.ZERO;
    @ColumnInf(name = "fsOrderNo", primaryKey = true)
    public String fsOrderNo = "";
    @ColumnInf(name = "fsSeq", primaryKey = true)
    public String fsSeq = "";
    @ColumnInf(name = "fsMenuClsId")
    public String fsMenuClsId = "";
    @ColumnInf(name = "fsOrderUint")
    public String fsOrderUint = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fdAddPrice")
    public BigDecimal fdAddPrice = BigDecimal.ZERO;
    @ColumnInf(name = "fdSalePrice")
    public BigDecimal fdSalePrice = BigDecimal.ZERO;

    public WxSellOrderItemDBModel() {

    }

    @Override
    public WxSellOrderItemDBModel clone() {
        WxSellOrderItemDBModel cloneObj = null;
        try {
            cloneObj = (WxSellOrderItemDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}