package xdroid.mwee.com.mwcommon.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import xdroid.mwee.com.mwcommon.callback.UiCallback;
import xdroid.mwee.com.mwcommon.callback.UiDelegate;
import xdroid.mwee.com.mwcommon.event.BusFactory;


public abstract class XActivity extends BaseActivity implements UiCallback {
    protected Activity context;
    protected UiDelegate uiDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;

        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
        setListener();
        initData(savedInstanceState);
    }


    protected UiDelegate getUiDelegate() {
        if (uiDelegate == null) {
            uiDelegate = UiDelegateBase.create(this);
        }
        return uiDelegate;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (useEventBus()) {
            BusFactory.getBus().register(this);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        getUiDelegate().resume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        getUiDelegate().pause();
    }


    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    public void setListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusFactory.getBus().unregister(this);
        getUiDelegate().destory();
    }
}
