package com.example.gary.newsdemo.retrofit;

import com.example.gary.newsdemo.bean.MeinvBean;
import com.example.gary.newsdemo.bean.NewsBean;


import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Gary on 2017/9/12.
 */

public interface ApiService {
    @GET("{type}/")
    Observable<NewsBean> getNewsData(@Path("type") String type, @Query("key") String key,
                                     @Query("num") String num, @Query("page") int page);
    @GET("meinv/")
    Observable <MeinvBean> getMeinvData(@Query("key") String key,
    @Query("num") String num, @Query("page") int page);
}
