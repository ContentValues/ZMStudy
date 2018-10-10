package com.mwee.android.base.net;

import java.io.Serializable;

/**
 * 通讯协议Bean的基类
 */
public class BusinessBean implements Cloneable, Serializable {

    @Override
    public BusinessBean clone() throws CloneNotSupportedException {
        return (BusinessBean) super.clone();
    }
}
