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
//        V2_5 yt1 = new V2_5();
//        V2_5 yt2 = new V2_5();
//        yt1.start();
//        yt2.start();

//       System.out.println(Math.pow(2, 3));
//       System.out.println(Math.sqrt(16));
//        System.out.println( Math.hypot(3, 4));


//        System.out.println( Math.toDegrees(30));
//        System.out.println( Math.toRadians(30));
//
//       System.out.println( Math.sin(Math.toRadians(30)));
//       System.out.println( Math.cos(Math.toRadians(60)));

//       System.out.println( Math.cos(30));
//       System.out.println( Math.cos(Math.toDegrees(30)));


//       System.out.println( Math.acos(1/2));
//       System.out.println( Math.sin(30));
//       System.out.println( Math.sin(30*Math.PI/180));
//       System.out.println( Math.sin(Math.toRadians(30)));


        //System.out.println(Math.asin(0.5));

        System.out.println(Math.toDegrees(Math.asin(Math.sin(0.5))));


        double degrees = 30;
        double radians = Math.toRadians(degrees);

        System.out.format("pi 的值为 %.4f%n", Math.PI);
        System.out.format("toRadians 的值为 %.4f%n", Math.toRadians(degrees));

       System.out.println( Math.toDegrees(Math.asin(Math.sin(radians))));





    }
}
