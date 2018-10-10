package com.mwee.android.tools.base;


/**
 * BaseConfig
 */
public class BaseConfig {
    public static int ENV = Environment.PRODUCT;


    public static boolean isDEV() {
        return ENV == Environment.DEV;
    }

    public static boolean isProduct() {
        return ENV == Environment.PRODUCT || ENV == Environment.BAOLEI;
    }
}
