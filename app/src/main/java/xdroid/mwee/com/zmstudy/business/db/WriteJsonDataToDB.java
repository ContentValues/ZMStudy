package xdroid.mwee.com.zmstudy.business.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mwee.android.sqlite.base.DBManager;
import com.mwee.android.sqlite.base.DBSimpleUtil;
import com.mwee.android.sqlite.base.IDBOperate;
import com.mwee.android.tools.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangmin on 2018/6/14.
 */

public class WriteJsonDataToDB {

    public static boolean writeDataToDB(final String dbName, final JSONObject ob, final String tag) {

        return DBManager.getInstance(dbName).executeInTransactionWithOutThread(new IDBOperate<Boolean>() {
            @Override
            public Boolean doJob(SQLiteDatabase db) {
                boolean success = false;
                long start = SystemClock.elapsedRealtime();
                long time1 = SystemClock.elapsedRealtime();
                //LogUtil.log("FILLDATA", "read cost " + (time1 - start));
                long time2 = SystemClock.elapsedRealtime();
                //LogUtil.log("FILLDATA", "json cost " + (time2 - time1));

                Iterator<Map.Entry<String, Object>> ite = ob.entrySet().iterator();
                StringBuilder sb;
                StringBuilder sbK;
                StringBuilder sbV;
                while (ite.hasNext()) {
                    Map.Entry<String, Object> entry = ite.next();
                    String tableName = entry.getKey();
                    JSONArray value = (JSONArray) entry.getValue();
                    for (int i = 0; i < value.size(); i++) {
                        sb = new StringBuilder();
                        sb.append("insert or replace into ").append(tableName).append(" ");
                        JSONObject tableValue = value.getJSONObject(i);
                        Iterator<Map.Entry<String, Object>> iteTableValue = tableValue.entrySet().iterator();
                        sbV = new StringBuilder();
                        sbK = new StringBuilder();
                        List<String> sbValue = new ArrayList<>();
                        List<String> columnList = getColumnList(db, tableName);
                        while (iteTableValue.hasNext()) {
                            Map.Entry<String, Object> entry2 = iteTableValue.next();
                            String key2 = entry2.getKey().toLowerCase();
                            String value2 = entry2.getValue() + "";
                            if (columnList.contains(key2)) {
                                sbK.append(key2).append(",");
                                sbV.append("?").append(",");
                                sbValue.add(value2);
                            } else {
                                continue;
                            }
                        }
                        if (sbK.length() < 1) {
                            //RunTimeLog.addLog(RunTimeLog.BOOT_SHOP_DATA, tableName + "  " + tableValue.toString());
                            continue;
                        }
                        sbK.deleteCharAt(sbK.length() - 1);
                        sbV.deleteCharAt(sbV.length() - 1);
                        sb.append("(").append(sbK).append(") ").append("values (").append(sbV).append(")");
                        if (LogUtil.SHOW) {
                            //LogUtil.log(sb.toString() + tableValue.toJSONString());
                        }
                        db.execSQL(sb.toString(), sbValue.toArray(new String[sbValue.size()]));
                    }
                }
                long time3 = SystemClock.elapsedRealtime();
                //LogUtil.log("FILLDATA", "replace cost " + (time3 - time2));
                //LogUtil.log("FILLDATA", "final cost " + (time3 - start));
                success = true;
                return success;
            }
        });

    }


    /**
     * 获取表名
     *
     * @param db        SQLiteDatabase
     * @param tableName String |
     * @return List<String>
     */
    private static List<String> getColumnList(SQLiteDatabase db, String tableName) {
        String sql = "pragma table_info (?) ";
        List<String> resultList = new ArrayList<>();
        Cursor ct = null;
        try {
            ct = db.rawQuery(sql.replace("?", tableName), null);
            int indexName = ct.getColumnIndex("name");
            while (ct.moveToNext()) {
                resultList.add(ct.getString(indexName).toLowerCase());

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBSimpleUtil.closeCursor(ct);
        }
        return resultList;
    }
}
