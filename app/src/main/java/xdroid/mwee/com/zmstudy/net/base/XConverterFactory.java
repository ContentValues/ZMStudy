package xdroid.mwee.com.zmstudy.net.base;
import java.io.IOException;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by zhangmin on 2018/11/9.
 */

public class XConverterFactory<T>  implements Converter<T, RequestBody> {


    @Override
    public RequestBody convert(T value) throws IOException {
        return null;
    }
}
