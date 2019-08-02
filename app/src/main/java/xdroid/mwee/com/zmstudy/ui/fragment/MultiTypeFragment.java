package xdroid.mwee.com.zmstudy.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import xdroid.mwee.com.mwbase.NetError;
import xdroid.mwee.com.mwbase.RXUtils;
import xdroid.mwee.com.mwbase.retrofit.ApiSubcriber;
import xdroid.mwee.com.mwbase.retrofit.XRetrofit;
import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.banner.BannerItemView;
import xdroid.mwee.com.zmstudy.banner.BannerListVo;
import xdroid.mwee.com.zmstudy.net.service.HttpService;
import xdroid.mwee.com.zmstudy.ui.xdview.DelegateAdapter;
import xdroid.mwee.com.zmstudy.ui.xdview.bean.VoBanner;
import xdroid.mwee.com.zmstudy.ui.xdview.bean.Item1Vo;
import xdroid.mwee.com.zmstudy.ui.xdview.bean.Item2Vo;
import xdroid.mwee.com.zmstudy.ui.xdview.bean.Item3Vo;
import xdroid.mwee.com.zmstudy.ui.xdview.bean.ItemVo;
import xdroid.mwee.com.zmstudy.ui.xdview.holder.Banner;
import xdroid.mwee.com.zmstudy.ui.xdview.holder.ItemType;
import xdroid.mwee.com.zmstudy.ui.xdview.holder.ItemType1;
import xdroid.mwee.com.zmstudy.ui.xdview.holder.ItemType2;

/**
 * Created by zhangmin on 2019/4/12.
 */

public class MultiTypeFragment extends BaseFragment {

    private List<Object> itemData;
    private RecyclerView recycler_view;
    private DelegateAdapter adapter;


    public static MultiTypeFragment newInstance() {
        return new MultiTypeFragment();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_multi_type;
    }

    @Override
    public void initView(View v) {

        itemData = new ArrayList<>();

        recycler_view = v.findViewById(R.id.recycler_view);

        adapter = new DelegateAdapter.Builder()
                //.bind(HeaderVo.class, new HeaderViewHolder(MultiTypeActivity.this, ProgressStyle.Pacman))
                .bind(BannerListVo.class, new BannerItemView(getContext()))
//                .bind(VoBanner.class, new Banner())
//                .bind(ItemVo.class, new ItemType())
//                .bind(Item1Vo.class, new ItemType1())
//                .bind(Item2Vo.class, new ItemType2())
//                .bind(FootVo.class, new FootViewHolder(MultiTypeActivity.this, ProgressStyle.Pacman))
//                .bindArray(Item3Vo.class, new ItemType3(), new ItemType4())
//                .withClass(new OneToMany<Item3Vo>() {
//                    @Override
//                    public Class<? extends VHolder<Item3Vo, ?>> onItemView(int position, Item3Vo item3Vo) {
//                        if (item3Vo.type.equals("3")) {
//                            return ItemType3.class;
//                        }
//                        if (item3Vo.type.equals("4")) {
//                            return ItemType4.class;
//                        }
//                        return ItemType4.class;
//                    }
//                })
//                .setOnItemClickListener(this)
                .build();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (itemData.get(position) instanceof VoBanner
//                        || itemData.get(position) instanceof HeaderVo
                        || itemData.get(position) instanceof Item1Vo
//                        || itemData.get(position) instanceof FootVo
                        ) {
                    return 4;
                } else if (itemData.get(position) instanceof ItemVo || itemData.get(position) instanceof Item3Vo) {
                    return 2;
                } else if (itemData.get(position) instanceof Item2Vo) {
                    return 1;
                }
                return 4;
            }
        });

        recycler_view.setAdapter(adapter);
        recycler_view.setLayoutManager(layoutManager);

    }

    @Override
    public void initData() {
        super.initData();

        itemData.clear();
//        itemData.add(new VoBanner());
//
//        for (int i = 0; i < 8; i++) {
//            itemData.add(new Item2Vo());
//        }
//
//        itemData.add(new Item1Vo("java"));
//        for (int i = 0; i < 6; i++) {
//            itemData.add(new ItemVo());
//        }
//
//
//        itemData.add(new Item1Vo("android"));
//        for (int i = 0; i < 6; i++) {
//            itemData.add(new ItemVo());
//        }
//        for (int i = 0; i < 3; i++) {
//            itemData.add(new Item3Vo("3"));
//        }for (int i = 0; i < 3; i++) {
//            itemData.add(new Item3Vo("4"));
//        }


         //loadData();


    }


//    private void loadData() {
//
//        HttpService httpService = XRetrofit.getInstance().getRetrofit("https://api.meiyuanbang.com/", false).create(HttpService.class);
//        Observable<BannerListVo> observable = httpService.getBannerData("1", "4", "109", "", null);
//        observable.compose(RXUtils.getScheduler())
//                .subscribe(new ApiSubcriber<BannerListVo>() {
//                    @Override
//                    protected void onFail(NetError error) {
////                        tv_error.setText(error.getMessage());
////                        contentLayout.showError();
//                    }
//
//                    @Override
//                    public void onNext(BannerListVo response) {
//
//                        itemData.add(response);
//                        adapter.setDatas(itemData);
//                        adapter.notifyDataSetChanged();
//
//                        System.out.println("");
////                        if (currentPager <= 1) {
////                            getAdapter().setData(response.results);
////                        } else {
////                            getAdapter().addData(response.results);
////                        }
////                        contentLayout.getRecyclerView().setPage(currentPager, MAX_PAGE);
////
////                        //todo 可以空数据接口 展示空数据 但是没有必要
////                        if (getAdapter().getItemCount() < 1) {
////                            contentLayout.showEmpty();
////                            return;
////                        }
//                    }
//                });
//    }
}
