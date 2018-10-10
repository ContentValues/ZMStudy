package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbhost")
public class HostDBModel extends DBModel {
    @ColumnInf(name = "fsHostId", primaryKey = true)
    public String fsHostId = "";
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fiHostCls")
    public int fiHostCls = 0;//站点类型;  11=点菜站点 \ 12=收银站点
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsEncryption")
    public String fsEncryption = "";
    @ColumnInf(name = "fiLanguage")
    public int fiLanguage = 0;
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsPrinterName")
    public String fsPrinterName = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";
    @ColumnInf(name = "fsHostDesc")
    public String fsHostDesc = "";
    @ColumnInf(name = "fsNote")
    public String fsNote = "";
    @ColumnInf(name = "sync")
    public int sync = 0;

    /**
     * 数据来源. 0, 后台; 1,Pos
     */
    @ColumnInf(name = "fiDataSource")
    public int fiDataSource = 0;

    public HostDBModel() {

    }

    @Override
    public String toString() {
        return fsHostId;
    }

    @Override
    public HostDBModel clone() {
        HostDBModel cloneObj = null;
        try {
            cloneObj = (HostDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}