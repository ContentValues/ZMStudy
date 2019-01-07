package xdroid.mwee.com.rxjavatest.proxy;

/**
 * Created by zhangmin on 2018/12/5.
 */

/**
 * 静态代理
 * 由程序员创建或特定工具自动生成源代码，也就是在编译时就已经将接口，被代理类，代理类等确定下来。在程序运行之前，代理类的.class文件就已经生成。
 * 代理模式就是在访问实际对象时引入一定程度的间接性
 */
public class ProxyStatic {

    public static void main(String[] args){

        Student student = new Student("张三",100);

        //student.pushMoney();
        StudentProxy studentProxy = new StudentProxy(student);

        studentProxy.pushMoney();


    }


    interface Person {

        void pushMoney();
    }


    static class Student implements Person {

        public String name;

        public int value;//学费

        public Student(String name, int value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public void pushMoney() {
            System.out.println(name + "缴纳了学费" + value);
        }
    }


    static class StudentProxy implements Person {

        public Student student;

        public StudentProxy(Student student) {
            this.student = student;
        }

        @Override
        public void pushMoney() {
            student.pushMoney();
        }
    }

}
