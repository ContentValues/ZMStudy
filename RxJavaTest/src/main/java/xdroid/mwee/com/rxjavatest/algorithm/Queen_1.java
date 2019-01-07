package xdroid.mwee.com.rxjavatest.algorithm;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangmin on 2018/12/26.
 */

public class Queen_1 {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();

        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>();

        Producer producer1 = new Producer(blockingQueue);
        Producer producer2 = new Producer(blockingQueue);
        Producer producer3 = new Producer(blockingQueue);

        service.submit(producer1);
        service.submit(producer2);
        service.submit(producer3);

        Consumer consumer = new Consumer(blockingQueue);
        service.submit(consumer);


        // 执行10s
        Thread.sleep(3000);
        producer1.stop();
        producer2.stop();
        producer3.stop();

        Thread.sleep(10000);
        // 退出Executor
        service.shutdown();

    }


    static class Consumer implements Runnable {

        private BlockingQueue<String> queue;

        public Consumer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName() + "开始消费线程");
            Random r = new Random();

            boolean isRunning = true;

            try {
                while (isRunning) {

                    System.out.println("正从队列获取数据...");
                    String data = queue.poll(2, TimeUnit.SECONDS);
                    if (data != null) {
                        System.out.println("拿到数据：" + data);
                        System.out.println("正在消费数据：" + data);
                        //Thread.sleep(r.nextInt(1000));
                    } else {
                        //连续两秒钟没有拿到数据 默认已经消费完毕
                        isRunning = false;
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "结束消费线程");
            }
        }
    }


    static class Producer implements Runnable {

        boolean isRunning = true;
        private static AtomicInteger count = new AtomicInteger();
        private BlockingQueue<String> queue;

        public Producer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "生产线程启动");
            Random random = new Random();
            try {
                while (isRunning) {
                    Thread.sleep(random.nextInt(1000));
                    String data = "data" + count.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + "放入队列..." + data);
                    if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                        System.out.println("放入数据失败：" + data);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "生产线程结束");
            }
        }

        public void stop() {
            isRunning = false;
        }
    }

}
