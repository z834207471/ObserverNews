package com.example.gary.newsdemo.ui.meinv.model;

import android.util.Log;

import com.example.gary.newsdemo.bean.MeinvBean;
import com.example.gary.newsdemo.bean.NewsBean;
import com.example.gary.newsdemo.constant.AppConfig;
import com.example.gary.newsdemo.retrofit.ApiService;
import com.example.gary.newsdemo.retrofit.RetrofitWrapper;
import com.example.gary.newsdemo.ui.meinv.observer.MeinvObserver;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Gary on 2017/9/12.
 */

public class MeinvModel implements MeinvObserver.model {
    @Override
    public void load( final MeinvObserver.firstLoadListener listener, int page) {
                          Log.e("grgrgr", "load: " );
        ApiService apimanager = RetrofitWrapper.getInstance().create(ApiService.class);
        apimanager.getMeinvData(AppConfig.API_KEY, "6", page)
                .subscribeOn(Schedulers.io())
                .map(new Func1<MeinvBean, List<MeinvBean.MeinvListBean>>() {
                    @Override
                    public List<MeinvBean.MeinvListBean> call(MeinvBean meinvBean) {
                        Log.e("grgrgr", "call: " );
                        return meinvBean.getNewslist();

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MeinvBean.MeinvListBean>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                            listener.onFailure("请求失败",e);
                    }

                    @Override
                    public void onNext(List<MeinvBean.MeinvListBean> meinvListBeen) {
                            listener.onSuccess(meinvListBeen);
                    }
                });
    }
}
