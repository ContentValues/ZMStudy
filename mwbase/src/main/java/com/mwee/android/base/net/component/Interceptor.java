package com.mwee.android.base.net.component;

import com.mwee.android.base.net.ResponseData;

import org.json.JSONObject;

/**
 * Created by virgil on 2016/11/10.
 */

public interface Interceptor {
    void write(JSONObject ob);

    void receive(ResponseData responseData);
}
