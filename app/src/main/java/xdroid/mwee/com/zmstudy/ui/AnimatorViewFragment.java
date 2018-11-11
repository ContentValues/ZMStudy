package xdroid.mwee.com.zmstudy.ui;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jakewharton.rxbinding.view.RxView;
import com.mwee.android.tools.LogUtil;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.functions.Action1;
import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.mwcommon.callback.ResultCallback;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.model.bean.KBTempDataResponse;
import xdroid.mwee.com.zmstudy.net.NetError;
import xdroid.mwee.com.zmstudy.net.base.ApiSubcriber;
import xdroid.mwee.com.zmstudy.net.base.HttpService;
import xdroid.mwee.com.zmstudy.net.base.XApi;
import xdroid.mwee.com.zmstudy.net.base.XApiService;

/**
 * Created by zhangmin on 2018/10/16.
 */

public class AnimatorViewFragment extends BaseFragment {

    private Button mAnimatorBtn;
    private TextView mAnimatorViewTv;
    private ImageView mAnimatorViewImg;

    private static int SECOND = 20;

    private Button mTimerBtn;


    public static AnimatorViewFragment newInstance() {
        return new AnimatorViewFragment();
    }


    @Override
    public void initView(View v) {
        mAnimatorBtn = v.findViewById(R.id.mAnimatorBtn);
        mAnimatorViewTv = v.findViewById(R.id.mAnimatorViewTv);
        mAnimatorViewImg = v.findViewById(R.id.mAnimatorViewImg);
        mTimerBtn = v.findViewById(R.id.mTimerBtn);

    }

    @Override
    public void initData() {
        super.initData();
        /*mAnimatorViewTv.getAlpha();
        mAnimatorViewTv.getRotationX();
        mAnimatorViewTv.getRotationY();
        mAnimatorViewTv.getScaleX();
        mAnimatorViewTv.getScaleY();
        mAnimatorViewTv.getTranslationX();
        mAnimatorViewTv.getTranslationY();*/


        mAnimatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* HashMap<String, String> params = new HashMap();
                params.put("pageNo", "1");
                params.put("pageSize", "20");
                params.put("queryType", "0");
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(params));*/


                String baseUrl = "http://pcdc.winpos.9now.net/posapi/shop/202385-103/";

                XApi.getInstance().getRetrofit(HttpService.MWEE_BASE_URL, true).create(HttpService.class)
                        //.queryKBOrderList(requestBody)
                        .queryKBOrderList("1","20","0")
                        .compose(RXUtils.getApiTransformer())
                        .compose(RXUtils.getScheduler())
                        .subscribe(new ApiSubcriber<KBTempDataResponse>() {
                            @Override
                            protected void onFail(NetError error) {
                                LogUtil.log("onFail-->" + error.getMessage());
                            }

                            @Override
                            public void onNext(KBTempDataResponse response) {
                                LogUtil.log("onNext-->" + JSON.toJSONString(response));
                            }
                        });

               /* ObjectAnimator objectAnimatorAlpha = ObjectAnimator.ofFloat(mAnimatorViewTv, "alpha", 1f, 0f, 1f);
                objectAnimatorAlpha.setDuration(3000);
                objectAnimatorAlpha.start();


                ObjectAnimator rotate = ObjectAnimator.ofFloat(mAnimatorViewImg, "rotation", 0f, 360f);
                ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(mAnimatorViewImg, "alpha", 1f, 0f, 1f);
                ObjectAnimator moveIn = ObjectAnimator.ofFloat(mAnimatorViewImg, "translationX", 0, -500f, 0f);
                ObjectAnimator moveY = ObjectAnimator.ofFloat(mAnimatorViewImg, "translationY", 0, -500f, 0f);
                AnimatorSet animSet = new AnimatorSet();
                animSet.play(rotate).with(fadeInOut).after(moveIn).after(moveY);
                animSet.setDuration(3000);
                animSet.start();*/

            }
        });


        RxView.clicks(mTimerBtn).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

                XApiService.queryKBOrderList("1", "20", "0", new ResultCallback<KBTempDataResponse>() {
                    @Override
                    public void onSuccess(KBTempDataResponse data) {

                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        super.onFailure(code, msg);
                    }
                });
            }
        });

       /* mTimerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                XApiService.queryKBOrderList("1", "20", "0", new ResultCallback<KBTempDataResponse>() {
                    @Override
                    public void onSuccess(KBTempDataResponse data) {

                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        super.onFailure(code, msg);
                    }
                });



               *//* HashMap<String, String> params = new HashMap();
                params.put("pageNo", "1");
                params.put("pageSize", "20");
                params.put("queryType", "0");
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(params));
                String baseUrl = "http://pcdc.winpos.9now.net/posapi/shop/202385-103/";
                XApi.getInstance().getRetrofit(HttpService.MWEE_BASE_URL, true).create(HttpService.class)
                        .queryKBOrderList(requestBody)
                        //.queryKBOrderList("1","20","0")
                        .compose(RXUtils.getApiTransformer())
                        .compose(RXUtils.getScheduler())
                        .subscribe(new ApiSubcriber<KBTempDataResponse>() {
                            @Override
                            protected void onFail(NetError error) {
                                LogUtil.log("onFail-->" + error.getMessage());
                            }

                            @Override
                            public void onNext(KBTempDataResponse response) {
                                LogUtil.log("onNext-->" + JSON.toJSONString(response));
                            }
                        });

                    *//*
               *//* Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).take(SECOND).subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        mAnimatorViewTv.setText("完成");
                        mAnimatorViewTv.setEnabled(true);
                        System.out.println("正在执行---->onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {

                        System.out.println("正在执行---->" + aLong);
                        mAnimatorViewTv.setText((SECOND - aLong) + "秒");
                        mAnimatorViewTv.setEnabled(false);
                    }
                });
                System.out.println("卡住线程---->onCompleted");*//*

            }
        });*/
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_animatorview_layout;
    }
}
