package com.mwee.android.base.task.net;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.mwee.android.base.net.BaseRequest;
import com.mwee.android.base.net.ResponseData;
import com.mwee.android.base.net.ServiceConnector;
import com.mwee.android.base.net.component.HttpParam;
import com.mwee.android.base.net.component.NetParam;
import com.mwee.android.base.net.component.NetResultType;
import com.mwee.android.base.net.component.Result;
import com.mwee.android.base.net.exception.ConnectException;
import com.mwee.android.base.task.BaseThreadPool;
import com.mwee.android.base.task.WorkJob;
import com.mwee.android.base.task.callback.BusinessCallback;
import com.mwee.android.tools.DateUtil;
import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.StringUtil;
import com.mwee.android.tools.timesync.TimeCalibrate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * NetJob
 */
public class NetJob extends WorkJob {
    public int timeOut = 0;
    /**
     * 调用者线程的Handler
     */
    protected Handler mHandler;
    /**
     * 如果不使用Handler进行通讯，则可以使用这个queue
     */
    protected ArrayBlockingQueue<ResponseData> mQueue;
    private List<BaseRequest> requestList = new ArrayList<>();
    private List<NetParam> params;
    private List<ResponseData> responseData = new ArrayList<>();
    private BusinessCallback serverThreadCallback;

    public NetJob(BaseRequest... beanList) {
        if (beanList != null && beanList.length > 0) {
            Collections.addAll(requestList, beanList);
        }
        initConfig();
    }

    public NetJob(List<BaseRequest> beanList) {
        if (beanList != null && beanList.size() > 0) {
            this.requestList = beanList;
        }
        initConfig();
    }

    public static String getRequestFullUrl(BaseRequest request) {
        return "";

    }

    public static int getRequestTimeOut(BaseRequest request) {
        return 0;
    }

    /**
     * 构建网络超时的ResponseData
     *
     * @return ResponseData
     */
    public static ResponseData buildTimeOutModel() {
        ResponseData responseData = new ResponseData();
        responseData.result = Result.FAIL;
        responseData.netResult = NetResultType.TIME_OUT;
        responseData.resultMessage = "网络超时";
        responseData.responseBean = null;
        return responseData;
    }

    /**
     * 校准时间
     *
     * @param time String
     */
    public static void adjustTime(String time) {
        try {
            Date date = DateUtil.formatDate(time, DateUtil.DATE_YYYYMMDDHHMMSS);
            if (date != null) {
                TimeCalibrate.initServerTime(date.getTime());
            }
        } catch (Exception e) {
            LogUtil.log("time=" + time, e);
        }
    }

    /**
     * 解析Request中注解相关的参数
     *
     * @param request BaseRequest
     * @return NetParam
     */
    public static NetParam parseParam(BaseRequest request) {
        NetParam param = null;
        if (request != null) {
            HttpParam httpAnnotation = request.getClass().getAnnotation(HttpParam.class);
            if (httpAnnotation != null) {
                param = new NetParam(httpAnnotation);
                param.url = request.optBaseUrl() + param.method;
            }
        }
        return param;
    }

    @Override
    public void initConfig() {
        needSync = true;
        String hashKey = "";
        if (requestList != null && requestList.size() > 0) {
            params = new ArrayList<>(requestList.size());
            for (BaseRequest entity : requestList) {
                NetParam netParam = parseParam(entity);
                if (netParam != null) {
                    hashKey += netParam.method;
                    timeOut += netParam.timeOut;
                    params.add(netParam);
                } else {
                    LogUtil.logError("", new ConnectException("cannot get netParam from this request=" + JSON.toJSONString(entity)));
                }
            }
        }
        mKey = hashKey + "_" + String.valueOf(System.currentTimeMillis());
    }

    @Override
    public void execute() {
        if (requestList != null && requestList.size() > 0) {
            try {
                for (int i = 0; i < requestList.size(); i++) {
                    ResponseData responseData = ServiceConnector.sendService(requestList.get(i), params.get(i), mKey);
                    decorResponse(responseData);
                    //这里判断为null，是由于，假如服务发送的耗时已经超过了ListenerThread，则不应该再接受任何的服务结果
                    this.responseData.add(responseData);
                    if (ServiceConnector.finalInterceptor != null) {
                        ServiceConnector.finalInterceptor.receive(responseData);
                    }
                    if (serverThreadCallback != null) {
                        boolean isContinue;
                        if (responseData.result == Result.SUCCESS) {
                            isContinue = serverThreadCallback.success(i, responseData);
                        } else {
                            isContinue = serverThreadCallback.fail(i, responseData);
                        }
                        if (!isContinue) {
                            break;
                        }
                    }

                }
            } catch (Exception e) {
                LogUtil.logError("", e);
            }
        }
        finish(buildResponseData(responseData));
    }

    /**
     * 构建ResponseData
     *
     * @param responseEntity BusinessEntity｜如果服务成功，则返回业务数据
     * @return ResponseData
     */
    private ResponseData buildResponseData(List<ResponseData> responseEntity) {
        ResponseData lastResponseData;
        if (this.responseData != null && this.responseData.size() > 0) {
            lastResponseData = responseEntity.get(responseEntity.size() - 1);
        } else {
            lastResponseData = new ResponseData();
            lastResponseData.netResult = NetResultType.HTTP_FAIL;
            lastResponseData.resultMessage = getNetResultMsg(lastResponseData.netResult);
        }

        if (!TextUtils.isEmpty(lastResponseData.debugMessage) && !StringUtil.isMsgWeb(lastResponseData.debugMessage)) {
            lastResponseData.resultMessage = "result message ＝ " + lastResponseData.resultMessage + "\n" + "(debug message ＝ " + lastResponseData.debugMessage + ")";
        }

        /**
         * 校准时间
         */
        if (lastResponseData.responseBean != null && !TextUtils.isEmpty(lastResponseData.responseBean.server_time)) {
            adjustTime(lastResponseData.responseBean.server_time);
        }
        return lastResponseData;
    }

    /**
     * 装饰服务结果
     *
     * @param responseData ResponseData
     */
    private void decorResponse(ResponseData responseData) {
        int resultCode;
        String resultMessage;
        if (responseData.netResult == NetResultType.SUCCESS) {
            resultCode = Result.SUCCESS;
            resultMessage = "";
            if (responseData.responseBean != null) {
                //兼容Server接口,存在errno和status两种写法
                int errorNo = responseData.responseBean.errno != 0 ? responseData.responseBean.errno : responseData.responseBean.status;
                String errMsg = !TextUtils.isEmpty(responseData.responseBean.errmsg) ? responseData.responseBean.errmsg : responseData.responseBean.msg;
                if (TextUtils.isEmpty(errMsg)) {
                    errMsg = "网络连接较慢，请稍后重试";
                }
                if (errorNo != 0) {
                    responseData.responseBean.errno = errorNo;
                    responseData.responseBean.status = errorNo;
                    responseData.responseBean.errmsg = errMsg;
                    responseData.responseBean.msg = errMsg;

                    resultCode = Result.BUSINESS_FAIL;
                    resultMessage = errMsg + "(BZ" + errorNo + ")";
                }
            }
        } else {
            resultCode = Result.FAIL;
            if (responseData.responseBean != null) {
                //兼容Server接口,存在errno和status两种写法
                int errorNo = responseData.responseBean.errno != 0 ? responseData.responseBean.errno : responseData.responseBean.status;
                String errMsg = !TextUtils.isEmpty(responseData.responseBean.errmsg) ? responseData.responseBean.errmsg : responseData.responseBean.msg;
                if (TextUtils.isEmpty(errMsg)) {
                    errMsg = "网络连接较慢，请稍后重试";
                }
                if (errorNo != 0) {
                    responseData.responseBean.errno = errorNo;
                    responseData.responseBean.status = errorNo;
                    responseData.responseBean.errmsg = errMsg;
                    responseData.responseBean.msg = errMsg;
                }
            }
            if (responseData.responseBean != null && !TextUtils.isEmpty(responseData.responseBean.errmsg)) {
                resultMessage = responseData.responseBean.errmsg;
            } else {
                resultMessage = getNetResultMsg(responseData.netResult);
            }
        }

        responseData.result = resultCode;
        responseData.resultMessage = resultMessage;
    }

    /**
     * 获取网络失败的时候文案
     *
     * @param netResult int
     * @return String
     */
    private String getNetResultMsg(int netResult) {
        String resultMessage;
        switch (netResult) {
            case NetResultType.HTTP_FAIL:
                resultMessage = "网络连接失败,请检查网络";
                break;
            case NetResultType.TIME_OUT:
                resultMessage = "网络超时";
                break;
            case NetResultType.NETWORK_UNAVAILABLE:
                resultMessage = "无可用网络";
                break;
            case NetResultType.ILLEGAL_URL:
                resultMessage = "系统繁忙，请稍后重试";
                break;
            case NetResultType.WRONG_FORMAT:
                resultMessage = "系统繁忙，请稍后重试";
                break;
            default:
                resultMessage = "系统繁忙，请稍后重试";
                break;
        }
        resultMessage += "(" + netResult + ")";

        return resultMessage;
    }

    @Override
    public void finish(ResponseData responseData) {
//        super.finish(responseData);
        if (needSync) {
            ArrayBlockingQueue<ResponseData> taskqueue = BaseThreadPool.getInstance().getResultQueue(mKey);
            if (taskqueue != null) {
                if (mHandler != null) {
                    Message message = mHandler.obtainMessage();
                    message.obj = responseData;
                    mHandler.sendMessage(message);
                } else if (mQueue != null) {
                    try {
                        mQueue.put(responseData);
                    } catch (InterruptedException e) {
                        LogUtil.logError("", e);
                    }
                }
            }

        }
        BaseThreadPool.getInstance().finishTask(mKey);

    }

    public void setServerThreadCallback(BusinessCallback serverThreadCallback) {
        this.serverThreadCallback = serverThreadCallback;
    }
}
