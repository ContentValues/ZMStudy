package xdroid.mwee.com.rxjavatest.proxy;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhangmin on 2018/12/5.
 */

public class ProxyD {


    public static void main(String[] args) {


        Person zhangsan = new Student("张三");
        InvocationHandler invocationHandler = new ProxyD.StudentProxy<>(zhangsan);

        Person person = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, invocationHandler);

        System.out.println("准备工作 start");
        //person.pushMoney(50 + "");
        person.sendMoney(100+"","201811010046");
        System.out.println("准备工作 end");


    }


    interface Person {

        void pushMoney(@TF("money") String money);

        void sendMoney(@TF("money") String money, @TF("order_id") String order_id);

    }


    static class Student implements Person {

        public String name;

        public Student(String name) {
            this.name = name;
        }

        @Override
        public void pushMoney(String money) {
            System.out.println(name + "缴纳了学费" + money);
        }

        @Override
        public void sendMoney(String money, String order_id) {
            System.out.println(name + "缴纳了学费" + money + "  学费订单号为-->" + order_id);
        }
    }


    static class StudentProxy<T> implements InvocationHandler {

        private T target;

        public StudentProxy(T target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

            System.out.println("调用代理方法 start");
            //Object result = method.invoke(target, "10000");
            //得到方法参数的注解
            Annotation[][] annotations = method.getParameterAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                Annotation[] temp = annotations[i];
                for (Annotation annotation : temp) {
                    if (annotation instanceof TF) {
                        TF tf = (TF) annotation;
                        System.out.println("方法参数注解 value-->" + tf.value() + "  location--" + i+"   值为"+objects[i]);
                    }
                }

            }


            System.out.println("调用代理方法  end");
            return null;
        }
    }


    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
    public @interface TF {
        String value();
    }


}
