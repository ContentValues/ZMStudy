package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbpaymenttype")
public class PaymenttypeDBModel extends DBModel {
    @ColumnInf(name = "fsPaymentTypeName")
    public String fsPaymentTypeName = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsPaymentTypeId", primaryKey = true)
    public String fsPaymentTypeId = "";
    @ColumnInf(name = "fiDataKind")
    public int fiDataKind = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";

    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    public PaymenttypeDBModel() {

    }

    @Override
    public PaymenttypeDBModel clone() {
        PaymenttypeDBModel cloneObj = null;
        try {
            cloneObj = (PaymenttypeDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}