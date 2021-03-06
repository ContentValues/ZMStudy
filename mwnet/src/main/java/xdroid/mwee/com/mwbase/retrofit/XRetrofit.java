package xdroid.mwee.com.mwbase.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mwee.android.sqlite.base.APPConfig;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xdroid.mwee.com.mwbase.retrofit.interceptor.EncryptInterceptor;

/**
 * Created by wanglei on 2016/12/24.
 */

public class XRetrofit {

    public static final String MWEE_BASE_URL = "http://pcdc.winpos.9now.net/posapi/shop/" +
            APPConfig.SHOP_ID +
            "-103/";

    public static final long connectTimeoutMills = 10 * 1000l;
    public static final long readTimeoutMills = 10 * 1000l;

    private static XRetrofit instance;

    private XRetrofit() {

    }

    public static XRetrofit getInstance() {
        if (instance == null) {
            synchronized (XRetrofit.class) {
                if (instance == null) {
                    instance = new XRetrofit();
                }
            }
        }
        return instance;
    }


    /***
     * 获取service 默认加密
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T create(Class<T> service) {
        return create(service, true);
    }


    /**
     * 获取service 默认加密
     * @param service
     * @param enctry  ture/加密 false/非加密
     * @param <T>
     * @return
     */
    public static <T> T create(Class<T> service, boolean enctry) {

        return create(service, XRetrofit.MWEE_BASE_URL,enctry);
    }


    /**
     * 获取service 默认加密
     * @param service
     * @param baseUrl
     * @param enctry
     * @param <T>
     * @return
     */
    public static <T> T create(Class<T> service, String baseUrl,boolean enctry) {
        return XRetrofit.getInstance().getRetrofit(baseUrl, enctry).create(service);
    }


    /**
     * @param baseUrl
     * @param enctry  是否需要加密
     * @return
     */
    public Retrofit getRetrofit(String baseUrl, boolean enctry) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getClient(baseUrl, enctry))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        Retrofit retrofit = builder.build();
        return retrofit;
    }

    private OkHttpClient getClient(String baseUrl, boolean enctry) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        //if (clientMap.get(baseUrl) != null) return clientMap.get(baseUrl);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (enctry) {
            builder.addInterceptor(new EncryptInterceptor());
        }
        OkHttpClient client = builder.connectTimeout(connectTimeoutMills, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeoutMills, TimeUnit.MILLISECONDS)
                .addInterceptor(logging)
                .build();
        //clientMap.put(baseUrl, client);
        return client;
    }


}
