package com.bin.mvpproject.ui.presenter;

import com.bin.mvpproject.bean.Root;
import com.bin.mvpproject.ui.contract.WeaterContract;
import com.hank.library.util.HttpSubscriber;

/**
 * author：hebin on 2016/11/15 10:14
 * email：hb494974108@gmail.com
 */
public class WeatherPresenter extends WeaterContract.Presenter {
    @Override
    public void getWeatherData() {
        mView.showLoadingProgress();
        mModel.getWeatherData(new HttpSubscriber<Root>() {
            @Override
            public void onSuccess(Root s) {
                mView.dismissLoadingProgress();
                mView.setWeatherData(s);
            }

            @Override
            public void onError(String msg) {
                mView.dismissLoadingProgress();
                mView.showError(msg);
            }

            @Override
            public void _complete() {

            }
        });
    }


    @Override
    public void onStart() {

    }
}
