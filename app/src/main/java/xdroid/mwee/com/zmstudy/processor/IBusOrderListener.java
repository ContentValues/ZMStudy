package xdroid.mwee.com.zmstudy.processor;

import xdroid.mwee.com.mwcommon.callback.ResultCallback;
import xdroid.mwee.com.zmstudy.model.order.OrderCache;

/**
 * Created by zhangmin on 2018/7/2.
 */

public interface IBusOrderListener {

    /**
     * 开单
     *
     * @param resultCallback
     */
    void loadOpenOrder(ResultCallback<OrderCache> resultCallback);


    /**
     * 下单
     *
     * @param orderCache
     * @param openNewOrder   ture/开新单 false返回原来的订单
     * @param resultCallback
     */
    void loadOrderToCenter(OrderCache orderCache, boolean openNewOrder, ResultCallback<OrderCache> resultCallback);


    /**
     * 取单
     */
    void loadPackOrder(String order_id, ResultCallback<OrderCache> resultCallback);


}
