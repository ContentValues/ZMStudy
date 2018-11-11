package xdroid.mwee.com.zmstudy.net.base;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.mwee.android.sqlite.META;
import com.mwee.android.sqlite.base.ClientMetaUtil;
import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.base.BaseResponse;
import com.mwee.android.tools.encrypt.EncryptUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by zhangmin on 2018/11/9.
 */

public class EncryptInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        request = encrypt(request);//模拟的加密方法

        Response response = chain.proceed(request);

        response = decrypt(response);
        return response;
    }


    /**
     * 加密
     *
     * @param request
     * @return
     */
    private Request encrypt(Request request) throws IOException {
        //获取请求body，只有@Body 参数的requestBody 才不会为 null
        RequestBody requestBody = request.body();
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        Charset charset = Charset.forName("UTF-8");
        MediaType contentType = requestBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(charset);
        }
        String string = buffer.readString(charset);
        String encryptStr = null;
        //todo FormBody 格式的解析没写好 求大神指点迷津
        if (requestBody instanceof FormBody) {
            FormBody formBody = (FormBody) requestBody;
            Map<String, String> formMap = new HashMap<>();
            for (int i = 0; i < formBody.size(); i++) {
                formMap.put(formBody.name(i), formBody.value(i));
            }
            encryptStr = MwEncryptaut(JSON.toJSONString(formMap));
        } else {
            encryptStr = MwEncryptaut(string);
        }
        requestBody = RequestBody.create(contentType, encryptStr);
        request = request.newBuilder()
                .post(requestBody)
                .build();
        return request;
    }


    /**
     * 解密
     *
     * @param response
     * @return
     */
    private Response decrypt(Response response) {

        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            BufferedSource source = body.source();
            Buffer buffer = source.buffer();
            Charset charset = Charset.defaultCharset();
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            String string = buffer.clone().readString(charset);
            String bodyString = null;
            if (string.startsWith("{")) {
                BaseResponse baseResponse = JSON.parseObject(string, BaseResponse.class);
                failBackDecrypt(baseResponse);
                bodyString = JSON.toJSONString(baseResponse);
            } else {
                bodyString = MwDecryptaut(string);

            }
            ResponseBody responseBody = ResponseBody.create(contentType, bodyString);
            response = response.newBuilder().body(responseBody).build();
        } else {
            //todo 暂时没做处理
            LogUtil.log("decrypt" + JSON.toJSONString(response));
        }

        return response;
    }


    private void failBackDecrypt(BaseResponse response) {
        if (response != null) {
            if (!TextUtils.isEmpty(response.errmsg)) {
                if (TextUtils.equals("602", response.errmsg) || response.errno == 602) {
                    response.errno = 602;
                    response.errmsg = "门店已被别的设备绑定，请联系客服";
                } else if (TextUtils.equals("601", response.errmsg) || response.errno == 601) {
                    response.errno = 601;
                    response.errmsg = "服务发生异常，请重试";
                } else if (response.errmsg.contains("70103") || response.errno == 70103) {
                    response.errno = 70103;
                    response.errmsg = "门店已被解绑，请重新绑定";
                } else if (TextUtils.equals("603", response.errmsg) || response.errno == 603) {
                    response.errno = 603;
                    response.errmsg = "系统时间不正确，请修改";
                }
            }
        }
    }


    /**
     * 加密工具类
     *
     * @param data
     * @return
     */
    private String MwEncryptaut(String data) {

        try {
            return EncryptUtil.MwEncryptaut(ClientMetaUtil.getSettingsValueByKey(META.SHOPID)
                    , ClientMetaUtil.getSettingsValueByKey(META.TOKEN)
                    , ClientMetaUtil.getSettingsValueByKey(META.SEED)
                    , data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //异常直接返回data
        return data;
    }


    /**
     * 解密工具类
     *
     * @param data
     * @return
     */
    private String MwDecryptaut(String data) {

        try {
            return EncryptUtil.MwDecryptaut(ClientMetaUtil.getSettingsValueByKey(META.SHOPID)
                    , ClientMetaUtil.getSettingsValueByKey(META.TOKEN)
                    , ClientMetaUtil.getSettingsValueByKey(META.SEED)
                    , data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //异常直接返回data
        return data;
    }


}
