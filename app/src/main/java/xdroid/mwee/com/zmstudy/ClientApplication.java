package xdroid.mwee.com.zmstudy;

import android.app.Application;
import android.content.Context;


import com.mwee.android.tools.GlobalCache;

import java.util.concurrent.TimeUnit;

import com.mwee.android.tools.base.BaseConfig;
import com.mwee.android.tools.base.Environment;
import com.squareup.leakcanary.LeakCanary;

import okhttp3.logging.HttpLoggingInterceptor;
import xdroid.mwee.com.mwbase.okhttp.OkHttpUtils;
import okhttp3.OkHttpClient;
import xdroid.mwee.com.zmstudy.business.db.DBInit;
import xdroid.mwee.com.zmstudy.utils.InitDebugTools;

/**
 * Created by zhangmin on 2018/4/8.
 */

public class ClientApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(logging)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

        BaseConfig.ENV = Environment.DEV;
        GlobalCache.getInstance().registerContext(context);
        GlobalCache.getInstance().setLogOpen(BaseConfig.ENV != Environment.PRODUCT);

        DBInit.init(context);
        InitDebugTools.initStetho(context);
        if (!LeakCanary.isInAnalyzerProcess(context)) {
            LeakCanary.install((Application) context.getApplicationContext());
        }


        //PrintApplication.init(context);

    }

    public static Context getContext() {
        return context;
    }
}
