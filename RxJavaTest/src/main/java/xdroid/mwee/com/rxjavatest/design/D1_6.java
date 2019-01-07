package xdroid.mwee.com.rxjavatest.design;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zhangmin on 2019/1/1.
 */

public class D1_6 {
    public static void main(String[] args) {


//        Student student = new Student();
        //Class<Student> studentClass = (Class<Student>) student.getClass();
//        Class studentClass = student.getClass();

        //Class studentClass = Student.class;
        Class<Student> studentClass =  Student.class;

        //Class.forName("xdroid.mwee.com.rxjavatest.design.D1_6.Student");

        System.out.println("getDeclaredMethods获取的方法：");
        for (Method method : studentClass.getDeclaredMethods()) {
            System.out.println(method);
        }

        System.out.println("getMethods：");
        for (Method method : studentClass.getMethods()) {
            System.out.println(method);
        }


        //Class<Student> studentClass = (Class<Student>) student.getClass();
        try {
            Student student =  studentClass.newInstance();
            System.out.println("age"+student.age);
            System.out.println("name"+student.name);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        try {
            try {
                Student student2 =  studentClass.getConstructor(int.class,String.class).newInstance(12,"桃谷绘里香");
                System.out.println("age"+student2.age);
                System.out.println("name"+student2.name);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    static class Student {

        public int age = 18;
        public String name = "波多野结衣";

        public Student() {

        }

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
