package com.mwee.android.base.net.component;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * HttpTypeInf
 * Created by virgil on 16/5/23.
 */

@Retention(RetentionPolicy.RUNTIME)
@IntDef(value = {HttpType.POST, HttpType.GET, HttpType.PUT, HttpType.DELETE}, flag = false)
public @interface HttpTypeInf {
}
