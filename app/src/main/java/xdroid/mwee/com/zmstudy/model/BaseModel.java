package xdroid.mwee.com.zmstudy.model;

import com.mwee.android.tools.base.BusinessBean;

public class BaseModel extends BusinessBean {
    protected boolean error;


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
