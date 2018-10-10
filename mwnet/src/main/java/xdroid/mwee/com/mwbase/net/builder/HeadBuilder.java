package xdroid.mwee.com.mwbase.net.builder;

import xdroid.mwee.com.mwbase.net.OkHttpUtils;
import xdroid.mwee.com.mwbase.net.request.OtherRequest;
import xdroid.mwee.com.mwbase.net.request.RequestCall;

public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
