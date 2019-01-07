package xdroid.mwee.com.rxjavatest.thread;

/**
 * Created by zhangmin on 2018/12/3.
 */

public class V3_3 {

    private static ThreadLocal threadLocal = new ThreadLocal();
    //private static Integer integer= 0;

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    threadLocal.set(i);
                   //integer=i;
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(threadLocal.get()+"_"+Thread.currentThread().getName());
                    //System.out.println(integer+"_"+Thread.currentThread().getName());
                }

            }
        },"thread");

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 20; i < 30; i++) {
                    threadLocal.set(i);
                    //integer=i;
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(threadLocal.get()+"_"+Thread.currentThread().getName());
                   //System.out.println(integer+"_"+Thread.currentThread().getName());
                }

            }
        },"thread1");
        thread.start();
        thread1.start();
    }
}
