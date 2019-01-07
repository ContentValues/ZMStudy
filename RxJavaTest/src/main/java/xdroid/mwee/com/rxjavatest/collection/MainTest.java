package xdroid.mwee.com.rxjavatest.collection;

import java.util.Observable;

/**
 * Created by zhangmin on 2018/11/22.
 */

public class MainTest {


    static DataObserver observer1 = new DataObserver() {
        @Override
        public void update(Observable observable, Object o) {
            super.update(observable, o);

            DataChange mData = (DataChange) o;
            System.out.println("update-->" + mData.data);
        }
    };


    public static void main(String[] args) {

        DataChange dataChange = new DataChange();
        dataChange.data = "张敏好帅";
        DataObservable.getInstance().addObserver(observer1);
        DataObservable.getInstance().notifyDataChange(dataChange);


    }
}
