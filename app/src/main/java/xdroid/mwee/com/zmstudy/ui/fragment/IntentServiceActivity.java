package xdroid.mwee.com.zmstudy.ui.fragment;

import android.app.Activity;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import com.mwee.android.tools.DateUtil;
import com.mwee.android.tools.LogUtil;
import com.mwee.android.tools.router.Router;
import xdroid.mwee.com.mwcommon.base.BaseActivity;
import xdroid.mwee.com.zmstudy.R;

/**
 * Created by zhangmin on 2018/12/6.
 */

public class IntentServiceActivity extends BaseActivity {

    private TextView tvImgPath;
    private IntentBroadcastReceiver intentBroadcastReceiver;

    private static String ACTION = "xdroid.mwee.com.zmstudy.ui.fragment.IntentServiceActivity";
    private TextView tvImgUpload;
    //private XStateController mXStateController;


    public static void launch(Activity activity) {
        Router.newIntent(activity)
                .to(IntentServiceActivity.class)
                //.putString(PARAM_URL, url)
                //.putString(PARAM_DESC, desc)
                .launch();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_intent_service;
    }

    @Override
    public void initView() {
        tvImgPath = findViewById(R.id.tvImgPath);
        tvImgUpload = findViewById(R.id.tvImgUpload);

       /* mXStateController = findViewById(R.id.mXStateController);
        mXStateController.showContent();*/
    }

    int i = 0;

    private void doUploadAction() {
        tvImgUpload.setText("数据上传中");
        //mXStateController.showLoading();
        String path = "/sdcard/cache/mwee" + DateUtil.getCurrentTime() + "   -->" + (++i);
        IntentForService.startIntentForService(this, path);
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        intentBroadcastReceiver = new IntentBroadcastReceiver();

        tvImgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUploadAction();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ACTION);
        registerReceiver(intentBroadcastReceiver, intentFilter);
    }


    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(intentBroadcastReceiver);
    }

    class IntentBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtil.log("IntentBroadcastReceiver " + this.getClass().getSimpleName() + " action--->" + intent.getAction());
            LogUtil.log("IntentBroadcastReceiver " + this.getClass().getSimpleName() + " path----->" + intent.getStringExtra(IntentForService.EXTRA_IMG_PATH));

            if (intent.getAction() == ACTION) {
                //DO something
                tvImgPath.setText(intent.getStringExtra(IntentForService.EXTRA_IMG_PATH));
                //mXStateController.showContent();
            }
        }
    }


    public static class IntentForService extends IntentService {

        private static final String ACTION_SERVICE = "xdroid.mwee.com.zmstudy.ui.fragment.IntentForService";

        public static final String EXTRA_IMG_PATH = "com.zhy.blogcodes.intentservice.extra.IMG_PATH";

        public static void startIntentForService(Context context, String path) {
            Intent intent = new Intent(context, IntentForService.class);
            intent.setAction(ACTION_SERVICE);
            intent.putExtra(EXTRA_IMG_PATH, path);
            context.startService(intent);
        }


        @Override
        public void onCreate() {
            super.onCreate();
            LogUtil.log("IntentForService " + this.getClass().getSimpleName() + " onCreate() ");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            LogUtil.log("IntentForService " + this.getClass().getSimpleName() + " onDestroy() ");
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            LogUtil.log("IntentForService " + this.getClass().getSimpleName() + " onBind()   intent-->" + intent.getAction());
            return super.onBind(intent);
        }


        @Override
        public void onStart(@Nullable Intent intent, int startId) {
            LogUtil.log("IntentForService " + this.getClass().getSimpleName() + " onStart()  intent-->" + intent.getAction() + "   -->" + startId);
            super.onStart(intent, startId);
        }

        @Override
        public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
            LogUtil.log("IntentForService " + this.getClass().getSimpleName() + " onStartCommand() intent-->" + intent.getAction() + "   -->" + startId);
            return super.onStartCommand(intent, flags, startId);
        }


        @Override
        public boolean onUnbind(Intent intent) {
            LogUtil.log("IntentForService " + this.getClass().getSimpleName() + " onUnbind()  intent-->" + intent.getAction());
            return super.onUnbind(intent);
        }


        public IntentForService(){
            super("IntentForService哈哈");
        }

        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         *
         * @param name Used to name the worker thread, important only for debugging.
         */
        public IntentForService(String name) {
            super(name);
            LogUtil.log("IntentForService " + this.getClass().getSimpleName() + " IntentForService() name-->" + name);
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {

            LogUtil.log("IntentForService " + this.getClass().getSimpleName() + " onHandleIntent() intent-->" + intent);
            LogUtil.log("IntentForService " + this.getClass().getSimpleName() + " onHandleIntent() getAction-->" + intent.getAction());
            LogUtil.log("IntentForService " + this.getClass().getSimpleName() + " onHandleIntent() getStringExtra-->" + intent.getStringExtra(EXTRA_IMG_PATH));

            if (Looper.getMainLooper() == Looper.myLooper()) {
                LogUtil.log("IntentForService Main thread");
            } else {
                LogUtil.log("IntentForService 子 thread");
            }

            String path = intent.getStringExtra(EXTRA_IMG_PATH);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intentBroad = new Intent(IntentServiceActivity.ACTION);
            intentBroad.putExtra(EXTRA_IMG_PATH, path + "   上传成功");
            sendBroadcast(intentBroad);

        }
    }


}
