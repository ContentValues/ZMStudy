package xdroid.mwee.com.mwdriverbus;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import xdroid.mwee.com.mwdriverbus.exception.DriverBusException;

/**
 * Created by zhangmin on 2018/7/16.
 */

public class DriverCalled {
    protected static boolean errorWithException = true;

    DriverCalled() {
    }

    protected static <T> T beCalled(IDriver driver, Method method, String uri, Object... paramList) throws DriverBusException {
        try {
            if(method == null) {
                Log.e("DriverBus", "No method " + uri);
                return null;
            }

            if(paramList != null && paramList.length > 0) {
                return (T) method.invoke(driver, paramList);
            }

            return (T) method.invoke(driver, new Object[0]);
        } catch (IllegalAccessException var5) {
            if(errorWithException) {
                throw new DriverBusException(var5);
            }

            var5.printStackTrace();
        } catch (InvocationTargetException var6) {
            if(errorWithException) {
                throw new DriverBusException(var6.getCause());
            }

            var6.printStackTrace();
        } catch (IllegalArgumentException var7) {
            if(errorWithException) {
                throw new DriverBusException(var7);
            }

            var7.printStackTrace();
        }

        return null;
    }
}
