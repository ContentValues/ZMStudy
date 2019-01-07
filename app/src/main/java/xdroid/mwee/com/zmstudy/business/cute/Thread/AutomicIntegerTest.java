package xdroid.mwee.com.zmstudy.business.cute.Thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangmin on 2018/9/12.
 */

public class AutomicIntegerTest {

    private AtomicInteger atomic = new AtomicInteger();

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    //private ExecutorService executorService = Executors.newFixedThreadPool(10);
    //private ExecutorService executorService = Executors.newCachedThreadPool();

    private static CountDownLatch latch = new CountDownLatch(2);


    public void test() {

        System.out.println("CountDownLatch:" + latch.getCount());

        for (int i = 0; i <= 100; i++) {
            int scale = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    atomic.addAndGet(scale);
                    latch.countDown();

                    System.out.println("latch.getCount()-->" + latch.getCount() + "异步线程---->key i= " + scale + "    atomic.get()值 = " + atomic.get());
                }
            };
            //executorService.submit(runnable);
            executorService.submit(runnable);
            //latch.countDown();
            //System.out.println("CountDownLatch:" + latch.getCount());
        }

        //System.out.println("CountDownLatch:" + latch.getCount());

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出最终的值
        System.out.println("0~100的和为:" + (atomic.get()));
    }

    class MyRunable implements Runnable {

        int scale = 0;

        public MyRunable(int scale) {
            this.scale = scale;
        }

        @Override
        public void run() {
            atomic.addAndGet(scale);
        }
    }


}
