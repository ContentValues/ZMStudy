package xdroid.mwee.com.zmstudy.db.dao.impl;

import android.content.ContentValues;

import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBSimpleUtil;

import java.util.List;

import xdroid.mwee.com.model.util.db.MtableDBModel;
import xdroid.mwee.com.zmstudy.db.dao.IMtableDao;

/**
 * Created by zhangmin on 2018/10/31.
 */

public class MtableDaoImpl extends BaseDaoImpl<MtableDBModel> implements IMtableDao {

    @Override
    public MtableDBModel query(ContentValues contentValues) {
        String sql = getCondition(contentValues);
        return DBSimpleUtil.query(APPConfig.DB_MAIN, sql, MtableDBModel.class);
    }

    @Override
    public List<MtableDBModel> queryList(ContentValues contentValues) {
        String sql = getCondition(contentValues);
        return DBSimpleUtil.queryList(APPConfig.DB_MAIN, sql, MtableDBModel.class);
    }
}
