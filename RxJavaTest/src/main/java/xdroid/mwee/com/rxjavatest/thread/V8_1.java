package xdroid.mwee.com.rxjavatest.thread;

/**
 * Created by zhangmin on 2019/1/2.
 */

/**
 * Synchronized是Java中解决并发问题的一种最常用的方法，也是最简单的一种方法。Synchronized的作用主要有三个：
 * （1）确保线程互斥的访问同步代码
 * （2）保证共享变量的修改能够及时可见
 * （3）有效解决重排序问题。从语法上讲，
 * Synchronized总共有三种用法：*
 * （1）修饰普通方法
 * （2）修饰静态方法
 * （3）修饰代码块
 */
public class V8_1 {


    public static void main(String[] args) {


//        int i = 0;
//        for (; ; ) {//相当于死循环
//            System.out.println(Thread.currentThread().getName() + (i++));
//            if(i==20){
//                return;//相当于跳出循环
//            }
//        }


        final V8_1 test = new V8_1();
        //final V8_1 test2 = new V8_1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method2();
            }
        }).start();
    }


    /**
     * 每个对象有一个监视器锁（monitor）。当monitor被占用时就会处于锁定状态，线程执行monitorenter指令时尝试获取monitor的所有权，过程如下：
     * 1、如果monitor的进入数为0，则该线程进入monitor，然后将进入数设置为1，该线程即为monitor的所有者。
     * 2、如果线程已经占有该monitor，只是重新进入，则进入monitor的进入数加1.
     * 3.如果其他线程已经占用了monitor，则该线程进入阻塞状态，直到monitor的进入数为0，再重新尝试获取monitor的所有权
     */
    public void method1() {
        System.out.println(this.hashCode());
        synchronized (this) {
            System.out.println("Method 1 start");
            try {
                System.out.println("Method 1 execute");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Method 1 end");
        }
    }

    public void method2() {
        System.out.println(this.hashCode());
        synchronized (this) {
            System.out.println("Method 2 start");
            try {
                System.out.println("Method 2 execute");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Method 2 end");
        }
    }


    /**
     * 方法的同步并没有通过指令monitorenter和monitorexit来完成（理论上其实也可以通过这两条指令来实现），不过相对于普通方法，其常量池中多了ACC_SYNCHRONIZED标示符。JVM就
     * 根据该标示符来实现方法的同步的：当方法调用时，调用指令将会检查方法的 ACC_SYNCHRONIZED 访问标志是否被设置，如果设置了，执行线程将先获取monitor，获取成功之后才能执行方
     * 法体，方法执行完后再释放monitor。在方法执行期间，其他任何线程都无法再获得同一个monitor对象。 其实本质上没有区别，只是方法的同步是一种隐式的方式来实现，无需通过字节码来
     * 完成
     */
//    public synchronized void method1() {
//        System.out.println("Method 1 start");
//        try {
//            System.out.println("Method 1 execute");
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Method 1 end");
//    }
//
//    public synchronized void method2() {
//        System.out.println("Method 2 start");
//        try {
//            System.out.println("Method 2 execute");
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Method 2 end");
//    }


//    public void method1() {
//        System.out.println("Method 1 start");
//        try {
//            synchronized (this) {
//                System.out.println("Method 1 execute");
//                Thread.sleep(3000);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Method 1 end");
//    }
//
//    public void method2() {
//        System.out.println("Method 2 start");
//        try {
//            synchronized (this) {
//                System.out.println("Method 2 execute");
//                Thread.sleep(1000);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Method 2 end");
//    }

}
