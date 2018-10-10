package com.mwee.android.base.net;


import com.mwee.android.base.net.component.NetResultType;
import com.mwee.android.base.net.component.Result;
import com.mwee.android.tools.LogUtil;

/**
 * ResponseData,一次业务通讯的最终结果
 */
public class ResponseData implements Cloneable {
    /**
     * 业务通讯结果代码,see{@link Result}
     */
    public int result;
    /**
     * 当{@link #result}为失败的时候,此字段赋值,值范围参考{@link NetResultType}
     */
    public int netResult;
    /**
     * Http返回的code
     */
    public int httpStatus=200;
    /**
     * 通讯耗时,毫秒
     */
    public long netCost = 0;
    /**
     * 业务结果信息,可用于UI交互
     */
    public String resultMessage;
    /**
     * 业务结果信息,开发者debug用,在生产环境禁止用于UI交互
     */
    public String debugMessage;
    /**
     * 服务结果的ResponseBean
     */
    public BaseResponse responseBean;

    /**
     * 本次通讯的唯一标示key,不会重复
     */
    public String key;

    public ResponseData() {

    }

    @Override
    public ResponseData clone() {
        ResponseData cloneObject = null;
        try {
            cloneObject = (ResponseData) super.clone();
            if (responseBean != null) {
                cloneObject.responseBean = responseBean.clone();
            }
        } catch (CloneNotSupportedException e) {
            LogUtil.logError("", e);
        }
        return cloneObject;
    }
}
