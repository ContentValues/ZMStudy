package xdroid.mwee.com.rxjavatest.thread;


/**
 * 产生死锁的四个必要条件：
 * （1） 互斥条件：一个资源每次只能被一个进程使用。
 * （2） 占有且等待：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
 * （3） 不可强行占有:进程已获得的资源，在末使用完之前，不能强行剥夺。
 * （4） 循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。
 * 这四个条件是死锁的必要条件，只要系统发生死锁，这些条件必然成立，而只要上述条件之
 * 一不满足，就不会发生死锁。
 *
 * 死锁的解除与预防：
 *  的四个必要条件，就可以最大可能地避免、预防和
 * 解除死锁。所以，在系统设计、进程调度等方面注意如何不让这四个必要条件成立，如何确
 * 定资源的合理分配算法，避免进程永久占据系统资源。此外，也要防止进程在处于等待状态
 * 的情况下占用资源。因此，对资源的分配要给予合理的规划
 *
 *
 *
 *
 */
public class V9_1 {


    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    private static class TaskA extends Thread {
        @Override
        public void run() {
            try {
                synchronized (LOCK_A) {
                    System.out.println(Thread.currentThread() + "I hold the LOCK_A");
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread() + "I am wake up and try to get lock");
                    synchronized (LOCK_B) {
                        System.out.println(Thread.currentThread() + "I get the LOCK_B");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static class TaskB extends Thread {
        @Override
        public void run() {
            try {
                synchronized (LOCK_B) {
                    System.out.println(Thread.currentThread() + "I hold the LOCK_B");
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread() + "I am wake up and try to get lock");
                    synchronized (LOCK_A) {
                        System.out.println(Thread.currentThread() + "I get the LOCK_A");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new TaskA().start();
        new TaskB().start();
    }
}
