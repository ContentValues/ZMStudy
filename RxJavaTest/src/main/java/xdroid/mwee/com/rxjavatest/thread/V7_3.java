package xdroid.mwee.com.rxjavatest.thread;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangmin on 2018/12/4.
 */

/**
 *
 */
public class V7_3 {

    public volatile int inc = 0;
    Lock lock = new ReentrantLock();

    public  void increase() {
        inc++;
       /* lock.lock();
        try {
            inc++;
        } finally {
            lock.unlock();
        }*/
    }

    public static void main(String[] args) {
//        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
//        Set<Thread> threads = allStackTraces.keySet();
//        for (Thread thread : threads) {
//
//            System.out.println("name"+thread.getName());
//            System.out.println("isalive"+thread.());
//        }

        System.out.println(Thread.currentThread().getName() + "before 哈哈哈" + Thread.activeCount());
        final V7_3 test = new V7_3();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        test.increase();
                    }
                    System.out.println(Thread.currentThread().getName() + "" + test.inc);
                }
            }.start();
        }
        System.out.println(Thread.currentThread().getName() + "after 哈哈哈" + Thread.activeCount());
        //保证前面的线程都执行完
        while (Thread.activeCount() > 2) {
            Thread.yield();
            //System.out.println(Thread.currentThread().getName() + "" + Thread.activeCount());
        }
        System.out.println("end-->"+test.inc);
    }
    /*public int inc = 0;

    public synchronized void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final V7_3 test = new V7_3();
        for (int i = 0; i < 3; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 100; j++)
                        test.increase();
                }
            }.start();
        }

        while (Thread.activeCount() > 1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }*/
}
