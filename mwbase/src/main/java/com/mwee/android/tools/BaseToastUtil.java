package com.mwee.android.tools;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.mwee.android.base.GlobalCache;

/**
 * BaseToastUtil
 */
public class BaseToastUtil {

    private static Toast toast = null;
    private static String lastContent = "";

    /**
     * Show一个Toast出来.see{@link #showToast(String)}
     *
     * @param stringID int | ResourceID
     */
    public static Toast showToast(@StringRes int stringID) {
        return showToast(GlobalCache.getContext().getResources().getString(stringID));
    }

    /**
     * showToast
     *
     * @param content String | Toast文案内容
     * @return Toast | 如果在子线程调用这个方法,将会返回一个null
     */
    public static Toast showToast(String content) {

        if (Looper.myLooper() != Looper.getMainLooper()) {
            showToastOnMainThread(content);
        } else {
//            if (TextUtils.equals(lastContent, content)) {
//                return toast;
//            }
            try {
                Toast toast;
//            if (toast == null) {
                toast = Toast.makeText(GlobalCache.getContext(), content, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
//            } else {
//                toast.setText(content);
//            }
//            lastContent = content;
                toast.show();
                return toast;
            } catch (Exception e) {
                LogUtil.logError(e);
            }
        }
        return null;
    }

    /**
     * 切换到主线程showToast
     *
     * @param content String
     */
    private static void showToastOnMainThread(final String content) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    showToast(content);
                }
            });
        } else {
            showToast(content);
        }
    }
}
