package xdroid.mwee.com.mwcommon.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;


/**
 * Host,Activity和Fragment都作为Host时的接口化.
 */
public interface Host {

    /**
     * 获取Context
     *
     * @return Context
     */
    Context getContextWithinHost();

    /**
     * 获取Activity
     *
     * @return BaseActivity
     */
    LifeCircleActivity getActivityWithinHost();

    /**
     * 获取FragmentManager
     *
     * @return FragmentManager
     */
    FragmentManager getFragmentManagerWithinHost();

    /**
     * 获取Resources
     *
     * @return Resources
     */
    Resources getResourcesWithinHost();

    /**
     * 获取String
     *
     * @param resId int
     * @return String
     */
    String getStringWithinHost(@StringRes int resId);

}
