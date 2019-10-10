package com.sugart.composition.welcome;

import rx.Observable;
import xdroid.mwee.com.mwbase.retrofit.XRetrofit;
import xdroid.mwee.com.zmstudy.model.bean.GankModel;
import xdroid.mwee.com.zmstudy.net.service.HttpService;

/**
 * Author：created by SugarT
 * Time：2019/9/30 13
 */
public class WelcomeModel implements WelcomeContract.Model {
    //做网络请求
    @Override
    public Observable<GankModel> queryModel(String type, int pageSize, int pageNum) {
        HttpService httpService = XRetrofit.getInstance().getRetrofit(HttpService.BASE_URL, false).create(HttpService.class);
        Observable<GankModel> observable = httpService.getGankData(type, pageSize, pageNum);
        return observable;
    }
}
