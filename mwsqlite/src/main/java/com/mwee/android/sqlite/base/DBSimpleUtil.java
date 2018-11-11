package com.mwee.android.sqlite.base;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Pair;

import com.alibaba.fastjson.JSONObject;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;
import com.mwee.android.tools.ListUtil;
import com.mwee.android.tools.LogUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DBSimpleUtil {


    /**
     * 根据SQL，返回所有查询到的第一列的数据，并映射为指定的类型
     * 重载方法{@link #queryInfoList(String, String, String, Class, boolean)}
     *
     * @param dbName String  | DB名称
     * @param sql    String | 待执行的SQL
     * @param column String | 指定的列名称
     * @param type   Class | 指定需要序列化数据的类型
     * @param <T>    指定的类型
     * @return <T> 指定的类型
     */
    public static <T> T queryInfo(String dbName, final String sql, final String column, final Class<T> type) {
        List<T> list = queryInfoList(dbName, sql, column, type, true);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据SQL，返回所有查询到的数据，并映射为指定的类型
     * 重载方法{@link #queryInfoList(String, String, String, Class, boolean)}
     *
     * @param dbName String  | DB名称
     * @param sql    String | 待执行的SQL
     * @param column String | 指定的列名称
     * @param type   Class | 指定需要序列化数据的类型
     * @param <T>    指定的类型
     * @return <T> 指定的类型
     */
    public static <T> List<T> queryInfoList(String dbName, final String sql, final String column, final Class<T> type) {
        return queryInfoList(dbName, sql, column, type, false);
    }

    /**
     * 根据SQL，返回指定列的数据，并映射为指定的类型
     *
     * @param dbName  String  | DB名称
     * @param sqlIn   String | 待执行的SQL
     * @param column  String | 指定的列名称
     * @param type    Class | 指定需要序列化数据的类型
     * @param <T>     指定的类型
     * @param onlyOne boolean | 是否只返回第一条数据
     * @return <T> 指定的类型
     */
    private static <T> List<T> queryInfoList(final String dbName, String sqlIn, final String column, final Class<T> type, boolean onlyOne) {
        if (TextUtils.isEmpty(sqlIn)) {
            return new ArrayList<>(0);
        }
        if (onlyOne) {
            if (!sqlIn.contains("limit")) {
                sqlIn = sqlIn + " limit 1";
            }
        }
        final String sql = sqlIn;
        IDBOperate<List<T>> op = new IDBOperate<List<T>>() {
            @Override
            public List<T> doJob(SQLiteDatabase db) {
                List<T> value = null;
                Cursor cursor = null;
                try {
                    if (db != null) {
                        cursor = db.rawQuery(sql, null);
                        if (cursor != null) {
                            value = new ArrayList<>();
                            int index = DBToolsUtil.getColumnIndex(cursor, column);
                            while (cursor.moveToNext()) {
                                value.add(DBToolsUtil.buildValueByCursor(cursor, index, type));
                            }
                        }
                    }
                } catch (Exception e) {
                    LogUtil.logError("", e);
                } finally {
                    closeCursor(cursor);
                }
                return value;
            }
        };
        return DBManager.getInstance(dbName).executeQuery(op);
    }

    /**
     * 根据SQL，返回指定列的第一条数据，并强制转换为String
     * 重载{@link #queryStringList(String, String, boolean)}
     *
     * @param dbName String  | DB名称
     * @param sql    String | 待执行的SQL
     * @return String
     */
    public static String queryString(String dbName, final String sql) {
        List<String> list = queryStringList(dbName, sql, true);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return "";
        }
    }

    /**
     * 根据SQL，返回指定列的所有数据，并强制转换为List<String>
     * 重载{@link #queryStringList(String, String, boolean)}
     *
     * @param dbName String  | DB名称
     * @param sql    String | 待执行的SQL
     * @return List<String>
     */
    public static List<String> queryStringList(String dbName, final String sql) {
        return queryStringList(dbName, sql, false);
    }

    /**
     * 根据SQL，返回指定列的数据，并强制转换为String
     *
     * @param dbName  String  | DB名称
     * @param sqlIn   String | 待执行的SQL
     * @param onlyOne boolean | 是否只返回第一条数据
     * @return List<String>
     */
    private static List<String> queryStringList(String dbName, String sqlIn, final boolean onlyOne) {
        if (TextUtils.isEmpty(sqlIn)) {
            return new ArrayList<>(0);
        }
        if (onlyOne) {
            if (!sqlIn.contains("limit")) {
                sqlIn = sqlIn + " limit 1";
            }
        }
        final String sql = sqlIn;
        IDBOperate<List<String>> op = new IDBOperate<List<String>>() {
            @Override
            public List<String> doJob(SQLiteDatabase db) {
                List<String> value = null;
                Cursor cursor = null;
                try {
                    if (db != null) {
                        cursor = db.rawQuery(sql, null);
                        if (cursor != null) {
                            value = new ArrayList<>();
                            while (cursor.moveToNext()) {
                                value.add(DBToolsUtil.buildValueByCursor(cursor, 0, String.class));
                                if (onlyOne) {
                                    break;
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    LogUtil.logError("", e);
                } finally {
                    closeCursor(cursor);
                }
                return value;
            }
        };
        return DBManager.getInstance(dbName).executeQuery(op);
    }

    /**
     * 根据SQL，返回指定列的所有数据，并强制转换为List<String>
     * 重载{@link #queryStringList(String, String, boolean)}
     *
     * @param dbName String  | DB名称
     * @param sql    String | 待执行的SQL
     * @return List<String>
     */
    public static <T> List<T> queryElementList(String dbName, final String sql, Class<T> clz) {
        return queryElementList(dbName, sql, clz, false);
    }

    /**
     * 根据SQL，返回指定列的数据，并强制转换为String
     *
     * @param dbName  String  | DB名称
     * @param sqlIn   String | 待执行的SQL
     * @param onlyOne boolean | 是否只返回第一条数据
     * @return List<String>
     */
    private static <T> List<T> queryElementList(String dbName, String sqlIn, final Class<T> clz, final boolean onlyOne) {
        if (TextUtils.isEmpty(sqlIn)) {
            return new ArrayList<>(0);
        }
        if (onlyOne) {
            if (!sqlIn.contains("limit")) {
                sqlIn = sqlIn + " limit 1";
            }
        }
        final String sql = sqlIn;
        IDBOperate<List<T>> op = new IDBOperate<List<T>>() {
            @Override
            public List<T> doJob(SQLiteDatabase db) {
                List<T> value = null;
                Cursor cursor = null;
                try {
                    if (db != null) {
                        cursor = db.rawQuery(sql, null);
                        if (cursor != null) {
                            value = new ArrayList<>();
                            while (cursor.moveToNext()) {
                                value.add(DBToolsUtil.buildValueByCursor(cursor, 0, clz));
                                if (onlyOne) {
                                    break;
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    LogUtil.logError("", e);
                } finally {
                    closeCursor(cursor);
                }
                return value;
            }
        };
        return DBManager.getInstance(dbName).executeQuery(op);
    }

    /**
     * 执行指定的Sql
     *
     * @param sql String
     * @return boolean | true：成功；fales：失败
     */
    public static boolean excuteSql(String dbName, final String... sql) {
        if (sql != null && sql.length > 0) {
            Boolean reulst = DBManager.getInstance(dbName).executeInTransactionWithOutThread(new IDBOperate<Boolean>() {
                @Override
                public Boolean doJob(SQLiteDatabase db) {
                    for (String temp : sql) {
                        db.execSQL(temp);
                    }
                    return true;
                }
            });
            if (reulst == null) {
                reulst = false;
            }
            return reulst;
        } else {
            return false;
        }
    }

    /**
     * 根据SQL，返回指定列的第一条数据，JSONObject
     * 重载{@link #queryJsonList(String, String, boolean)}
     *
     * @param dbName String  | DB名称
     * @param sql    String | 待执行的SQL
     * @return JSONObject
     */
    public static JSONObject queryJson(String dbName, final String sql) {
        List<JSONObject> list = queryJsonList(dbName, sql, true);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return new JSONObject();
        }
    }

    /**
     * 根据SQL，返回指定列的数据，并强制转换为List<JSONObject>
     * 重载{@link #queryJsonList(String, String, boolean)}
     *
     * @param dbName String  | DB名称
     * @param sql    String | 待执行的SQL
     * @return List<String>
     */
    public static List<JSONObject> queryJsonList(String dbName, final String sql) {
        return queryJsonList(dbName, sql, false);
    }

    /**
     * 根据SQL，返回指定列的数据，并强制转换为List<JSONObject>
     *
     * @param dbName  String  | DB名称
     * @param sqlIn   String | 待执行的SQL
     * @param onlyOne boolean | 是否只返回第一条数据
     * @return List<JSONObject>
     */
    private static List<JSONObject> queryJsonList(String dbName, String sqlIn, final boolean onlyOne) {
        if (TextUtils.isEmpty(sqlIn)) {
            return new ArrayList<>(0);
        }

        if (onlyOne) {
            if (!sqlIn.contains("limit")) {
                sqlIn = sqlIn + " limit 1";
            }
        }
        final String sql = sqlIn;
        IDBOperate<List<JSONObject>> op = new IDBOperate<List<JSONObject>>() {
            @Override
            public List<JSONObject> doJob(SQLiteDatabase db) {
                List<JSONObject> value = null;
                Cursor cursor = null;
                try {
                    if (db != null) {
                        cursor = db.rawQuery(sql, null);
                        if (cursor != null) {
                            value = new ArrayList<>();
                            int count = cursor.getColumnCount();
                            while (cursor.moveToNext()) {
                                JSONObject map = new JSONObject();

                                for (int i = 0; i < count; i++) {
                                    map.put(cursor.getColumnName(i), DBToolsUtil.buildValueByCursor(cursor, i, String.class));
                                }
                                value.add(map);
                                if (onlyOne) {
                                    break;
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    LogUtil.logError("", e);
                } finally {
                    closeCursor(cursor);
                }
                return value;
            }
        };
        return DBManager.getInstance(dbName).executeQuery(op);
    }

    /**
     * 根据SQL，查询到的第一条数据，并映射为指定类型的T
     *
     * @param dbName String  | DB名称
     * @param sql    String | 待执行的SQL
     * @param clz    Class<T> | 指定的映射类
     * @param <T>    | 指定的映射类
     * @return T
     */
    public static <T extends DBModel> T query(String dbName, String sql, final Class<T> clz) {
        List<T> resultList = queryList(dbName, sql, clz, true);
        if (resultList != null && !resultList.isEmpty()) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据SQL，查询到所有数据，并映射为指定类型的List<T>
     *
     * @param dbName String  | DB名称
     * @param sql    String | 待执行的SQL
     * @param clz    Class<T> | 指定的映射类
     * @param <T>    | 指定的映射类
     * @return List<T>
     */
    public static <T extends DBModel> List<T> queryList(String dbName, final String sql, final Class<T> clz) {
        return queryList(dbName, sql, clz, false);
    }

    /**
     * 根据SQL，查询到的数据，并映射为指定类型的List<T>
     *
     * @param dbName  String  | DB名称
     * @param sqlIn   String | 待执行的SQL
     * @param clz     Class<T> | 指定的映射类
     * @param onlyOne boolean | 是否只返回第一条数据
     * @param <T>     | 指定的映射类
     * @return List<T>
     */
    private static <T extends DBModel> List<T> queryList(final String dbName, String sqlIn, final Class<T> clz, final boolean onlyOne) {
        if (TextUtils.isEmpty(sqlIn)) {
            return new ArrayList<>(0);
        }
        if (onlyOne) {
            if (!sqlIn.contains("limit")) {
                sqlIn = sqlIn + " limit 1";
            }
        }
        final String sql = sqlIn;
        List<T> modules = DBManager.getInstance(dbName).executeQuery(new IDBOperate<List<T>>() {
            @Override
            public List<T> doJob(SQLiteDatabase db) {
                List<T> modelList = null;
                Cursor cursor = null;
                try {
                    if (db != null) {
                        String realSql;
                        //如果传入的sql为空,则手动拼接一个
                        if (TextUtils.isEmpty(sql)) {
                            realSql = "select * from " + DBModel.getTableName(clz);
                        } else if (sql.equalsIgnoreCase("where")) {
                            realSql = "select * from " + DBModel.getTableName(clz) + " " + sql;
                        } else {
                            realSql = sql;
                        }
                        cursor = db.rawQuery(realSql, null);
                        if (cursor != null) {
                            modelList = new ArrayList<T>();
                            Field[] fields = clz.getFields();
                            List<Pair<Field, ColumnInf>> cFields = new ArrayList<Pair<Field, ColumnInf>>();
                            for (Field temp : fields) {
                                temp.setAccessible(true);
                                ColumnInf tempC = temp.getAnnotation(ColumnInf.class);
                                if (tempC != null) {
                                    cFields.add(new Pair<Field, ColumnInf>(temp, tempC));
                                }
                            }
                            while (cursor.moveToNext()) {
                                T model = clz.newInstance();
                                for (Pair<Field, ColumnInf> temp : cFields) {
                                    int index = DBToolsUtil.getColumnIndex(cursor, temp.second.name());

                                    if (index == -1) {
                                        continue;
                                    }
                                    temp.first.set(model, DBToolsUtil.buildValueByCursor(cursor, index, temp.first.getType()));
                                }
                                modelList.add(model);
                            }
                        }
                    }
                } catch (Exception e) {
                    LogUtil.logError(e);
                } finally {
                    closeCursor(cursor);
                }
                return modelList;
            }
        });

        return !ListUtil.isEmpty(modules) ? modules : new ArrayList<>(0);
    }

    /**
     * 批量删除
     *
     * @param list List<T> list
     * @param <T>  <T extends DBModel>
     */
    public static <T extends DBModel> void deleteList(final List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        DBManager.getInstance().executeInTransaction(new IDBOperate<Object>() {
            @Override
            public Object doJob(SQLiteDatabase db) {
                Object ob = list.get(0);
                final TableInf table = ob.getClass().getAnnotation(TableInf.class);
                String tableName = table.name();
                List<Pair<String, Field>> primaryKeys = new ArrayList<>();
                Field[] fields = ob.getClass().getFields();
                for (Field tempField : fields) {
                    tempField.setAccessible(true);
                    ColumnInf temp = tempField.getAnnotation(ColumnInf.class);
                    if (temp != null && temp.primaryKey()) {
                        primaryKeys.add(new Pair<String, Field>(temp.name(), tempField));
                    }
                }
                for (T t : list) {
                    db.delete(tableName, DBModel.getDeleteWhere(t, primaryKeys), null);
                }
                return null;
            }
        });
    }


    /**
     * 批量处理replace
     *
     * @param list List<T>
     * @param <T>  <T extends DBModel>
     */
    public static <T extends DBModel> void replaceList(final List<T> list) {
        DBManager.getInstance().executeInTransaction(new IDBOperate<Object>() {
            @Override
            public Object doJob(SQLiteDatabase db) {
                Object ob = list.get(0);
                final TableInf table = ob.getClass().getAnnotation(TableInf.class);
                String tableName = table.name();
                for (T t : list) {
                    db.replace(tableName, null, DBModel.getReplaceInfo(t));
                }
                return null;
            }
        });
    }

    public static void closeCursor(Cursor cursor) {
        try {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
