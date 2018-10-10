package xdroid.mwee.com.mwdriverbus;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhangmin on 2018/7/16.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DriverMethod {

    String uri() default "";

    boolean UIThread() default false;
}
