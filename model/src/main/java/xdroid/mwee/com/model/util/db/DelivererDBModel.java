package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

import java.math.BigDecimal;

@TableInf(name = "tbdeliverer")
public class DelivererDBModel extends DBModel {
    @ColumnInf(name = "fsDeliverer")
    public String fsDeliverer = "";
    @ColumnInf(name = "fsCellphone")
    public String fsCellphone = "";
    @ColumnInf(name = "fsDelivererId", primaryKey = true)
    public String fsDelivererId = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsDelivererName")
    public String fsDelivererName = "";
    @ColumnInf(name = "fiDeliverertype")
    public int fiDeliverertype = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fdChange")
    public BigDecimal fdChange = BigDecimal.ZERO;
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";

    public DelivererDBModel() {

    }

    @Override
    public DelivererDBModel clone() {
        DelivererDBModel cloneObj = null;
        try {
            cloneObj = (DelivererDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}