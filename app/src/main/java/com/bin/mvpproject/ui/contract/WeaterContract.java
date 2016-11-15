package com.bin.mvpproject.ui.contract;

import com.bin.mvpproject.bean.Root;
import com.hank.library.common.BasePresenter;
import com.hank.library.common.IBaseView;
import com.hank.library.common.IModel;
import com.hank.library.common.IPresenter;
import com.hank.library.util.HttpSubscriber;

import rx.Subscription;

/**
 * author：hebin on 2016/11/15 10:03
 * email：hb494974108@gmail.com
 */
public interface WeaterContract {
    public interface Model extends IModel {
        /**
         * 定义model层获取数据的接口
         * @param subscriber
         * @return
         */
        Subscription getWeatherData(HttpSubscriber<Root> subscriber);

    }

    public interface View extends IBaseView {
        /**
         * 定义model层数据通过presenter通知到view的接口
         *
         * @param data
         */
        void setWeatherData(Root data);
    }

    /**
     * 建立view和model的关联关系
     */
    abstract class Presenter extends BasePresenter<View,Model> {
        /**
         *presenter的请求接口
         */
        public abstract void getWeatherData();
    }
}
