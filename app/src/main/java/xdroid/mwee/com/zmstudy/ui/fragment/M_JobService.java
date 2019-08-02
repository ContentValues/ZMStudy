package xdroid.mwee.com.zmstudy.ui.fragment;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.mwee.android.tools.LogUtil;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhangmin on 2019/3/25.
 */

public class M_JobService extends JobService {
    /**
     * false: 该系统假设任何任务运行不需要很长时间并且到方法返回时已经完成。
     * true: 该系统假设任务是需要一些时间并且当任务完成时需要调用jobFinished()告知系统。
     */
    @Override
    public boolean onStartJob(JobParameters params) {
        LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onStartJob  " + params.getJobId());


//        if (isNetworkConnected()) {
//            new SimpleDownloadTask().execute(params);
//            return true;
//        } else {
//            Log.i(this.getClass().getSimpleName(), "No connection on job " + params.getJobId() + "; sad face");
//        }
        new SimpleDownloadTask().execute(params);

        return false;
    }

    /**
     * 当收到取消请求时，该方法是系统用来取消挂起的任务的。
     * 如果onStartJob()返回false，则系统会假设没有当前运行的任务，故不会调用该方法。
     */
    @Override
    public boolean onStopJob(JobParameters params) {
        LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " onStopJob " + params.getJobId());
        return false;
    }


    /**
     * 判断网络
     *
     * @return
     */
//    private boolean isNetworkConnected() {
//        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo info = manager.getActiveNetworkInfo();
//        return (info != null && info.isConnected());
//    }

    private class SimpleDownloadTask extends AsyncTask<JobParameters, Void, String> {

        private JobParameters mJobParam;

        @Override
        protected String doInBackground(JobParameters... params) {
            mJobParam = params[0];
            try {
                int len = 50;
                URL url = new URL("http://www.baidu.com");
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.connect();
                int responseCode = conn.getResponseCode();
                Log.i(this.getClass().getSimpleName(), "response code is : " + responseCode);
                InputStream is = conn.getInputStream();
                Reader reader = new InputStreamReader(is, "UTF-8");
                char[] buffer = new char[len];
                reader.read(buffer);
                return new String(buffer);
            } catch (Exception e) {
                return "unable to retrieve web page";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            /**
             * 任务处理完成 需要关闭job
             */
            LogUtil.log("FragmentLifeCircle " + this.getClass().getSimpleName() + " jobFinished " + mJobParam.getJobId());
            jobFinished(mJobParam, false);
            Log.i(this.getClass().getSimpleName(), "获取结果：" + result);
        }
    }
}
