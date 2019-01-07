package xdroid.mwee.com.rxjavatest.thread;

/**
 * Created by zhangmin on 2018/11/28.
 */

/**
 * Interrupt()
 *  1 Thread的非静态方法
 *  2 wait join sleep 一直检测线程的中断位 如果中断为true报InterruptedException 如果false不处理
 *  3
 *
 */
public class V2_4 implements Runnable {

    public static Object lock = new Object();

    @Override
    public void run() {

        System.out.println(Thread.currentThread() + " -->" +"start");

        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread() + " -->" + Thread.currentThread().isInterrupted());//获取中断标志位的结果
                Thread.currentThread().interrupt(); //设置中断标志位为true
                System.out.println(Thread.currentThread() + " -->" + Thread.currentThread().isInterrupted());//获取中断标志位的结果
                e.printStackTrace();
            }
        }


        System.out.println(Thread.currentThread() + " -->" + "end");


    }

    public static void main(String[] args) {

        Thread thread = new Thread(new V2_4());
        thread.start();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();//设置中断标志位为true

        System.out.println(Thread.currentThread() + " 滴滴-->" + "end");

    }
}
