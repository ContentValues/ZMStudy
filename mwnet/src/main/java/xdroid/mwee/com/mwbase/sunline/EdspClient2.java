package xdroid.mwee.com.mwbase.sunline;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class EdspClient2 {

    private static String TAG = "EdspClient ";

    private static String ApiId, Version, AppId, AppSecret, SignType, TimeStamp;
    private static String PublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyUUQpKc9U6bsuQUR6gdm\n" +
            "C13GGUsPQQy8ngeSyiNg233TGpkRNOEHmW65tWU1wPd471TLvq49SN125wtvrjGR\n" +
            "vOENq56zqFpF2zxHVnBiA+rqEyr5zYcW4q5v/djv+4xQSNmev4PrTVj6EPEk97Ic\n" +
            "gufMdT5qLKPhBKDjJy2j0lu4ILvwKFURox53BZuJtaUNlKxaU6awqwXm5y9zjawH\n" +
            "PWEE4ISlBty1+mJhijHIu0+s2BtCe7AJE4mUSc4KHU9DS1DSQ+Pyd9/xDM5CupwG\n" +
            "4KsRMxV1YTjSdK4EARN3O7mBsynr2vvY4k69A4LDT7pu6THdUYdKGGCkuPHr6fu0\n" +
            "RwIDAQAB";

    private static String PrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDA7fgluq+uYaRU\n" +
            "nndkgdctFXbdSo3hTHu02CHL2AxhmlRVV19kym6xxMjrlwiWy3GfvEpLEK6IKYhi\n" +
            "m0M1RPUSpz/J/2ZidSBE8YCbiz8F8jmNHqGAfgr8W8SpJjux036EXaHLsKnl2Lpq\n" +
            "z8iWO85nBYqHbjQE3oQMoAaEU+7lWo81rKsNYYV7jIlh/0Wwgtszjh94DdwcVwKb\n" +
            "sN/RtEe69plDRuIFfWeAR8kMqHtQd86IJxGiLZIhnXokxBD4SKAYzT15kG6/RzMr\n" +
            "gP9rU/mDr446BfRHiK8FEpSHmrVf0QnbvZl7v4IviAKPl5jp4gfjxcjeKUN79c3m\n" +
            "kQEedEknAgMBAAECggEAIDATtIe/A8L6nbuKg9Q9yCHKoQTtQud1xChDCzVSa35K\n" +
            "D6ryGWjEqxf/xX5/SllK7bGGW5u+4qy7OmhZACwvDxukQtk/hbGw3o/EOy8yJpXR\n" +
            "EEBKBZwBzTSqTOY6E2MLup28frVMwzIzd9qOxrHXr8x23j1+pElTkp+oNq+iLdVx\n" +
            "W9OoaJ0g7BqD1+E2lJjWzJGNcjB2iGusLCQxhVFF68QP/oan6//92foDHpk9sdmU\n" +
            "t7MIyeWTlOWwvFOTXleV3XwlgsL3+F4WqajdEi4Z/r7DvAgUQoTAU5E6fEAd8s54\n" +
            "W+SywhfGPwwM979upDFt8tLDaxy1mEWb7Sx83SEwgQKBgQD6Z5iHy7JkF8nZTwIo\n" +
            "x5lFg/56cV4Q6RgROv+q4pwhvVQFbGAOPZB9dCXlxEohLzGrgfBuYXjECz3fnsG2\n" +
            "BLv+hamwr1FFxdwQ1jFOLeGjR9sSlJoj0y40O58W+Gw8in59bR1kIVakimMI37KC\n" +
            "6eYEXrpWle0ii9fqj1mR22huRwKBgQDFPZhqtRNBk9FMgxEA3YAZVCXRDIrIspvh\n" +
            "Ih+hoSgWU4/aQKrVB6iBHDJwTz5c3gBaJ+MufiJMIgzes8xTIh6Jkxd+BLjASTWW\n" +
            "X/+yGvR2niXPKnWYG7vz02OFfDPmMrs5onAaON6UV6/Euyuw6RWr2tBkhKNRSWLz\n" +
            "ziVb1oBeIQKBgBALKSuyMmjVWVpyBHpmZ3ybu2dO8i71MFSSSiVBxa0nu0VprzHa\n" +
            "YfKXyYHSNMmxg2Ywuh3oYtt0uO8l9dVaskzZdYjbgsKMrc/qWVhf+jAC/5dOLB4V\n" +
            "fsVb+JJ5Mj4ixX31ysdKl4mNUFEWPBLFN7aZ9yhrDLmE6j2eF6gPvb4rAoGAU4K0\n" +
            "2dk5Afb4ExEbTeZ6qj66lDxfNoKJBVVRVznIo0tWCO6AZhoL+j5aDLtAiElmlMV2\n" +
            "puS42MtB77jotfooO8+PjsepAaD9k2Gp/Vr0TpS/JWuwxmpXtfC2nnnr73djVg+0\n" +
            "ryWM9/n4ba9h3FGT32OSAD04eN49i0SpBK1ZRIECgYAlg47nrntmh86rXJiz59J5\n" +
            "GU07RoxkWaQyuPffMA+BvXP2gcVmRv70uNIHMOcsbBiFvVCDccs/RPC+43OiwJif\n" +
            "eArGFmt8UfyzSkvnjWG/Iqg5QTqiQ7EqTFKIrf95MRI3UNXCZ3nzDbcHk2Q5/lQ+\n" +
            "xpEMtLnODlwbZf9K3mh7eQ==";

    static OkHttpClient client;

    private  static OkHttpClient getInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (client == null) {
            synchronized (EdspClient.class) {
                if (client == null) {
                    client = new OkHttpClient()
                            .newBuilder()
                            .connectTimeout(3000, TimeUnit.SECONDS)
                            .readTimeout(3000, TimeUnit.SECONDS)
                            .writeTimeout(3000, TimeUnit.SECONDS)
                            .addInterceptor(logging)
                            .build();
                }
            }
        }
        return client;
    }

    /**
     * send the request to server by POST
     *
     * @param apiId
     * @param url
     * @param params
     * @param callback
     */
    public static void doPost(String apiId, String url,String version, String appId, String appSecret, String signType, Map<String, String> params, final RespCallBack callback) {
        initClient(version,appId,appSecret,signType);
        ApiId = apiId;
        TimeStamp = formatDate();
        JSONObject object = new JSONObject(params);
        String body = "";
        try {
            byte[] bytes = object.toString().getBytes();
//            if (SignType.equals("RSA")) {
//                body = RSAUtil.encrypt(PublicKey, bytes);
//            } else if (SignType.equals("AES")) {
//                byte[] base = AESUtil.encrypt(object.toString(), "12345678".getBytes());
//                body = Base64.encode(base);
//            }
            body = RSAUtil.encrypt(PublicKey, bytes);
        } catch (Exception e) {
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body);

        Request request = new Request
                .Builder()
                .url(url)
                .post(requestBody)
                .addHeader("sign", sign(object.toString()))
                .addHeader("api_id", ApiId)
                .addHeader("version", Version)
                .addHeader("app_id", AppId)
                .addHeader("app_secret", AppSecret)
                .addHeader("sign_type", SignType)
                .addHeader("timestamp", TimeStamp)
                .addHeader("id_token", "")
                .build();
        getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    callback.onResponse(call, decryptResponse(response));
                }catch (Exception e){
                    Log.d(TAG, e.getMessage());
                }
            }
        });
    }


    private static void initClient(String version, String appId, String appSecret, String signType){
        Version = version;
        AppId = appId;
        AppSecret = appSecret;
        SignType = signType;
    }
    /**
     * get timestamp
     *
     * @return
     */
    private static String formatDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        String timeStamp = dateFormat.format(new Date());
        return timeStamp;
    }

    /**
     * sign the body by signtype
     *
     * @param body
     * @return
     */
    private static String sign(String body) {
        String sign = "";
        try {
            String str = "timestamp=" + TimeStamp + "&apiId=" + ApiId + "&app_id=" + AppId + "&version=" + Version + "&body=" + body;
            sign = RSAUtil.sign(PrivateKey, str.getBytes());
        } catch (Exception e) {
            Log.e(TAG, "signException -- > " + e.getMessage());
        }
        return sign;
    }

    /**
     * decrypt response to string
     *
     * @param response
     * @return
     */
    private static String decryptResponse(Response response) {
        try {
            String strResponse = response.body().string();
            String resStr = "";
//            if (SignType.equals("RSA")) {
//                byte[] res = RSAUtil.decrypt(PrivateKey, strResponse);
//                resStr = new String(res);
//            } else if (SignType.equals("AES")) {
//                resStr = AESUtil.decrypt(Base64.decode(strResponse), "12345678".getBytes());
//            }
            byte[] res = RSAUtil.decrypt(PrivateKey, strResponse);
            resStr = new String(res);
            Log.d(TAG, "resStr  " + resStr);
            return resStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
