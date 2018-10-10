package xdroid.mwee.com.rxjavatest.proxy;

/**
 * Created by zhangmin on 2018/8/1.
 */

/**
 * 实现接口的代理类。
 */
public class Cinema implements Movie {

    RealMovie realMovie;


    public Cinema(RealMovie realMovie) {
        this.realMovie = realMovie;
    }

    @Override
    public void play() {
        guanggao(true);
        realMovie.play();
        guanggao(false);
    }

    public void guanggao(boolean isStart) {
        if (isStart) {
            System.out.println("电影马上开始了，爆米花、可乐、口香糖9.8折，快来买啊！");
        } else {
            System.out.println("电影马上结束了，爆米花、可乐、口香糖9.8折，买回家吃吧！");
        }
    }
}
