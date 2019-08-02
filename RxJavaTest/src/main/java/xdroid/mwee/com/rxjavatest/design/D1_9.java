package xdroid.mwee.com.rxjavatest.design;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangmin on 2019/3/19.
 */

public class D1_9 {


    /**
     * 生产者
     */

    static class Produce implements Runnable {

        BlockingQueue<String> blockingQueue = null;
        AtomicInteger atomicInteger = new AtomicInteger();

        volatile boolean isRunning = true;

        public Produce(BlockingQueue<String> blockingQueue) {

            this.blockingQueue = blockingQueue;
        }

        public void stop() {
            isRunning = false;
        }


        @Override
        public void run() {


            while (isRunning) {

                int s = atomicInteger.incrementAndGet();

                try {
                    if (blockingQueue.offer(s + "", 2, TimeUnit.SECONDS)) {
                        System.out.println("加入数据成功");
                    } else {
                        System.out.println("加入数据失败");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }


    /**
     * 消费者
     */

    static class Cousumer implements Runnable {

        //AtomicInteger atomicInteger = new AtomicInteger();

        BlockingQueue<String> blockingQueue = null;

        volatile boolean isRunning = true;

        public Cousumer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        public void stop() {
            isRunning = false;
        }


        @Override
        public void run() {

            while (isRunning) {

                try {
                    String data = blockingQueue.poll(2,TimeUnit.SECONDS);
                    if(data != null){
                        System.out.println(data);
                    }else {
                        System.out.println("-------2秒取不到数据 是否可以执行关闭");
                        isRunning = false;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }


        }
    }


}
