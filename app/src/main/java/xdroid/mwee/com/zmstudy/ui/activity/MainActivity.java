package xdroid.mwee.com.zmstudy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.sugart.composition.welcome.GoogleDevActivity;
import com.sugart.composition.welcome.WelcomeActitvity;

import java.util.ArrayList;
import java.util.List;

import xdroid.mwee.com.mwcommon.base.BaseActivity;
import xdroid.mwee.com.mwcommon.base.XFragmentAdapter;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.fragment.AnimatorViewFragment;
import xdroid.mwee.com.zmstudy.ui.fragment.GirlFragment;
import xdroid.mwee.com.zmstudy.ui.fragment.HomeFragment;
import xdroid.mwee.com.zmstudy.ui.fragment.jdfragment.JDFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;

    TabLayout tabLayout;

    ViewPager viewPager;

    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] titles = {"小帅哥", "妹子", "京东主页","滑动冲突"};
    private DrawerLayout drawer_layout;
    private NavigationView navigationView;


    @Override
    public void initView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);


        /**
         *
         * 1) bindToLifecycle()可以使Observable事件和当前的Activity绑定，实现生命周期同步。也就是Activity 的 onDestroy() 方法被调用后，Observable 的订阅关系才解除
         *
         * 2) 指定在Activity其他的生命状态和订阅关系保持同步， bindUntilEvent()方法。
         *
         * bindUntilEvent( ActivityEvent event)
         *
         * ActivityEvent.CREATE: 在Activity的onCreate()方法执行后，解除绑定。
         *
         * ActivityEvent.START:在Activity的onStart()方法执行后，解除绑定。
         *
         * ActivityEvent.RESUME:在Activity的onResume()方法执行后，解除绑定。
         *
         * ActivityEvent.PAUSE: 在Activity的onPause()方法执行后，解除绑定。
         *
         * ActivityEvent.STOP:在Activity的onStop()方法执行后，解除绑定。
         *
         * ActivityEvent.DESTROY:在Activity的onDestroy()方法执行后，解除绑定
         *
         */
        //循环发送数字
//        Observable.interval(0, 1, TimeUnit.SECONDS)
//                .subscribeOn( Schedulers.io())
////                .compose(this.bindToLifecycle())   //bindToLifecycle()可以使Observable事件和当前的Activity绑定，实现生命周期同步。也就是Activity 的 onDestroy() 方法被调用后，Observable 的订阅关系才解除
//                .compose(this.bindUntilEvent(ActivityEvent.STOP ))//todo 这行代码很重要 不然会导致内存泄漏 activity销毁 事件还没有解绑 容易出问题
//                .observeOn( AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        System.out.println("lifecycle--" + aLong);
//                    }
//                });

    }


    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(GirlFragment.newInstance());
        fragmentList.add(JDFragment.newInstance());
        fragmentList.add(AnimatorViewFragment.newInstance());

        XFragmentAdapter adapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            // Handle the camera action
//           Router.newIntent(this).to(WelcomeActitvity.class).launch();
            Intent intent = new Intent(this, WelcomeActitvity.class);
            startActivity(intent);
            this.finish();

        } else if (id == R.id.nav_gallery) {

            Intent intent = new Intent(this, GoogleDevActivity.class);
            startActivity(intent);
            this.finish();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }


}
