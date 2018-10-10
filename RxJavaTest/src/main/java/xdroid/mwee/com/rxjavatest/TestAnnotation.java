package xdroid.mwee.com.rxjavatest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhangmin on 2018/7/30.
 */

//可以给一个类型进行注解，比如类、接口、枚举
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {

    int id() default -1;

    String value() default "hello";
}
