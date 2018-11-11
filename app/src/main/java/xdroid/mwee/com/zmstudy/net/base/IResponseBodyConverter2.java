package xdroid.mwee.com.zmstudy.net.base;

import com.mwee.android.sqlite.META;
import com.mwee.android.sqlite.base.ClientMetaUtil;
import com.mwee.android.tools.encrypt.EncryptUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by zhangmin on 2018/11/9.
 */

public class IResponseBodyConverter2 implements Converter<ResponseBody, String> {

    @Override
    public String convert(ResponseBody value) throws IOException {
        String string = value.string();
        System.out.println("ResponseBody #解密前@#" + string);
        try {
            string = EncryptUtil.MwDecryptaut(ClientMetaUtil.getSettingsValueByKey(META.SHOPID)
                    , ClientMetaUtil.getSettingsValueByKey(META.TOKEN)
                    , ClientMetaUtil.getSettingsValueByKey(META.SEED)
                    , string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ResponseBody #解密后@#" + string);
        return string;
    }
}