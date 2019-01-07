package xdroid.mwee.com.rxjavatest.collection;

import java.util.Observable;

/**
 * Created by zhangmin on 2018/11/22.
 */

public class DataObservable extends Observable {

    private static DataObservable mInstance;

    public static DataObservable getInstance() {
        if (mInstance == null) {
            synchronized (DataObservable.class) {
                if (mInstance == null) {
                    mInstance = new DataObservable();
                }
            }
        }
        return mInstance;
    }


    public void notifyDataChange(DataChange t) {
        setChanged();
        notifyObservers(t);
    }


    public void notifyStringChange(String t) {
        setChanged();
        notifyObservers(t);
    }

}
