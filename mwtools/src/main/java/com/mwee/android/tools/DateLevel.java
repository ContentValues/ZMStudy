package com.mwee.android.tools;

import android.support.annotation.IntDef;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Calendar;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Created by virgil on 2017/11/10.
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE})
@IntDef({Calendar.MILLISECOND, Calendar.SECOND, Calendar.MINUTE, Calendar.HOUR, Calendar.DATE, Calendar.MONTH, Calendar.YEAR})
public @interface DateLevel {
}
