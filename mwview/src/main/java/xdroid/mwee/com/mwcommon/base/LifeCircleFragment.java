package xdroid.mwee.com.mwcommon.base;

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
 * Created by zhangmin on 2018/6/19.
 */

public class LifeCircleFragment extends Fragment implements Host {

    private long timestamp;

    @Override
    public void onAttach(Context context) {
        if (BaseConfig.isDEV()) {
            timestamp = SystemClock.elapsedRealtime();
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onAttach " + timestamp);
        }
        super.onAttach(context);
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
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onViewStateRestored");
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
    public LifeCircleActivity getActivityWithinHost() {
        return (LifeCircleActivity) getActivity();
    }

    @Override
    public Context getContextWithinHost() {
        return getActivity();
    }
}
