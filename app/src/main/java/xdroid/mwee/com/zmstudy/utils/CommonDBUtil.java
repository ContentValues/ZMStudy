package xdroid.mwee.com.zmstudy.utils;

import android.text.TextUtils;
import com.mwee.android.sqlite.base.APPConfig;
import com.mwee.android.sqlite.base.DBSimpleUtil;
import com.mwee.android.tools.StringUtil;

/**
 * CommonDBUtil
 * Created by virgil on 16/7/25.
 */
public class CommonDBUtil {
    /**
     * 获取配置，不需要传SHopID
     *
     * @param id String
     * @return String
     */
    public static String getConfig(String id) {
        return getConfigWithDefault(id, null);
    }

    public static String getConfigWithDefault(String id, String defaultValue) {
        String sql = "select fsParamValue from tbParamValue where fsParamId='" + id + "'";
        String value = DBSimpleUtil.queryString(APPConfig.DB_MAIN, sql);
        if (TextUtils.isEmpty(value) && !TextUtils.isEmpty(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }

    public static String getFSStr1WithDefault(String id, String defaultValue) {
        String sql = "select fsStr1 from tbParamValue where fsParamId='" + id + "'";
        String value = DBSimpleUtil.queryString(APPConfig.DB_MAIN, sql);
        if (TextUtils.isEmpty(value) && !TextUtils.isEmpty(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }

    @Deprecated
    public static String getConfig(String id, String shopID) {
        String sql = "select fsParamValue from tbParamValue where fsParamId='" + id + "'"
                + " and fsShopGUID='" + shopID + "'";
        return DBSimpleUtil.queryString(APPConfig.DB_MAIN, sql);
    }

    public static void setConfig(final String id, final String value) {
        DBSimpleUtil.excuteSql(APPConfig.DB_MAIN, "update tbParamValue set fsParamValue='" + value + "' where fsParamId='" + id + "'");
    }


    public static int getParamIntByParamId(String paramId) {
        String value = DBSimpleUtil.queryString(APPConfig.DB_MAIN, "select fiInt1 from tbParamValue where fsParamId = '" + paramId + "'");
        return StringUtil.toInt(value, 0);
    }
}
