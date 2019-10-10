package com.sugart.composition.welcome;

import com.sugart.composition.BaseView;
import rx.Observable;
import xdroid.mwee.com.zmstudy.model.bean.GankModel;

/**
 * Author：created by SugarT
 * Time：2019/9/30 13
 */

/**
 *
 *
 * MVP框架由3部分组成：View负责显示，Presenter负责逻辑处理，Model提供数据。在MVP模式里通常包含3个要素（加上View interface是4个）：
 *
 * View:负责绘制UI元素、与用户进行交互(在Android中体现为Activity)
 *
 * Model:负责存储、检索、操纵数据(有时也实现一个Model interface用来降低耦合)
 *
 * Presenter:作为View与Model交互的中间纽带，处理与用户交互的负责逻辑。
 *
 *
 * 降低耦合，方便维护
 * MVP的使用，使Activity中的网络请求剥离出来 成为model、presenter，
 * model只负责网络的请求、
 * pesenter负责处理请求网络后的数据处理：加载中 成功 or 失败 取消加载；
 * 最后View进行界面的展示
 */
public interface WelcomeContract {


    /**
     * 定义数据的操作 一般都是增删改查
     */
    interface Model {
        /**
         * @param type
         * @param pageSize
         * @param pageNum
         * @return
         */
        Observable<GankModel> queryModel( String type,  int pageSize,  int pageNum);
    }


    interface View extends BaseView {

        void doMakeLoving(String data);
    }


    interface Presenter {

        /**
         * 写一个和HttpService同样的接口
         * @param type
         * @param number
         * @param page
         * @return
         */
        void doWelcome(String type, int number, int page);
    }


}
