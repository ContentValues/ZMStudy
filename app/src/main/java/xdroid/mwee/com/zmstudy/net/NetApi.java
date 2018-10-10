package xdroid.mwee.com.zmstudy.net;

import com.mwee.android.sqlite.META;
import com.mwee.android.sqlite.base.ClientMetaUtil;

import java.util.HashMap;

import xdroid.mwee.com.mwbase.net.OkHttpUtils;
import xdroid.mwee.com.mwbase.net.callback.Callback;
import xdroid.mwee.com.zmstudy.model.BindResponse;
import xdroid.mwee.com.zmstudy.model.GankResults;
import xdroid.mwee.com.zmstudy.model.GetDataResponse;
import xdroid.mwee.com.zmstudy.model.KBAuthAddressReponse;


public class NetApi {

    public static void getGankData(String type, int pageSize, int pageNum, JsonCallback<GankResults> callback) {
        String url = UrlKit.getUrl(UrlKit.ACTION_DATA_RESULT);
        url = url.replace("{type}", type)
                .replace("{number}", "" + pageSize)
                .replace("{page}", "" + pageNum);

        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(callback);
    }


    /**
     * 绑定本店
     *
     * @param shopId
     * @param callback
     */
    public static void bindShop(String shopId, JsonCallback<BindResponse> callback) {
        String url = UrlKit.getMWUrl(UrlKit.MWEE_ACTION)
                .replace("{shopid}", shopId)
                .replace("{action}", "bindingWithShopType");
        HashMap<String, String> params = new HashMap();
        params.put("deviceId", "TM18184V00014");
        params.put("shopType", "1");

        OkHttpUtils.post()
                .url(url)
                .params(params)
                .build()
                .execute(callback);
    }

    /**
     * 初次 下载数据
     *
     * @param callback
     */
    public static void downLoad(EncryptJsonCallBack<GetDataResponse> callback) {
        String url = UrlKit.getMWUrl(UrlKit.MWEE_ACTION)
                .replace("{shopid}", ClientMetaUtil.getSettingsValueByKey(META.SHOPID))
                .replace("{action}", "download");
        String content = "-1";

        OkHttpUtils.postString()
                .url(url)
                .encryptContent(content)
                .build()
                .execute(callback);
    }


}
