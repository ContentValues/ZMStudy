package xdroid.mwee.com.mwcommon.base;

import android.os.Bundle;

import xdroid.mwee.com.mwcommon.callback.UiCallback;
import xdroid.mwee.com.mwcommon.callback.UiDelegate;
import xdroid.mwee.com.mwcommon.event.BusFactory;

public abstract class XLazyFragment extends LazyFragment implements UiCallback {

    private UiDelegate uiDelegate;


    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
        if (useEventBus()) {
            BusFactory.getBus().register(this);
        }
        setListener();
        initData(savedInstanceState);
    }


    @Override
    protected void onDestoryLazy() {
        super.onDestoryLazy();
        if (useEventBus()) {
            BusFactory.getBus().unregister(this);
        }
        getUiDelegate().destory();
    }


    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    public void setListener() {

    }

    protected UiDelegate getUiDelegate() {
        if (uiDelegate == null) {
            uiDelegate = UiDelegateBase.create(getContext());
        }
        return uiDelegate;
    }


}
