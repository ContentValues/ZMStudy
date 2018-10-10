package com.mwee.android.base.net.component;

/**
 * NetResultType
 */
public class NetResultType {
    /**
     * 成功
     */
    public static final int SUCCESS = 0;//255
    /**
     * 超时255
     */
    public static final int TIME_OUT = 0xFF;
    /**
     * URL非法254
     */
    public static final int ILLEGAL_URL = TIME_OUT - 1;
    /**
     * HttpCode非成功253
     */
    public static final int HTTP_FAIL = ILLEGAL_URL - 1;
    /**
     * 网络不可用252
     */
    public static final int NETWORK_UNAVAILABLE = HTTP_FAIL - 1;
    /**
     * 序列化失败251
     */
    public static final int WRONG_FORMAT = NETWORK_UNAVAILABLE - 1;
    /**
     * 其他失败250
     */
    public static final int OTHER = WRONG_FORMAT - 1;

}
