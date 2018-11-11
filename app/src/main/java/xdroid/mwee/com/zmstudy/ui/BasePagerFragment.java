package xdroid.mwee.com.zmstudy.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import xdroid.mwee.com.mwcommon.base.SimpleRecAdapter;
import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.mwcommon.xrecyclerview.XRecyclerContentLayout;
import xdroid.mwee.com.mwcommon.xrecyclerview.XRecyclerView;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.model.bean.GankModel;
import xdroid.mwee.com.zmstudy.net.NetError;
import xdroid.mwee.com.zmstudy.net.base.ApiSubcriber;
import xdroid.mwee.com.zmstudy.net.base.HttpService;
import xdroid.mwee.com.zmstudy.net.base.XApi;

/**
 * Created by zhangmin on 2018/6/19.
 */

public abstract class BasePagerFragment extends BaseFragment {

    XRecyclerContentLayout contentLayout;
    TextView tv_error;
    TextView tv_empty;

    protected static final int PAGE_SIZE = 10;
    protected static final int MAX_PAGE = 10;

    @Override
    public void initView(View v) {
        contentLayout = v.findViewById(R.id.contentLayout);
        tv_error = v.findViewById(R.id.tv_error);
        tv_empty = v.findViewById(R.id.tv_empty);
    }

    @Override
    public void initData() {
        super.initData();
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
        XApi.getInstance().getRetrofit(HttpService.BASE_URL, false).create(HttpService.class)
        //XApi.create(HttpService.class, HttpService.BASE_URL, false)
                .getGankData(getType(), PAGE_SIZE, currentPager)
                .compose(RXUtils.getScheduler())
                .subscribe(new ApiSubcriber<GankModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        tv_error.setText(error.getMessage());
                        contentLayout.showError();
                    }

                    @Override
                    public void onNext(GankModel response) {
                        if (currentPager <= 1) {
                            getAdapter().setData(response.results);
                        } else {
                            getAdapter().addData(response.results);
                        }
                        contentLayout.getRecyclerView().setPage(currentPager, MAX_PAGE);

                        //todo 可以空数据接口 展示空数据 但是没有必要
                        if (getAdapter().getItemCount() < 1) {
                            contentLayout.showEmpty();
                            return;
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
