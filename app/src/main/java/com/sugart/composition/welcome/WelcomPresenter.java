package com.sugart.composition.welcome;

import com.alibaba.fastjson.JSON;
import com.sugart.composition.BasePresenter;

import rx.Observable;
import xdroid.mwee.com.mwbase.NetError;
import xdroid.mwee.com.mwbase.RXUtils;
import xdroid.mwee.com.mwbase.retrofit.ApiSubcriber;
import xdroid.mwee.com.mwbase.retrofit.XRetrofit;
import xdroid.mwee.com.zmstudy.model.bean.GankModel;
import xdroid.mwee.com.zmstudy.net.service.HttpService;

/**
 * Author：created by SugarT
 * Time：2019/9/30 13
 *
 *
 * 1 Presenter持有 View和Model
 * 2 View和Model不能直接交互
 *
 *  * 降低耦合，方便维护
 *  * MVP的使用，使Activity中的网络请求剥离出来 成为model、presenter，
 *  * model只负责网络的请求、
 *  * pesenter负责处理请求网络后的数据处理：加载中 成功 or 失败 取消加载；
 *  * 最后View进行界面的展示
 *
 *
 *  TODO WelcomPresenter中我个人感觉可以直接把Model获取网络数据这块下载prestener中
 *
 */
public class WelcomPresenter extends BasePresenter<WelcomeContract.View> implements WelcomeContract.Presenter {

//    WelcomeModel model;
//
//    public WelcomPresenter() {
//        model = new WelcomeModel();
//    }


    //todo 第一种标准写法
//    @Override
//    public void doWelcome(String type, int number, int page) {
//
//        mView.showLoading();
//        Observable<GankModel> observable = model.queryModel(type, number, page);
//        observable.compose(RXUtils.getScheduler())
//                .subscribe(new ApiSubcriber<GankModel>() {
//                    @Override
//                    protected void onFail(NetError error) {
//                        mView.onError("网络数据获取失败");
//                        mView.hideLoading();
//                    }
//
//                    @Override
//                    public void onNext(GankModel response) {
//                        mView.doMakeLoving(JSON.toJSONString(response));
//                        mView.hideLoading();
//                    }
//                });
//    }


    //todo 第二种写法直接省略Model层 这种写法是不是就成了 VP结构了[类似于一个activity拥有一个processor结构]   如果model层代码比较复杂 是不是会导致代码冗余？
    @Override
    public void doWelcome(String type, int number, int page) {
        mView.showLoading();
        HttpService httpService = XRetrofit.getInstance().getRetrofit(HttpService.BASE_URL, false).create(HttpService.class);
        Observable<GankModel> observable = httpService.getGankData(type, number, page);
//        Observable<GankModel> observable = model.doWelcome(type, number, page);
        observable.compose(RXUtils.getScheduler())
                .subscribe(new ApiSubcriber<GankModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        mView.onError("网络数据获取失败");
                        mView.hideLoading();
                    }

                    @Override
                    public void onNext(GankModel response) {
                        mView.doMakeLoving(JSON.toJSONString(response));
                        mView.hideLoading();
                    }
                });
    }


}
