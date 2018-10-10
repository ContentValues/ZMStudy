package com.mwee.android.base.task.callback;

/**
 * ICallback
 */
public interface ICallback<S> {
    /**
     * @param info Spublic interface IExecutorCallback extends ICallback<ResponseData,ResponseData>{
     */
    void success(S info);

    /**
     * 通讯过程中的任何失败
     *
     * @param info F
     * @return 是否处理此异常:true:处理;false:不处理
     */
    boolean fail(S info);

}
