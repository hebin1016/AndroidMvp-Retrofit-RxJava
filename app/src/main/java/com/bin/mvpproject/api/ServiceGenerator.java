package com.bin.mvpproject.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author hank.he
 * @date 2016/8/30 15:11
 */
public class ServiceGenerator {

    /**
     * 超时时间
     */
    private static final int DEFAULT_TIMEOUT = 10;
    /**
     * retrofit
     */
    private Retrofit retrofit;
    /**
     * 接口请求
     */
    private HttpService httpService;

    public HttpService getHttpService() {
        return httpService;
    }

    private ServiceGenerator() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Api.BASE_URL)
                .build();
        httpService = retrofit.create(HttpService.class);
    }

    //在访问HttpUtil时创建单例
    private static class SingletonHolder {
        private static final ServiceGenerator INSTANCE = new ServiceGenerator();
    }

    //获取单例
    public static ServiceGenerator getInstance() {
        return SingletonHolder.INSTANCE;
    }


}
