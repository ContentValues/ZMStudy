package xdroid.mwee.com.rxjavatest.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangmin on 2018/11/28.
 */

/**
 * Thread.sleep(1000);
 * 1 Thread静态方法
 * 2 睡眠时间为毫秒级
 * 3 不释放所在线程锁
 */
public class V2 {

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");


    public static void main(String[] args) {

        System.out.println(df.format(new Date()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(df.format(new Date()));
    }
}
