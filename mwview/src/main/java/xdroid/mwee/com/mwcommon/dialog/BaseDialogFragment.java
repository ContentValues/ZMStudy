package xdroid.mwee.com.mwcommon.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.base.BaseConfig;

import java.lang.reflect.Field;

import xdroid.mwee.com.mwcommon.R;
import xdroid.mwee.com.mwcommon.base.BaseActivity;
import xdroid.mwee.com.mwcommon.base.FragmentController;
import xdroid.mwee.com.mwcommon.base.Host;

/**
 * BaseDialogFragment
 */
public class BaseDialogFragment extends DialogFragment implements Host {

    public static final String ARGUMENTS_KEY = "BaseDialogFragment";
    public boolean bIsBackable = true;// 是否back取消
    public DialogParamBundle mParamBundle;
    protected String mDialogTag;// 标记
    protected String mContentTxt;// 内容
    protected boolean isCloseKeyboard;
    private long timestamp;
    protected View.OnClickListener mSpaceClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (mParamBundle != null && mParamBundle.isCancelable) {
                if (mParamBundle.cancelClick != null) {
                    mParamBundle.cancelClick.response();
                }
                if (isCloseKeyboard) {
                    hideKeyboard();
                } else {
                    dismiss();
                }
            }
        }
    };

    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != imm) {
            boolean isOpen = imm.isActive();//isOpen若返回true，则表示输入法打开
            if (isOpen) {
                if (getDialog().getCurrentFocus() != null) {//强制关闭软键盘
                    imm.hideSoftInputFromWindow(getDialog().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
    }

    public BaseDialogFragment() {

    }

    public static BaseDialogFragment getInstance(Bundle bundle) {
        BaseDialogFragment baseDialogFragment = new BaseDialogFragment();
        baseDialogFragment.setArguments(bundle);
        return baseDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onCreate " + (SystemClock.elapsedRealtime() - timestamp));
        }
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogTheme);
        if (mParamBundle != null) {
            mDialogTag = mParamBundle.mTag;
            bIsBackable = mParamBundle.isCancelable;
            mContentTxt = mParamBundle.mLoadingText;
        }
        setCancelable(bIsBackable);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onCreateView " + (SystemClock.elapsedRealtime() - timestamp));
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        if (BaseConfig.isDEV()) {
            timestamp = SystemClock.elapsedRealtime();
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onAttach " + timestamp);
        }
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onViewCreated " + (SystemClock.elapsedRealtime() - timestamp));
        }
        super.onViewCreated(view, savedInstanceState);
        if (bIsBackable) {
            view.setOnClickListener(mSpaceClickListener);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onHiddenChanged " + hidden);
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onViewStateRestored");
        }
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onActivityCreated");
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onConfigurationChanged");
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onInflate");
        }
        super.onInflate(context, attrs, savedInstanceState);
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onAttachFragment childFragment " + childFragment);
        }
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onStart() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onStart");
        }
        super.onStart();

    }

    @Override
    public void onResume() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onResume " + (SystemClock.elapsedRealtime() - timestamp));
        }
        super.onResume();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onSaveInstanceState");
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onPause");
        }
        super.onPause();

    }

    @Override
    public void onStop() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onStop");
        }
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onDestroyView");
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onDestroy");
        }
        super.onDestroy();
    }

    @Override
    public void setCancelable(boolean cancelable) {
        super.setCancelable(cancelable);
        bIsBackable = cancelable;
    }

    @Override
    public void dismiss() {
        if (getFragmentManager() != null) {
            try {
                super.dismissAllowingStateLoss();
            } catch (Exception e) {
                LogUtil.logError(e);
            }
        } else {
            dismissSelf();
        }
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        return show(transaction, tag, true);
    }

    public int show(FragmentTransaction transaction, String tag, boolean allowStateLoss) {
        transaction.add(this, tag);
        int mBackStackId = allowStateLoss ? transaction.commitAllowingStateLoss() : transaction.commit();
        return mBackStackId;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        // TODO Auto-generated method stub
        super.onCancel(dialog);
        if (mParamBundle != null && mParamBundle.isCancelable && mParamBundle.cancelClick != null) {
            mParamBundle.cancelClick.response();
        }
    }

    @Override
    public void onDetach() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onDetach");
        }
        if (mParamBundle != null && mParamBundle.dismissListener != null) {
            mParamBundle.dismissListener.response();
        }
        super.onDetach();
    }

    @Deprecated
    public void dismissSelf() {
        FragmentController.removeFragment(getFragmentManager(), this);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (manager == null) {
            return;
        }
        if (manager.isDestroyed()) {
            return;
        }
        try {
            Field mDismissed = DialogFragment.class.getDeclaredField("mDismissed");
            Field mShownByMe = DialogFragment.class.getDeclaredField("mShownByMe");
            mDismissed.setAccessible(true);
            mShownByMe.setAccessible(true);
            mDismissed.set(this, false);
            mShownByMe.set(this, true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentTransaction ft = manager.beginTransaction();
        ft.remove(this).add(this, tag);
        ft.commitAllowingStateLoss();
    }

    @Override
    public Context getContextWithinHost() {
        return getActivity();
    }

    @Override
    public BaseActivity getActivityWithinHost() {
        return (BaseActivity) getActivity();
    }

    @Override
    public FragmentManager getFragmentManagerWithinHost() {
        return getFragmentManager();
    }

    @Override
    public Resources getResourcesWithinHost() {
        return getResources();
    }

    @Override
    public String getStringWithinHost(@StringRes int resId) {
        return getString(resId);
    }


}