package xdroid.mwee.com.zmstudy.net;

import com.alibaba.fastjson.JSON;
import com.mwee.android.tools.LogUtil;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import xdroid.mwee.com.mwbase.retrofit.ApiSubcriber;
import xdroid.mwee.com.mwbase.retrofit.XRetrofit;
import xdroid.mwee.com.mwcommon.callback.ResultCallback;
import xdroid.mwee.com.zmstudy.model.bean.KBTempDataResponse;
import xdroid.mwee.com.mwbase.NetError;
import xdroid.mwee.com.mwbase.RXUtils;
import xdroid.mwee.com.zmstudy.net.service.HttpService;

/**
 * Created by zhangmin on 2018/11/11.
 */

public class XApiService {


    public static void queryKBOrderList(String pageNo, String pageSize, String queryType, ResultCallback<KBTempDataResponse> resultCallback) {

        HashMap<String, String> params = new HashMap();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("queryType", queryType);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(params));
        //XApi.getInstance().getRetrofit(HttpService.MWEE_BASE_URL, true).create(HttpService.class)
        XRetrofit.create(HttpService.class)
                .queryKBOrderList(requestBody)
                //.queryKBOrderList(pageNo,pageSize,queryType)
                .compose(RXUtils.getApiTransformer())
                .compose(RXUtils.getScheduler())
                .subscribe(new ApiSubcriber<KBTempDataResponse>() {
                    @Override
                    protected void onFail(NetError error) {
                        LogUtil.log("onFail-->" + error.getMessage());
                        resultCallback.onFailure(error.errorNo, error.getMessage());
                    }

                    @Override
                    public void onNext(KBTempDataResponse response) {
                        LogUtil.log("onNext-->" + JSON.toJSONString(response));
                        resultCallback.onSuccess(response);
                    }
                });
    }


}
