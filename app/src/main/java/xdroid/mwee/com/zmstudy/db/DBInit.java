package xdroid.mwee.com.zmstudy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mwee.android.sqlite.base.DBManager;
import com.mwee.android.sqlite.base.IDBCreate;
import com.mwee.android.tools.LogUtil;

import xdroid.mwee.com.zmstudy.R;

/**
 * Created by zhangmin on 2018/4/17.
 */

public class DBInit {

    protected final static int DBVERSION = 1;

    public final static String DB_MAIN = "posclientdb.sqlite";

    public static void init(Context context) {
        DBManager.setDefaultDbName(DB_MAIN);
        DBManager.init(context, R.raw.posclientdb, DB_MAIN, DBVERSION, new IDBCreate() {
            @Override
            public void onCreate(SQLiteDatabase db) {
                LogUtil.log("BaseDBInit initDB onCreate version " + DBVERSION);
                //初次创建
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                //升级
                LogUtil.log("BaseDBInit initDB onUpgrade version " + DBVERSION);

            }
        });
    }
}
