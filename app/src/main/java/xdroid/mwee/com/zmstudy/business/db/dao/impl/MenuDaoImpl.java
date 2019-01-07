package xdroid.mwee.com.zmstudy.business.db.dao.impl;
import android.content.ContentValues;
import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBSimpleUtil;
import java.util.List;
import xdroid.mwee.com.model.util.db.MenuitemDBModel;

/**
 * Created by zhangmin on 2018/11/1.
 */

public class MenuDaoImpl extends BaseDaoImpl<MenuitemDBModel> {

    @Override
    public MenuitemDBModel query(ContentValues contentValues) {
        String sql = getCondition(contentValues);
        return DBSimpleUtil.query(APPConfig.DB_MAIN, sql, MenuitemDBModel.class);
    }

    @Override
    public List<MenuitemDBModel> queryList(ContentValues contentValues) {
        String sql = getCondition(contentValues);
        return DBSimpleUtil.queryList(APPConfig.DB_MAIN, sql, MenuitemDBModel.class);
    }




}
