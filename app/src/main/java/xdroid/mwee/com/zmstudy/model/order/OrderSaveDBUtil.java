package xdroid.mwee.com.zmstudy.model.order;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBManager;
import com.mwee.android.sqlite.base.DBSimpleUtil;
import com.mwee.android.sqlite.base.DBToolsUtil;
import com.mwee.android.sqlite.base.IDBOperate;
import com.mwee.android.tools.DateUtil;
import com.mwee.android.tools.LogUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mwee.android.tools.ListUtil;
import xdroid.mwee.com.zmstudy.model.menu.MenuItem;

/**
 * OrderSaveDBUtil
 * Created by virgil on 16/6/17.
 */
public class OrderSaveDBUtil {
    /**
     * 存储订单信息
     *
     * @param orderID String
     * @param cache   OrderCache
     */
    public static void save(final String orderID, final OrderCache cache) {
        save(orderID, cache, false);
    }


    /**
     * 存储订单信息
     *
     * @param orderID            String
     * @param cache              OrderCache
     * @param saveUnOrderedItems 是否存储未下单的菜 TRUE： 保存；FALSE：不保存
     */
    public static void save(final String orderID, final OrderCache cache, final boolean saveUnOrderedItems) {
        LogUtil.log("OrderSave " + orderID);
        IDBOperate op = new IDBOperate() {
            @Override
            public Object doJob(SQLiteDatabase db) {
                List<MenuItem> list = cache.originMenuList;

                ContentValues contentValues = new ContentValues();
                contentValues.put("order_id", orderID);
                if (saveUnOrderedItems) {
                    saveMenu(db, orderID, list);
                } else {
                    saveMenu(db, orderID, filterMenuItem(cache));
                }
                cache.originMenuList = null;
                contentValues.put("value", JSON.toJSONString(cache, SerializerFeature.DisableCircularReferenceDetect));
                contentValues.put("order_status", cache.orderStatus);
                contentValues.put("total_price", cache.totalPrice + "");
                contentValues.put("person_num", cache.personNum);
                contentValues.put("create_time", cache.createTime);
                contentValues.put("tableID", cache.fsmtableid);
                contentValues.put("tableName", cache.fsmtablename);
                contentValues.put("business_date", cache.businessDate);
                cache.originMenuList = list;
                db.replace("order_cache", null, contentValues);
                return null;
            }
        };
        DBManager.getInstance().executeInTransactionWithOutThread(op);
    }

    /**
     * 更新菜品信息--起菜
     *
     * @param menuItemList
     * @param newThread
     */
    public static void updateMenuMakeState(final List<MenuItem> menuItemList, boolean newThread) {
        IDBOperate op = new IDBOperate() {
            @Override
            public Object doJob(SQLiteDatabase db) {
                ContentValues contentValues = new ContentValues();
                if (ListUtil.isEmpty(menuItemList)) {
                    return null;
                }
                for (int i = 0; i < menuItemList.size(); i++) {
                    contentValues.clear();
                    MenuItem temp = menuItemList.get(i);
                    String sql = "update order_menu_cache set fiItemMakeState = '" + temp.menuBiz.fiItemMakeState + "' where uniq = '" + temp.menuBiz.uniq + "'";
                    db.execSQL(sql);
                }
                return null;
            }
        };
        if (newThread) {
            DBManager.getInstance().executeInTransaction(op);
        } else {
            DBManager.getInstance().executeInTransactionWithOutThread(op);
        }
    }

    /**
     * 更新菜品信息--催菜
     *
     * @param menuItemList
     * @param newThread
     */
    public static void updateMenuHurryTimes(final List<MenuItem> menuItemList, boolean newThread) {
        IDBOperate op = new IDBOperate() {
            @Override
            public Object doJob(SQLiteDatabase db) {
                ContentValues contentValues = new ContentValues();
                if (ListUtil.isEmpty(menuItemList)) {
                    return null;
                }
                for (int i = 0; i < menuItemList.size(); i++) {
                    contentValues.clear();
                    MenuItem temp = menuItemList.get(i);
                    String sql = "update order_menu_cache set fiHurryTimes = '" + temp.menuBiz.fiHurryTimes + "' where uniq = '" + temp.menuBiz.uniq + "'";
                    db.execSQL(sql);
                }
                return null;
            }
        };
        if (newThread) {
            DBManager.getInstance().executeInTransaction(op);
        } else {
            DBManager.getInstance().executeInTransactionWithOutThread(op);
        }
    }


    /**
     * 过滤出已下单的菜品
     *
     * @param cache
     * @return
     */
    private static List<MenuItem> filterMenuItem(OrderCache cache) {
        List<MenuItem> orderedItems = new ArrayList<>();
        for (MenuItem menuItem : cache.originMenuList) {
            OrderSeqModel orderSeqModel = cache.optSeqModel(menuItem.menuBiz.orderSeqID);
            if (cache.isOrderedSeqNo(menuItem.menuBiz.orderSeqID)) {
                orderedItems.add(menuItem);
            }
        }
        return orderedItems;
    }


    /**
     * 保存菜品，避免db开两次事物
     *
     * @param db       SQLiteDatabase
     * @param order_id String
     * @param itemList List<MenuItem>
     */
    private static void saveMenu(SQLiteDatabase db, final String order_id, final List<MenuItem> itemList) {
        ContentValues contentValues = new ContentValues();
        db.execSQL("delete from order_menu_cache where order_id = '" + order_id + "'");
        if (ListUtil.isEmpty(itemList)) {
            return;
        }
        for (int i = 0; i < itemList.size(); i++) {
            contentValues.clear();
            MenuItem temp = itemList.get(i);

            contentValues.put("indexnum", i);
            contentValues.put("order_id", order_id);
            contentValues.put("value", JSON.toJSONString(temp, SerializerFeature.DisableCircularReferenceDetect));
            contentValues.put("uniq", temp.menuBiz.uniq);
            contentValues.put("orderseq", temp.menuBiz.orderSeqID);
            contentValues.put("uniq_m", "");
            contentValues.put("fiItemMakeState", temp.menuBiz.fiItemMakeState);
            contentValues.put("fiHurryTimes", temp.menuBiz.fiHurryTimes);
            db.replace("order_menu_cache", null, contentValues);
        }
    }


    /**
     * 获取订单的菜品列表
     *
     * @param order_id String
     * @return List<MenuItem>
     */
    public static List<MenuItem> getOrderMenuList(final String order_id) {
        List<JSONObject> list = DBSimpleUtil.queryJsonList(APPConfig.DB_MAIN, "select * from order_menu_cache where order_id='" + order_id + "' order by orderseq desc,indexnum asc");
        List<MenuItem> itemList = new ArrayList<>();
        if (list != null && !ListUtil.isEmpty(list)) {
            for (JSONObject temp : list) {
                MenuItem menu = buildMenuByJson(temp);
                if (menu != null) {
                    itemList.add(menu);
                }
            }
        }
        return itemList;
    }


    /**
     * 根据表数据来构建菜品
     *
     * @param jsonObject JSONObject
     * @return MenuItem
     */
    public static MenuItem buildMenuByJson(JSONObject jsonObject) {
        try {
            if (jsonObject != null) {
                MenuItem menu = JSON.parseObject(jsonObject.getString("value"), MenuItem.class);
                menu.menuBiz.fiHurryTimes = jsonObject.getInteger("fiHurryTimes");
                menu.menuBiz.fiItemMakeState = jsonObject.getInteger("fiItemMakeState");
                //pro2.5 处理升级以后做法丢失的问题
                if (menu.currentPractice != null && menu.menuBiz.selectMulProcedure.size() == 0) {
                    menu.menuBiz.selectMulProcedure.add(menu.currentPractice);
                }
                return menu;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 还原菜品
     *
     * @param orderCache OrderCache
     */
    private static void buildOrderMenuList(OrderCache orderCache) {
        if (orderCache != null) {
            List<MenuItem> list = getOrderMenuList(orderCache.orderID);
            if (!ListUtil.isEmpty(list) && ListUtil.isEmpty(orderCache.originMenuList)) {
                orderCache.originMenuList = list;
                orderCache.reCalcAllByAll();
            }
        }
    }

    /**
     * 还原订单
     *
     * @param cursor Cursor
     * @return OrderCache
     */
    private static OrderCache buildOrder(Cursor cursor) {
        OrderCache value = null;
        String str = cursor.getString(cursor.getColumnIndex("value"));
        int order_status = cursor.getInt(cursor.getColumnIndex("order_status"));
        int personNum = cursor.getInt(cursor.getColumnIndex("person_num"));
        BigDecimal total_price = DBToolsUtil.buildValueByCursor(cursor, cursor.getColumnIndex("total_price"), BigDecimal.class);
        String create_time = cursor.getString(cursor.getColumnIndex("create_time"));
        String tableID = cursor.getString(cursor.getColumnIndex("tableID"));
        String tableName = cursor.getString(cursor.getColumnIndex("tableName"));
        String business_date = cursor.getString(cursor.getColumnIndex("business_date"));
        if (!TextUtils.isEmpty(str)) {
            value = JSON.parseObject(str, OrderCache.class);
            if (order_status >= 0) {
                value.orderStatus = order_status;
            }
            if (personNum >= 0) {
                value.personNum = personNum;
            }
            if (total_price != null && total_price.compareTo(BigDecimal.ZERO) > 0) {
                value.totalPrice = total_price;
            }
            if (!TextUtils.isEmpty(create_time)) {
                value.createTime = create_time;
            }
            if (!TextUtils.isEmpty(tableID)) {
                value.fsmtableid = tableID;
            }
            if (!TextUtils.isEmpty(tableName)) {
                value.fsmtablename = tableName;
            }
            if (!TextUtils.isEmpty(business_date)) {
                if (!business_date.contains("-")) {
                    business_date = DateUtil.formartDateStrToTarget(business_date, "yyyyMMdd", "yyyy-MM-dd");
                }
                value.businessDate = business_date;
            }
        }
        return value;
    }

    /**
     * 获取订单
     *
     * @param orderID String
     * @return OrderCache
     */
    public static OrderCache get(final String orderID) {
        if (TextUtils.isEmpty(orderID)) {
            return null;
        }
        IDBOperate<OrderCache> op = new IDBOperate<OrderCache>() {
            @Override
            public OrderCache doJob(SQLiteDatabase db) {
                OrderCache value = null;
                Cursor cursor = null;
                try {
                    if (db != null) {

                        cursor = db.query("order_cache", null, "order_id='" + orderID + "'", null, null, null,
                                null);
                        if (cursor != null) {
                            while (cursor.moveToNext()) {
                                value = buildOrder(cursor);
                            }
                        }
                    }
                } catch (Exception e) {
                    LogUtil.logError("", e);
                } finally {
                    DBSimpleUtil.closeCursor(cursor);
                }
                buildOrderMenuList(value);
                return value;
            }
        };
        return DBManager.getInstance().executeQuery(op);
    }

    /**
     * 更新order_cache表里的订单状态
     * 更新tbsell表里的订单状态，同时lver+1
     *
     * @param orderID String | 订单号
     * @param status  int | 订单状态see{@link OrderStatus}
     */
    public static void updateOrderStatus(final String orderID, final int status) {
        DBManager.getInstance(APPConfig.DB_MAIN).executeInTransactionWithOutThread(new IDBOperate<Boolean>() {
            @Override
            public Boolean doJob(SQLiteDatabase db) {
                String sqlSell = "update tbsell set lver=lver+5,fiBillStatus='" + status + "' where fssellno='" + orderID + "' ";
                String sqlOrderCache = "update order_cache set order_status='" + status + "' where order_id='" + orderID + "'";
                db.execSQL(sqlSell);
                db.execSQL(sqlOrderCache);
                return null;
            }
        });
    }


    /**
     * 更新order_cache表里的反结数量
     * 更新tbsell表里的订单状态，同时lver+1
     *
     * @param orderID String | 订单号
     */
    public static void updateAntiCount(final String orderID, final int antiPayCount) {
        DBManager.getInstance(APPConfig.DB_MAIN).executeInTransactionWithOutThread(new IDBOperate<Boolean>() {
            @Override
            public Boolean doJob(SQLiteDatabase db) {
                String sqlOrderCache = "update order_cache set antiPayCount='" + antiPayCount + "' where order_id='" + orderID + "'";
                db.execSQL(sqlOrderCache);
                return null;
            }
        });
    }


    /**
     * 根据订单号查询桌台ID
     *
     * @param orderID String
     * @return String
     */
    public static String getTableIDByOrderID(String orderID) {
        return DBSimpleUtil.queryString(APPConfig.DB_MAIN, "select tableID from order_cache where order_id='" + orderID + "'");
    }

}
