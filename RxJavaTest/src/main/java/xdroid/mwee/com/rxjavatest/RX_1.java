package xdroid.mwee.com.rxjavatest;

import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RX_1 {

    // 返回事件流
//    private Subject<Integer> mBackPressed = PublishSubject.create();
//    public static void main(String[] args){
//
//        ObservableSource<Integer> source = mBackPressed.debounce(2000, TimeUnit.MILLISECONDS)
//                .map(new Function<Integer, Integer>() {
//                    @Override
//                    public Integer apply(Integer integer) throws Exception {
//                        // 两次点击返回间隔大于2s的事件, 用0表示, 区分正常的点击
//                        return 0;
//                    }
//                });
//        Disposable disposable = mBackPressed.mergeWith(source)
//                .scan(new BiFunction<Integer, Integer, Integer>() {
//                    @Override
//                    public Integer apply(Integer integer, Integer integer2) {
//                        if (integer2 == 0) {
//                            return 0;
//                        }
//                        return integer + 1;
//                    }
//                })
//                .filter(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) {
//                        if (integer > 0) {
//                            return true;
//                        }
//                        return false;
//                    }
//                })
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) {
//                        switch (integer) {
//                            case 1://适配7.0系统,语言的string跟随activity各自的context不能用getApplicationContext()
//                                ToastUtil.showToast(R.string.exitTip);
//                                break;
//                            case 2:
//                                appExit();
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                });
//
//    }


}
