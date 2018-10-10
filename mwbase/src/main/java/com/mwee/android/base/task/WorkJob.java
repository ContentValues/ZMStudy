package com.mwee.android.base.task;

import com.mwee.android.base.net.component.NetResultType;
import com.mwee.android.base.net.ResponseData;
import com.mwee.android.base.net.component.Result;
import com.mwee.android.tools.LogUtil;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * NetWorkTask
 */
public abstract class WorkJob implements Runnable {
    public String mKey;
    public boolean needSync = true;

    public WorkJob() {
        initConfig();
    }

    @Override
    public void run() {
        setName();
        try {
            execute();
        } catch (Exception e) {
            LogUtil.logError("", e);
            ResponseData responseData = new ResponseData();
            responseData.result = Result.FAIL;
            responseData.netResult = NetResultType.OTHER;
            responseData.resultMessage = "系统繁忙，请稍后重试";
            responseData.responseBean = null;
            finish(responseData);
        }
    }

    private void setName() {
        String originName = Thread.currentThread().getName();
        String subFix = this.getClass().getSimpleName();
        StringBuilder name = new StringBuilder("");
        if (originName.contains("|-")) {
            name.append(originName.substring(0, originName.indexOf("|-")));
        } else {
            name.append(originName);
        }
        name.append("|-").append(subFix);
        Thread.currentThread().setName(name.toString());
    }

    public abstract void initConfig();

    public abstract void execute();

    public void finish(ResponseData responseData) {
        if(!needSync){
            return;
        }
        ArrayBlockingQueue<ResponseData> taskqueue = BaseThreadPool.getInstance().getResultQueue(mKey);
        if (taskqueue != null) {
            try {
                taskqueue.put(responseData);
            } catch (InterruptedException e) {
                LogUtil.logError("", e);
            }
        }
    }

}
