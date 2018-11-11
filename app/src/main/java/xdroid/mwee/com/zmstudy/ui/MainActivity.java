package xdroid.mwee.com.zmstudy.ui;

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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import xdroid.mwee.com.mwcommon.base.BaseActivity;
import xdroid.mwee.com.mwcommon.base.XFragmentAdapter;
import xdroid.mwee.com.zmstudy.R;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;

    TabLayout tabLayout;

    ViewPager viewPager;

    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] titles = {"小帅哥", "妹子", "订单", "桌台", "动画"};

    private int[] pics = new int[]{R.mipmap.ios_icon, R.mipmap.js_icon, R.mipmap.other_icon, R.mipmap.android_icon, R.mipmap.js_icon};
    private DrawerLayout drawer_layout;
    private NavigationView navigationView;


    @Override
    public void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

    }


    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(GirlFragment.newInstance());
        fragmentList.add(FastFoodOrderFragment.newInstance());
        fragmentList.add(TableFragment.newInstance());
        fragmentList.add(AnimatorViewFragment.newInstance());

       /* tabLayout.addTab(tabLayout.newTab().setText("小帅哥"));
        tabLayout.addTab(tabLayout.newTab().setText("妹子好漂亮"));
        tabLayout.addTab(tabLayout.newTab().setText("菜品"));
        tabLayout.addTab(tabLayout.newTab().setText("桌台"));*/

        //XFragmentAdapter adapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);

        //tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.view_menu_item_header));

        XFragmentAdapter adapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setCustomIcon();
        setupDrawerSelectedListener();
      /*  for(int i=0;i<titles.length;i++){
            tabLayout.getTabAt(i).setText(titles[i]);
        }*/


        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //绑定tab点击事件
        /*tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.out.println("tab.getPosition()-->" + tab.getPosition());
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
    }

    private void setupDrawerSelectedListener() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            private MenuItem mPreMenuItem;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (mPreMenuItem != null) mPreMenuItem.setChecked(false);
                menuItem.setChecked(true);
                drawer_layout.closeDrawers();
                mPreMenuItem = menuItem;
                return true;
            }
        });
    }


    /**
     * 设置自定义位置图标
     */
    private void setCustomIcon() {

        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }

        for (int i = 0; i < titles.length; i++) {
            tabLayout.getTabAt(i).setCustomView(makeTabView(i));
        }
    }

    /**
     * 引入布局设置图标和标题
     *
     * @param position
     * @return
     */
    private View makeTabView(int position) {
        View tabView = LayoutInflater.from(this).inflate(R.layout.view_empty1, null);
        TextView textView = tabView.findViewById(R.id.textview);
        ImageView imageView = tabView.findViewById(R.id.imageview);
        textView.setText(titles[position]);
        imageView.setImageResource(pics[position]);
        return tabView;
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {

        System.out.println("哈哈哈--->"+Arrays.toString(fragmentList.toArray()));
        super.onResume();
    }
}
