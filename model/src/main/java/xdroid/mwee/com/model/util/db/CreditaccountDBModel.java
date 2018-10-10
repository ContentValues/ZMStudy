package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbcreditaccount")
public class CreditaccountDBModel extends DBModel {
    @ColumnInf(name = "fsCreditAccountId", primaryKey = true)
    public String fsCreditAccountId = "";
    @ColumnInf(name = "fsCreditAccountName")
    public String fsCreditAccountName = "";
    @ColumnInf(name = "fdCreditAmt")
    public BigDecimal fdCreditAmt = BigDecimal.ZERO;//总信用额度
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fdDebtAmt")
    public BigDecimal fdDebtAmt = BigDecimal.ZERO;//可用余额
    @ColumnInf(name = "fsTelCt")
    public String fsTelCt = "";
    @ColumnInf(name = "fiImgHeight")
    public int fiImgHeight = 0;
    @ColumnInf(name = "fiImgWidth")
    public int fiImgWidth = 0;
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";

    @ColumnInf(name = "fsCellphoneCt")
    public String fsCellphoneCt = "";
    @ColumnInf(name = "fsImagePath")
    public String fsImagePath = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsContact")
    public String fsContact = "";

    @ColumnInf(name = "fsImageURL")
    public String fsImageURL = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";

    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";

    public CreditaccountDBModel() {

    }

    @Override
    public CreditaccountDBModel clone() {
        CreditaccountDBModel cloneObj = null;
        try {
            cloneObj = (CreditaccountDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}