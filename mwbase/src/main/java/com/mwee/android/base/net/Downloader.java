package com.mwee.android.base.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.mwee.android.base.GlobalCache;
import com.mwee.android.tools.FileUtil;
import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.NetWorkUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Downloader
 * Created by Virgil on 15/11/13.
 */
public class Downloader {

    public static void downloadFile(String urlString, String savePath) {
        if (!NetWorkUtil.isNetworkAvailable(GlobalCache.getContext())) {
            return;
        }
        if (TextUtils.isEmpty(urlString)) {
            return;
        }
        URL url;
        HttpURLConnection urlConn = null;

        FileUtil.makeFileSafe(GlobalCache.getContext(),savePath);

        try {
            url = new URL(urlString);
            if (urlString.startsWith("https")) {
                urlConn = (HttpsURLConnection) url.openConnection();
            } else {
                urlConn = (HttpURLConnection) url.openConnection();
            }
            urlConn.setDoInput(true);
            urlConn.setConnectTimeout(15 * 1000);
            urlConn.setReadTimeout(45 * 1000);
            urlConn.connect();
            // 将得到的数据转化成InputStream
            int responseCode = urlConn.getResponseCode();
            if (responseCode == 200) {
                InputStream is = urlConn.getInputStream();
                if (is != null) {
                    long lengthOfFile = urlConn.getContentLength();

                    FileOutputStream fileOutputStream = new FileOutputStream(new File(savePath));
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = is.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    is.close();
                }
            }
        } catch (SocketTimeoutException e) {
            LogUtil.logError("url=" + urlString, e);
        } catch (MalformedURLException e) {
            LogUtil.logError("url=" + urlString, e);
        } catch (IOException e) {
            LogUtil.logError("url=" + urlString, e);
        } catch (Exception e) {
            LogUtil.logError("url=" + urlString, e);
        } finally {
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
    }

    public static Bitmap downloadBitmap(String urlString, String savePath) {
        if (!NetWorkUtil.isNetworkAvailable(GlobalCache.getContext())) {
            return null;
        }
        if (TextUtils.isEmpty(urlString)) {
            return null;
        }
        URL imgUrl;
        HttpURLConnection urlConn = null;
        Bitmap bitmap = null;

        FileUtil.makeFolderPathSafe(GlobalCache.getContext(),savePath);
        File destFile = new File(savePath);
        try {

            if (destFile.exists()) {
                destFile.delete();
            }
            boolean result = destFile.createNewFile();
            if (!result) {
                return null;
            }
        } catch (Exception e) {
            LogUtil.logError("savePath=" + savePath, e);
        }
        try {
            imgUrl = new URL(urlString);
            if (urlString.startsWith("https")) {
                urlConn = (HttpsURLConnection) imgUrl.openConnection();
            } else {
                urlConn = (HttpURLConnection) imgUrl.openConnection();
            }
            ServiceConnector.buildRequestHead(urlConn, urlString);
            urlConn.setDoInput(true);
            urlConn.setConnectTimeout(15 * 1000);
            urlConn.setReadTimeout(45 * 1000);
            urlConn.connect();
            // 将得到的数据转化成InputStream
            int responseCode = urlConn.getResponseCode();
            if (responseCode == 200) {
                InputStream is = urlConn.getInputStream();
                if (is != null) {
                    // 将InputStream转换成Bitmap
                    bitmap = BitmapFactory.decodeStream(is);


                    FileOutputStream fileOutStream = new FileOutputStream(destFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutStream);
//
//                byte[] tmpBuf = new byte[BUFFER];
//                int tmpLen = 0;
//                while ((tmpLen = is.read(tmpBuf)) > 0) {
//                    fileOutStream.write(tmpBuf, 0, tmpLen);
//                }
                    fileOutStream.flush();
                    fileOutStream.close();

                    is.close();
                }

            }
        } catch (SocketTimeoutException e) {
            // Do Nothing
            LogUtil.log("url=" + urlString, e);
        } catch (MalformedURLException e) {
            LogUtil.logError("url=" + urlString, e);
        } catch (IOException e) {
            LogUtil.logError("url=" + urlString, e);
        } catch (Exception e) {
            LogUtil.logError("url=" + urlString, e);
        } finally {
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
        return bitmap;
    }
}
