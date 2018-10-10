package xdroid.mwee.com.rxjavatest;


import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import xdroid.mwee.com.rxjavatest.proxy.Cinema;
import xdroid.mwee.com.rxjavatest.proxy.GuitaiA;
import xdroid.mwee.com.rxjavatest.proxy.MaiMaoTaiWine;
import xdroid.mwee.com.rxjavatest.proxy.RealMovie;
import xdroid.mwee.com.rxjavatest.proxy.SellWine;

public class myClass {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start thread do something");
               /* while (true){
                    System.out.println("start thread do something");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
            }
        }, myClass.class.getSimpleName() + "--Thread");

        thread.start();
        System.out.println("thread.getId()-->" + thread.getId());
        System.out.println("thread.getName()-->" + thread.getName());
        System.out.println("thread.getPriority()-->" + thread.getPriority());

        System.out.println("thread.isAlive()-->" + thread.isAlive());
        System.out.println("thread.isDaemon()-->" + thread.isDaemon());
        System.out.println("thread.isInterrupted()-->" + thread.isInterrupted());

        System.out.println("thread.activeCount()-->" + Thread.activeCount());
        System.out.println("thread.currentThread().getName-->" + Thread.currentThread().getName());
        System.out.println("thread.currentThread().getPriority-->" + Thread.currentThread().getPriority());

      /*  test1();

        test2();*/

        //testFiled();\

        //testProxy1();
        //testProxy2();
    }


    /**
     * 动态代理
     */
    private static void testProxy2() {
        /*MaiMaoTaiWine maiMaoTaiWine = new MaiMaoTaiWine();
        GuitaiA guitaiA = new GuitaiA(maiMaoTaiWine);*/
        /*SellWine dynamicProxy = (SellWine) Proxy.newProxyInstance(maiMaoTaiWine.getClass().getClassLoader(), maiMaoTaiWine.getClass().getInterfaces(), guitaiA);
        dynamicProxy.maijiu();
        dynamicProxy.maiYing();*/

        SellWine sellWine = (SellWine)Proxy.newProxyInstance(MaiMaoTaiWine.class.getClassLoader(), MaiMaoTaiWine.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("销售开始  柜台是： " + MaiMaoTaiWine.class.getSimpleName());
                method.invoke(MaiMaoTaiWine.class.newInstance(), args);
                System.out.println("销售结束");

                return null;
            }
        });
        sellWine.maiYing();
        sellWine.maijiu();



        MaiMaoTaiWine maiMaoTaiWine = new MaiMaoTaiWine();

        SellWine sellWine2 = (SellWine)Proxy.newProxyInstance(maiMaoTaiWine.getClass().getClassLoader(), maiMaoTaiWine.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("销售开始2  柜台是： " + maiMaoTaiWine.getClass().getSimpleName());
                method.invoke(maiMaoTaiWine, args);
                System.out.println("销售结束2 ");

                return null;
            }
        });
        sellWine2.maiYing();
        sellWine2.maijiu();
    }


    /**
     * 静态代理
     */
    private static void testProxy1() {

        RealMovie realMovie = new RealMovie();
        Cinema cinema = new Cinema(realMovie);
        cinema.play();
    }


    private static void test1() {

        System.out.println("testAnnotation  test1()");
        boolean isAnnotation = Test.class.isAnnotationPresent(TestAnnotation.class);
        if (isAnnotation) {
            TestAnnotation testAnnotation = Test.class.getAnnotation(TestAnnotation.class);
            System.out.println("testAnnotation  id()-->" + testAnnotation.id());
            System.out.println("testAnnotation  value()-->" + testAnnotation.value());
        }
    }


    private static void test2() {
        System.out.println("testAnnotation  test2()");
        boolean isAnnotation = Test.class.isAnnotationPresent(TestAnnotation.class);
        if (isAnnotation) {
            for (Annotation annotation : Test.class.getAnnotations()) {
                if (annotation instanceof TestAnnotation) {
                    TestAnnotation testAnnotation = Test.class.getAnnotation(TestAnnotation.class);
                    System.out.println("testAnnotation  id()-->" + testAnnotation.id());
                    System.out.println("testAnnotation  value()-->" + testAnnotation.value());
                }
            }
        }
    }


    private static void testFiled() {

       /* try {
            Field field = Test.class.getDeclaredField("filed");
            field.setAccessible(true);
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation instanceof TestAnnotation) {
                    System.out.println("testFiled1  id()-->" + ((TestAnnotation) annotation).id());
                    System.out.println("testFiled1  value()-->" + ((TestAnnotation) annotation).value());
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
*/

  /*     Test test = new Test();
        try {
            for (Field field : test.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                for (Annotation annotation : field.getAnnotations()) {
                    if (annotation instanceof TestAnnotation) {
                        System.out.println("testFiled2  -->" + field.get(test).toString());
                        System.out.println("testFiled2  id()-->" + ((TestAnnotation) annotation).id());
                        System.out.println("testFiled2  value()-->" + ((TestAnnotation) annotation).value());
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/

      /*  for (Method method : Test.class.getMethods()) {
            TestAnnotation methodAnnotation = method.getAnnotation(TestAnnotation.class);
            if (methodAnnotation != null) {
                System.out.println("testMetbord1  id()-->" + methodAnnotation.id());
                System.out.println("testMetbord1  value()-->" + methodAnnotation.value());
            }
        }


        for (Method method : Test.class.getMethods()) {
            boolean isAnnotation = method.isAnnotationPresent(TestAnnotation.class);
            if (isAnnotation) {
                for (Annotation annotation : method.getAnnotations()) {
                    if (annotation instanceof TestAnnotation) {
                        System.out.println("testMetbord2  id()-->" + ((TestAnnotation) annotation).id());
                        System.out.println("testMetbord2  value()-->" + ((TestAnnotation) annotation).value());
                    }
                }
            }
        }*/


        try {

            System.out.println("Test.class.getName()--->" + Test.class.getName());
            Class object = Class.forName(Test.class.getName());


            /*for (Method method : object.getMethods()) {
                TestAnnotation methodAnnotation = method.getAnnotation(TestAnnotation.class);
                if (methodAnnotation != null) {
                    System.out.println("testMetbord1  id()-->" + methodAnnotation.id());
                    System.out.println("testMetbord1  value()-->" + methodAnnotation.value());
                }
            }*/

            //Test test = new Test();

            for (Method method : object.getMethods()) {
                if (method.isAnnotationPresent(TestAnnotation.class)) {
                    TestAnnotation testAnnotation = method.getAnnotation(TestAnnotation.class);
                    System.out.println("testMetbord1  id()-->" + testAnnotation.id());
                    System.out.println("testMetbord1  value()-->" + testAnnotation.value());
                    method.invoke(object.newInstance(), testAnnotation.value());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
