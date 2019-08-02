package xdroid.mwee.com.zmstudy.ui.fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.zmstudy.R;
import xdroid.mwee.com.zmstudy.ui.activity.ActivityBad;

/**
 * Created by zhangmin on 2018/10/16.
 */

public class AnimatorViewFragment extends BaseFragment {

    private Button mAnimatorBtn;
    private TextView mAnimatorViewTv;
    private ImageView mAnimatorViewImg;


    private ImageView mTimerImg;

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
        mTimerImg = v.findViewById(R.id.mTimerImg);



       /* mAnimatorViewTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TAG", "mAnimatorViewTv onClick execute"+event.getAction());
                return false;
            }
        });*/

        /*v.findViewById(R.id.mAnimatorViewTv2).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TAG", "mAnimatorViewTv2 onClick execute  onTouch"+event.getAction());
                return false;
            }
        });

        v.findViewById(R.id.mAnimatorViewTv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "mAnimatorViewTv2 onClick execute  onClick");
            }
        });


        mTimerImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.d("TAG", "mAnimatorViewTv2 onClick execute  onTouch"+event.getAction());
                return true;
            }
        });*/
        mTimerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Log.d("TAG", "mTimerImg onClick execute  onClick");
                IntentServiceActivity.launch(getActivityWithinHost());*/
                ActivityBad.launch(getActivity());

            }
        });

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


        /*mAnimatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               *//* FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                ViewFragment1 viewFragment1 = ViewFragment1.newInstance();
                fragmentTransaction.replace(R.id.fragmentContainer, viewFragment1);

                fragmentTransaction.addToBackStack("ViewFragment1");
                fragmentTransaction.commit();*//*


               *//* HashMap<String, String> params = new HashMap();
                params.put("pageNo", "1");
                params.put("pageSize", "20");
                params.put("queryType", "0");
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(params));*//*


               *//* HashMap<String, String> params = new HashMap();
                params.put("pageNo", "1");
                params.put("pageSize", "20");
                params.put("queryType", "0");
                String baseUrl = "http://pcdc.winpos.9now.net/posapi/shop/202385-103/";

                XRetrofit.getInstance().getRetrofit(HttpService.MWEE_BASE_URL, true).create(HttpService.class)
                        //.queryKBOrderList(requestBody)
                        //.queryKBOrderList("1","20","0")
                        .queryKBOrderList(params)
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
                        });*//*

               *//* ObjectAnimator objectAnimatorAlpha = ObjectAnimator.ofFloat(mAnimatorViewTv, "alpha", 1f, 0f, 1f);
                objectAnimatorAlpha.setDuration(3000);
                objectAnimatorAlpha.start();


                ObjectAnimator rotate = ObjectAnimator.ofFloat(mAnimatorViewImg, "rotation", 0f, 360f);
                ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(mAnimatorViewImg, "alpha", 1f, 0f, 1f);
                ObjectAnimator moveIn = ObjectAnimator.ofFloat(mAnimatorViewImg, "translationX", 0, -500f, 0f);
                ObjectAnimator moveY = ObjectAnimator.ofFloat(mAnimatorViewImg, "translationY", 0, -500f, 0f);
                AnimatorSet animSet = new AnimatorSet();
                animSet.play(rotate).with(fadeInOut).after(moveIn).after(moveY);
                animSet.setDuration(3000);
                animSet.start();*//*

            }
        });


        *//*RxView.clicks(mTimerBtn).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
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
        });*//*


        mTimerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getChildFragmentManager().popBackStack();

                Log.d("TAG", "mTimerBtn onClick execute");
            }
        });*/




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
