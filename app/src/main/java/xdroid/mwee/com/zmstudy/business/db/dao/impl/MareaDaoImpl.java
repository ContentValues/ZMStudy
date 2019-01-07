package xdroid.mwee.com.zmstudy.business.db.dao.impl;

import android.content.ContentValues;

import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBSimpleUtil;

import java.util.List;

import xdroid.mwee.com.model.util.db.MareaDBModel;
import xdroid.mwee.com.zmstudy.business.db.dao.IMareaDao;

/**
 * Created by zhangmin on 2018/11/1.
 */

public class MareaDaoImpl extends BaseDaoImpl<MareaDBModel> implements IMareaDao {
    @Override
    public MareaDBModel query(ContentValues contentValues) {
        String sql = getCondition(contentValues);
        return DBSimpleUtil.query(APPConfig.DB_MAIN, sql, MareaDBModel.class);
    }

    @Override
    public List<MareaDBModel> queryList(ContentValues contentValues) {
        String sql = getCondition(contentValues);
        return DBSimpleUtil.queryList(APPConfig.DB_MAIN, sql, MareaDBModel.class);
    }
}
