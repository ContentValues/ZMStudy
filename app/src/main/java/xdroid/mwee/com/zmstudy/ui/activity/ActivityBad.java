package xdroid.mwee.com.zmstudy.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.mwee.android.tools.router.Router;
import java.util.ArrayList;
import java.util.List;
import xdroid.mwee.com.mwcommon.base.BaseActivity;
import xdroid.mwee.com.mwcommon.base.XFragmentAdapter;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.view.BadViewPager;

/**
 * Created by zhangmin on 2018/12/18.
 */

public class ActivityBad extends BaseActivity {

    private String[] titles = {"View1", "View2", "View3", "View4"};

    public static void launch(Activity activity) {
        Router.newIntent(activity)
                .to(ActivityBad.class)
               /* .putString(PARAM_URL, url)
                .putString(PARAM_DESC, desc)*/
                .launch();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bad_layout;
    }

    @Override
    public void initView() {
        ViewPager badViewPager = findViewById(R.id.mBadViewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(BadFragment.newInstance("View1"));
        fragments.add(BadFragment2.newInstance());
        fragments.add(BadFragment3.newInstance());
        fragments.add(BadFragment4.newInstance());
        XFragmentAdapter xFragmentAdapter = new XFragmentAdapter(getSupportFragmentManager(),fragments,titles);
        badViewPager.setAdapter(xFragmentAdapter);


        badViewPager.setOffscreenPageLimit(0);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(badViewPager);
    }


    private final static String TAG = ActivityBad.class.getSimpleName();

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, TAG + "--" + "dispatchTouchEvent MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, TAG + "--" + "dispatchTouchEvent MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, TAG + "--" + "dispatchTouchEvent MotionEvent.ACTION_UP");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,TAG+"--"+ "onTouchEvent MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG,TAG+"--"+ "onTouchEvent MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,TAG+"--"+"onTouchEvent MotionEvent.ACTION_UP");
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

}
