package xdroid.mwee.com.mwcommon.dialog;

import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.mwcommon.base.FragmentController;
import xdroid.mwee.com.mwcommon.base.Host;


/**
 * ProgressManager
 */
public class ProgressManager {

    public static String getDefaultTag(Object ob) {
        if (ob == null) {
            return "null_progress";
        }
        return ob.getClass().getName() + "_progress";
    }

    public static Progress showProgress(Host host) {
        return showProgress(host, getDefaultTag(host), "", true);
    }

    public static Progress showProgressUncancel(Host host) {
        return showProgress(host, getDefaultTag(host), "", false);
    }

    public static Progress showProgressUncancel(Host host, String loadingText) {
        return showProgress(host, getDefaultTag(host), loadingText, false);
    }

    public static Progress showProgressUncancel(Host host, @StringRes int loadingText) {
        return showProgress(host, getDefaultTag(host), loadingText, false);
    }

    public static Progress showProgress(Host host, @StringRes int loadingText) {
        return showProgress(host, getDefaultTag(host), loadingText, true);
    }

    public static Progress showProgress(Host host, String loadingText) {
        return showProgress(host, getDefaultTag(host), loadingText, true);
    }

    public static Progress showProgress(Host host, String loadingText, boolean cancelable) {
        return showProgress(host, getDefaultTag(host), loadingText, cancelable);
    }

    public static Progress showProgress(Host host, @StringRes int loadingText, boolean cancelable) {
        return showProgress(host, getDefaultTag(host), loadingText, cancelable);
    }

    public static Progress showProgress(Host host, String key, @StringRes int loadingText, boolean cancelable) {
        if (host == null) {
            return null;
        }
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setActivity(host.getActivityWithinHost())
                .setTag(key)
                .setLoadingText(loadingText)
                .setCancelable(cancelable);
        if (host instanceof BaseFragment) {
            builder.setFragment((BaseFragment) host);
        }
        return showProgress(builder.build());
    }

    public static Progress showProgress(Host host, String key, String loadingText, boolean cancelable) {
        if (host == null) {
            return null;
        }
        DialogParamBundle.Builder builder = new DialogParamBundle.Builder();
        builder.setActivity(host.getActivityWithinHost())
                .setTag(key)
                .setLoadingText(loadingText)
                .setCancelable(cancelable);
        if (host instanceof BaseFragment) {
            builder.setFragment((BaseFragment) host);
        }
        return showProgress(builder.build());
    }

    public static Progress showProgress(DialogParamBundle bundle) {
        Progress progress = Progress.newInstance(bundle);
        FragmentManager fragmentManager = bundle.mFragmentManager;

        if (fragmentManager != null) {
            if (bundle.mFragmentRef != null && bundle.mFragmentRef.get() != null) {
                progress.setTargetFragment(bundle.mFragmentRef.get(), bundle.DIALOG_REQUEST_CODE);
            }
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(progress, bundle.mTag);
            ft.commitAllowingStateLoss();
//            fragmentManager.executePendingTransactions();
        }
        return progress;
    }

    public static void closeProgress(Host host) {
        if (host == null) {
            return;
        }
        closeProgress(host, getDefaultTag(host));
    }

    public static void closeProgress(Host host, String key) {
        if (host == null) {
            return;
        }
        closeProgress(host.getFragmentManagerWithinHost(), key);
    }

    public static void closeProgress(FragmentManager fragmentManager, String key) {
        if (fragmentManager == null) {
            return;
        }
        FragmentController.removeFragment(fragmentManager, key);
    }


}
