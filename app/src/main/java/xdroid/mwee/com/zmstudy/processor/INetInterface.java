package xdroid.mwee.com.zmstudy.processor;

import xdroid.mwee.com.mwcommon.callback.ResultCallback;
import xdroid.mwee.com.zmstudy.model.GetDataResponse;

/**
 * Created by zhangmin on 2018/6/15.
 */

public interface INetInterface {


    /**
     * 激活流程（包括绑定和下载数据到业务中心）
     */
    void loadActive(String shopid, ResultCallback<GetDataResponse> callback);


}
