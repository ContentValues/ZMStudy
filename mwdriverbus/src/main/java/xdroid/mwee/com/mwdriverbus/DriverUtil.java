package xdroid.mwee.com.mwdriverbus;

import android.text.TextUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by zhangmin on 2018/7/16.
 */

public class DriverUtil {

    public DriverUtil() {
    }

    public static HashMap<String, Method> indexDriver(IDriver driver) {
        HashMap<String, Method> methodInfo = new HashMap();
        Method[] methods = driver.getClass().getDeclaredMethods();
        Method[] var3 = methods;
        int var4 = methods.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Method method = var3[var5];
            Annotation annotation = method.getAnnotation(DriverMethod.class);
            if(annotation != null) {
                String uri = ((DriverMethod)annotation).uri();
                if(!TextUtils.isEmpty(uri)) {
                    method.setAccessible(true);
                    methodInfo.put(uri, method);
                }
            }
        }

        return methodInfo;
    }
}
