package xdroid.mwee.com.zmstudy.net.base;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.mwee.android.sqlite.META;
import com.mwee.android.sqlite.base.ClientMetaUtil;
import com.mwee.android.tools.encrypt.EncryptUtil;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by zhangmin on 2018/11/9.
 */

public class IRequestBodyConverter2<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType
            .parse("application/json; charset=UTF-8");
    static final Charset UTF_8 = Charset.forName("UTF-8");

    final Gson gson;
    final TypeAdapter<T> adapter;

    IRequestBodyConverter2(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
        System.out.println("#IRequestBodyConverter初始化#");
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        String json = value.toString();
        System.out.println("RequestBody #加密前#" + json);
        try {
            json = EncryptUtil.MwEncryptaut(ClientMetaUtil.getSettingsValueByKey(META.SHOPID)
                    , ClientMetaUtil.getSettingsValueByKey(META.TOKEN)
                    , ClientMetaUtil.getSettingsValueByKey(META.SEED)
                    , json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("RequestBody #加密后#" + json);
        return RequestBody.create(MEDIA_TYPE, json);
    }
}
