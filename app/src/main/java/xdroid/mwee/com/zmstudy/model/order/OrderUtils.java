package xdroid.mwee.com.zmstudy.model.order;

import android.text.TextUtils;

import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBSimpleUtil;
import com.mwee.android.tools.DateUtil;

import java.text.DecimalFormat;

import xdroid.mwee.com.model.util.db.CacheModel;
import xdroid.mwee.com.zmstudy.business.db.client.HostDBUtil;
import xdroid.mwee.com.zmstudy.model.IOCache;

/**
 * Created by zhangmin on 2018/6/30.
 */

public class OrderUtils {

    public static OrderCache createNewOrderCache() {
        OrderCache orderCache = new OrderCache();
        orderCache.orderID = DateUtil.getCurrentDate("yyyyMMdd") + generateNewKBNO();
        orderCache.createTime = DateUtil.getCurrentDateTime("HH:mm:ss");
        orderCache.shopID = HostDBUtil.getShopID();
        orderCache.businessDate = HostDBUtil.getHistoryBusineeDate();
        orderCache.orderStatus = OrderStatus.NORMAL;
        orderCache.updateSeqStatus(1, OrderSeqStatus.NORMAL, null, "");
        OrderSession.getInstance().writeOrder(orderCache.orderID, orderCache);
        return orderCache;

    }

    /**
     * 存储自定牌号序列
     */
    private static String generateNewKBNO() {

        CacheModel cacheModel = DBSimpleUtil.query(APPConfig.DB_MAIN, "select  * from datacache where type=" + IOCache.TYPE_AUTO_KB_N0, CacheModel.class);
        String time = DateUtil.getCurrentDateTime(DateUtil.DATE_VISUAL14FORMAT);
        if (cacheModel == null) {
            cacheModel = new CacheModel();
            cacheModel.type = IOCache.TYPE_AUTO_KB_N0;
            cacheModel.value = "0001";
            cacheModel.createtime = time;
        } else {

            if (TextUtils.isEmpty(cacheModel.value) || cacheModel.value.length() != 4) {//todo 容错
                cacheModel.value = "0001";
            } else {
                int num = Integer.parseInt(cacheModel.value.substring(0, 4)) + 1;
                cacheModel.value = new DecimalFormat("0000").format(num);
            }
        }
        cacheModel.updatetime = time;
        cacheModel.replaceNoTrans();
        return cacheModel.value;
    }


}
