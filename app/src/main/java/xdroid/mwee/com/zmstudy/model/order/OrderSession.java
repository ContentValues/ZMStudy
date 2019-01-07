package xdroid.mwee.com.zmstudy.model.order;

import android.os.SystemClock;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import com.mwee.android.tools.LogUtil;

import xdroid.mwee.com.zmstudy.business.cache.Cache;

/**
 * 订单缓存，所有订单操作需要加锁，锁通过这个方法获得
 * Created by virgil on 2017/7/11.
 */
public class OrderSession extends Cache {

    private final static Object object = new Object();

    private final static OrderSession instance = new OrderSession();
    private ArrayMap<String, OrderCache> orderMap = new ArrayMap<>();

    /**
     * 订单最后操作时间
     * key - orderID
     * value - timestamp
     */
    private ArrayMap<String, Long> optTimeMap = new ArrayMap<>();


    public static OrderSession getInstance() {
        return instance;
    }


    @Override
    public void refresh() {

    }

    @Override
    public void clean() {

    }

    public OrderCache getOrder(String orderID) {

        OrderCache orderCache = orderMap.get(orderID);
        if (orderCache == null) {
            synchronized (object) {
                orderCache = orderMap.get(orderID);
                if (orderCache == null) {
                    orderCache = OrderSaveDBUtil.get(orderID);
                    if (orderCache != null) {
                        orderCache.reCalcAllByAll();
                    }
                    orderMap.put(orderID, orderCache);
                }
                optTimeMap.put(orderID, SystemClock.elapsedRealtime());
            }
        }
        return orderCache;
    }


    public void writeOrder(String orderID, OrderCache orderCache) {
        if (TextUtils.isEmpty(orderID)) {
            LogUtil.log("存储订单信息出现异常 orderID---->" + orderID + "            orderCache--->" + orderCache);
            return;
        }
        synchronized (object) {
            orderMap.put(orderID, orderCache);
            optTimeMap.put(orderID, SystemClock.elapsedRealtime());

            if (orderCache != null) {
                OrderSaveDBUtil.save(orderID, orderCache);
            }
        }
    }


}
