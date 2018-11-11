package xdroid.mwee.com.mwbase.net.builder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import xdroid.mwee.com.mwbase.net.request.PostFormRequest;
import xdroid.mwee.com.mwbase.net.request.RequestCall;

public class PostFormBuilder extends OkHttpRequestBuilder<PostFormBuilder> implements HasParamsable {
    private List<FileInput> files = new ArrayList<>();

    @Override
    public RequestCall build() {
        PostFormRequest request = new PostFormRequest(url, tag, params, headers, files, id);
        RequestCall requestCall = request.build();
        return requestCall;
    }

    public PostFormBuilder files(String key, Map<String, File> files) {
        for (String filename : files.keySet()) {
            this.files.add(new FileInput(key, filename, files.get(filename)));
        }
        return this;
    }

    public PostFormBuilder addFile(String name, String filename, File file) {
        files.add(new FileInput(name, filename, file));
        return this;
    }

    public static class FileInput {
        public String key;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file) {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

        @Override
        public String toString() {
            return "FileInput{" +
                    "key='" + key + '\'' +
                    ", filename='" + filename + '\'' +
                    ", file=" + file +
                    '}';
        }
    }


    @Override
    public PostFormBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public PostFormBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }


//    private HashMap<String,String> wrapHeader(){
//
//        HashMap<String,String> map = new HashMap<>();
//
//        map.put("Accept","*/*");
//        map.put("Accept-Encoding","gzip");
//        map.put("Content-Type","application/txt");
//        map.put("Connection","close");
//        return map;
//    }


}
