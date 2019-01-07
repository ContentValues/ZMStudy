package xdroid.mwee.com.zmstudy.business.db.client;


import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBSimpleUtil;

import xdroid.mwee.com.model.util.db.HostDBModel;
import xdroid.mwee.com.model.util.db.UserDBModel;

/**
 * 站点相关操作的工具类
 * Created by virgil on 16/9/11.
 */
public class HostDBUtil {

    public static String getShopID() {
        return DBSimpleUtil.queryString(APPConfig.DB_MAIN, "select fsShopGUID from tbShop");
    }

    public static String getHistoryBusineeDate() {
        String lastDate = DBSimpleUtil.queryString(APPConfig.DB_MAIN, "select fsParamValue from tbParamValue where fsParamId = '001'");
        return lastDate;
    }

    /**
     * 获取到当前用户
     * @return String
     */
    public static UserDBModel getUserDBModel() {
        return DBSimpleUtil.query(APPConfig.DB_MAIN, "select * from tbuser where fiStatus = '1' limit 1", UserDBModel.class);
    }


    /**
     * 获取到当前站点
     * @return String
     */
    public static HostDBModel getHostDBModel() {
        return DBSimpleUtil.query(APPConfig.DB_MAIN, "select * from tbHost where fiStatus = '1' and fsHostId = 'Cashier' limit 1", HostDBModel.class);
    }


}
