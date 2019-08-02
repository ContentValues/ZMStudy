package xdroid.mwee.com.zmstudy.rx;

import android.os.Looper;
import android.util.Log;

import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhangmin on 2019/3/15.
 */

public class R1_D {

    private final static String TAG = "R1_D";


    /**
     * 测试最基础的观察者模式
     */
    public static void r1_1() {
        /**
         * 思考:
         *    call 返回参数为什么是Subscriber
         *
         *   1 被观察者订阅了观察者?[写法有点奇怪  为了代码的可阅读]   Observable subscribe(Subscriber<? super T> subscriber)
         *   2 被观察者回调call 方法  Observable RxJavaHooks.onObservableStart(observable, observable.onSubscribe).call(subscriber);
         *   然后被观察者持有了观察者的引用 此时就可以触发观察者的接口了
         */
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext("Observable");
                subscriber.onNext("hello");
                subscriber.onNext("world");
                subscriber.onNext("!!!!");
                subscriber.onCompleted();

            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted--->");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError---->" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext---->" + s);
            }

            @Override
            public void onStart() {
                super.onStart();
                Log.d(TAG, "onStart()---->");
            }
        });

    }


    /**
     * 测试线程切换
     * <p>
     * <p>
     * subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程
     * <p>
     * observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程
     */
    public static void r1_2() {

        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                //subscriber.onCompleted();
                Log.d(TAG, "new Observable<Object>()");
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    Log.d(TAG, "new Observable---->主线程");
                } else {
                    Log.d(TAG, "new Observable---->子线程");
                }
                subscriber.onNext(new Object());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        Log.d(TAG, "new Action1<Object>()");
                        if (Looper.getMainLooper() == Looper.myLooper()) {
                            Log.d(TAG, "new Action1---->主线程");
                        } else {
                            Log.d(TAG, "new Action1---->子线程");
                        }
                    }
                });

        //observable.observeOn(AndroidSchedulers.mainThread());

    }


    public static void r1_Interval() {

        final int count = 5;
        Observable.interval(0, 2, TimeUnit.SECONDS)
                .take(count)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return count - aLong;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted--->");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError---->" + e.getMessage());
                    }

                    @Override
                    public void onNext(Long s) {
                        Log.d(TAG, "onNext---->" + s);
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.d(TAG, "onStart()---->");
                    }
                });

    }


    public static void r1_3() {

        String[] str = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                try {
                    subscriber.onNext("1");
                    Thread.sleep(500);
                    subscriber.onNext("2");
                    Thread.sleep(500);
                    subscriber.onNext("3");
                    Thread.sleep(500);
                    subscriber.onNext("4");
                    Thread.sleep(500);
                    subscriber.onNext("5");
                    Thread.sleep(500);
                    subscriber.onNext("6");
                    Thread.sleep(500);
                    subscriber.onNext("7");
                    Thread.sleep(500);
                    subscriber.onNext("8");
                    Thread.sleep(500);
                    subscriber.onNext("9");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                subscriber.onCompleted();
            }
        }).buffer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String> stringList) {
                        Log.d(TAG, "onNext---->" + Arrays.toString(stringList.toArray()));
                    }
                });

    }

}
