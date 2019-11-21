package xdroid.mwee.com.rxjavatest;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class myClass {

    public static void main(String[] args) {

        Hello vvv = new Hello();
        vvv.aa();

//        test1();
//
//        test2();

        testFiled();
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

//        try {
//            Field field = Test.class.getDeclaredField("filed");
//            field.setAccessible(true);
//            for (Annotation annotation : field.getAnnotations()) {
//                if (annotation instanceof TestAnnotation) {
//                    System.out.println("testFiled1  id()-->" + ((TestAnnotation) annotation).id());
//                    System.out.println("testFiled1  value()-->" + ((TestAnnotation) annotation).value());
//                }
//            }
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }


       Test test = new Test();
        try {
            for (Field field : test.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                for (Annotation annotation : field.getAnnotations()) {
                    if (annotation instanceof TestAnnotation) {
//                        System.out.println("testFiled2  -->" + field.get(test).toString());
                        System.out.println("testFiled2  id()-->" + ((TestAnnotation) annotation).id());
                        System.out.println("testFiled2  value()-->" + ((TestAnnotation) annotation).value());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Method method : Test.class.getMethods()) {
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
        }


        try {

//            System.out.println("Test.class.getName()--->" + Test.class.getName());
//            Class object = Class.forName(Test.class.getName());


            /*for (Method method : object.getMethods()) {
                TestAnnotation methodAnnotation = method.getAnnotation(TestAnnotation.class);
                if (methodAnnotation != null) {
                    System.out.println("testMetbord1  id()-->" + methodAnnotation.id());
                    System.out.println("testMetbord1  value()-->" + methodAnnotation.value());
                }
            }*/

            //Test test = new Test();

            /*for (Method method : object.getMethods()) {
                if (method.isAnnotationPresent(TestAnnotation.class)) {
                    TestAnnotation testAnnotation = method.getAnnotation(TestAnnotation.class);
                    System.out.println("testMetbord1  id()-->" + testAnnotation.id());
                    System.out.println("testMetbord1  value()-->" + testAnnotation.value());
                    method.invoke(object.newInstance(), testAnnotation.value());
                }
            }
*/
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
