package com.mwee.android.sqlite.base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Pair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mwee.android.tools.LogUtil;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Some business With DB
 */
public class ClientMetaUtil {
    /**
     * 配置是否打开
     *
     * @param key          int | key,see{@link META}
     * @param okValue      String | 开关打开时的value
     * @param defauleValue String | 默认的value
     * @return boolean | 配置是否打开，true：配置打开；false，配置关闭
     */
    public static boolean isConfigOpen(int key, String okValue, String defauleValue) {
        String value = ClientMetaUtil.getConfig(key, defauleValue);
        return TextUtils.equals(okValue, value);
    }

    /**
     * 获取门店配置，如果没有设置，则返回默认值
     *
     * @param key          int | see{@link META}
     * @param defauleValue String |
     * @return String
     */
    public static String getConfig(int key, String defauleValue) {
        String value = ClientMetaUtil.getSettingsValueByKey(key);
        if (TextUtils.isEmpty(value)) {
            value = defauleValue;
            ClientMetaUtil.updateSettingsValueByKey(key, defauleValue);
        }
        return value;
    }

    /**
     * 删除字典表里的数据
     *
     * @param key
     */
    public static void deleteSettingsValueByKey(final Integer key) {
        IDBOperate op = new IDBOperate() {
            @Override
            public Object doJob(SQLiteDatabase db) {
                try {
                    if (db != null) {
                        if (LogUtil.SHOW) {
                            LogUtil.log("DB Meta", "updateSettingsValueByKey " + JSON.toJSONString(list));
                        }
                        db.delete("meta", "key = '" + key + "'", null);
                    }
                } catch (Exception e) {
                    LogUtil.logError("", e);
                }
                return null;
            }
        };
        DBManager.getInstance(APPConfig.DB_MAIN).executeInTransactionWithOutThread(op);
    }

    /**
     * 批量更新字典表里的数据
     *
     * @param list List<Pair<String, String>>
     */
    public static void updateSettingsValueByKey(final List<Pair<Integer, String>> list) {
        IDBOperate op = new IDBOperate() {
            @Override
            public Object doJob(SQLiteDatabase db) {
                try {
                    if (db != null) {
                        ContentValues s = new ContentValues(2);
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
        DBManager.getInstance(APPConfig.DB_MAIN).executeInTransactionWithOutThread(op);
    }

    /**
     * 批量更新字典表里的数据
     *
     * @param list List<Pair<String, String>>
     */
    public static void updateSettings(final List<JSONObject> list) {

        IDBOperate op = new IDBOperate() {
            @Override
            public Object doJob(SQLiteDatabase db) {
                try {
                    if (db != null) {
                        ContentValues s = new ContentValues(2);
                        for (JSONObject temp : list) {

                            s.clear();
                            s.put("key", temp.getString("key"));
                            s.put("value", temp.getString("value"));
                            db.replace("meta", null, s);
                        }
                    }
                } catch (Exception e) {
                    LogUtil.logError("", e);
                }
                return null;
            }
        };
        DBManager.getInstance(APPConfig.DB_MAIN).executeInTransactionWithOutThread(op);
    }

    /**
     * 查询字典表，根据key查value
     *
     * @param key Integer
     * @return String|result
     */
    public static String getSettingsValueByKey(final int key) {
        IDBOperate<String> op = new IDBOperate<String>() {
            @Override
            public String doJob(SQLiteDatabase db) {
                String value = null;
                Cursor cursor = null;
                try {
                    if (db != null) {
                        cursor = db.query("meta", new String[]{"value"}, "key = ?", new String[]{key + ""}, null, null, null);
                        if (cursor != null) {
                            if (cursor.moveToNext()) {
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
        return DBManager.getInstance(APPConfig.DB_MAIN).executeQuery(op);
    }

    /**
     * 更新字典表里的数据
     *
     * @param key   String|key
     * @param value String|value
     */
    public static void updateSettingsValueByKey(final int key, final String value) {
        List<Pair<Integer, String>> list = new ArrayList<Pair<Integer, String>>();
        Pair<Integer, String> pair = new Pair<Integer, String>(key, value);
        list.add(pair);
        updateSettingsValueByKey(list);
    }

    /**
     * 重载，更新字典表里的数据
     *
     * @param key   int｜key
     * @param value int｜value
     */
    public static void updateSettingsValueByKey(int key, int value) {
        updateSettingsValueByKey(key, String.valueOf(value));
    }

    public static List<JSONObject> getAllValue() {
        return DBSimpleUtil.queryJsonList(APPConfig.DB_MAIN, "select * from meta");
    }

    /**
     * 删除
     */
    public static void deleteAll() {
        DBSimpleUtil.excuteSql(APPConfig.DB_MAIN, "delete from meta where key<>'1'");
    }


}
