package com.mwee.android.print.base;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by virgil on 2017/12/25.
 *
 * @author virgil
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@IntDef({PaperType.Thermal, PaperType.Impact, PaperType.Label})
public @interface PaperType {
    /**
     * 热敏纸
     */
    int Thermal = 0;

    /**
     * 针式纸
     */
    int Impact = 1;
    /**
     * 标签纸
     */
    int Label = 2;


}
