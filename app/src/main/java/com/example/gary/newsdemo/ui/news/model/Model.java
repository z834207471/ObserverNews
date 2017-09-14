package com.example.gary.newsdemo.ui.news.model;

import android.util.Log;

import com.example.gary.newsdemo.bean.NewsBean;
import com.example.gary.newsdemo.constant.AppConfig;
import com.example.gary.newsdemo.constant.NewsType;
import com.example.gary.newsdemo.retrofit.ApiService;
import com.example.gary.newsdemo.retrofit.RetrofitWrapper;
import com.example.gary.newsdemo.ui.news.observer.NewsObserver;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Gary on 2017/9/12.
 */

public class Model implements NewsObserver.model {
    @Override
    public void load(int type, final NewsObserver.firstLoadListener listener, int page) {
        ApiService apimanager = RetrofitWrapper.getInstance().create(ApiService.class);
        apimanager.getNewsData(NewsType.getNewsType(type), AppConfig.API_KEY,"10",page)
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
                            listener.onFailure("请求失败",e);
                    }

                    @Override
                    public void onNext(List<NewsBean.NewsListBean> newsListBeen) {
                            listener.onSuccess(newsListBeen);
                    }
                });
    }
}
