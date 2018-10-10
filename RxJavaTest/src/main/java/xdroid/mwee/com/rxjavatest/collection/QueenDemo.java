package xdroid.mwee.com.rxjavatest.collection;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangmin on 2018/9/25.
 */

public class QueenDemo {
    public static void main(String[] args) throws InterruptedException {

        /* 声明一个容量为10的缓存队列 */
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);


        ExecutorService executorService = Executors.newCachedThreadPool();


        Product product1 = new Product(queue);
        Product product2 = new Product(queue);
        Product product3 = new Product(queue);

        Consumer consumer = new Consumer(queue);

        executorService.execute(product1);
        executorService.execute(product2);
        executorService.execute(product3);

        Thread.sleep(2 * 1000);

        System.out.println(Thread.currentThread().getName() + "队列中数据的大小为：" + queue.size());

        executorService.execute(consumer);

        // 执行10s
        Thread.sleep(2 * 1000);
        product1.stop();
        product2.stop();
        product3.stop();

        Thread.sleep(2000);
        // 退出Executor
        executorService.shutdown();


    }


    static class Product implements Runnable {

        private LinkedBlockingQueue<String> queue;
        private boolean isRunning = true;
        private static AtomicInteger count = new AtomicInteger();
        private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

        //int count = 0;

        public Product(LinkedBlockingQueue queue) {
            this.queue = queue;
        }

        public void stop() {
            isRunning = false;
        }

        @Override
        public void run() {

            String data = null;
            Random r = new Random();
            System.out.println(Thread.currentThread().getName() + "生产者启动生产者线程！");
            try {
                while (isRunning) {
                    System.out.println(Thread.currentThread().getName() + "生产者正在生产数据...");
                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                    data = "data:" + count.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + "生产者将数据：" + data + "放入队列...");
                    if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                        System.out.println(Thread.currentThread().getName() + "生产者放入数据失败：" + data);
                    }

                    System.out.println(Thread.currentThread().getName() + "生产者生产[" + data +
                            "]以后队列中数据的大小为：" + queue.size());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + "生产者线程发成异常！");
                Thread.currentThread().interrupt();
            } finally {
                System.out.println(Thread.currentThread().getName() + "生产者退出线程！");
            }

        }
    }


    static class Consumer implements Runnable {

        private boolean isRunning = true;
        private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;
        private LinkedBlockingQueue<String> queue;

        public Consumer(LinkedBlockingQueue queue) {
            this.queue = queue;
        }

        public void stop() {
            isRunning = false;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "消费者启动线程！");
            Random r = new Random();
            try {
                while (isRunning) {
                    System.out.println(Thread.currentThread().getName() + "消费者正从队列获取数据...");
                    String data = queue.poll(2, TimeUnit.SECONDS);
                    if (null != data) {
                        System.out.println(Thread.currentThread().getName() + "消费者拿到数据：" + data);
                        System.out.println(Thread.currentThread().getName() + "消费者正在消费数据：" + data);
                        Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                    } else {
                        // 超过2s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
                        isRunning = false;
                    }
                    System.out.println(Thread.currentThread().getName() + "消费者消费[" + data +
                            "]以后队列中数据的大小为：" + queue.size());

                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + "消费者线程发成异常！");
                Thread.currentThread().interrupt();
            } finally {
                System.out.println(Thread.currentThread().getName() + "消费者退出线程！");
            }

        }
    }


}
