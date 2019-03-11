package xdroid.mwee.com.rxjavatest.proxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmin on 2019/3/10.
 */

/**
 *
 *  extends 可用于的返回类型限定，不能用于参数类型限定。
 *  super 可用于参数类型限定，不能用于返回类型限定。
 *
 */

public class T1 {

    public static void main(String[] args) {

        //具有任何Fruit超类型的列表
        List<? super Frulit> list = new ArrayList();


        //具有任何从Fruit继承类型的列表
        //List<? extends Frulit> list = new ArrayList();




        Apple apple = new Apple();
        RedApple redApple = new RedApple();
        Banala banala = new Banala();

        list.add(apple);
        list.add(redApple);
        list.add(banala);


        Apple apple1 = (Apple) list.get(0);
        apple1.speak();


    }


    //水果
    static  class Frulit {

        public void speak() {
            System.out.println("Frulit");
        }
    }

    //苹果
    static class Apple extends Frulit {
        public void speak() {
            System.out.println("Apple");
        }
    }


    //红苹果
    static class RedApple extends Apple {
        public void speak() {
            System.out.println("RedApple");
        }
    }


    //香蕉
    static class Banala extends Frulit {
        public void speak() {
            System.out.println("Banala");
        }
    }

}
