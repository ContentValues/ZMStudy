package com.mwee.android.base.task.async;

import com.mwee.android.base.task.ASyncExecute;
import com.mwee.android.base.task.WorkJob;
import com.mwee.android.tools.LogUtil;

/**
 * UnSyncJob
 */
public class UnSyncJob extends WorkJob {
    private ASyncExecute mExecute;

    public UnSyncJob(ASyncExecute execute) {
        this.mExecute = execute;
        initConfig();
    }

    @Override
    public void initConfig() {
        needSync = false;
        mKey = String.valueOf(System.currentTimeMillis());
    }

    @Override
    public void execute() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        if (mExecute != null) {
            try {
                mExecute.execute();
            } catch (Exception e) {
                LogUtil.logError("", e);
            }
        }
    }
}
