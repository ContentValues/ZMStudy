package xdroid.mwee.com.zmstudy.cute.Thread;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhangmin on 2018/9/12.
 */

public class URLDemo {

    public static void testURL(){

        String str = "http://www.socs.uts.edu.au:8080/MosaicDocs-old/url-primer.html#chapter1";
        //String str = "https://www.cnblogs.com/dolphin0520/p/3932921.html";

        try {
            URL urldemo = new URL(str);
            System.out.println("urldemo.getAuthority()-->"+urldemo.getAuthority());
            System.out.println("urldemo.getHost()-->"+urldemo.getHost());
            System.out.println("urldemo.getFile()-->"+urldemo.getFile());
            System.out.println("urldemo.getPath()-->"+urldemo.getPath());
            System.out.println("urldemo.getProtocol()-->"+urldemo.getProtocol());
            System.out.println("urldemo.getRef()-->"+urldemo.getRef());
            System.out.println("urldemo.getUserInfo()-->"+urldemo.getUserInfo());

            System.out.println("urldemo.getDefaultPort()-->"+urldemo.getDefaultPort());
            System.out.println("urldemo.getPort()-->"+urldemo.getPort());

            System.out.println("urldemo.getQuery()-->"+urldemo.getQuery());

            System.out.println("urldemo.getContent()-->"+urldemo.getContent());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
