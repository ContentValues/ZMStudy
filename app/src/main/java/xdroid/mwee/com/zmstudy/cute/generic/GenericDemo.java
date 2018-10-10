package xdroid.mwee.com.zmstudy.cute.generic;

/**
 * Created by zhangmin on 2018/8/16.
 */

import java.util.Random;

/**
 * 测试泛型
 * <p>
 * 1 泛型类
 * 2 泛型方法
 * 3 泛型接口
 */
public class GenericDemo {


    //todo 泛型方法
    public <K, V> GenericClass testGenericClass(K k, V v) {
        GenericClass genericClass = new GenericClass(k, v);
        System.out.println("getK()--->" + genericClass.getK() + "     getV()---->" + genericClass.getV());

        genericClass.next("测试滴滴滴滴");

        System.out.println("genericClass.next2()-->" + genericClass.next2());


        System.out.println(genericClass.transparent(3));

        return genericClass;
    }


    //TODO 泛型类
    class GenericClass<K, V> implements GeneratorInter<String>, GeneratorInterMuch<Integer, String> {
        K k;
        V v;

        public GenericClass(K k, V v) {
            this.k = k;
            this.v = v;
        }

        public K getK() {
            return k;
        }

        public void setK(K k) {
            this.k = k;
        }

        public V getV() {
            return v;
        }

        public void setV(V v) {
            this.v = v;
        }

        @Override
        public void next(String s) {
            System.out.println("next()--->" + s);
        }

        @Override
        public String next2() {
            String[] fruits = new String[]{"Apple", "Banana", "Pear"};
            Random rand = new Random();
            return fruits[rand.nextInt(3)];
        }


        @Override
        public String transparent(Integer integer) {

            return integer + "哈哈哈哈结果";
        }
    }


    //todo 下载图片
    interface GeneratorInter<T> {

        void next(T t);

        T next2();
    }


    interface GeneratorInterMuch<T, G> {

        G transparent(T t);
    }


    /**
     * 有界的类型参数:可能有时候，你会想限制那些被允许传递到一个类型参数的类型种类范围。
     * 例如，一个操作数字的方法可能只希望接受Number或者Number子类的实例。这就是有界类型参数的目的要声明一个有界的类型参数，
     * 首先列出类型参数的名称，后跟extends关键字，最后紧跟它的上界
     * @param x
     * @param y
     * @param z
     * @param <T>
     * @return
     */
    public  <T extends Comparable<T>> T testMaximum(T x, T y, T z) {
        T max = x;
        if (y.compareTo(x) > 0) {
            max = x;
        }
        if(z.compareTo(max)>0){
            max = z;
        }
        return max;
    }


}
