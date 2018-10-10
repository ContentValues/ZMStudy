package com.mwee.android.base.net.exception;

/**
 * HttpFailException
 */
public class HttpFailException extends RuntimeException {
    public HttpFailException(int responseCode, String msg) {
        super("responseCode=" + responseCode + ";info=" + msg);
    }

    public HttpFailException(int responseCode, String url, String requestData, String errorStream) {
        super("responseCode=" + responseCode + ";info:" + "url=" + url + ";requestData=" + requestData + ";ErrorStream=" + errorStream);
    }

    public HttpFailException(String msg) {
        super("info=" + msg);
    }
}
