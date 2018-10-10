package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbSeqNo")
public class SeqnoDBModel extends DBModel {
    @ColumnInf(name = "fiSeqNo")
    public int fiSeqNo = 0;
    @ColumnInf(name = "fsItem")
    public String fsItem = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsshopguid = "";
    @ColumnInf(name = "fsCls", primaryKey = true)
    public String fscls = "";
    @ColumnInf(name = "lver")
    public int lver = 0;
    @ColumnInf(name = "pver")
    public int pver = 0;

    public SeqnoDBModel() {

    }

    @Override
    public SeqnoDBModel clone() {
        SeqnoDBModel cloneObj = null;
        try {
            cloneObj = (SeqnoDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}