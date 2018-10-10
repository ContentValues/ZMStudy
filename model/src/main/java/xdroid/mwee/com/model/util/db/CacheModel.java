package xdroid.mwee.com.model.util.db;

import com.mwee.android.sqlite.base.DBModel;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;

/**
 * IOCache的DBModel
 * Created by virgil on 2017/7/24.
 */

@TableInf(name = "datacache")
public class CacheModel extends DBModel {
    @ColumnInf(name = "key", primaryKey = true)
    public String key = "";
    @ColumnInf(name = "value")
    public String value = "";
    @ColumnInf(name = "type", primaryKey = true)
    public int type = 0;
    @ColumnInf(name = "biz_key")
    public String biz_key = "";
    @ColumnInf(name = "info")
    public String info = "";

    @ColumnInf(name = "createtime")
    public String createtime = "";//创建时间
    @ColumnInf(name = "updatetime")
    public String updatetime = "";//更新时间

    public CacheModel() {

    }
}
