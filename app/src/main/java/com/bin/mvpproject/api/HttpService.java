package com.bin.mvpproject.api;

import com.bin.mvpproject.bean.Root;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author hank.he
 * @date 2016/8/30 15:11
 */
public interface HttpService {
    /**
     * 获取天气数据
     * @return
     */
    @GET("weather.php")
    Observable<Root> getWeatherData();



}
