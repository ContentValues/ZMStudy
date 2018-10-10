package com.mwee.android.base.task.callback;


import com.mwee.android.base.net.ResponseData;

/**
 * IExecutorCallback
 */
public interface IExecutorCallback extends ICallback<ResponseData> {
    /**
     * 服务成功的回调,满足如下条件调用这个回调:
     * 1,通讯及反序列化的过程成功;
     * 2,业务的code为0,see{@link com.mwee.android.base.net.BaseResponse}
     *
     * @param responseData ResponseData
     */
    @Override
    void success(ResponseData responseData);

    /**
     * 通讯过程中的任何失败,或者业务code不为0.
     *
     * @param responseData response ResponseData
     * @return 是否处理此异常:true:处理;false:不处理
     */
    @Override
    boolean fail(ResponseData responseData);

}
