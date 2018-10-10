package com.mwee.android.base.net.exception;

/**
 * ConnectException,网络通讯时各种自定义的问题
 */
public class ConnectException extends RuntimeException {
    public ConnectException(String msg) {
        super(msg);
    }
}
