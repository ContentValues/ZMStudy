package xdroid.mwee.com.mwcommon.base;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.mwee.android.tools.LogUtil;

import xdroid.mwee.com.mwcommon.R;


/**
 * FragmentController
 */
public class FragmentController {
    public static void showFragment(BaseFragment targetFragment) {
        if (targetFragment == null) {
            return;
        }
        //FragmentTransaction trx = targetFragment.getFragmentManagerWithinHost().beginTransaction();
        FragmentTransaction trx = targetFragment.getFragmentManager().beginTransaction();
        trx.show(targetFragment).commitAllowingStateLoss();
    }

    public static void addFragmentWithHide(Host host, BaseFragment targetFragment, int postion) {
        addFragmentWithHide(host.getFragmentManagerWithinHost(), targetFragment, targetFragment.TAG, postion, true);
    }

    public static void addFragmentNoHide(Host host, BaseFragment targetFragment, int postion) {
        addFragmentWithHide(host.getFragmentManagerWithinHost(), targetFragment, targetFragment.TAG, postion, false);
    }

    public static void addFragmentWithHide(FragmentManager fragmentManager, Fragment fragment, String tag, int postion, boolean hideTarget) {
        if (fragmentManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (fragmentManager.isDestroyed()) {
                    return;
                }
            }
            FragmentTransaction ft = fragmentManager.beginTransaction();
            Fragment lastFragment = fragmentManager.findFragmentById(postion);
            if (lastFragment != null && hideTarget) {
                ft.hide(lastFragment);
                fragment.setTargetFragment(lastFragment, BaseFragment.TARGET_REQUEST);
            }
            ft.add(postion, fragment, tag);
            ft.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
    }

    public static void addFragment(LifecycleActivity activity, BaseFragment fragment) {
        addFragment(activity.getSupportFragmentManager(), fragment, fragment.TAG, android.R.id.content, -1, -1);
    }

    public static void addFragment(Host host, BaseFragment fragment) {
        addFragment(host.getFragmentManagerWithinHost(), fragment, fragment.TAG, android.R.id.content, -1, -1);
    }

    public static void addFragment(Host host, BaseFragment fragment, boolean hasAnim) {
        if (hasAnim) {
            addFragment(host, fragment);
        } else {
            addFragment(host.getFragmentManagerWithinHost(), fragment, fragment.TAG, android.R.id.content, -2, -2);
        }
    }

    public static void addFragment(BaseFragment currentFragment, BaseFragment fragment) {
        addFragment(currentFragment.getFragmentManager(), fragment, fragment.TAG, android.R.id.content, -1, -1);
    }

    public static void addFragment(FragmentManager fragmentManager, BaseFragment fragment, String tag) {
        addFragment(fragmentManager, fragment, tag, android.R.id.content, -1, -1);
    }

    public static void addFragment(FragmentManager fragmentManager, BaseFragment fragment, String tag, int aniIN, int aniOut) {
        addFragment(fragmentManager, fragment, tag, android.R.id.content, aniIN, aniOut);
    }

    public static void addFragment(FragmentManager fragmentManager, BaseFragment fragment, String tag, int postion, int aniIN, int aniOut) {
        if (fragmentManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (fragmentManager.isDestroyed()) {
                    return;
                }
            }
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (aniIN > 0 || aniOut > 0) {
                ft.setCustomAnimations(aniIN, 0, 0, aniOut);
            } else if (aniIN == -1 || aniOut == -1) {
                ft.setCustomAnimations(R.anim.anim_fragment_in, R.anim.anim_fragment_out, R.anim.anim_fragment_close_in, R.anim.anim_fragment_close_out);
            }

            Fragment lastFragment = fragmentManager.findFragmentById(postion);
            if (lastFragment != null) {

                ft.hide(lastFragment);
            }

            ft.add(postion, fragment, tag);

            ft.addToBackStack(tag);
            ft.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
    }

    public static void initFragment(LifecycleActivity lifecycleActivity, Fragment fragment, String tag) {
        initFragment(lifecycleActivity, fragment, tag, android.R.id.content);
    }

    public static void initFragment(LifecycleActivity lifecycleActivity, Fragment fragment, String tag, int postion) {
        if (lifecycleActivity != null && !lifecycleActivity.isFinishing() && !lifecycleActivity.isDestroyed()) {
            FragmentManager fragmentManager = lifecycleActivity.getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();

            ft.replace(postion, fragment, tag);
            ft.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment targetFragment, String tag) {
        replaceFragment(fragmentManager, targetFragment, tag, android.R.id.content);
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment targetFragment, String tag, int position) {
        if (fragmentManager != null && !fragmentManager.isDestroyed()) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(position, targetFragment, tag);
            transaction.commitAllowingStateLoss();
        }
    }

    /**
     * 在指定的id位置显示指定的fragment,将该位置原有的fragment隐藏掉,避免fragment的重新创建
     *
     * @param fragmentManager
     * @param to              要显示的fragment
     * @param id              指定的id位置
     * @param addTag          要显示的fragment的tag
     */
    public static void showHideSwitch(FragmentManager fragmentManager, Fragment from, Fragment to, int id, String addTag) {
        if (fragmentManager != null && !fragmentManager.isDestroyed()) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            try {
                if (from != null && from.isVisible()) {
                    fragmentTransaction.hide(from);
                }
                if (!to.isAdded()) {
                    fragmentTransaction.add(id, to, addTag).commitAllowingStateLoss();
                } else {
                    fragmentTransaction.show(to).commitAllowingStateLoss();
                }
                fragmentManager.executePendingTransactions();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void removeFragment(FragmentManager fragmentManager, Fragment targetFragment) {
        if (fragmentManager != null && !fragmentManager.isDestroyed()) {
            String tag = targetFragment.getTag();

            try {
                if (fragmentManager.findFragmentByTag(tag) != null) {
                    fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                if (!targetFragment.isDetached() && !targetFragment.isRemoving()) {
                    FragmentTransaction localFragmentTransaction = fragmentManager.beginTransaction();

                    localFragmentTransaction.remove(targetFragment);
                    localFragmentTransaction.commitAllowingStateLoss();
                    fragmentManager.executePendingTransactions();
                }
            } catch (Exception e) {
                LogUtil.logError("", e);
                try {
                    FragmentTransaction localFragmentTransaction = fragmentManager.beginTransaction();
                    localFragmentTransaction.remove(targetFragment);
                    localFragmentTransaction.commitAllowingStateLoss();
                    fragmentManager.executePendingTransactions();
                } catch (Exception e2) {
                    LogUtil.logError("", e2);
                }
            }

        }
    }

    public static void removeFragment(FragmentManager fragmentManager, String tag) {
        if (fragmentManager != null && !TextUtils.isEmpty(tag) && !fragmentManager.isDestroyed()) {
            Fragment targment = fragmentManager.findFragmentByTag(tag);
            if (targment != null) {
                try {
                    fragmentManager.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } catch (Exception e) {
                    LogUtil.log("", e);
                }
                FragmentTransaction localFragmentTransaction = fragmentManager.beginTransaction();

                localFragmentTransaction.remove(targment);
                localFragmentTransaction.commitAllowingStateLoss();
                try {
                    fragmentManager.executePendingTransactions();
                } catch (Exception e) {
                    LogUtil.log("", e);
                }
            }
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment != null && !fragment.isDetached()) {
                FragmentTransaction localFragmentTransaction = fragmentManager.beginTransaction();
                localFragmentTransaction.remove(fragment);
                localFragmentTransaction.commitAllowingStateLoss();
                try {
                    fragmentManager.executePendingTransactions();
                } catch (Exception e) {
                    LogUtil.log("", e);
                }
            }
        }
    }
}
