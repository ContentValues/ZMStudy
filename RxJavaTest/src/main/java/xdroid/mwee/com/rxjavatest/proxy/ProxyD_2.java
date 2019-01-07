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

public class ProxyD_2 {


    public static void main(String[] args) {


        MConProxy.c(Person.class).sendMoney("100", "201811260014");
    }


    static class MConProxy {

        @SuppressWarnings("unchecked")
        public static <T> T c(Class<T> base) {
            return (T) Proxy.newProxyInstance(base.getClassLoader(), new Class<?>[]{base}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
                    System.out.println("调用代理方法 start");
                    //得到方法参数的注解
                    Annotation[][] annotations = method.getParameterAnnotations();
                    for (int i = 0; i < annotations.length; i++) {
                        Annotation[] temp = annotations[i];
                        for (Annotation annotation : temp) {
                            if (annotation instanceof TF) {
                                TF tf = (TF) annotation;
                                System.out.println("方法参数注解 value-->" + tf.value() + "  location--" + i + "   值为" + objects[i]);
                            }
                        }
                    }
                    System.out.println("方法参数注解  end");
                    return null;
                }
            });
        }


    }


    interface Person {
        void sendMoney(@TF("money") String money, @TF("order_id") String order_id);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
    public @interface TF {
        String value();
    }


}
