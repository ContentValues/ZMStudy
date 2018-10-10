package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbcutmoney")
public class CutmoneyDBModel extends DBModel {
    @ColumnInf(name = "fiDiscountRate")
    public int fiDiscountRate = 0;
    @ColumnInf(name = "fsPaymentId")
    public String fsPaymentId = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsBargainId", primaryKey = true)
    public String fsBargainId = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fdFullmoney")
    public BigDecimal fdFullmoney = BigDecimal.ZERO;

    /**
     * 叠加方式：0，不叠加；1，叠加
     */
    @ColumnInf(name = "fifullcuttype")
    public int fifullcuttype = 0;

    @ColumnInf(name = "fdCutmoney")
    public BigDecimal fdCutmoney = BigDecimal.ZERO;
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    public CutmoneyDBModel() {

    }

    @Override
    public CutmoneyDBModel clone() {
        CutmoneyDBModel cloneObj = null;
        try {
            cloneObj = (CutmoneyDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}