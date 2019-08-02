package xdroid.mwee.com.rxjavatest;

public class OutClass {

    public static String name = "张欣";

    public int age = 18;


    /**
     * 静态内部类
     */
    static class InnerClass{

        public static String _name2 = "chenssy_inner";

        public void display(){
            /*
             * 静态内部类只能访问外围类的静态成员变量和方法
             * 不能访问外围类的非静态成员变量和方法
             */
            //System.out.println("OutClass name :" + age);
            System.out.println("OutClass name :" + name);
        }

    }


    /**
     * 成员类部类
     */
    class InnerClass2{


        /**
         * 非静态内部类中不能存在静态成员
         */
        //public static String _name2 = "chenssy_inner";
        public  String _name2 = "chenssy_inner";

        public void display(){
            /*
             * 静态内部类只能访问外围类的静态成员变量和方法
             * 不能访问外围类的非静态成员变量和方法
             */
            System.out.println("OutClass name :" + name);
        }
    }


    /**
     * new 父类构造器（参数列表）|实现接口（）
     *     {
     *      //匿名内部类的类体部分
     *     }
     *
     *
     *     public abstract class Bird {
     *     private String name;
     *
     *     public String getName() {
     *         return name;
     *     }
     *
     *     public void setName(String name) {
     *         this.name = name;
     *     }
     *
     *     public abstract int fly();
     * }
     *
     *
     *
     * public class Test {
     *
     *     public void test(Bird bird){
     *         System.out.println(bird.getName() + "能够飞 " + bird.fly() + "米");
     *     }
     *
     *     public static void main(String[] args) {
     *         Test test = new Test();
     *
     *         //匿名内部类
     *         test.test(new Bird() {
     *
     *             public int fly() {
     *                 return 10000;
     *             }
     *
     *             public String getName() {
     *                 return "大雁";
     *             }
     *         });
     *     }
     * }
     */

}
