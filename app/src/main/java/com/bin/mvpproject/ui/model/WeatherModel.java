package com.bin.mvpproject.ui.model;

import com.bin.mvpproject.api.ServiceGenerator;
import com.bin.mvpproject.bean.Root;
import com.bin.mvpproject.ui.contract.WeaterContract;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hank.library.util.HttpSubscriber;

import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * author：hebin on 2016/11/15 10:11
 * email：hb494974108@gmail.com
 */
public class WeatherModel implements WeaterContract.Model {
    @Override
    public Subscription getWeatherData(HttpSubscriber<Root> subscriber) {
        return ServiceGenerator.getInstance().getHttpService().getWeatherData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
