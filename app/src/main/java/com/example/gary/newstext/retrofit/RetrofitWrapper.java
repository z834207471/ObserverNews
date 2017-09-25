package com.example.gary.newstext.retrofit;

import com.example.gary.newstext.content.AppConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gary on 2017/9/24.
 */

public class RetrofitWrapper {
    private static RetrofitWrapper instance;
    private Retrofit retrofit;

    public static RetrofitWrapper getInstance() {
        if (instance == null) {
            synchronized (RetrofitWrapper.class) {
                if (instance == null) {
                    instance = new RetrofitWrapper();
                }
            }
        }
        return instance;
    }

    private RetrofitWrapper() {
        retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }
}
