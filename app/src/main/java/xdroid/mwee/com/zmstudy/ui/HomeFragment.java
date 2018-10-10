package xdroid.mwee.com.zmstudy.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import xdroid.mwee.com.mwcommon.base.SimpleRecAdapter;
import xdroid.mwee.com.zmstudy.adapter.HomeAdapter;

/**
 * Created by zhangmin on 2018/4/9.
 */

public class HomeFragment extends BasePagerFragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    HomeAdapter homeAdapter;

    @Override
    public SimpleRecAdapter getAdapter() {

        if (homeAdapter == null) {
            homeAdapter = new HomeAdapter(getContext());
        }
        return homeAdapter;
    }

    @Override
    public String getType() {
        return "all";
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }
}
