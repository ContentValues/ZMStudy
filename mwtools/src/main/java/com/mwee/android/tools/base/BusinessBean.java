package com.mwee.android.tools.base;

import java.io.Serializable;

/**
 * Created by zhangmin on 2018/4/11.
 */

public class BusinessBean implements Cloneable, Serializable {

    public BusinessBean() {
    }

    public BusinessBean clone() throws CloneNotSupportedException {
        return (BusinessBean)super.clone();
    }
}
