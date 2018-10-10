package xdroid.mwee.com.rxjavatest.proxy;

/**
 * Created by zhangmin on 2018/8/1.
 */


/**
 * 真正的实现这个 Movie 接口的类
 */
public class RealMovie implements Movie {
    @Override
    public void play() {
        System.out.println("播放电影");
    }
}
