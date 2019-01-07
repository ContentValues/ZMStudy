package xdroid.mwee.com.rxjavatest.thread;

import java.text.SimpleDateFormat;

/**
 * Created by zhangmin on 2018/11/28.
 */

/**
 * notify
 *  1 Object类中的非静态方法
 *  2 nofity()调用以后 通知同一个线程wait()继续向下执行
 *  3 nofityAll()调用以后  通知所有的线程下wait()继续向下执行
 *  4 释放调用wait对象的线程锁 如果对象不占用锁 直接异常java.lang.IllegalMonitorStateExceptio
 *
 */
public class V2_2 extends Thread {

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");

    private static boolean BOOLEAN = true;


    @Override
    public void run() {
        super.run();

        synchronized (df) {
            if (BOOLEAN) {
                System.out.println("wait start");
                try {
                    df.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait end");

            } else {
                System.out.println("notify start");
                df.notify();
                System.out.println("notify end");
            }
        }
    }


    public static void main(String[] args) {

        V2_2 v2_2 = new V2_2();
        v2_2.start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        V2_2 v2_21 = new V2_2();

        BOOLEAN = false;
        v2_21.start();

    }


}
