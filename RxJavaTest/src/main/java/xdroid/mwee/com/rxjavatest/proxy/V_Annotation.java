package xdroid.mwee.com.rxjavatest.proxy;

/**
 * Created by zhangmin on 2018/12/5.
 */

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 注解
 */
public class V_Annotation {

    public static void main(String[] args) {


        /**
         * 类注解
         */
        /*for (Annotation annotation : Student.class.getAnnotations()) {
            Annotation_Inf inf = (Annotation_Inf) annotation;
            System.out.println("类 id-->" + inf.id());
            System.out.println("类 value-->" + inf.value());
        }

        Annotation_Inf inf2 = Student.class.getDeclaredAnnotation(Annotation_Inf.class);
        System.out.println("类 id-->" + inf2.id());
        System.out.println("类 value-->" + inf2.value());*/


        /**
         * 域注解
         */
        /*Class<Student> student = Student.class;
        for (Field field : student.getDeclaredFields()) {
            field.setAccessible(true);
            Annotation_Inf inf = field.getAnnotation(Annotation_Inf.class);
            System.out.println("域 id-->" + inf.id());
            System.out.println("域 value-->" + inf.value());
        }*/

        /**
         * 方法注解
         */
        /*for (Method method : student.getClass().getDeclaredMethods()) {
            for (Annotation annotation : method.getAnnotations()) {
                Annotation_Inf inf = (Annotation_Inf) annotation;
                System.out.println("方法 id-->" + inf.id());
                System.out.println("方法 value-->" + inf.value());
            }
        }

        for (Method method : student.getClass().getDeclaredMethods()) {
            Annotation_Inf info = method.getAnnotation(Annotation_Inf.class);
            System.out.println("方法 id-->" + info.id());
            System.out.println("方法 value-->" + info.value());
        }*/

        /**
         * 方法参数注解
         */


        Class<Student> student = Student.class;

        for (Method method : student.getDeclaredMethods()) {

            Annotation[][] annotations = method.getParameterAnnotations();

            for(int i=0;i<annotations.length;i++){
                Annotation[] annotation = annotations[i];

                System.out.println("annotation size()-->" + annotation.length);
                for (Annotation item : annotation) {
                    if (item instanceof SF) {
                        System.out.println("方法参数注解 value-->" + ((SF) item).value()+"---location-->"+i);
                    }
                }
            }
           /* for (Annotation[] annotation : annotations) {

                for (Annotation item : annotation) {

                    if (item instanceof SF) {
                        System.out.println("方法参数注解 value-->" + ((SF) item).value());
                    }
                }

            }*/
        }


    }


    @Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Annotation_Inf {

        int id() default -1;

        String value() default "hello";
    }


    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
    public @interface SF {
        String value();
    }


    //@Annotation_Inf(id = 11, value = "Student类注解")
    static class Student {

        @Annotation_Inf(value = "张敏private域注解")
        public String name;

       /* @Annotation_Inf(id = 18)
        public int age;*/


        /*@Annotation_Inf(id = 14, value = "Speaking")
        public void speak() {
            System.out.println("张敏说话");
        }

        @Annotation_Inf(id = 15, value = "making")
        public void make(@SF("order_id") String order_id) {
            System.out.println("涛涛制造");
        }*/

        public void make(@SF("order_id") String order_id,@SF("order_id2") String order_id2,@SF("order_id3") String order_id3) {
            System.out.println("方法参数注解-->" + order_id);
        }

        public void make1(@SF("100") String order_id) {
            System.out.println("方法参数注解-->" + order_id);
        }



    }

}
