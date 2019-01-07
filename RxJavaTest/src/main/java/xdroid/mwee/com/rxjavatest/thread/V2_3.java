package xdroid.mwee.com.rxjavatest.thread;

import java.text.SimpleDateFormat;

import static java.lang.Thread.sleep;

/**
 * Created by zhangmin on 2018/11/28.
 */

/**
 *
 * 很重要 可以卡网络请求线程 明天试一试
 *
 *
 * 1 Thread类里的非静态方法
 * 2 a.join() 等待a执行完成之后 在执行a.join所在的线程
 * 3 Main线程开启了AB线程 B线程不受影响 只是说主线程在a.join所在的线程执行完成以后再执行
 */
public class V2_3 {

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");

    public static void main(String[] args) {


        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("start A");
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end A");
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("start B");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end B");
            }
        });

        a.start();
        b.start();
        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main end");

    }

}
