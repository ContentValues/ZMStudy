package xdroid.mwee.com.mwdriverbus.exception;

/**
 * Created by zhangmin on 2018/7/16.
 */

public class NoDriverException extends DriverBusException  {

    public NoDriverException() {
        super("No driver found");
    }

    public NoDriverException(String msg) {
        super(msg);
    }
}
