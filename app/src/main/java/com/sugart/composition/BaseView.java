package com.sugart.composition;

/**
 * Author：created by SugarT
 * Time：2019/9/30 13
 */
public interface BaseView {

    /**
     * 显示加载中
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 数据获取失败
     * @param message
     */
    void onError(String message);

    /**
     * 绑定Android生命周期 防止RxJava内存泄漏
     *
     * @param <T>
     * @return
     */
//    <T> AutoDisposeConverter<T> bindAutoDispose();


}
