package xdroid.mwee.com.zmstudy.business.processor;

import android.text.TextUtils;

import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBSimpleUtil;

import com.mwee.android.tools.ToastUtil;
import xdroid.mwee.com.model.util.db.HostDBModel;
import xdroid.mwee.com.model.util.db.UserDBModel;
import com.mwee.android.tools.ListUtil;
import xdroid.mwee.com.mwcommon.callback.ResultCallback;
import xdroid.mwee.com.zmstudy.business.db.client.HostDBUtil;
import xdroid.mwee.com.zmstudy.model.menu.MenuItem;
import xdroid.mwee.com.zmstudy.model.order.OrderCache;
import xdroid.mwee.com.zmstudy.model.order.OrderProcessor;
import xdroid.mwee.com.zmstudy.model.order.OrderSeqStatus;
import xdroid.mwee.com.zmstudy.model.order.OrderSession;
import xdroid.mwee.com.zmstudy.model.order.OrderUtils;

/**
 * Created by zhangmin on 2018/6/15.
 */

public class BusDataProcessor implements IBusOrderListener {


    private final static BusDataProcessor processor = new BusDataProcessor();

    private BusDataProcessor() {

    }

    public static BusDataProcessor getInstance() {
        return processor;
    }


    @Override
    public void loadOpenOrder(ResultCallback<OrderCache> resultCallback) {
        /**
         * 1 查询fastfood_order_biz表中有没有下单并且未被锁定的订单
         * 或者
         * 2 创建一个订单
         */

        String order_id = DBSimpleUtil.queryString(APPConfig.DB_MAIN, "select order_id from order_cache where order_status = '1' ORDER BY create_time DESC limit 1");
        OrderCache orderCache = null;
        if (TextUtils.isEmpty(order_id)) {
            orderCache = OrderUtils.createNewOrderCache();
        } else {
            orderCache = OrderSession.getInstance().getOrder(order_id);
        }
        resultCallback.onSuccess(orderCache);

    }

    @Override
    public void loadOrderToCenter(OrderCache orderCache, boolean openNewOrder, ResultCallback<OrderCache> resultCallback) {

        UserDBModel userDBModel = HostDBUtil.getUserDBModel();
        HostDBModel hostDBModel = HostDBUtil.getHostDBModel();
        if (!ListUtil.isEmpty(orderCache.tempMenuList)) {
            //给未下单的菜赋值下单时间，拆分配料菜
            for (int i = 0; i < orderCache.tempMenuList.size(); i++) {
                MenuItem menuItem = orderCache.tempMenuList.get(i);
                if (!orderCache.isOrderedSeqNo(menuItem.menuBiz.orderSeqID)) {
                    orderCache.updateSeqStatus(menuItem.menuBiz.orderSeqID, OrderSeqStatus.ORDERED, userDBModel, hostDBModel.fsHostId);
                }
            }
            orderCache.originMenuList.addAll(orderCache.tempMenuList);
            orderCache.tempMenuList.clear();
            //更新单序前先打印小票
            orderCache.currentSeq++;
            orderCache.updateSeqStatus(orderCache.currentSeq, OrderSeqStatus.NORMAL, userDBModel, hostDBModel.fsHostId);
            orderCache.reCalcAllByAll();
            OrderSession.getInstance().writeOrder(orderCache.orderID, orderCache);
            //入库
            OrderProcessor.saveOrder(orderCache, null);
        } else {
            ToastUtil.showToast("不能下空单");
        }

        if (openNewOrder) {
            OrderCache orderCacheNew = OrderUtils.createNewOrderCache();
            resultCallback.onSuccess(orderCacheNew);
        } else {
            resultCallback.onSuccess(orderCache);
        }
    }

    @Override
    public void loadPackOrder(String order_id, ResultCallback<OrderCache> resultCallback) {
        OrderCache orderCache = OrderSession.getInstance().getOrder(order_id);
        resultCallback.onSuccess(orderCache);
    }
}
