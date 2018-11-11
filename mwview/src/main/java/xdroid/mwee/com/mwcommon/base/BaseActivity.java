package xdroid.mwee.com.mwcommon.base;
import android.os.Bundle;
import android.support.annotation.Nullable;


public abstract class BaseActivity extends LifecycleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData(savedInstanceState);
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public  void initData(Bundle savedInstanceState){}

}
