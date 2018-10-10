package xdroid.mwee.com.zmstudy.db;
import com.mwee.android.sqlite.base.ClientMetaUtil;
import com.mwee.android.sqlite.META;

/**
 * Created by zhangmin on 2018/6/15.
 */

public class AppConfiguration {

    /**
     * 保存 初始化数据
     *
     * @param shopID 门店ID
     * @param token
     * @param seed
     */
    public static void saveTokenSeed(String shopID, String token, String seed) {
        ClientMetaUtil.updateSettingsValueByKey(META.TOKEN, token);
        ClientMetaUtil.updateSettingsValueByKey(META.SEED, seed);
        ClientMetaUtil.updateSettingsValueByKey(META.SHOPID, shopID);
    }
}
