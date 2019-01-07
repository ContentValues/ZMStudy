package xdroid.mwee.com.mwbase.okhttp.builder;

import com.mwee.android.sqlite.META;
import com.mwee.android.sqlite.base.ClientMetaUtil;
import com.mwee.android.tools.encrypt.EncryptUtil;

import xdroid.mwee.com.mwbase.okhttp.request.PostStringRequest;
import xdroid.mwee.com.mwbase.okhttp.request.RequestCall;
import okhttp3.MediaType;

public class PostStringBuilder extends OkHttpRequestBuilder<PostStringBuilder> {
    private String content;
    private MediaType mediaType;


    /**
     * 普通的content
     *
     * @param content
     * @return
     */
    public PostStringBuilder content(String content) {
        this.content = content;
        return this;
    }

    /**
     * 加密的content
     *
     * @param content
     */
    public PostStringBuilder encryptContent(String content) {
        try {
            this.content = EncryptUtil.MwEncryptaut(ClientMetaUtil.getSettingsValueByKey(META.SHOPID)
                    , ClientMetaUtil.getSettingsValueByKey(META.TOKEN)
                    , ClientMetaUtil.getSettingsValueByKey(META.SEED)
                    , content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public PostStringBuilder mediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostStringRequest(url, tag, params, headers, content, mediaType, id).build();
    }


}
