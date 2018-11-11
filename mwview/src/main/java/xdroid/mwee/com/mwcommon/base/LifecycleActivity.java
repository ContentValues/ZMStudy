package xdroid.mwee.com.mwcommon.base;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.base.BaseConfig;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by zhangmin on 2018/6/19.
 */

public class LifecycleActivity extends RxAppCompatActivity implements Host{

    //public static BaseActivity topActivity = null;
    private long timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BaseConfig.isDEV()) {
            timestamp = SystemClock.elapsedRealtime();
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onCreate");
        }
        super.onCreate(savedInstanceState);
        //topActivity = this;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onCreate 2");
        }
        super.onCreate(savedInstanceState, persistentState);

    }


    @Override
    protected void onStart() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onStart " + (SystemClock.elapsedRealtime() - timestamp));
        }
        super.onStart();
    }

    @Override
    protected void onResumeFragments() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onResumeFragments");
        }
        super.onResumeFragments();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onConfigurationChanged");
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onAttachedToWindow() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onAttachedToWindow");
        }
        super.onAttachedToWindow();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onSaveInstanceState");
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onSaveInstanceState 2");
        }
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onStateNotSaved() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onStateNotSaved");
        }
        super.onStateNotSaved();
    }

    @Override
    public void onContentChanged() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onContentChanged");
        }
        super.onContentChanged();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onPostCreate");
        }
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onPostCreate 2");
        }
        super.onPostCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onRestoreInstanceState");
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onRestoreInstanceState 2");
        }
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onPostResume() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onPostResume");
        }
        super.onPostResume();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onAttachFragment");
        }
        super.onAttachFragment(fragment);
    }

    @Override
    public void onAttachFragment(android.app.Fragment fragment) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onAttachFragment app ");
        }
        super.onAttachFragment(fragment);
    }


    @Override
    protected void onRestart() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onRestart");
        }
        super.onRestart();

    }


    @Override
    public void onDetachedFromWindow() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onDetachedFromWindow");
        }
        super.onDetachedFromWindow();

    }

    @Override
    public void onVisibleBehindCanceled() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onVisibleBehindCanceled");
        }
        super.onVisibleBehindCanceled();

    }

    @Override
    public void onLowMemory() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onLowMemory");
        }
        super.onLowMemory();

    }

    @Override
    public void onTrimMemory(int level) {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onTrimMemory");
        }
        super.onTrimMemory(level);

    }

    @Override
    protected void onResume() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onResume " + (SystemClock.elapsedRealtime() - timestamp));
        }
        super.onResume();
        //topActivity = this;

    }

    @Override
    protected void onPause() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onPause");
        }
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onStop");
        }
    }

    @Override
    protected void onDestroy() {
        if (BaseConfig.isDEV()) {
            LogUtil.log("ActivityLifeCircle " + this.getClass().getSimpleName() + " onDestroy");
        }
        /*if (topActivity != null && TextUtils.equals(topActivity.getClass().getName(), this.getClass().getName())) {
            topActivity = null;
        }*/

        super.onDestroy();
    }


    @Override
    public FragmentManager getFragmentManagerWithinHost() {
        return getSupportFragmentManager();
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
    public LifecycleActivity getActivityWithinHost() {
        return this;
    }

    @Override
    public Context getContextWithinHost() {
        return this;
    }

}
