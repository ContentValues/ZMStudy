package xdroid.mwee.com.rxjavatest.thread;

/**
 * Created by zhangmin on 2018/11/29.
 */

/**
 * 编发编程的三个概念
 * 1 原子性 一个操作或者多个操作 全部执行不被干扰 要么就全部不执行
 * 2 可见性 多个线程同时访问一个变量的时候  一个线程改变了变量 其他线程都可见
 * 3 有序性 及程序的执行顺序是按照代码的先后顺序执行
 * <p>
 * <p>
 * 可见性 volatite
 * 原子性 synchronized Lock AtomicInteger
 * <p>
 * <p>
 * 常用场景: 1.状态标记量 2double check
 * 详见：https://www.cnblogs.com/dolphin0520/p/3920373.html  5volatile使用场景
 */
public class V3 {

    //private static boolean stop = false;
    private static volatile boolean stop = false;

    public static void main(String[] args) {

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "  线程A 开始");
                while (!stop) {
                    System.out.println(Thread.currentThread().getName() + " 等待主内存改变");
                }
                System.out.println(Thread.currentThread().getName() + "  线程A结束");
            }
        }, "Thread A");


        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "  线程B开始");
                stop = true;
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "  线程B结束");
            }
        }, "Thread B");

        a.start();
        b.start();

    }

}
