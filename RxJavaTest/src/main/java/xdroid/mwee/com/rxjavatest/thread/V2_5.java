package xdroid.mwee.com.rxjavatest.thread;

/**
 * Created by zhangmin on 2018/11/28.
 */

public class V2_5 extends Thread {


    @Override
    public void run() {
        super.run();

        for (int i = 1; i <= 20; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (i == 10) {
                this.yield();
            }
        }
    }

    public static void main(String[] args) {
        V2_5 yt1 = new V2_5();
        V2_5 yt2 = new V2_5();
        yt1.start();
        yt2.start();
    }
}
