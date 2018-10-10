package com.mwee.android.base.net.component;

import com.mwee.android.base.net.BaseResponse;
import com.mwee.android.base.net.SerializeType;
import com.mwee.android.tools.StringUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@SuppressWarnings("unused")
public @interface HttpParam {

    /**
     * Http超时时间
     *
     * @return int | see {@link HttpType}
     */
    @HttpTypeInf int httpType() default HttpType.GET;

    String method() default "";

    /**
     * Response的Class
     *
     * @return Class
     */
    Class<? extends BaseResponse> response() default BaseResponse.class;

    /**
     * Http的ContentType,默认"application/x-www-form-urlencoded"
     *
     * @return String
     */
    String contentType() default "application/x-www-form-urlencoded";

    /**
     * 序列化类型,默认see{@link SerializeType#NameValuePairs}
     *
     * @return int | see{@link SerializeType}
     */
    @SerializeTypeInf int serializeType() default SerializeType.NameValuePairs;

    /**
     * 超时时间,默认60秒
     *
     * @return int
     */
    int timeOut() default 60;

    /**
     * 是否可以重连,默认不重连
     *
     * @return boolean | true:可以重连;false:不能重连
     */
    boolean reConnect() default false;

    /**
     * 编码类型
     * @return String
     */
    String encodeType() default StringUtil.CHARSET_GB2312;

    /**
     * 是否保存通讯日志
     * @return boolean |true：保存；false：不保存
     */
    boolean saveToLog() default false;

    /**
     * 是否要求快速重连
     * @return @return boolean |true：2.5秒超时重连；false：正常10秒超时
     */
    boolean specialFast() default false;
}
