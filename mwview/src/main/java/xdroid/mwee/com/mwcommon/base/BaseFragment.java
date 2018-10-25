package xdroid.mwee.com.mwcommon.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.base.BaseConfig;

/**
 */
public abstract class BaseFragment extends Fragment implements Host {
    public final static int TARGET_REQUEST = 0X323;
    public String TAG = this.getClass().getName();
    public BaseActivity mActivity;
    protected View rootView;

    private long timestamp;

    @Override
    public void onAttach(Context context) {
        if (BaseConfig.isDEV()) {
            timestamp = SystemClock.elapsedRealtime();
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onAttach " + timestamp);
        }
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        } else {
            LogUtil.logError(context.toString() + " cannot cast to BaseActivity");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onCreate " + (SystemClock.elapsedRealtime() - timestamp));
        }
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onCreateView " + (SystemClock.elapsedRealtime() - timestamp));
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onViewCreated " + (SystemClock.elapsedRealtime() - timestamp));
        }
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
        rootView.setClickable(true);

    }

    public String getName() {
        return BaseFragment.class.getName();
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onViewStateRestored");
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
    public void onDetach() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onDetach");
        }
        if (mActivity != null) {
            mActivity = null;
        }
        super.onDetach();
    }



    @Override
    public FragmentManager getFragmentManagerWithinHost() {
        return getFragmentManager();
    }



    @Override
    public String getStringWithinHost(@StringRes int resId) {
        return getString(resId);
    }

    @Override
    public Resources getResourcesWithinHost() {
        return getResources();
    }

    @Override
    public BaseActivity getActivityWithinHost() {
        if (mActivity != null) {
            return mActivity;
        }
        if (getActivity() instanceof BaseActivity) {
            return (BaseActivity) getActivity();
        }
        if (getContext() instanceof BaseActivity) {
            return (BaseActivity) getContext();
        }
        return null;
    }

    @Override
    public Context getContextWithinHost() {
        return getActivityWithinHost();
    }

    public void callTarget() {
        final Fragment targetFragment = getTargetFragment();
        if (targetFragment != null && getTargetRequestCode() != -1) {
            targetFragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
        }
    }

    public void dismissSelf() {
        callTarget();
        FragmentController.removeFragment(getFragmentManager(), this);
    }

    /**
     * 判断Fragment是否存活
     *
     * @return boolean
     */
    public boolean isFragmentAlive() {
        return isAdded() && !isDetached() && !isRemoving() && getFragmentManagerWithinHost() != null && !getFragmentManagerWithinHost().isDestroyed();
    }

}
