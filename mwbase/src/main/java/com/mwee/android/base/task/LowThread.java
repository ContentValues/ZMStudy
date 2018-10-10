package com.mwee.android.base.task;

/**
 * LowThread
 */
public class LowThread extends Thread {
    public LowThread() {
        super();
    }

    public LowThread(Runnable runnable) {
        super(runnable);
    }

    @Override
    public void run() {
        setPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        super.run();
    }
}
