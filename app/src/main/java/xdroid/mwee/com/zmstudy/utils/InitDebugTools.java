package xdroid.mwee.com.zmstudy.utils;

import android.content.Context;
import android.os.StrictMode;

import com.facebook.stetho.Stetho;
import com.mwee.android.tools.LogUtil;


/**
 * Created by virgil on 2016/10/19.
 */

public class InitDebugTools {
    /**
     * Facebook的Stetho
     * @param context Context
     */
    public static void initStetho(Context context){
        /*if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(context);
        }*/
        LogUtil.log("InitDebugTools --->initStetho");
        Stetho.initializeWithDefaults(context);
    }

    /**
     * see https://github.com/markzhai/AndroidPerformanceMonitor
     * @param context Context
     */
    public static void initBlockCanary(Context context){
       /* if (BuildConfig.DEV) {
            LogUtil.log("已开启严格模式");
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().penaltyDialog().build());
                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
//            BlockCanary.install(context, new AppBlockCanaryContext()).start();
        }*/
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().penaltyDialog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }
}
