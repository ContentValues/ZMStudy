package com.mwee.android.sqlite.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.callback.SyncCallback;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;

public final class DBManager {
    public static boolean init(Context context, int resID, String dbName, int dbVersion) {
        checkInitInstance(dbName);
        DBManager instance = instanceMap.get(dbName);
        instance.setContext(context);
        instance.DB_VERSION = dbVersion;
        return InitDB.init(context, resID, instance.dbFileName);
    }

    public static boolean init(Context context, int resID, String dbName, int dbVersion, IDBCreate create) {
        checkInitInstance(dbName);
        DBManager instance = instanceMap.get(dbName);

        instance.createCallback = create;
        return init(context, resID, instance.dbFileName, dbVersion);
    }

    public static boolean init(Context context, String dbName, int dbVersion, IDBCreate create) {
        checkInitInstance(dbName);
        DBManager instance = instanceMap.get(dbName);
        instance.createCallback = create;
        return init(context, 0, instance.dbFileName, dbVersion);
    }

    /**
     * 检测DBManager的实例的初始化状况。
     * 并将DB名称赋值到对应的实例
     *
     * @param dbName String
     */
    private static synchronized void checkInitInstance(String dbName) {
        DBManager manager = instanceMap.get(dbName);
        if (manager == null) {
            manager = new DBManager();
            instanceMap.put(dbName, manager);
        }
        manager.dbFileName = dbName;
        if (TextUtils.isEmpty(DEFAULT_DB_NAME)) {
            DEFAULT_DB_NAME = dbName;
        }
    }

    private int DB_VERSION = 1;
    /**
     * DB文件的名称，带后缀名
     */
    private String dbFileName = "";
    /**
     * SqLiteHelper实例
     */
    private SqlHelper helper;
    /**
     * 持有的DB对象
     */
    private SQLiteDatabase db;
    /**
     * 持有的DB对象的引用计数器
     */
    private final AtomicInteger dbReference = new AtomicInteger();
    private WeakReference<Context> refContext;
    private IDBCreate createCallback;
    private final static ArrayMap<String, DBManager> instanceMap = new ArrayMap<>();
    private static String DEFAULT_DB_NAME = "";

    /**
     * 获取默认的DBManager实例
     *
     * @return DBManager
     */
    public static DBManager getInstance() {
        if (TextUtils.isEmpty(DEFAULT_DB_NAME)) {
            throw new NullPointerException("You shall call DBManager.setDefaultDbName(String) first");
        }
        return instanceMap.get(DEFAULT_DB_NAME);
    }

    /**
     * 设置默认的DB名称
     *
     * @param defaultDbName String
     */
    public static void setDefaultDbName(String defaultDbName) {
        DEFAULT_DB_NAME = defaultDbName;
    }

    public static DBManager getInstance(String dbname) {
        if (TextUtils.isEmpty(dbname)) {
            return getInstance();
        }
        return instanceMap.get(dbname);
    }

    public void setContext(Context context) {
        refContext = new WeakReference<Context>(context);
    }

    /**
     * 功能：初始化DBHelper
     */
    public synchronized void initHelper() {
        try {
            if(helper!=null){
                helper.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        helper = new SqlHelper(refContext.get(), dbFileName, null, DB_VERSION);
        optDB();
        try {
            helper.setWriteAheadLoggingEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能：执行查询操作，不带事务。
     * 描述：封装DB的打开和关闭
     *
     * @param operate IDBOperate｜数据库操作接口
     * @param <T>     T｜查询操作需要返回的数据类型
     * @return T｜查询操作返回的数据
     */
    public <T> T executeQuery(IDBOperate<T> operate, SyncCallback<T> callback) {
        T t = null;
        if (operate != null) {
            db = optDB();
            if (db != null) {
                try {
                    t = operate.doJob(db);
                } catch (Exception e) {
                    LogUtil.logError("", e);
                }
            }
            close();
        }
        if (callback != null) {
            callback.callback(t);
        }
        return t;
    }

    public <T> T executeQuery(IDBOperate<T> operate) {
        return executeQuery(operate, null);
    }

    /**
     * 重载,see{@link #executeInTransaction(IDBOperate, SyncCallback)}
     *
     * @param operate IDBOperate<T>
     * @param <T>     T｜
     */
    public <T> void executeInTransaction(IDBOperate<T> operate) {
        executeInTransaction(operate, null);
    }
    public <T> T executeInTransactionWithOutThread(IDBOperate<T> operate) {
        T result = null;
        db = optDB();
        if (db != null) {
            try {
                db.beginTransactionNonExclusive();
                result = operate.doJob(db);
                db.setTransactionSuccessful();
            } catch (Exception e) {
                LogUtil.logError("", e);
            } finally {
                if (db != null && db.inTransaction()) {
                    db.endTransaction();
                }
            }
        }
        close();
        return result;
    }

    /**
     * * 功能：执行带事务的DB操作。
     * 描述：封装DB的打开、关闭、事务的开启、结束等。
     *
     * @param operate  IDBOperate｜数据库操作接口
     * @param callback SyncCallback | 需要异步回调的接口
     * @param <T>      T｜DB操作需要返回的数据类型
     */
    public <T> void executeInTransaction(final IDBOperate<T> operate, SyncCallback<T> callback) {
        if (operate == null) {
            return;
        }

        T result = null;
        db = optDB();
        if (db != null) {
            try {
                db.beginTransactionNonExclusive();
                result = operate.doJob(db);
                db.setTransactionSuccessful();
            } catch (Exception e) {
                LogUtil.logError("", e);
            } finally {
                if (db != null && db.inTransaction()) {
                    db.endTransaction();
                }
            }
        }
        close();
        callback.callback(result);

        //TODO 这里面可以使用Rxjava 进行线程切换回调 后期优化
    /*    BusinessExecutor.executeAsyncExcuteOnMain(new ASyncExecute<T>() {
            @Override
            public T execute() {
                T result = null;
                db = optDB();
                if (db != null) {
                    try {
                        db.beginTransactionNonExclusive();
                        result = operate.doJob(db);
                        db.setTransactionSuccessful();
                    } catch (Exception e) {
                        LogUtil.logError("", e);
                    } finally {
                        if (db != null && db.inTransaction()) {
                            db.endTransaction();
                        }
                    }
                }
                close();
                return result;
            }
        }, callback);*/

    }

    /**
     * 功能：获取DB对象的实例。
     * 描述：DB对象的引用计数管理器dbReference来管理是否需要初始化DB对象。
     * 当DB的引用数量小于1的时候，会直接进行初始化。
     * 之后再次判断当DB对象为null或者不是Open的时候，会再次进行一次初始化。
     *
     * @return SQLiteDatabase
     */
    private synchronized SQLiteDatabase optDB() {

        if (dbReference.incrementAndGet() < 2) {
            //保证调用这个方法之后引用数量至少为1
            dbReference.set(1);
        }
        if (db == null || !db.isOpen()) {
            db = prepareDB();
        }
//        if (Config.ENV != ConstantValue.Environment.ENV_PRODUCT) {
//            LogUtil.log("DBManager optDB(),dbReference = " + dbReference.get());
//        }
        return db;
    }

    /**
     * 功能：对DB进行open操作。
     * 描述：通过调用{@link DBManager.SqlHelper}相关方法
     *
     * @return SQLiteDatabase｜DB实例
     */
    private synchronized SQLiteDatabase prepareDB() {
        SQLiteDatabase dbTemp = null;
        if (helper == null) {
            initHelper();
        }
        try {
            dbTemp = helper.getWritableDatabase();
        } catch (Exception e) {
            LogUtil.logError("First Get", e);
        }
        if (dbTemp == null) {
            initHelper();
            try {
                dbTemp = helper.getWritableDatabase();
            } catch (Exception e) {
                LogUtil.logError("Second Get", e);
            }
        }
        return dbTemp;
    }

    public synchronized void closeAll() {
        dbReference.set(0);
        if (db != null) {
            db.close();
            db = null;
        }
        if (helper != null) {
            helper.close();
            helper = null;
        }
    }

    private synchronized void close() {
//        if (Config.ENV != ConstantValue.Environment.ENV_PRODUCT) {
//            LogUtil.log("DBManager close(),dbReference = " + dbReference.get());
//        }
        if (null != helper && dbReference.decrementAndGet() <= 0) {
//            if (Config.ENV != ConstantValue.Environment.ENV_PRODUCT) {
//                LogUtil.log("DBManager close() success,dbReference = " + dbReference.get());
//            }
            dbReference.set(0);

        }
    }

    public static class SqlHelper extends SQLiteOpenHelper {
        private WeakReference<Context> mContextRef = null;
        private String dbName="";

        public SqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            mContextRef = new WeakReference<Context>(context);
            this.dbName=name;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            if (DBManager.getInstance(dbName).createCallback != null) {
                DBManager.getInstance(dbName).createCallback.onCreate(db);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            LogUtil.log("DB", "SqlHelper onUpgrade, oldVersion=" + oldVersion + ",newVersion=" + newVersion);
            if (DBManager.getInstance(dbName).createCallback != null) {
                DBManager.getInstance(dbName).createCallback.onUpgrade(db, oldVersion, newVersion);
                DBManager.getInstance(dbName).createCallback = null;
            }
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }
}
