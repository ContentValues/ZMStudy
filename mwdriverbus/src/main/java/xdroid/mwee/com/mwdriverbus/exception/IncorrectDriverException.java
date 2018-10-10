package xdroid.mwee.com.mwdriverbus.exception;

/**
 * Created by zhangmin on 2018/7/16.
 */

public class IncorrectDriverException extends DriverBusException  {

    public IncorrectDriverException() {
    }

    public IncorrectDriverException(String msg) {
        super(msg);
    }

    public IncorrectDriverException(Throwable throwable) {
        super(throwable);
    }
}
