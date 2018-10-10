package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbmsection")
public class MSectionDBModel extends DBModel {
    @ColumnInf(name = "fsUpdateUserName")
    public String fsUpdateUserName = "";
    @ColumnInf(name = "fsMSectionId", primaryKey = true)
    public String fsMSectionId = "";
    @ColumnInf(name = "fiStatus")
    public int fiStatus = 0;
    @ColumnInf(name = "fsMSectionName")
    public String fsMSectionName = "";
    @ColumnInf(name = "fsBeginTime")
    public String fsBeginTime = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsShopGUID", primaryKey = true)
    public String fsShopGUID = "";
    @ColumnInf(name = "fsEndTime")
    public String fsEndTime = "";
    @ColumnInf(name = "fsUpdateUserId")
    public String fsUpdateUserId = "";

    public MSectionDBModel() {

    }

    @Override
    public MSectionDBModel clone() {
        MSectionDBModel cloneObj = null;
        try {
            cloneObj = (MSectionDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}