package xdroid.mwee.com.mwcommon.callback;

import android.os.Bundle;


public interface UiCallback {


    void initData(Bundle savedInstanceState);

    void setListener();

    int getLayoutId();

    boolean useEventBus();
}
