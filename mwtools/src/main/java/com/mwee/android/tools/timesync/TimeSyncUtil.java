package com.mwee.android.tools.timesync;

import com.mwee.android.tools.LogUtil;

import java.util.concurrent.CountDownLatch;

/**
 * TimeSyncUtil
 * Created by virgil on 16/6/29.
 */
@SuppressWarnings("unused")
public class TimeSyncUtil {
    /**
     * 亚洲的时间服务器,已经按照成功率进行了排序
     */
    private static final String[] NTP_SERVER_1 = {"202.108.6.95", "120.25.108.11", "202.112.29.82", "115.28.122.198", "110.75.186.249", "182.92.12.11"};
    private static final String[] NTP_SERVER_2 = {"1.asia.pool.ntp.org", "3.tw.pool.ntp.org", "1.cn.pool.ntp.org", "1.tw.pool.ntp.org", "2.cn.pool.ntp.org", "3.cn.pool.ntp.org", "0.cn.pool.ntp.org", "cn.pool.ntp.org", "tw.pool.ntp.org", "0.tw.pool.ntp.org", "2.tw.pool.ntp.org", "0.asia.pool.ntp.org"};

    /**
     * 是否成功获取到时间
     */
    public volatile boolean successGetRightTime = false;
    /**
     * 获取时间任务结束
     */
    public volatile boolean finish = false;

    private CountDownLatch countDownLatch = new CountDownLatch(2);

    public TimeSyncUtil() {

    }

    /**
     * 开始校准
     *
     * @return boolean | true: 时间校准成功;false:时间校准失败
     */
    public boolean syncTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish = true;
            }
        }).start();
        start(NTP_SERVER_1);
        start(NTP_SERVER_2);

        return false;
    }

    private void start(final String[] serverList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String temp : serverList) {
                    if (successGetRightTime) {
                        countDownLatch.countDown();
                        break;
                    }
                    if (sync(temp)) {
                        if (LogUtil.SHOW) {
                            LogUtil.log("TimeSyncUtil success [" + temp + "]");
                        }
                        break;
                    } else {
                        if (LogUtil.SHOW) {
                            LogUtil.log("TimeSyncUtil fail [" + temp + "]");
                        }
                    }
                    countDownLatch.countDown();
                }
            }
        }).start();
    }

    /**
     * 10秒钟的时间校准
     *
     * @param server String
     * @return boolean
     */
    private boolean sync(String server) {
        SntpClient client = new SntpClient();
        int timeout = 10000;
        if (client.requestTime(server, timeout)) {
            long time = client.getNtpTime();
            TimeCalibrate.initServerTime(time);
            successGetRightTime = true;
            return true;
        } else {
            return false;

        }
    }
}
