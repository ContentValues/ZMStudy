package xdroid.mwee.com.mwdriverbus;

/**
 * Created by zhangmin on 2018/7/16.
 */

public interface IDriveProcessor {
    void broadcast(String var1, Object... var2);

    <T> T call(String var1, Object... var2);
}
