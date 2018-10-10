package com.mwee.android.tools;

import android.os.SystemClock;
import android.view.View;


/**
 *
 */
public class ButtonClickTimer {
    public static long lastClickTime;
    public static int viewId;
    private static int defaultDelay=500;
    public static boolean canClick(View view) {
        if (viewId != view.getId()) {
            viewId = view.getId();
            lastClickTime = SystemClock.elapsedRealtime();
            return true;
        }
        long currentTimeMillis = SystemClock.elapsedRealtime();
        if (currentTimeMillis - lastClickTime > defaultDelay) {
            lastClickTime = currentTimeMillis;
            return true;
        } else {
            return false;
        }
    }

    public static boolean canClick() {
        return canClick(defaultDelay);
    }

    public static boolean canClick(int timeLimit) {
        long currentTimeMillis = SystemClock.elapsedRealtime();
        //写死
        if (currentTimeMillis - lastClickTime > defaultDelay) {
            lastClickTime = currentTimeMillis;
            return true;
        } else {
            return false;
        }
    }
}
