package xdroid.mwee.com.zmstudy.db.dao;

import android.content.ContentValues;

import com.mwee.android.sqlite.base.DBModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库表服务通用接口
 * Created by zhangmin on 2018/8/17.
 */

public interface IBaseDao<T extends DBModel> {

    T query(ContentValues contentValues);

    List<T> queryList(ContentValues contentValues);

}
