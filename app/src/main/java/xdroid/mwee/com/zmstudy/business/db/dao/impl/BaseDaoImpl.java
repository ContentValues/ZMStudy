package xdroid.mwee.com.zmstudy.business.db.dao.impl;
import android.content.ContentValues;
import com.mwee.android.sqlite.base.DBModel;
import java.util.Iterator;
import xdroid.mwee.com.zmstudy.business.db.dao.IBaseDao;

/**
 * Created by zhangmin on 2018/10/31.
 */

public abstract class BaseDaoImpl<T extends DBModel> implements IBaseDao<T> {

    /**
     * 得到where条件
     *
     * @param contentValues
     * @return
     */
    public String getCondition(ContentValues contentValues) {
        Iterator<String> iterator = contentValues.keySet().iterator();
        StringBuilder sb = new StringBuilder("where ");
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = contentValues.getAsString(key);
            sb.append(key).append(" = ").append(String.format("'%s'", value));
            if (iterator.hasNext()) {
                sb.append(" and ");
            }
        }
        return sb.toString();
    }
}
