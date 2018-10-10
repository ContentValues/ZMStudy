package com.mwee.android.base;

import android.content.Context;

import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.timesync.TimeSyncUtil;

/**
 * GlobalCache
 * Created by virgil on 16/5/20.
 */
@SuppressWarnings("unused")
public class GlobalCache {
    private final static GlobalCache instance = new GlobalCache();
    private Context mContext;

    public static GlobalCache getInstance() {
        return instance;
    }

    public static Context getContext() {
        return getInstance().mContext;
    }

    public void registerContext(Context context) {
        if (context != null) {
            this.mContext = context.getApplicationContext();
        }
    }

    public void setLogOpen(boolean printLog) {
        if (mContext == null) {
            throw new NullPointerException("You shall call GlobalCache.registerContext first");
        }
        LogUtil.init(mContext, printLog);
    }

}
