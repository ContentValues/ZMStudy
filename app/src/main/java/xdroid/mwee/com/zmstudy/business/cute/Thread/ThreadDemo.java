package xdroid.mwee.com.zmstudy.business.cute.Thread;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangmin on 2018/9/10.
 */

public class ThreadDemo {

    private final static Object object = new Object();

    public static void main() {
        testThread0();
    }


    public static void testThread0() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("start thread do something");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, ThreadDemo.class.getSimpleName() + "--Thread");
        thread.start();
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(thread);


        ExecutorService service = Executors.newSingleThreadExecutor();
        //ExecutorService service = Executors.newCachedThreadPool();
        //ExecutorService service = Executors.newFixedThreadPool(4);
        /*service.execute(thread0);
        service.execute(thread1);
        service.execute(thread2);*/

    }


    public static void testThread1() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("start thread do something");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, ThreadDemo.class.getSimpleName() + "--Thread");

        thread.start();
       /* System.out.println("thread.getId()-->" + thread.getId());
        System.out.println("thread.getName()-->" + thread.getName());
        System.out.println("thread.getPriority()-->" + thread.getPriority());

        System.out.println("thread.isAlive()-->" + thread.isAlive());
        System.out.println("thread.isDaemon()-->" + thread.isDaemon());
        System.out.println("thread.isInterrupted()-->" + thread.isInterrupted());

        System.out.println("thread.activeCount()-->" + Thread.activeCount());
        System.out.println("thread.currentThread().getName-->" + Thread.currentThread().getName());
        System.out.println("thread.currentThread().getPriority-->" + Thread.currentThread().getPriority());*/

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(thread);

    }


    private static CountDownLatch latch = new CountDownLatch(10);
    ExecutorService executorService = Executors.newFixedThreadPool(3);


    public void testThread3() {

       InsertData insertData = new InsertData();

         /*for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    insertData.insert(Thread.currentThread());
                    latch.countDown();
                    System.out.println("latch.countDown()--->" + latch.getCount());
                }
            });
            executorService.submit(thread);
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 数据源--->" + Arrays.toString(insertData.integers.toArray()));
*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                insertData.insert();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                insertData.insert1();
            }
        }).start();
    }


    static class InsertData {

        public ArrayList<Integer> integers = new ArrayList<>();

        public synchronized void insert(Thread thread) {
            for (int i = 0; i < 100; i++) {
                if (!integers.contains(i)) {
                    System.out.println(thread.getName() + "在插入数据" + i);
                    integers.add(i);
                }
            }
        }


        public synchronized void insert(){
            System.out.println("执行insert");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行insert完毕");
        }

        public synchronized  void insert1() {
            System.out.println("执行insert1");
            System.out.println("执行insert1完毕");
        }
    }


}
