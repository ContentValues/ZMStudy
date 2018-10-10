package com.mwee.android.base.net.component;

import com.mwee.android.base.net.BaseResponse;

public class NetParam {
    public String url;
    public int httpType;
    public int timeOut;
    public String contentType;
    public int serializeType;
    public Class<? extends BaseResponse> responseClz;
    public String method;
    public String encodeType;
    public boolean saveToLog=false;
    /**
     * 是否可以重连
     */
    public boolean reConnect=false;
    /**
     * 是否要求快速重连
     */
    public boolean specialFast=false;
    public NetParam(HttpParam param) {
        this.method = param.method();
        this.httpType = param.httpType();
        this.timeOut = param.timeOut();
        this.contentType = param.contentType();
        this.serializeType = param.serializeType();
        this.responseClz = param.response();
        this.encodeType=param.encodeType();
        this.saveToLog=param.saveToLog();
        this.reConnect=param.reConnect();
        this.specialFast=param.specialFast();
    }
}
