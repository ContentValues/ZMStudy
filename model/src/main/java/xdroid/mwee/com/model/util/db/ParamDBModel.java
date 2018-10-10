package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

@TableInf(name = "tbparam")
public class ParamDBModel extends DBModel {
    @ColumnInf(name = "fsParamId", primaryKey = true)
    public String fsParamId = "";
    @ColumnInf(name = "fsUpdateTime")
    public String fsUpdateTime = "";
    @ColumnInf(name = "fsParamName")
    public String fsParamName = "";
    @ColumnInf(name = "fiParamCls")
    public int fiParamCls = 0;
    @ColumnInf(name = "fsNote")
    public String fsNote = "";

    public ParamDBModel() {

    }

    @Override
    public ParamDBModel clone() {
        ParamDBModel cloneObj = null;
        try {
            cloneObj = (ParamDBModel) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}