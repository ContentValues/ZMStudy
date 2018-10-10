package xdroid.mwee.com.rxjavatest;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangmin on 2018/9/10.
 */

public class ThreadDemo {

    private int i = 10;
    private static final Object object = new Object();

    public static void main(String[] args) {

        ThreadDemo test = new ThreadDemo();
        MyThread thread0 = test.new MyThread();
        thread0.setName("哈哈哈0");

        MyThread thread1 = test.new MyThread();
        thread1.setName("哈哈哈1");

        MyThread thread2 = test.new MyThread();
        thread2.setName("哈哈哈2");


        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(thread0);
        service.execute(thread1);
        service.execute(thread2);


       /* try {
            System.out.println("线程"+Thread.currentThread().getName()+"等待");
            thread0.join();
            //thread0.wait();
            System.out.println("线程"+Thread.currentThread().getName()+"继续执行");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
*/


        new Thread(new Runnable() {
            @Override
            public void run() {
                InsertData insertData = new InsertData();

                insertData.insert();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                InsertData insertData = new InsertData();

                insertData.insert1();
            }
        }).start();


    }


    class MyThread extends Thread {
        @Override
        public void run() {

            synchronized (object) {
                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "进入睡眠状态");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                System.out.println("线程" + Thread.currentThread().getName() + "睡眠结束--->i:" + --i);
            }

            /*while (i>0){

            }*/
           /* try {
                System.out.println("线程" + Thread.currentThread().getName() + "进入睡眠状态");
                //Thread.currentThread().sleep(1000);
                Thread.yield();
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println("线程" + Thread.currentThread().getName() + "睡眠结束");
                   *//* i--;
                    System.out.println("i:" + i);*//*
            System.out.println("线程" + Thread.currentThread().getName()+"--->i:" + --i);*/


        }
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


        public synchronized static void insert() {
            System.out.println("执行insert");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行insert完毕");
        }

        public synchronized static void insert1() {
            System.out.println("执行insert1");
            System.out.println("执行insert1完毕");
        }
    }
}
