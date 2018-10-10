package com.mwee.android.sqlite.base;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Pair;
import com.mwee.android.sqlite.inject.ColumnInf;
import com.mwee.android.sqlite.inject.TableInf;
import com.mwee.android.tools.base.BusinessBean;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class DBModel extends BusinessBean {
    private String tableName = "";
    private String dbName = "";
    private List<Pair<String, Field>> primaryKeys = new ArrayList<>();

    public static String getTableName(Class<? extends DBModel> clz) {
        final TableInf table = clz.getAnnotation(TableInf.class);
        return table.name();
    }

    /**
     * 重载{@link #getReplaceInfo(Object, boolean)}
     *
     * @param object Object
     * @return ContentValues
     */
    public static ContentValues getReplaceInfo(Object object) {
        return getReplaceInfo(object, false);
    }

    /**
     * 工具方法,需要replace的时候,反射获取类的相关信息并填充到ContentValues里
     *
     * @param object      Object
     * @param onlyPrimary boolean | 是否只拼接主键
     * @return ContentValues
     */
    public static ContentValues getReplaceInfo(Object object, boolean onlyPrimary) {
        Field[] fields = object.getClass().getFields();
        ContentValues values = new ContentValues(fields.length);
        try {
            for (Field tempField : fields) {
                tempField.setAccessible(true);
                ColumnInf temp = tempField.getAnnotation(ColumnInf.class);
                if (temp != null) {
                    Object value = tempField.get(object);
                    if (value != null) {
                        if ((onlyPrimary && temp.primaryKey()) || !onlyPrimary && !temp.ignoreWrite()) {
                            String valueStr = "";
                            if (value instanceof BigDecimal) {
                                valueStr = ((BigDecimal) value).toPlainString();
                            } else {
                                valueStr = value.toString();
                            }
                            if ("null".equalsIgnoreCase(valueStr)) {
                                valueStr = "";
                            }
                            values.put(temp.name(), valueStr);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return values;
    }

    /**
     * 工具方法,拼接delete里的where相关的条件语句
     *
     * @param object      Object
     * @param primaryKeys List<String>
     * @return String
     */
    public static String getDeleteWhere(Object object, List<Pair<String, Field>> primaryKeys) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Pair<String, Field> temp : primaryKeys) {
                sb.append(temp.first)
                        .append(" = '")
                        .append(temp.second.get(object).toString())
                        .append("' and ");
            }
            if (sb.length() > 4) {
                sb.delete(sb.length() - 4, sb.length());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 初始化
     * 缓存表名、主键,提高性能
     */
    private synchronized void init() {
        if (TextUtils.isEmpty(tableName)) {
            final TableInf table = this.getClass().getAnnotation(TableInf.class);
            tableName = table.name();
            Field[] fields = this.getClass().getFields();
            for (Field tempField : fields) {
                tempField.setAccessible(true);
                ColumnInf temp = tempField.getAnnotation(ColumnInf.class);
                if (temp != null && temp.primaryKey()) {
                    primaryKeys.add(new Pair<String, Field>(temp.name(), tempField));
                }
            }
            if(TextUtils.isEmpty(dbName)) {
                dbName = table.dbname();
            }
        }

    }

    /**
     * 删除当前Mode
     * see {@link #delete(String)}
     */
    public void delete() {
        init();
        if (primaryKeys.isEmpty()) {
            throw new RuntimeException("This Table has no primaryKeys,you shall call delete(String where) instead of delete()");
        }
        delete(getDeleteWhere(this, primaryKeys));
    }

    /**
     * 删除当前Model
     * Example:delete("key1 = value1 and key2 = value2")
     *
     * @param where String
     */
    public void delete(final String where) {
        init();
        DBManager.getInstance(dbName).executeInTransaction(new IDBOperate<Object>() {
            @Override
            public Object doJob(SQLiteDatabase db) {
                db.delete(tableName, where, null);
                return null;
            }
        });
    }

    /**
     * replace当前model到DB,see{@link #replace(ContentValues, boolean)}
     */
    public void replace() {
        init();
        replace(getReplaceInfo(this), false);
    }

    /**
     * replace当前model到DB,不启用线程
     */
    public void replaceNoTrans() {
        init();
        replace(getReplaceInfo(this), true);
    }

    /**
     * replace指定Model
     * Example:replace("key1 = value1 and key2 = value2")
     *
     * @param value ContentValues
     */
    public void replace(final ContentValues value, boolean sync) {
        init();
        IDBOperate op = new IDBOperate<Object>() {
            @Override
            public Object doJob(SQLiteDatabase db) {
                db.replace(tableName, null, value);
                return null;
            }
        };
        if (sync) {
            DBManager.getInstance(dbName).executeInTransactionWithOutThread(op);
        } else {
            DBManager.getInstance(dbName).executeInTransaction(op);
        }
    }

    /**
     * 清除表所有数据
     */
    public void clearTable() {
        init();
        DBManager.getInstance(dbName).executeInTransaction(new IDBOperate<Object>() {
            @Override
            public Object doJob(SQLiteDatabase db) {
                db.delete(tableName, null, null);
                return null;
            }
        });
    }

    public void update(ContentValues value) {
        update(value, false);
    }

    public void update(ContentValues value, boolean sync) {
        init();
        ContentValues primaryValue = getReplaceInfo(this, true);
        StringBuilder sb = new StringBuilder();
        Iterator<String> ite = primaryValue.keySet().iterator();
        sb.append(" ");
        while (ite.hasNext()) {
            String key = ite.next();
            sb.append(key).append("='").append(primaryValue.get(key)).append("'");
            if (ite.hasNext()) {
                sb.append(" AND ");
            }
        }
        update(value, sb.toString(), sync);
    }

    public void update(final ContentValues value, final String where, boolean sync) {
        init();
        IDBOperate op = new IDBOperate<Object>() {
            @Override
            public Object doJob(SQLiteDatabase db) {
                db.update(tableName, value, where, null);
                return null;
            }
        };
        if (sync) {
            DBManager.getInstance(dbName).executeInTransactionWithOutThread(op);
        } else {
            DBManager.getInstance(dbName).executeInTransaction(op);
        }
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
