package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbbargainitem")
public class BargainitemDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fdBargainPrice")
    public BigDecimal fdBargainPrice = BigDecimal.ZERO;
    @ColumnInf(name = "fiOrderUintCd", primaryKey = true)
    public int fiOrderUintCd = 0;
    @ColumnInf(name = "fsBargainId", primaryKey = true)
    public String fsBargainId = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fiItemCd", primaryKey = true)
    public int fiItemCd = 0;
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    public BargainitemDBModel() {

    }

    @Override
    public BargainitemDBModel clone() {
        BargainitemDBModel cloneObj = null;
        try {
            cloneObj = (BargainitemDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}