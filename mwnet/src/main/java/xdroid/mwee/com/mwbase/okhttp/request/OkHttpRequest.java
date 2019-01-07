package xdroid.mwee.com.mwbase.okhttp.request;

import java.util.Map;

import xdroid.mwee.com.mwbase.okhttp.callback.Callback;
import xdroid.mwee.com.mwbase.okhttp.utils.Exceptions;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class OkHttpRequest
{
    public String url;
    public Object tag;
    public Map<String, String> params;
    public Map<String, String> headers;
    public int id;

    protected Request.Builder builder = new Request.Builder();

    protected OkHttpRequest(String url, Object tag,
                            Map<String, String> params, Map<String, String> headers, int id)
    {
        this.url = url;
        this.tag = tag;
        this.params = params;
        this.headers = headers;
        this.id = id ;

        if (url == null)
        {
            Exceptions.illegalArgument("url can not be null.");
        }

        initBuilder();
    }



    /**
     * 初始化一些基本参数 url , tag , headers
     */
    private void initBuilder()
    {
        builder.url(url).tag(tag);
        appendHeaders();
    }

    /**
     * 构建RequestBody
     * @return
     */
    protected abstract RequestBody buildRequestBody();

    /**
     * 包装RequestBody 返回回调
     * @param requestBody
     * @param callback
     * @return
     */
    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback)
    {
        return requestBody;
    }

    /**
     * 构建Request
     * @param requestBody
     * @return
     */
    protected abstract Request buildRequest(RequestBody requestBody);

    public RequestCall build()
    {
        return new RequestCall(this);
    }


    public Request generateRequest(Callback callback)
    {
        RequestBody requestBody = buildRequestBody();
        RequestBody wrappedRequestBody = wrapRequestBody(requestBody, callback);
        Request request = buildRequest(wrappedRequestBody);
        return request;
    }


    protected void appendHeaders()
    {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet())
        {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

    public int getId()
    {
        return id  ;
    }

}
