package com.example.gary.newstext.retrofit;

import com.example.gary.newstext.gson.MeinvBean;
import com.example.gary.newstext.gson.NewsBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;

/**
 * Created by Gary on 2017/9/24.
 */

public interface ApiService {
    @GET("{type}/")
    Observable<NewsBean> getNewsData(@Path("type") String type, @Query("key") String key,
                                     @Query("num") String num, @Query("page") int page);

    @GET("meinv/")
    Observable<MeinvBean> getMeinvData(@Query("key") String key, @Query("num") String num,
                                     @Query("page") int page);
}
