package com.mwee.android.base.net;

/**
 * BaseRequest
 */
public abstract class BaseRequest extends BusinessBean implements Cloneable {

    public BaseRequest() {

    }

    @Override
    public BaseRequest clone() throws CloneNotSupportedException {
        return (BaseRequest) super.clone();
    }

    public abstract String optBaseUrl();

    /**
     * 加密对应request的报文
     * @param data String
     * @return String
     */
    public String encrypt(String data){
        return  data;
    }

    /**
     * 解密对应String的报文
     *
     * @param data String
     * @return String
     */
    public String decrypt(String data,int httpStatus) {
        return data;
    }
}

