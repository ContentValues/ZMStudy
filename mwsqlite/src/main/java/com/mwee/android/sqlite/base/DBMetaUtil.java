package com.mwee.android.sqlite.base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

import com.alibaba.fastjson.JSON;
import com.mwee.android.tools.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class DBMetaUtil {

    /**
     * 批量更新字典表里的数据
     *
     * @param list List<Pair<String, String>>
     */
    public static void updateSettingsValueByKey(final String dbName, final List<Pair<Integer, String>> list) {
        IDBOperate op = new IDBOperate() {
            @Override
            public Object doJob(SQLiteDatabase db) {
                try {
                    if (db != null) {
                        ContentValues s = new ContentValues(2);
                        if (LogUtil.SHOW) {
                            LogUtil.log("DB Meta", "updateSettingsValueByKey " + JSON.toJSONString(list));
                        }
                        for (Pair<Integer, String> temp : list) {

                            s.clear();
                            s.put("key", temp.first);
                            s.put("value", temp.second);
                            db.replace("meta", null, s);
                        }
                    }
                } catch (Exception e) {
                    LogUtil.logError("", e);
                }
                return null;
            }
        };
        DBManager.getInstance(dbName).executeInTransaction(op);
    }

    /**
     * 查询字典表，根据key查value
     *
     * @param key Integer
     * @return String|result
     */
    public static String getSettingsValueByKey(final String dbName, final int key) {
        IDBOperate<String> op = new IDBOperate<String>() {
            @Override
            public String doJob(SQLiteDatabase db) {
                String value = null;
                Cursor cursor = null;
                try {
                    if (db != null) {
                        cursor = db.query("meta", new String[]{"value"}, "key = ?", new String[]{key + ""}, null, null, null);
                        if (cursor != null) {
                            while (cursor.moveToNext()) {
                                value = cursor.getString(0);
                            }
                        }
                    }
                } catch (Exception e) {
                    LogUtil.logError("", e);
                } finally {
                    DBSimpleUtil.closeCursor(cursor);
                }
                return value;
            }

        };
        return DBManager.getInstance(dbName).executeQuery(op);
    }

    /**
     * 更新字典表里的数据
     *
     * @param key   String|key
     * @param value String|value
     */
    public static void updateSettingsValueByKey(final String dbName, final int key, final String value) {
        List<Pair<Integer, String>> list = new ArrayList<Pair<Integer, String>>();
        Pair<Integer, String> pair = new Pair<Integer, String>(key, value);
        list.add(pair);
        updateSettingsValueByKey(dbName, list);
    }

    /**
     * 重载，更新字典表里的数据
     *
     * @param key   int｜key
     * @param value int｜value
     */
    public static void updateSettingsValueByKey(final String dbName, int key, int value) {
        updateSettingsValueByKey(dbName, key, String.valueOf(value));
    }
}
