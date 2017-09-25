package com.example.gary.newstext.ui.meinv.model;

import com.example.gary.newstext.content.AppConfig;
import com.example.gary.newstext.gson.MeinvBean;
import com.example.gary.newstext.retrofit.ApiService;
import com.example.gary.newstext.retrofit.RetrofitWrapper;
import com.example.gary.newstext.ui.meinv.meinvobserver.MeinvObserver;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Gary on 2017/9/24.
 */

public class Model implements MeinvObserver.model {
    @Override
    public void load(final MeinvObserver.firstLoadListener listener, int page) {
        ApiService manager = RetrofitWrapper.getInstance().create(ApiService.class);
        manager.getMeinvData(AppConfig.APP_KEY,"6",page)
                .subscribeOn(Schedulers.io())
                .map(new Func1<MeinvBean, List<MeinvBean.MeinvListBean>>() {
                    @Override
                    public List<MeinvBean.MeinvListBean> call(MeinvBean meinvBean) {
                        return meinvBean.getMeinvList();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MeinvBean.MeinvListBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailed("Request error",e);
                    }

                    @Override
                    public void onNext(List<MeinvBean.MeinvListBean> meinvListBeen) {
                        listener.onSuccess(meinvListBeen);
                    }
                });
    }
}
