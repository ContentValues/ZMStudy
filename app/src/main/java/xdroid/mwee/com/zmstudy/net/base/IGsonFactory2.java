package xdroid.mwee.com.zmstudy.net.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by zhangmin on 2018/11/9.
 */

public class IGsonFactory2 extends Converter.Factory{

    public static IGsonFactory2 create() {
        return create(new GsonBuilder().setLenient().create());
    }

    public static IGsonFactory2 create(Gson gson) {
        if (gson == null)
            throw new NullPointerException("gson == null");
        return new IGsonFactory2(gson);
    }

    private final Gson gson;

    private IGsonFactory2(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                            Annotation[] annotations, Retrofit retrofit) {
        return new IResponseBodyConverter2(); // 响应
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {
        System.out.println("#发起请求#");
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new IRequestBodyConverter2<>(gson, adapter); // 请求
    }




}
