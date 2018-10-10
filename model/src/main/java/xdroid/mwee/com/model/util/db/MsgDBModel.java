package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbmsg")
public class MsgDBModel extends DBModel {
    @ColumnInf(name = "fsMsg")
    public String fsMsg = "";
    @ColumnInf(name = "fsCreateTime")
    public String fsCreateTime = "";
    @ColumnInf(name = "fsShopGUID")
    public String fsShopGUID = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsSender")
    public String fsSender = "";
    @ColumnInf(name = "fsReadTime")
    public String fsReadTime = "";
    @ColumnInf(name = "fsMTableName")
    public String fsMTableName = "";
    @ColumnInf(name = "fiMsgKind")
    public int fiMsgKind = 0;
    @ColumnInf(name = "fiMsgNo", primaryKey = true)
    public long fiMsgNo = 0;
    @ColumnInf(name = "fsMTableId")
    public String fsMTableId = "";
    @ColumnInf(name = "fsReadUserName")
    public String fsReadUserName = "";
    @ColumnInf(name = "fsSellNo")
    public String fsSellNo = "";
    @ColumnInf(name = "fsReceiver")
    public String fsReceiver = "";

    public MsgDBModel() {

    }

    @Override
    public MsgDBModel clone() {
        MsgDBModel cloneObj = null;
        try {
            cloneObj = (MsgDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}