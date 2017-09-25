package com.example.gary.newstext.ui.news.model;

import com.example.gary.newstext.content.AppConfig;
import com.example.gary.newstext.content.NewsType;
import com.example.gary.newstext.gson.NewsBean;
import com.example.gary.newstext.retrofit.ApiService;
import com.example.gary.newstext.retrofit.RetrofitWrapper;
import com.example.gary.newstext.ui.news.observer.NewsObserver;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Gary on 2017/9/24.
 */

public class Model implements NewsObserver.Model {

    @Override
    public void load(int type, final NewsObserver.firstLoadListener listener, int page) {
        ApiService manager = RetrofitWrapper.getInstance().create(ApiService.class);
        manager.getNewsData(NewsType.getNewsType(type), AppConfig.APP_KEY,"10",page)
                .subscribeOn(Schedulers.io())
                .map(new Func1<NewsBean, List<NewsBean.NewsListBean>>() {
                    @Override
                    public List<NewsBean.NewsListBean> call(NewsBean newsBean) {
                        return newsBean.getNewslist();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<NewsBean.NewsListBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailed("请求失败",e);
                    }

                    @Override
                    public void onNext(List<NewsBean.NewsListBean> newsListBeen) {
                            listener.onSuccess(newsListBeen);
                    }
                });
    }
}
