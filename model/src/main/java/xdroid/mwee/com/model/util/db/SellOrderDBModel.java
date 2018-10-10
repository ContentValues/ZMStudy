package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbSellOrder")
public class SellOrderDBModel extends DBModel {
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime = "";
    @ColumnInf(name = "fsCreateUserName")
    public String fsCreateUserName = "";
    @ColumnInf(name = "fsCreateUserId")
    public String fsCreateUserId = "";
    @ColumnInf(name = "fsSellNo", primaryKey = true)
    public String fssellno = "";
    @ColumnInf(name = "fsSellDate")
    public String fsSellDate = "";
    @ColumnInf(name = "fiOrderCls")
    public int fiOrderCls = 0;
    @ColumnInf(name = "fiOrderSeq", primaryKey = true)
    public int fiorderseq = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsshopguid = "";
    @ColumnInf(name = "fiOrderSte")
    public int fiOrderSte = 0;
    @ColumnInf(name = "fsNote")
    public String fsNote = "";
    @ColumnInf(name = "lver")
    public int lver = 0;
    @ColumnInf(name = "pver")
    public int pver = 0;

    public SellOrderDBModel() {

    }

    @Override
    public SellOrderDBModel clone() {
        SellOrderDBModel cloneObj = null;
        try {
            cloneObj = (SellOrderDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}