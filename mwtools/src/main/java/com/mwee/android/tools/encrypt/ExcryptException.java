package com.mwee.android.tools.encrypt;

/**
 * 加密异常
 * Created by zhangmin on 2017/2/8.
 */

public class ExcryptException extends RuntimeException {
    public int errorNo = 601;

    public ExcryptException(int errorNo) {
        this.errorNo = errorNo;
    }
}
