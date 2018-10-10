package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbfoodtrade")
public class FoodtradeDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsFoodTradeId", primaryKey = true)
    public String fsFoodTradeId = "";
    @ColumnInf(name = "fsFoodTradeName")
    public String fsFoodTradeName = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    public FoodtradeDBModel() {

    }

    @Override
    public FoodtradeDBModel clone() {
        FoodtradeDBModel cloneObj = null;
        try {
            cloneObj = (FoodtradeDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}