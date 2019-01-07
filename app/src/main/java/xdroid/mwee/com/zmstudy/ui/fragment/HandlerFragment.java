package xdroid.mwee.com.zmstudy.ui.fragment;

import android.os.Looper;
import android.view.View;

import com.mwee.android.tools.LogUtil;

import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.zmstudy.R;

/**
 * Created by zhangmin on 2018/12/6.
 */

public class HandlerFragment extends BaseFragment {

   /* private TextView tvHanler;
    private TextView tvImgHandler;

    private Handler handler;*/

    private final static int HANDLER_MSG = 0X001;
    private Subscription subscription;

    public static HandlerFragment newInstance() {
        return new HandlerFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_handler;
    }

    @Override
    public void onResume() {
        super.onResume();
        //handler.sendEmptyMessageDelayed(HANDLER_MSG, 1000);
    }

    @Override
    public void initView(View v) {

       /* tvHanler = v.findViewById(R.id.tvHanler);
        tvImgHandler = v.findViewById(R.id.tvImgHandler);*/


        subscription = Schedulers.newThread().createWorker().schedulePeriodically(new Action0() {
            @Override
            public void call() {

                if (Looper.myLooper() == Looper.getMainLooper()) {
                    LogUtil.log("HandlerFragment Main thread");
                } else {
                    LogUtil.log("HandlerFragment 子 thread");
                }
                doNewWork();
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);


       /* HandlerThread handlerThread = new HandlerThread("HandlerThread") {

            @Override
            protected void onLooperPrepared() {
                super.onLooperPrepared();

                handler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {

                        if (Looper.myLooper() == Looper.getMainLooper()) {
                            LogUtil.log("HandlerFragment Main thread");
                        } else {
                            LogUtil.log("HandlerFragment 子 thread");
                        }
                        doNewWork();
                        handler.sendEmptyMessageDelayed(HANDLER_MSG, 1000);
                        return false;
                    }
                });

            }
        };
        handlerThread.start();*/
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    private void doNewWork() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtil.log("循环do something");


    }

}
