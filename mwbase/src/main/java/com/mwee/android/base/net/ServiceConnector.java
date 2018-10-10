package com.mwee.android.base.net;

import android.os.SystemClock;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.mwee.android.base.GlobalCache;
import com.mwee.android.base.net.component.HttpType;
import com.mwee.android.base.net.component.Interceptor;
import com.mwee.android.base.net.component.NetParam;
import com.mwee.android.base.net.component.NetResultType;
import com.mwee.android.base.net.component.Result;
import com.mwee.android.base.net.exception.ConnectException;
import com.mwee.android.base.net.serialize.IgnoreSerialize;
import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.NetWorkUtil;
import com.mwee.android.tools.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * ServiceConnector
 *
 * @author virgil
 */
public class ServiceConnector {
    protected static Interceptor logInterceptor = null;
    public static Interceptor finalInterceptor = null;

    public static ResponseData sendService(final BaseRequest request, final NetParam param, final String taskKey) {
        final ResponseData responseData = new ResponseData();
        responseData.key = taskKey;
        if (!NetWorkUtil.isNetworkAvailable(GlobalCache.getContext())) {
            responseData.netResult = NetResultType.NETWORK_UNAVAILABLE;
            return responseData;
        }
        try {
            final long timeStart = SystemClock.elapsedRealtime();
            final String requestStr = buildRequestParamStr(request, param);
            IHttpResultCallBack iHttpResultCallBack = new IHttpResultCallBack() {
                @Override
                public void onResult(int resultCode, byte[] infoBytes, int httpStatus, Exception netException) {
                    String result = "";
                    responseData.httpStatus = httpStatus;
                    try {
                        result = request.decrypt(getFormatedString(infoBytes, param.encodeType), httpStatus);
                    } catch (Exception e) {
                        e.printStackTrace();
                        resultCode = NetResultType.WRONG_FORMAT;
                    }
                    if (TextUtils.isEmpty(result)) {
                        if (resultCode == NetResultType.SUCCESS) {
                            resultCode = NetResultType.TIME_OUT;
                        }
                    }
                    long netCost = SystemClock.elapsedRealtime() - timeStart;
                    responseData.netCost = netCost;
                    if (LogUtil.SHOW) {
                        LogUtil.logNET(taskKey, "Net cost" + " = " + netCost + "ms");
                        String httpStatusStr = "";
                        if (httpStatus != -1) {
                            httpStatusStr = ":httpStatus is " + httpStatus;
                        }
                        if (resultCode != NetResultType.SUCCESS) {
                            LogUtil.logNET(taskKey, "Response netResult is :" + resultCode + (TextUtils.isEmpty(httpStatusStr) ? "" : httpStatusStr));
                        }
                        LogUtil.logNET(taskKey, "ResponseStr is :" + result);
                    }
                    if (param.saveToLog || logInterceptor != null) {
                        if (param.saveToLog) {
                            LogUtil.logBusiness("通讯日志", "URL:" + param.url + "\n" + "Request:" + requestStr + "\n" + "Response:" + result + "\n" + "NetResult:" + resultCode + "\n" + "HttpStatus:" + httpStatus + "\n" + "NetCost:" + netCost);
                        }
                        if (logInterceptor != null) {
                            JSONObject ob = new JSONObject();
                            try {
                                ob.put("url", param.url);
                                ob.put("request", requestStr);
                                ob.put("response", result);
                                ob.put("netresult", resultCode);
                                ob.put("httpstatus", httpStatus);
                                ob.put("netcost", netCost);
                                if (netException != null) {
                                    ob.put("netexception", StringUtil.getExceptionInfo(netException));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            logInterceptor.write(ob);
                        }
                    }
                    if (resultCode == NetResultType.SUCCESS) {
                        responseData.netResult = NetResultType.SUCCESS;
                        try {
                            if (param.responseClz != null) {
                                responseData.responseBean = JSON.parseObject(result, param.responseClz);
                            }
                        } catch (Exception e) {
                            responseData.responseBean = attempToGetErrorBusinessBean(result);
                            if (responseData.responseBean == null) {
                                responseData.netResult = NetResultType.WRONG_FORMAT;
                            } else {
                                LogUtil.logError("反序列化失败", e);
                            }
                            if (LogUtil.SHOW) {
                                if (StringUtil.isMsgWeb(result)) {
                                    responseData.debugMessage = param.url + "\n" + result;
                                } else {
                                    if (responseData.responseBean == null) {
                                        LogUtil.logError("反序列化失败", e);
                                    } else {
                                        LogUtil.log("反序列化失败", e);
                                    }
                                }
                            }
                        }
                    } else {
                        responseData.responseBean = null;

                        try {
                            if (param.responseClz != null) {
                                responseData.responseBean = JSON.parseObject(result, param.responseClz);
                            }
                        } catch (Exception e) {
                            responseData.responseBean = attempToGetErrorBusinessBean(result);
                            if (LogUtil.SHOW) {
                                if (StringUtil.isMsgWeb(result)) {
                                    responseData.debugMessage = param.url + "\n" + result;
                                } else {
                                    if (responseData.responseBean == null) {
                                        LogUtil.logError("反序列化失败", e);
                                    } else {
                                        LogUtil.log("反序列化失败", e);
                                    }
                                }
                            }
                        }
                        responseData.netResult = resultCode;
                        if (LogUtil.SHOW) {
                            responseData.debugMessage = result;
                        }
                    }
                }
            };
            String encryptedRequest = request.encrypt(requestStr);
            BaseHttpURLConnection httpURLConnection = new BaseHttpURLConnection(param, encryptedRequest, iHttpResultCallBack);
            httpURLConnection.setTaskKey(taskKey);
            httpURLConnection.setEncodeType(param.encodeType);
            if (LogUtil.SHOW) {
                LogUtil.logNET(taskKey, "URL=" + param.url);
                LogUtil.logNET(taskKey, "RequestStr is :" + requestStr);
                if (!TextUtils.equals(encryptedRequest, requestStr)) {
                    LogUtil.logNET(taskKey, "RequestStr encrypted is :" + encryptedRequest);
                }
            }
            switch (param.httpType) {
                case HttpType.GET:
                    httpURLConnection.startHttpGetJob();
                    break;
                case HttpType.POST:
                    if (param.specialFast) {
                        httpURLConnection.startSpecialFastHttpPostJob();
                    } else {
                        httpURLConnection.startHttpPostJob();
                    }
                    break;
                case HttpType.DELETE:
                    httpURLConnection.startHttpDeleteJob();
                    break;
                case HttpType.PUT:
                    httpURLConnection.startHttpPUTJob();
                    break;
                default:
                    httpURLConnection.startHttpPostJob();
                    break;
            }

        } catch (ConnectException e) {
            LogUtil.logError("", e);
            if (LogUtil.SHOW) {
                return buildNetExceptionResponseData(e);
            }
        }
        return responseData;
    }

    /**
     * 构建通讯的URL
     *
     * @return String
     * @throws ConnectException
     */
    private static String buildURL(String rootUrl, String url) throws ConnectException {
        if (!(TextUtils.isEmpty(url) || TextUtils.isEmpty(rootUrl))) {
            if (!url.startsWith("http")) {
                url = rootUrl + url;
            }
        } else {
            throw new ConnectException("buildURL BusinessEntity is null");
        }
        return url;
    }

    /**
     * 构建RequestParam
     * 1,Post,构建JsonString
     * 2,Get,构建跟在Url后面Query
     *
     * @param request BusinessEntity
     * @return String
     */
    private static String buildRequestParamStr(BaseRequest request, NetParam netParam) throws ConnectException {

        String tempRequestStr = null;
        if (request != null) {
            switch (netParam.httpType) {
                case HttpType.DELETE:
                case HttpType.GET:
                    tempRequestStr = buildHttpGetQueryParam(request);
                    break;
                case HttpType.PUT:
                case HttpType.POST:
                    switch (netParam.serializeType) {
                        case SerializeType.Json:
                            tempRequestStr = JSON.toJSONString(request);
                            break;
                        case SerializeType.NameValuePairs:
                            tempRequestStr = transRequestToNameValuePairs(request);
                            break;
                        default:
                            throw new ConnectException("Don't supportService this SerializeType " + netParam.serializeType);
                    }
                    break;
                default:
                    throw new ConnectException("Don't supportService this HttpType " + netParam.httpType);
            }
        } else {
            throw new ConnectException("buildRequestParamStr BusinessEntity is null");
        }
        return tempRequestStr;
    }

    /**
     * PUT和POST格式的body,反射转换为NameValuePairs的形式
     *
     * @param request BaseRequest
     * @return String
     */
    private static String transRequestToNameValuePairs(BaseRequest request) {
        StringBuilder sb = new StringBuilder("");
        Field[] fields = request.getClass().getFields();
        try {
            for (Field temp : fields) {
                temp.setAccessible(true);
                if (TextUtils.equals(temp.getName(), "$change")) {
                    continue;
                }
                if (TextUtils.equals(temp.getName(), "serialVersionUID")) {
                    continue;
                }
                if (temp.getAnnotation(IgnoreSerialize.class) != null) {
                    continue;
                }
                sb.append(temp.getName()).append("=").append(temp.get(request)).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        } catch (IllegalAccessException e) {
            sb.delete(0, sb.length());
        }
        return sb.toString();
    }

    /**
     * 构建HttpGet的Param
     *
     * @param request BusinessBean
     * @return String
     */
    private static String buildHttpGetQueryParam(BusinessBean request) {
        String resultStr = "";
        if (request != null) {
            Field[] fields = request.getClass().getFields();
            StringBuilder build = new StringBuilder();
            try {
                if (fields.length > 0) {
                    build.append("?");
                    //CALLME 这里需要处理复杂类型的Request
                    for (Field temp : fields) {
                        temp.setAccessible(true);
                        if (TextUtils.equals(temp.getName(), "$change")) {
                            continue;
                        }
                        if (TextUtils.equals(temp.getName(), "serialVersionUID")) {
                            continue;
                        }
                        if (temp.getAnnotation(IgnoreSerialize.class) != null) {
                            continue;
                        }
                        build.append(URLEncoder.encode(temp.getName(), "UTF-8"));
                        build.append("=");
                        build.append(URLEncoder.encode(temp.get(request) + "", "UTF-8"));
                        build.append("&");
                    }
                    build.deleteCharAt(build.length() - 1);
                }

                resultStr = build.toString();
            } catch (UnsupportedEncodingException e) {
                LogUtil.logError("", e);
            } catch (IllegalAccessException e) {
                LogUtil.logError("", e);
            } catch (Exception e) {
                LogUtil.logError("", e);
            }
        }
        return resultStr;
    }

    /**
     * 获取UserAgent
     *
     * @return String
     */
    private static String getUserAgent() {
        try {
//            String userAgent = "os/android device/"
//                    + Build.FINGERPRINT.replace("/","|")
//                    + " version/" + BaseApplication.getInstance().getAppVersionName()
//                    + " network/" + NetWorkUtil.getNetWorkDes(BaseApplication.getInstance())
//                    + " user_id/"
//                    + (BaseApplication.getInstance().getNewUser() == null ? "" : BaseApplication.getInstance().getNewUser().user_id)
//                    + " sessionid/"
//                    + BaseApplication.getInstance().getUUID();
//            return userAgent;
        } catch (Exception e) {
            LogUtil.logError("", e);
        }
        return null;
    }

    /**
     * 构建Auth
     *
     * @return String
     */
    public static String getAuthInfo() {
        String auth = "";

//        String userName = BaseApplication.getInstance().getUsername();
//        String passWord = BaseApplication.getInstance().getPassword();
//        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord)) {
//            auth = userName + ":" + passWord;
//        } else {
//            auth = ":";
//        }
        return auth;
    }

    /**
     * 出现NetException的时候,构建抛出的ResponseData
     *
     * @param e ConnectException
     * @return ResponseData
     */
    private static ResponseData buildNetExceptionResponseData(ConnectException e) {
        ResponseData responseData = new ResponseData();
        responseData.result = Result.FAIL;
        responseData.netResult = NetResultType.WRONG_FORMAT;
        responseData.debugMessage = e.getMessage();
        responseData.responseBean = null;
        return responseData;
    }

    public static void buildRequestHead(HttpURLConnection urlConnection, String url) {
        urlConnection.setRequestProperty("Accept", "*/*");
//        urlConnection.setRequestProperty("User-Agent", getUserAgent());
//        urlConnection.setRequestProperty("Charsert", "UTF-8");
        urlConnection.setRequestProperty("Connection", "close");

//        urlConnection.setRequestProperty("App-Version", BaseApplication.getInstance().getAppVersionName());
//        urlConnection.setRequestProperty("build", BaseApplication.getInstance().getAppInnerVersionName());
//
//        String auth = getAuthInfo();
//        String header = "";
//        if (!TextUtils.isEmpty(auth)) {
//            header = Base64.encodeToString(auth.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
//            urlConnection.setRequestProperty("Authorization", "Basic " + header);
//        }
//        buildXAUTH(urlConnection, url, header);
//        String visitorID = BaseApplication.getInstance().getHardwareSymbol();
//        if (!TextUtils.isEmpty(visitorID)) {
//            urlConnection.setRequestProperty("Visitor-Id", visitorID);
//        }

    }

    public static void buildXAUTH(URLConnection connection, String path, String auth) {
//        if (connection != null) {
//            String site = BaseConfig.getHOSTS();
//            path = path.replace(site, "");
//            String t = SystemClock.elapsedRealtime() + "";
//            String m = NetConnection.getToken(path, auth, t);
//            connection.setRequestProperty("X-Auth-T", t);
//            connection.setRequestProperty("X-Auth-M", m);
//
//        }
    }

    /**
     * 发序列化失败,尝试判断是否是API不规范造成的;
     * 1,首先直接反序列化为ResponseBusinessBean,仅解析code和msg字段
     * 2,如果失败,说明格式不对,再尝试解决"{}{}"这种形式的异常API
     *
     * @param originInfo String
     * @return BaseResponse
     */
    private static BaseResponse attempToGetErrorBusinessBean(String originInfo) {
        BaseResponse temp = null;
        try {
            temp = JSON.parseObject(originInfo, BaseResponse.class);
        } catch (Exception e) {
            LogUtil.log("originInfo=" + originInfo, e);
        }
        if (temp == null) {
            try {
                if (originInfo.contains("errno") && originInfo.contains("errmsg")) {
                    String[] infos = originInfo.split("\\}");
                    if (infos.length > 0) {
                        infos[0] = infos[0] + "}";
                        temp = JSON.parseObject(infos[0], BaseResponse.class);
                    }
                }
            } catch (Exception e) {
                LogUtil.log("originInfo=" + originInfo, e);
            }
        }
        return temp;
    }

    public static String getFormatedString(byte[] infoBytes, String encodeType) {
        String result = StringUtil.bytes2String(infoBytes, encodeType);
//        if (!TextUtils.isEmpty(result)) {
//            result = StringUtil.fromEncodedUnicode(result.toCharArray(), 0, result.length());
//        }
        if (result == null) {
            result = "";
        }
        return result;
    }
}
