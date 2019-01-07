package xdroid.mwee.com.rxjavatest.collection;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zhangmin on 2019/1/3.
 */

public class Array_T {

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> array_1 = new ArrayBlockingQueue<Integer>(16);
        //Array_1<Integer> array_1 = new Array_1<Integer>(16);
        ThreadRunnable runnable = new ThreadRunnable(array_1);
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("数据-->" + Arrays.toString(array_1.toArray()));
        System.out.println("数据大小-->" + array_1.size());
        while (!array_1.isEmpty()) {
            System.out.println("数据-->" + array_1.poll());
        }

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                while (true) {
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    int val = new Random().nextInt(10);
//                    array_1.offer(val);
//
//                    System.out.println("数据take()之前-->" + array_1.size());
//                }
//            }
//        }).start();
//
//        try {
//            //take()取不到数据 会一致阻塞 while (count == 0) notEmpty.await();
//            Integer val = array_1.take();
//            while ( val!= null) {
//                System.out.println("数据take()-->" + val);
//                System.out.println("数据take()之后-->" + array_1.size());
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    static class ThreadRunnable implements Runnable {
        ArrayBlockingQueue<Integer> array_1;

        public ThreadRunnable(ArrayBlockingQueue<Integer> array_1) {
            this.array_1 = array_1;
        }

        @Override
        public void run() {
            for (int i = 0; i < 2000; i++) {
                array_1.offer(i);
            }
        }
    }

}
