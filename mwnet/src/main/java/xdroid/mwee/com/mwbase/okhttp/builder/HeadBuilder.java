package xdroid.mwee.com.mwbase.okhttp.builder;

import xdroid.mwee.com.mwbase.okhttp.OkHttpUtils;
import xdroid.mwee.com.mwbase.okhttp.request.OtherRequest;
import xdroid.mwee.com.mwbase.okhttp.request.RequestCall;

public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
