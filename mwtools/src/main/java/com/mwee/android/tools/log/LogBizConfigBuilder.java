package com.mwee.android.tools.log;

/**
 * LogBizConfigBuilder
 * Created by virgil on 16/7/21.
 */
public class LogBizConfigBuilder {
    public static final String URL_LOG_SERVER_PRODUCT = "http://b.mwee.cn/shop/error.php";
    public static final String URL_LOG_SERVER_TEST = "http://st.9now.net/shop/error.php";
    public String FROM_ZIP = null;
    protected String PATH_ZIP = null;
    protected String UPLOAD_URL = null;
    protected String SERVER_SAVED_NAME = null;

    protected String SESSION = null;
    protected String DeviceID = null;
    protected String APP_VERSION = null;
    protected int uploadIntervalMinute = 0;
    protected boolean upload = false;
    protected String shopID;
    protected String appID;
    protected boolean isTest = false;

    public LogBizConfigBuilder(String session, String hardWareSymbol) {
        DeviceID = hardWareSymbol;
        SESSION = session;

    }

    public LogBizConfigBuilder setLogPath(String logPath) {
        FROM_ZIP = logPath;
        return this;
    }

    public LogBizConfigBuilder setZipPath(String zipPath) {
        PATH_ZIP = zipPath;
        return this;
    }

    public LogBizConfigBuilder setUrl(String url) {
        UPLOAD_URL = url;
        return this;
    }

    public LogBizConfigBuilder setServerSavedName(String serverSavedName) {
        SERVER_SAVED_NAME = serverSavedName;
        return this;
    }

    public LogBizConfigBuilder setAppVersion(String appVersion) {
        APP_VERSION = appVersion;
        return this;
    }


    public LogBizConfigBuilder setUploadInterval(int minute) {
        uploadIntervalMinute = minute;
        return this;
    }

    public LogBizConfigBuilder setTest(boolean isTest) {
        this.isTest = isTest;
        return this;
    }

    public void setAutoUpload(boolean upload) {
        this.upload = upload;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }
}
