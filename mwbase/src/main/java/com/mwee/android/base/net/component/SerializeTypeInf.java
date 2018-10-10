package com.mwee.android.base.net.component;

import android.support.annotation.IntDef;

import com.mwee.android.base.net.SerializeType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * SerializeTypeInf
 * Created by virgil on 16/5/23.
 */
@Retention(RetentionPolicy.RUNTIME)
@IntDef(value = {SerializeType.Json, SerializeType.NameValuePairs}, flag = false)
public @interface SerializeTypeInf {

}
