package xdroid.mwee.com.zmstudy.business;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import xdroid.mwee.com.mwcommon.callback.ResultAction;

/**
 * Created by zhangmin on 2018/5/11.
 */

public class Basket {

    private BlockingQueue<String> basket = new LinkedBlockingDeque<>();


    private List<String> loadingTasks = new ArrayList<>();


    public void product(String appleProduct) {

        //basket.put(""); //对于put方法，若向队尾添加元素的时候发现队列已经满了会发生阻塞一直等待空间，以加入元素。

        //basket.add("");//add方法在添加元素的时候，若超出了度列的长度会直接抛出异常：

        //System.out.println("loadingTasks product()" + loadingTasks.size());


        if (loadingTasks.size() > 3) {

            basket.offer(appleProduct);//方法在添加元素时，如果发现队列已满无法添加的话，会直接返回false。

            System.out.println(" product()  添加队列中的苹果" + appleProduct);

        } else {

            System.out.println(" product()  添加缓存立即执行的苹果" + appleProduct);
            start(appleProduct);
        }

        // System.out.println("生产苹果：大小" + basket.size());

    }


    private void start(String appleProduct) {

        loadingTasks.add(appleProduct);

        //System.out.println("生产苹果：大小   start()" + loadingTasks.size());


        ConsumeApple consumeApple = new ConsumeApple(appleProduct, new ResultAction<String>() {
            @Override
            public void action(String s) {

                loadingTasks.remove(s);

                System.out.println("已经消费掉的苹果 ConsumeApple" + s);

                String apple = basket.poll();
                if (apple != null) {

                    System.out.println("basket.poll()   队列中重新拿出来的苹果" + apple);

                    product(apple);
                }

            }
        });

        consumeApple.excute();

    }


    public class ConsumeApple {

        public String appleName;

        public ResultAction<String> resultAction;


        public ConsumeApple(String appleName, ResultAction<String> resultAction) {
            this.appleName = appleName;
            this.resultAction = resultAction;

        }

        public void excute() {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(1000);
                        //loadingTasks.remove(appleName);

                        resultAction.action(appleName);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

    }


    public String consume() throws InterruptedException {

        //basket.take();  //若队列为空，发生阻塞，等待有元素。

        //basket.remove();  //若队列为空，抛出NoSuchElementException异常。

        System.out.println("消费苹果：大小" + basket.size());

        String apple = basket.poll();   //若队列为空，返回null。


        if (apple == null) {

            System.out.println("消费者准备消费苹果：完毕!!!");
        }

        return apple;

    }


    public class Product implements Runnable {

        private String name;

        private Basket basket;

        private int productCount = 15;

        public Product(Basket basket, String name) {
            this.name = name;
            this.basket = basket;
        }


        @Override
        public void run() {
            try {
                while (--productCount > 0) {
                    //System.out.println("生产者准备生产苹果：" + name);
                    String creaateAppleName = " @@@new apple -->" + productCount;
                    System.out.println(creaateAppleName);
                    basket.product(creaateAppleName);
                    Thread.sleep(200);

                }
            } catch (InterruptedException e) {
                System.out.println("Product Interrupted");
                e.printStackTrace();
            }

        }
    }


    public class Consume implements Runnable {

        private String name;

        private Basket basket;

        private int consumeCount = 6;

        public Consume(Basket basket, String name) {
            this.name = name;
            this.basket = basket;
        }

        @Override
        public void run() {

            try {

                while (--consumeCount > 0) {

                    System.out.println("消费者准备消费苹果：" + name);
                    basket.consume();
                    System.out.println("!消费者消费苹果完毕：" + name);
                    Thread.sleep(1000);

                }
            } catch (InterruptedException e) {
                System.out.println("Consumer Interrupted");
                e.printStackTrace();
            }

        }
    }


}
