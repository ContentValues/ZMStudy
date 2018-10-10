package xdroid.mwee.com.zmstudy.net;

public class UrlKit {

    public static final String API_BASE_URL = "http://gank.io/api/";

    public static final String ACTION_DATA_RESULT = "data/{type}/{number}/{page}";

    public static String getUrl(String action) {
        return new StringBuilder(API_BASE_URL).append(action).toString();
    }


    /**
     * 测试环境-点餐服务URL
     */
    public final static String MWEE_URL_ROOT = "http://pcdc.winpos.9now.net/";

    public static final String MWEE_ACTION = "posapi/shop/{shopid}-103/shopapi/{action}";

    /**
     * 测试环境美味-
     *
     * @param action
     * @return
     */
    public static String getMWUrl(String action) {
        return new StringBuilder(MWEE_URL_ROOT).append(action).toString();
    }

}
