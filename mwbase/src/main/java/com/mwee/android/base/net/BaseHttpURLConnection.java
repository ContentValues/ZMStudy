package com.mwee.android.base.net;

import android.os.SystemClock;

import com.mwee.android.base.net.component.NetParam;
import com.mwee.android.base.net.component.NetResultType;
import com.mwee.android.base.net.exception.HttpFailException;
import com.mwee.android.tools.DateUtil;
import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.StringUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

/**
 * BaseHttpURLConnection
 */
public class BaseHttpURLConnection {
    protected String connectStartTime = "";
    protected String connectStopTime = "";
    private String mRequestData;
    private IHttpResultCallBack mHttpResultCallBack;
    private String taskKey;
    private NetParam mParam;
    private String encodeType;
    private int retry = 0;

    public BaseHttpURLConnection(final NetParam param, String requestData, IHttpResultCallBack httpResultCallBack) {
        mParam = param;
        mRequestData = requestData;
        mHttpResultCallBack = httpResultCallBack;
    }

    public static void disconnect(HttpURLConnection urlConnection) {
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
    }

    public void startHttpGetJob() {
        HttpURLConnection urlConnection = null;
        try {
            final String url = mParam.url + mRequestData;
            if (url.startsWith("https")) {
                urlConnection = (HttpsURLConnection) new URL(url).openConnection();
            } else {
                urlConnection = (HttpURLConnection) new URL(url).openConnection();
            }
            urlConnection.setDoInput(true);
            urlConnection.setConnectTimeout(10 * 1000);
            urlConnection.setReadTimeout(mParam.timeOut * 1000);
            urlConnection.setRequestMethod("GET");
            ServiceConnector.buildRequestHead(urlConnection, url);

            if (LogUtil.SHOW) {
                LogUtil.logNET(taskKey, "RequestHead is:" + urlConnection.getRequestProperties());
            }
            int responseCode = urlConnection.getResponseCode();
            InputStream input;
            if (responseCode == 200) {
                input = urlConnection.getInputStream();
                mHttpResultCallBack.onResult(NetResultType.SUCCESS, readInputStream(input), responseCode, null);
                if (input != null) {
                    input.close();
                }
            } else {
                input = urlConnection.getErrorStream();
                byte[] inputBytes = readInputStream(input);

                LogUtil.logError(new HttpFailException(responseCode, mParam.url, mRequestData, StringUtil.bytes2String(inputBytes, encodeType)));

                mHttpResultCallBack.onResult(NetResultType.HTTP_FAIL, inputBytes, responseCode, null);
                if (input != null) {
                    input.close();
                }
            }
        } catch (SocketTimeoutException e) {
            if (LogUtil.SHOW) {
                LogUtil.log("Socket timeout url=" + mParam.url, e);
            }
            mHttpResultCallBack.onResult(NetResultType.TIME_OUT, null, -1, e);
        } catch (MalformedURLException e) {
            LogUtil.logError("Wrong url url=" + mParam.url, e);
            mHttpResultCallBack.onResult(NetResultType.ILLEGAL_URL, null, -1, e);
        } catch (IOException e) {
            if (LogUtil.SHOW) {
                LogUtil.log("IO exception url=" + mParam.url, e);
            }
            mHttpResultCallBack.onResult(NetResultType.HTTP_FAIL, null, -1, e);
        } catch (Exception e) {
            LogUtil.logError("Other error url=" + mParam.url, e);
            mHttpResultCallBack.onResult(NetResultType.OTHER, null, -1, e);

        } finally {
            disconnect(urlConnection);
        }
    }

    public void startHttpPostJob() {
        HttpURLConnection urlConnection = null;
        long start = SystemClock.elapsedRealtime();
        try {
            final String url = mParam.url;
            if (url.startsWith("https")) {
                urlConnection = (HttpsURLConnection) new URL(url).openConnection();
            } else {
                urlConnection = (HttpURLConnection) new URL(url).openConnection();
            }
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(10 * 1000);
            urlConnection.setReadTimeout(mParam.timeOut * 1000);
            urlConnection.setRequestProperty("Accept-Encoding", "gzip");
            ServiceConnector.buildRequestHead(urlConnection, url);

            OutputStream out = null;
            if (mRequestData != null) {
                urlConnection.setRequestProperty("Content-Type", mParam.contentType);

                urlConnection.setRequestProperty("Content-Length", mRequestData.getBytes().length + "");

                if (LogUtil.SHOW) {
                    LogUtil.logNET(taskKey, "RequestHead is:" + urlConnection.getRequestProperties());
                }
                long startBeforeConnect = SystemClock.elapsedRealtime();
                urlConnection.connect();
                long startAfterConnect = SystemClock.elapsedRealtime();
                out = urlConnection.getOutputStream();
                long startAtGetOutPutSteam = SystemClock.elapsedRealtime();

                out.write(mRequestData.getBytes());

                out.flush();
                long writeFinish = SystemClock.elapsedRealtime();
                if (LogUtil.SHOW) {
                    LogUtil.logNET(taskKey, "scoket connect cost=" + (startAfterConnect - startBeforeConnect));
                }
//                out.close();
            }
            connectStartTime = DateUtil.getCurrentDateTime(DateUtil.DATE_YYYYMMDDHHMMSSSSS);
            int responseCode = urlConnection.getResponseCode();
            connectStopTime = DateUtil.getCurrentDateTime(DateUtil.DATE_YYYYMMDDHHMMSSSSS);
            if (LogUtil.SHOW) {
                LogUtil.logNET(taskKey, "get responseCode=" + responseCode);
            }
            if (responseCode == 200) {
                InputStream input;
                if (urlConnection.getContentEncoding() != null && urlConnection.getContentEncoding().contains("gzip")) {
                    input = new GZIPInputStream(urlConnection.getInputStream());
                } else {
                    input = urlConnection.getInputStream();
                }
                mHttpResultCallBack.onResult(NetResultType.SUCCESS, readInputStream(input), responseCode, null);
                if (input != null) {
                    input.close();
                }
            } else {
                InputStream input = urlConnection.getErrorStream();
                byte[] inputBytes = readInputStream(input);
                LogUtil.logError(new HttpFailException(responseCode, mParam.url, mRequestData, StringUtil.bytes2String(inputBytes, encodeType)));

                mHttpResultCallBack.onResult(NetResultType.HTTP_FAIL, inputBytes, responseCode, null);
                if (input != null) {
                    input.close();
                }
            }
            if (out != null) {
                out.close();
            }
        } catch (MalformedURLException e) {
            LogUtil.logError("Wrong url url=" + mParam.url, e);
            mHttpResultCallBack.onResult(NetResultType.ILLEGAL_URL, null, -1, e);
        } catch (SocketTimeoutException e) {
            if (LogUtil.SHOW) {
                LogUtil.log("Socket timeout url=" + mParam.url, e);
            }
            boolean doRetry = false;
            if (retry == 0) {
                if (mParam.reConnect) {
                    doRetry = true;
                    if (LogUtil.SHOW) {
                        LogUtil.logNET(taskKey, "开始重试");
                    }
                    retry++;
                }
            }
            if (doRetry) {
                startHttpPostJob();
            } else {
                mHttpResultCallBack.onResult(NetResultType.TIME_OUT, null, -1, e);
            }
        } catch (IOException e) {
            if (LogUtil.SHOW) {
                LogUtil.log("IO exception  url=" + mParam.url, e);
            }
            mHttpResultCallBack.onResult(NetResultType.HTTP_FAIL, null, -1, e);
        } catch (Exception e) {
            LogUtil.logError("Other error url=" + mParam.url, e);
            mHttpResultCallBack.onResult(NetResultType.OTHER, null, -1, e);
        } finally {
            disconnect(urlConnection);
        }
    }

    public void startSpecialFastHttpPostJob() {
        HttpURLConnection urlConnection = null;
        long start = SystemClock.elapsedRealtime();
        try {
            final String url = mParam.url;
            if (url.startsWith("https")) {
                urlConnection = (HttpsURLConnection) new URL(url).openConnection();
            } else {
                urlConnection = (HttpURLConnection) new URL(url).openConnection();
            }
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(2 * 1000);
            urlConnection.setReadTimeout(mParam.timeOut * 1000);
            urlConnection.setRequestProperty("Accept-Encoding", "gzip");
            ServiceConnector.buildRequestHead(urlConnection, url);

            OutputStream out = null;
            if (mRequestData != null) {
                urlConnection.setRequestProperty("Content-Type", mParam.contentType);

                urlConnection.setRequestProperty("Content-Length", mRequestData.getBytes().length + "");

                if (LogUtil.SHOW) {
                    LogUtil.logNET(taskKey, "RequestHead is:" + urlConnection.getRequestProperties());
                }
                long startBeforeConnect = SystemClock.elapsedRealtime();
                urlConnection.connect();
                long startAfterConnect = SystemClock.elapsedRealtime();
                out = urlConnection.getOutputStream();
                long startAtGetOutPutSteam = SystemClock.elapsedRealtime();

                out.write(mRequestData.getBytes());

                out.flush();
                long writeFinish = SystemClock.elapsedRealtime();
                if (LogUtil.SHOW) {
                    LogUtil.logNET(taskKey, "scoket connect cost=" + (startAfterConnect - startBeforeConnect));
                }
//                out.close();
            }
            connectStartTime = DateUtil.getCurrentDateTime(DateUtil.DATE_YYYYMMDDHHMMSSSSS);
            int responseCode = urlConnection.getResponseCode();
            connectStopTime = DateUtil.getCurrentDateTime(DateUtil.DATE_YYYYMMDDHHMMSSSSS);
            if (LogUtil.SHOW) {
                LogUtil.logNET(taskKey, "get responseCode=" + responseCode);
            }
            if (responseCode == 200) {
                InputStream input;
                if (urlConnection.getContentEncoding() != null && urlConnection.getContentEncoding().contains("gzip")) {
                    input = new GZIPInputStream(urlConnection.getInputStream());
                } else {
                    input = urlConnection.getInputStream();
                }
                mHttpResultCallBack.onResult(NetResultType.SUCCESS, readInputStream(input), responseCode, null);
                if (input != null) {
                    input.close();
                }
            } else {
                InputStream input = urlConnection.getErrorStream();
                byte[] inputBytes = readInputStream(input);
                LogUtil.logError(new HttpFailException(responseCode, mParam.url, mRequestData, StringUtil.bytes2String(inputBytes, encodeType)));

                mHttpResultCallBack.onResult(NetResultType.HTTP_FAIL, inputBytes, responseCode, null);
                if (input != null) {
                    input.close();
                }
            }
            if (out != null) {
                out.close();
            }
        } catch (MalformedURLException e) {
            LogUtil.logError("Wrong url url=" + mParam.url, e);
            mHttpResultCallBack.onResult(NetResultType.ILLEGAL_URL, null, -1, e);
        } catch (SocketTimeoutException e) {
            if (LogUtil.SHOW) {
                LogUtil.log("Socket timeout url=" + mParam.url, e);
            }
            boolean doRetry = false;
            if (retry < 3) {
                if (mParam.reConnect) {
                    doRetry = true;
                    if (LogUtil.SHOW) {
                        LogUtil.logNET(taskKey, "开始重试");
                    }
                    retry++;
                }
            }
            if (doRetry) {
                startSpecialFastHttpPostJob();
            } else {
                mHttpResultCallBack.onResult(NetResultType.TIME_OUT, null, -1, e);
            }
        } catch (IOException e) {
            if (LogUtil.SHOW) {
                LogUtil.log("IO exception  url=" + mParam.url, e);
            }
            mHttpResultCallBack.onResult(NetResultType.HTTP_FAIL, null, -1, e);
        } catch (Exception e) {
            LogUtil.logError("Other error url=" + mParam.url, e);
            mHttpResultCallBack.onResult(NetResultType.OTHER, null, -1, e);
        } finally {
            disconnect(urlConnection);
        }
    }

    public void startHttpDeleteJob() {
        HttpURLConnection urlConnection = null;
        try {
            final String url = mParam.url + mRequestData;
            if (url.startsWith("https")) {
                urlConnection = (HttpsURLConnection) new URL(url).openConnection();
            } else {
                urlConnection = (HttpURLConnection) new URL(url).openConnection();
            }
            urlConnection.setDoInput(true);
//            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setConnectTimeout(10 * 1000);
            urlConnection.setReadTimeout(mParam.timeOut * 1000);
            urlConnection.setRequestMethod("DELETE");
            ServiceConnector.buildRequestHead(urlConnection, url);

            if (LogUtil.SHOW) {
                LogUtil.logNET(taskKey, "RequestHead is:" + urlConnection.getRequestProperties());
            }
            int responseCode = urlConnection.getResponseCode();
            InputStream input;
            if (responseCode == 200) {
                input = urlConnection.getInputStream();
                mHttpResultCallBack.onResult(NetResultType.SUCCESS, readInputStream(input), responseCode, null);
                if (input != null) {
                    input.close();
                }
            } else {
                input = urlConnection.getErrorStream();
                byte[] inputBytes = readInputStream(input);
                LogUtil.logError(new HttpFailException(responseCode, mParam.url, mRequestData, StringUtil.bytes2String(inputBytes, encodeType)));

                mHttpResultCallBack.onResult(NetResultType.HTTP_FAIL, inputBytes, responseCode, null);
                if (input != null) {
                    input.close();
                }
            }
        } catch (MalformedURLException e) {
            LogUtil.logError("Wrong url url=" + mParam.url, e);
            mHttpResultCallBack.onResult(NetResultType.ILLEGAL_URL, null, -1, e);
        } catch (SocketTimeoutException e) {
            if (LogUtil.SHOW) {
                LogUtil.log("Socket timeout url=" + mParam.url, e);
            }
            mHttpResultCallBack.onResult(NetResultType.TIME_OUT, null, -1, e);
        } catch (IOException e) {
            if (LogUtil.SHOW) {
                LogUtil.log("IO exception  url=" + mParam.url, e);
            }
            mHttpResultCallBack.onResult(NetResultType.HTTP_FAIL, null, -1, e);
        } catch (Exception e) {
            LogUtil.logError("Other error url=" + mParam.url, e);
            mHttpResultCallBack.onResult(NetResultType.OTHER, null, -1, e);
        } finally {
            disconnect(urlConnection);
        }
    }

    public void startHttpPUTJob() {
        HttpURLConnection urlConnection = null;

        try {
            final String url = mParam.url;
            if (url.startsWith("https")) {
                urlConnection = (HttpsURLConnection) new URL(url).openConnection();
            } else {
                urlConnection = (HttpURLConnection) new URL(url).openConnection();
            }
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("PUT");
            urlConnection.setConnectTimeout(10 * 1000);
            urlConnection.setReadTimeout(mParam.timeOut * 1000);
            urlConnection.setRequestProperty("Accept-Encoding", "gzip");
            ServiceConnector.buildRequestHead(urlConnection, url);

            OutputStream out = null;

            if (mRequestData != null) {
                urlConnection.setRequestProperty("Content-Type", mParam.contentType);
                urlConnection.setRequestProperty("Content-Length", mRequestData.getBytes().length + "");

                if (LogUtil.SHOW) {
                    LogUtil.logNET(taskKey, "RequestHead is:" + urlConnection.getRequestProperties());
                }
                urlConnection.connect();

                out = urlConnection.getOutputStream();
                out.write(mRequestData.getBytes());
                out.flush();
//                out.close();

            }
            connectStartTime = DateUtil.getCurrentDateTime(DateUtil.DATE_YYYYMMDDHHMMSSSSS);
            int responseCode = urlConnection.getResponseCode();
            connectStopTime = DateUtil.getCurrentDateTime(DateUtil.DATE_YYYYMMDDHHMMSSSSS);
            if (responseCode == 200) {
                InputStream input;
                if (urlConnection.getContentEncoding() != null && urlConnection.getContentEncoding().contains("gzip")) {
                    input = new GZIPInputStream(urlConnection.getInputStream());
                } else {
                    input = urlConnection.getInputStream();
                }
                mHttpResultCallBack.onResult(NetResultType.SUCCESS, readInputStream(input), responseCode, null);
                if (input != null) {
                    input.close();
                }
            } else {
                InputStream input = urlConnection.getErrorStream();
                byte[] inputBytes = readInputStream(input);
                LogUtil.logError(new HttpFailException(responseCode, mParam.url, mRequestData, StringUtil.bytes2String(inputBytes, encodeType)));

                mHttpResultCallBack.onResult(NetResultType.HTTP_FAIL, inputBytes, responseCode, null);
                if (input != null) {
                    input.close();
                }
            }
            if (out != null) {
                out.close();
            }
        } catch (MalformedURLException e) {
            LogUtil.logError("Wrong url url=" + mParam.url, e);
            mHttpResultCallBack.onResult(NetResultType.ILLEGAL_URL, null, -1, e);
        } catch (SocketTimeoutException e) {
            if (LogUtil.SHOW) {
                LogUtil.log("Socket timeout url=" + mParam.url, e);
            }
            mHttpResultCallBack.onResult(NetResultType.TIME_OUT, null, -1, e);
        } catch (IOException e) {
            if (LogUtil.SHOW) {
                LogUtil.log("IO exception  url=" + mParam.url, e);
            }
            mHttpResultCallBack.onResult(NetResultType.HTTP_FAIL, null, -1, e);
        } catch (Exception e) {
            LogUtil.logError("Other error url=" + mParam.url, e);
            mHttpResultCallBack.onResult(NetResultType.OTHER, null, -1, e);
        } finally {
            disconnect(urlConnection);
        }
    }

    /**
     * 将返回从输入流中读取
     *
     * @param input InputStream
     * @return String
     */
    public byte[] readInputStream(InputStream input) {
        if (input == null) {
            return null;
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
        } catch (IOException e) {
            LogUtil.log("IO error", e);
        }
        return output.toByteArray();
    }

    public String getUrl() {
        return mParam.url;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public void setEncodeType(String encodeType) {
        this.encodeType = encodeType;
    }
}
