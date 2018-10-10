package com.mwee.android.sqlite.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.FIELD})
public @interface ColumnInf {
    String name() default "";

    boolean primaryKey() default false;

    boolean ignoreWrite() default false;
}
