package xdroid.mwee.com.zmstudy.business.cute.rxjava;


import java.util.Observable;
import java.util.Observer;

/**
 * Created by zhangmin on 2018/8/30.
 */

public class CustomerObserver2 implements Observer {
    @Override
    public void update(Observable o, Object arg) {

        /*if (o instanceof CustomerObservable) {
            System.out.println("CustomerObserver2 模拟点击事件---> true");
        }*/

        Integer aa = (Integer) arg;
        System.out.println("CustomerObserver2 模拟点击事件--->" + aa);


    }
}
