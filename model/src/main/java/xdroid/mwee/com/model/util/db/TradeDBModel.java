package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbtrade")
public class TradeDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsCompanyGUID", primaryKey = true)
    public String fsCompanyGUID = "";
    @ColumnInf(name = "fsTradeId", primaryKey = true)
    public String fsTradeId = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsTradeName")
    public String fsTradeName = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    public TradeDBModel() {

    }

    @Override
    public TradeDBModel clone() {
        TradeDBModel cloneObj = null;
        try {
            cloneObj = (TradeDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}