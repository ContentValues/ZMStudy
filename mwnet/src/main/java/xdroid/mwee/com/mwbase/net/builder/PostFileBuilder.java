package xdroid.mwee.com.mwbase.net.builder;

import java.io.File;

import xdroid.mwee.com.mwbase.net.request.PostFileRequest;
import xdroid.mwee.com.mwbase.net.request.RequestCall;
import okhttp3.MediaType;

public class PostFileBuilder extends OkHttpRequestBuilder<PostFileBuilder>
{
    private File file;
    private MediaType mediaType;


    public OkHttpRequestBuilder file(File file)
    {
        this.file = file;
        return this;
    }

    public OkHttpRequestBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build()
    {
        return new PostFileRequest(url, tag, params, headers, file, mediaType,id).build();
    }


}
