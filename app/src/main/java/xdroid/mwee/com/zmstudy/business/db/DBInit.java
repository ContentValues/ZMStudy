package xdroid.mwee.com.zmstudy.business.db;

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

    protected final static int DBVERSION = 2;

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

                if (oldVersion < 2) {

                    LogUtil.log("BaseDBInit initDB onUpgrade 执行了升级的方法22 oldVersion--->[ " + oldVersion + "]------------DBVERSION[" + DBVERSION+"---------["+newVersion);
                    db.execSQL("CREATE TABLE kbOrder(\n" +
                            "    order_id text(100),\n" +
                            "    merchant_id text(100),\n" +
                            "    biz_product text(100),\n" +
                            "    shop_id text(50),\n" +
                            "    business_type text(20),\n" +
                            "    dinner_type text(20),\n" +
                            "    order_style text(20),\n" +
                            "    channel text(20),\n" +
                            "    people_num text(20),\n" +
                            "    take_style text(20),\n" +
                            "    take_no text(20),\n" +
                            "    bill_amount decimal(10,2),\n" +
                            "    receipt_amount decimal(10,2),\n" +
                            "    trade_amount decimal(10,2),\n" +
                            "    pay_amount decimal(10,2),\n" +
                            "    service_amount decimal(10,2),\n" +
                            "    packing_amount decimal(10,2),\n" +
                            "    other_amount decimal(10,2),\n" +
                            "    table_time text(20),\n" +
                            "    order_time text(20),\n" +
                            "    user_mobile text(20),\n" +
                            "    memo text(200),\n" +
                            "    ext_info text(500),\n" +
                            "    member_flag text(20),\n" +
                            "    status text(10),\n" +
                            "    dish_details text,\n" +
                            "    pay_channels text, payStatus VARCHAR(80) DEFAULT '',\n" +
                            "    PRIMARY KEY (order_id));");
                }

            }
        });
    }
}
