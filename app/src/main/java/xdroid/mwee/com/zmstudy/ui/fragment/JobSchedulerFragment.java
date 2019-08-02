package xdroid.mwee.com.zmstudy.ui.fragment;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mwee.android.tools.LogUtil;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.zmstudy.R;

/**
 * Created by zhangmin on 2019/3/25.
 */

public class JobSchedulerFragment extends BaseFragment {


    private TextView mResult;

    ComponentName mServiceComponent;


    public static JobSchedulerFragment newInstance() {
        return new JobSchedulerFragment();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_jobscheduler;
    }

    @Override
    public void initView(View v) {
        v.findViewById(R.id.mStartJob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pollServer();
            }
        });
        v.findViewById(R.id.mEndJob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mResult = v.findViewById(R.id.mResult);

        mServiceComponent = new ComponentName(getActivity(), M_JobService.class);
        Intent service = new Intent(getActivity(), M_JobService.class);
        getActivity().startService(service);


    }

    private void pollServer() {
        JobScheduler scheduler = (JobScheduler) getActivity().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        for (int i = 0; i < 3; i++) {
            JobInfo jobInfo = buildJobInfo(i);
            scheduler.schedule(jobInfo);
        }
    }

    private JobInfo buildJobInfo(int mJobId) {

        //开始配置JobInfo
        JobInfo.Builder builder = new JobInfo.Builder(mJobId, mServiceComponent);

        //设置任务的延迟执行时间(单位是毫秒)
        builder.setMinimumLatency(3 * 1000);

        //设置任务最晚的延迟时间。如果到了规定的时间时其他条件还未满足，你的任务也会被启动。
        builder.setOverrideDeadline(10 * 1000);

        //让你这个任务只有在满足指定的网络条件时才会被执行
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);

        //你的任务只有当用户没有在使用该设备且有一段时间没有使用时才会启动该任务。
        builder.setRequiresDeviceIdle(false);

        //告诉你的应用，只有当设备在充电时这个任务才会被执行。
        builder.setRequiresCharging(false);

        // Extras, work duration.
//        PersistableBundle extras = new PersistableBundle();
//        String workDuration = mEt_DurationTime.getText().toString();
//        if (TextUtils.isEmpty(workDuration)) {
//            workDuration = "1";
//        }
//        extras.putLong(WORK_DURATION_KEY, Long.valueOf(workDuration) * 1000);
//        builder.setExtras(extras);
        return builder.build();
    }


}
