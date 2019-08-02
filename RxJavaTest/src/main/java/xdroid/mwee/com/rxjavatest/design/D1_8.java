package xdroid.mwee.com.rxjavatest.design;

/**
 * Created by zhangmin on 2019/3/19.
 */

public class D1_8 {


    /**
     * public static void show(){}
     * java的静态方法不能被重写。
     静态成员（方法或属性）是类的成员存放在方法区中，类可以直接调用（是属于类的静态成员，当然对象也可以调用，只是说你可以使用而已）；实例成员是对象的成员，存放在堆中，只能被对象调用。
     重写的目的在于根据创造对象的所属类型不同而表现出多态。因为静态方法无需创建对象即可使用。没有对象，重写所需要的“对象所属类型” 这一要素不存在，因此无法被重写
     *
     *
     * public final void take(){}
     * 若希望一个方法的行为在继承期间保持不变，而且不可被覆盖或改写，就可以采取这种做法。
     *
     *
     */
    static  abstract class Super {
        public static int m = 11;

        static {
            System.out.println("执行了super类静态语句块");
        }

        public Super() {
            System.out.println("执行了super类构造方法");
        }


        public  void take(){

        }

//        public final  void show(){
//            System.out.println(" Static Method of Super");
//        }
//
//        public final  void  show(String a){
//            System.out.println(" Static Method of Super");
//        }
    }

    static class Father extends Super {




        public static int m = 22;

        static {
            System.out.println("执行了Father父类静态语句块");
        }


//        @Override
//        public void take() {
//            super.take();
//        }

        //        public static void show(){
//            System.out.println(" Static Method of Father");
//        }

        public Father() {
            System.out.println("执行了Father类构造方法");
        }

    }

    static class Child extends Father {
        public static int m = 33;

        static {
            System.out.println("执行了Child子类静态语句块");
        }

        public Child() {
            System.out.println("执行了Child类构造方法");
        }
    }

    public static void main(String[] args) {

        //Child child = new Child();
        //System.out.println(child.m);
        System.out.println(Child.m);
    }
}
