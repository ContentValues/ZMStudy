package xdroid.mwee.com.zmstudy.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.mwcommon.callback.ItemCallback;
import xdroid.mwee.com.mwcommon.callback.SimpleCallback;
import xdroid.mwee.com.mwcommon.xrecyclerview.XRecyclerView;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.adapter.MenuAdapter;
import xdroid.mwee.com.zmstudy.adapter.MenuClsAdapter;
import xdroid.mwee.com.zmstudy.cache.AppCache;
import xdroid.mwee.com.zmstudy.model.menu.MenuItem;

/**
 * Created by zhangmin on 2018/6/20.
 */

public class MenuFragment extends BaseFragment {

    private MenuClsAdapter menuClsAdapter;
    private MenuAdapter menuAdapter;
    private XRecyclerView mMenuClsLsv;
    private XRecyclerView mMenuRecyclerView;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public void initView(View v) {
        mMenuClsLsv = v.findViewById(R.id.mMenuClsLsv);
        mMenuRecyclerView = v.findViewById(R.id.menuContentLayout);
    }

    @Override
    public void initData() {
        super.initData();


        menuClsAdapter = new MenuClsAdapter(getContext());
        mMenuClsLsv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //mMenuClsLsv.addItemDecoration(new VerticalDividerItemDecoration.Builder(getContext()).margin(1).colorResId(R.color.system_red).build());
        mMenuClsLsv.setAdapter(menuClsAdapter);
        menuClsAdapter.setData(AppCache.getInstance().firstNodeMap);

        menuClsAdapter.setItemClick(new ItemCallback() {
            @Override
            public void onItemClick(int position, Object model, int tag) {
                super.onItemClick(position, model, tag);
                loadMenuItemsByClsPosition(position);
            }
        });

        menuAdapter = new MenuAdapter(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        mMenuRecyclerView.setLayoutManager(layoutManager);
        mMenuRecyclerView.setAdapter(menuAdapter);
        menuAdapter.setItemClick(new ItemCallback<MenuItem>() {
            @Override
            public void onItemClick(int position, MenuItem model, int tag) {
                super.onItemClick(position, model, tag);
                simpleCallback.action(model.clone());
            }
        });


        loadMenuItemsByClsPosition(0);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_menu;
    }

    private void loadMenuItemsByClsPosition(int index) {
        int size = AppCache.getInstance().firstNodeMap.size();
        if (size != 0 && index < size) {
            menuAdapter.setData(AppCache.getInstance().firstNodeMap.get(index).menuList);
        }
    }


    SimpleCallback<MenuItem> simpleCallback;

    /**
     * 点击菜品回调
     *
     * @param simpleCallback
     */
    public void setOnMenuItemClickListener(SimpleCallback<MenuItem> simpleCallback) {

        this.simpleCallback = simpleCallback;
    }

}
