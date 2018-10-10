package com.mwee.android.base.net;


/**
 * BaseResponse
 */
public class BaseResponse extends BusinessBean implements Cloneable {
    public String server_time = "";
    public int errno = 0;
    public String errmsg = "";

    public int status = 0;
    public String msg = "";

    public BaseResponse() {

    }

    @Override
    public BaseResponse clone() throws CloneNotSupportedException {
        return (BaseResponse) super.clone();
    }

}
