package xdroid.mwee.com.zmstudy.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import xdroid.mwee.com.mwcommon.base.SimpleRecAdapter;
import xdroid.mwee.com.mwcommon.base.XLazyFragment;
import xdroid.mwee.com.mwcommon.xrecyclerview.XRecyclerContentLayout;
import xdroid.mwee.com.mwcommon.xrecyclerview.XRecyclerView;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.model.GankResults;
import xdroid.mwee.com.zmstudy.net.JsonCallback;
import xdroid.mwee.com.zmstudy.net.NetApi;

/**
 * Created by zhangmin on 2018/6/19.
 */

public abstract class BasePagerFragment extends XLazyFragment {

    XRecyclerContentLayout contentLayout;
    TextView tv_error;
    TextView tv_empty;

    protected static final int PAGE_SIZE = 10;
    protected static final int MAX_PAGE = 10;

    @Override
    public void initData(Bundle savedInstanceState) {
        contentLayout = findViewById(R.id.contentLayout);
        tv_error = findViewById(R.id.tv_error);
        tv_empty = findViewById(R.id.tv_empty);
        tv_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(1);
            }
        });
        tv_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(1);
            }
        });
        initAdapter();
        loadData(1);
    }

    private void initAdapter() {

        XRecyclerView xRecyclerView = contentLayout.getRecyclerView();
        xRecyclerView.setLayoutManager(getLayoutManager());
        xRecyclerView.setAdapter(getAdapter());
        xRecyclerView.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                loadData(1);
            }

            @Override
            public void onLoadMore(int page) {
                loadData(page);
            }
        });
        xRecyclerView.useDefLoadMoreView();
    }


    private void loadData(int currentPager) {

        NetApi.getGankData(getType(), PAGE_SIZE, currentPager, new JsonCallback<GankResults>(1 * 60 * 60 * 1000) {
            @Override
            public void onFail(okhttp3.Call call, String erroMsg, int id) {
                if (!TextUtils.isEmpty(erroMsg)) {
                    tv_error.setText(erroMsg);
                    contentLayout.showError();
                }
            }

            @Override
            public void onResponse(GankResults response, int id) {
                if (!response.isError()) {
                    if (currentPager <= 1) {
                        getAdapter().setData(response.getResults());
                    } else {
                        getAdapter().addData(response.getResults());
                    }
                    contentLayout.getRecyclerView().setPage(currentPager, MAX_PAGE);
                    if (getAdapter().getItemCount() < 1) {
                        contentLayout.showEmpty();
                        return;
                    }
                }
            }
        });

    }


    public abstract SimpleRecAdapter getAdapter();

    public abstract String getType();

    public abstract RecyclerView.LayoutManager getLayoutManager();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_pager;
    }
}
