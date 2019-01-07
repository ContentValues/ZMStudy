package xdroid.mwee.com.zmstudy.business.cute.rxjava;

import java.util.Observable;

/**
 * Created by zhangmin on 2018/8/30.
 */

public class CustomerObservable extends Observable {


    public void setData(int position){
        setChanged();
        notifyObservers(position);
    }
}
