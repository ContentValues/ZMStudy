package xdroid.mwee.com.rxjavatest.thread;

/**
 * Created by zhangmin on 2018/11/28.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * wait()
 * <p>
 * 1 Object类的非静态方法
 * 2 释放调用wait对象的线程锁 如果对象不占用锁 直接异常java.lang.IllegalMonitorStateException
 * 3 wait(100) 毫秒级
 * 4 wait() 被调用 会一致等待被唤醒 notify nofityAll
 */
public class V2_1 {

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");

    public static void main(String[] args) {

        try {
            new V2_1().wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //V2_1.class 有锁，但是v2_1没有锁所以也会报IllegalMonitorStateException
//        synchronized (V2_1.class) {
//
//            System.out.println(Thread.currentThread().getName());
//            try {
//                V2_1 v2_1 = new V2_1();
//                v2_1.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        //df.notify();

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("start a "+Thread.currentThread().getName() + "" + df.format(new Date()));

                synchronized (df) {
                    try {
                        df.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("end a "+Thread.currentThread().getName() + "" + df.format(new Date()));
            }
        });

        a.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        df.notifyAll();
        synchronized (df){
            df.notifyAll();
        }

        System.out.println("end main "+Thread.currentThread().getName() + "" + df.format(new Date()));
       /* V2_1 v2_1 = new V2_1();

        synchronized (v2_1) {
            System.out.println(Thread.currentThread().getName() + "" + df.format(new Date()));
            try {
                v2_1.wait();
//                v2_1.wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + "" + df.format(new Date()));*/


    }
}
