package xdroid.mwee.com.zmstudy.ui.fragment;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import xdroid.mwee.com.mwbase.okhttp.callback.JsonCallback;
import xdroid.mwee.com.mwcommon.base.SimpleRecAdapter;
import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.mwcommon.dialog.Progress;
import xdroid.mwee.com.mwcommon.dialog.ProgressManager;
import xdroid.mwee.com.mwcommon.xrecyclerview.XRecyclerContentLayout;
import xdroid.mwee.com.mwcommon.xrecyclerview.XRecyclerView;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.model.bean.GankModel;
import xdroid.mwee.com.mwbase.NetError;
import xdroid.mwee.com.mwbase.retrofit.ApiSubcriber;
import xdroid.mwee.com.zmstudy.net.NetApi;
import xdroid.mwee.com.zmstudy.net.service.HttpService;
import xdroid.mwee.com.mwbase.retrofit.XRetrofit;
import xdroid.mwee.com.mwbase.RXUtils;

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

        System.out.println("测试2");


        System.out.println("git branch");
        System.out.println("git checkout dev");


        System.out.println("git checkout 冲突了");
        System.out.println("git branch future");
        System.out.println("git checkout future");

        System.out.println("git checkout dev");
        System.out.println("git checkout future");


        System.out.println("git checkout futuredev");

        System.out.println("issuse-101 修复了一个紧急的bug 也需要合并到正在开发的最新的dev分支上");

        System.out.println("git checkout 测试dev 假设中途有bug需要修复");


        System.out.println("测试远程分支功能");
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

        NetApi.getGankData(getType(), PAGE_SIZE, currentPager, new JsonCallback<GankModel>() {
            @Override
            public void onFail(Call call, String erroMsg, int id) {
                tv_error.setText(erroMsg);
                contentLayout.showError();
            }

            @Override
            public void onResponse(GankModel response, int id) {
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


//        HttpService httpService = XRetrofit.getInstance().getRetrofit(HttpService.BASE_URL, false).create(HttpService.class);
//
//        retrofit2.Call<GankModel> call = httpService.getGankDataRetrofit(getType(), PAGE_SIZE, currentPager);
//
//
//        call.enqueue(new Callback<GankModel>() {
//            @Override
//            public void onResponse(retrofit2.Call<GankModel> call, Response<GankModel> response) {
//
//                if(Looper.myLooper() == Looper.getMainLooper()){
//                    System.out.println(JSON.toJSONString("call.enqueue 运行在主线程"));
//                }else {
//                    System.out.println(JSON.toJSONString("call.enqueue 运行在子线程"));
//                }
//
//
//                getAdapter().setData(response.body().results);
//
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<GankModel> call, Throwable t) {
//
//            }
//        });


//        HashMap<String, String> map = new HashMap();
//        map.put("type", getType() + "");
//        map.put("number", PAGE_SIZE + "");
//        map.put("page", currentPager + "");
//
//        HttpService httpService = XRetrofit.getInstance().getRetrofit(HttpService.BASE_URL, false).create(HttpService.class);
//        //XApi.create(HttpService.class, HttpService.BASE_URL, false)
//        Observable<GankModel> observable = httpService.getGankData(getType(), PAGE_SIZE, currentPager);
//        //.getGankData(map)
//        observable.compose(RXUtils.getScheduler())
//                .subscribe(new ApiSubcriber<GankModel>() {
//                    @Override
//                    protected void onFail(NetError error) {
//                        tv_error.setText(error.getMessage());
//                        contentLayout.showError();
//                    }
//
//                    @Override
//                    public void onNext(GankModel response) {
//                        if (currentPager <= 1) {
//                            getAdapter().setData(response.results);
//                        } else {
//                            getAdapter().addData(response.results);
//                        }
//                        contentLayout.getRecyclerView().setPage(currentPager, MAX_PAGE);
//
//                        //todo 可以空数据接口 展示空数据 但是没有必要
//                        if (getAdapter().getItemCount() < 1) {
//                            contentLayout.showEmpty();
//                            return;
//                        }
//                    }
//                });
    }


    public abstract SimpleRecAdapter getAdapter();

    public abstract String getType();

    public abstract RecyclerView.LayoutManager getLayoutManager();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_pager;
    }
}
