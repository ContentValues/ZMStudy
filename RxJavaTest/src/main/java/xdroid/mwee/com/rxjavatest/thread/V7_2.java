package xdroid.mwee.com.rxjavatest.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangmin on 2018/12/4.
 */

/**
 *
 * CountDownLatch 这个类能够使一个线程等待其他线程完成各自的工作后再执行。
 * 通过计数器完成工作的 指定初始值没完成一个任务 初始值减1 当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
 *
 *
 */
public class V7_2 {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + "---main---start");
        CountDownLatch latch = new CountDownLatch(10);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(new WorkRunnable(latch,i));
        }


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                latch.countDown();
//                System.out.println(Thread.currentThread().getName() + "  latch.getCount()-->" + latch.getCount() + "  end ");
//            }
//        }).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "---main---end");

    }

    static class WorkRunnable implements Runnable {

        CountDownLatch latch;
        int i = 0;

        public WorkRunnable(CountDownLatch latch, int i) {

            this.latch = latch;
            this.i = i;
        }

        @Override
        public void run() {
            doWork(i);

            //System.out.println(Thread.currentThread().getName() + "  latch.getCount()-->" + latch.getCount() + "  start ");
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + "  latch.getCount()-->" + latch.getCount() + "  end run()");
        }


        private void doWork(int i) {

            System.out.println(Thread.currentThread().getName() + " doWork i-->" + i);
        }

    }
}
