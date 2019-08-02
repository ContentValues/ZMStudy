package xdroid.mwee.com.rxjavatest.thread;

/**
 * Created by zhangmin on 2018/11/28.
 * <p>
 * 匿名内部类创建线程
 * <p>
 * 匿名内部类创建线程
 */

/**
 * 匿名内部类创建线程
 */

/**
 *
 * (1) 每个对象都有一个锁来控制同步访问，Synchronized关键字可以和对象的锁交互，来实现同步方法或同步块。
 * sleep()方法正在执行的线程主动让出CPU（然后CPU就可以去执行其他任务），
 * 在sleep指定时间后CPU再回到该线程继续往下执行(注意：sleep方法只让出了CPU，而并不会释放同步资源锁！！！)；
 * wait()方法则是指当前线程让自己暂时退让出同步资源锁，以便其他正在等待该资源的线程得到该资源进而运行，
 * 只有调用了notify()方法，之前调用wait()的线程才会解除wait状态，可以去参与竞争同步资源锁，进而得到执行。
 * （注意：notify的作用相当于叫醒睡着的人，而并不会给他分配任务，就是说notify只是让之前调用wait的线程有权利重新参与线程的调度）
 * (2) sleep()方法可以在任何地方使用；wait()方法则只能在同步方法或同步块中使用
 * (3) sleep()是线程线程类（Thread）的方法，调用会暂停此线程指定的时间，但监控依然保持，不会释放对象锁，到时间自动恢复；
 *     wait()是Object的方法，调用会放弃对象锁，进入等待队列，待调用notify()/notifyAll()唤醒指定的线程或者所有线程，
 *     才会进入锁池，不再次获得对象锁才会进入运行状态
 *
 *
 */
public class V1_3Thread {


    public static void main(String[] args) {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hello");
//            }
//        }).start();
        new Thread(new Thread1()).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Thread2()).start();

    }


    static class Thread1 implements Runnable {
        @Override
        public void run() {
            //由于 Thread1和下面Thread2内部run方法要用同一对象作为监视器，如果用this则Thread1和Threa2的this不是同一对象
            //所以用MultiThread.class这个字节码对象，当前虚拟机里引用这个变量时指向的都是同一个对象
            synchronized (V1_3Thread.class) {
                System.out.println("enter thread1 ...");
                System.out.println("thread1 is waiting");

                try {
                    //释放锁有两种方式：(1)程序自然离开监视器的范围，即离开synchronized关键字管辖的代码范围
                    //(2)在synchronized关键字管辖的代码内部调用监视器对象的wait()方法。这里使用wait方法
                    V1_3Thread.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 is going on ...");
                System.out.println("thread1 is being over!");
            }
        }

    }

    static class Thread2 implements Runnable {
        @Override
        public void run() {
            //notify方法并不释放锁，即使thread2调用了下面的sleep方法休息10ms，但thread1仍然不会执行
            //因为thread2没有释放锁，所以Thread1得不到锁而无法执行
            synchronized (V1_3Thread.class) {
                System.out.println("enter thread2 ...");
                System.out.println("thread2 notify other thread can release wait status ...");
                V1_3Thread.class.notify();
                System.out.println("thread2 is sleeping ten millisecond ...");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 is going on ...");
                System.out.println("thread2 is being over!");
            }
        }
    }

}
