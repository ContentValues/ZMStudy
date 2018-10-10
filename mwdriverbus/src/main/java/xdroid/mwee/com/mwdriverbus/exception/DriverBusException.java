package xdroid.mwee.com.mwdriverbus.exception;

/**
 * Created by zhangmin on 2018/7/16.
 */

public class DriverBusException extends RuntimeException {

    public DriverBusException() {
    }

    public DriverBusException(String msg) {
        super(msg);
    }

    public DriverBusException(Throwable throwable) {
        super(throwable);
    }
}
