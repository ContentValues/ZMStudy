package com.mwee.android.sqlite.base;

import android.database.sqlite.SQLiteDatabase;

/**
 * IDBOperate<T>,数据库操作的通用接口，see{@link DBManager}类封装DB对象的打开、关闭、事务管理等，由此接口执行SQL
 */
public interface IDBOperate<T> {
    T doJob(SQLiteDatabase db);
}
