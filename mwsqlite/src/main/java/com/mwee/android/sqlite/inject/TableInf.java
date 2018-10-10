package com.mwee.android.sqlite.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.TYPE})
public @interface TableInf {
    /**
     * 表名
     *
     * @return name
     */
    String name() default "";

    /**
     * db文件的名称
     *
     * @return String
     */
    String dbname() default "";
}
