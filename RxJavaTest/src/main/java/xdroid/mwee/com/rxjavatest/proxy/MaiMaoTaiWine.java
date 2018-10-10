package xdroid.mwee.com.rxjavatest.proxy;

/**
 * Created by zhangmin on 2018/8/1.
 */

public class MaiMaoTaiWine implements SellWine {
    @Override
    public String maijiu() {
        System.out.println("我卖的是茅台酒");
        return "我卖的是茅台酒";
    }

    @Override
    public String maiYing() {
        System.out.println("我卖的是卖淫");
        return "我卖的是卖淫";
    }


    public String maijiu2() {
        System.out.println("我卖的是茅坑");
        return "我卖的是茅坑";
    }
}
