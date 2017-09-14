package com.example.gary.newsdemo.ui.news.presenter;

import android.content.Context;
import android.util.Log;

import com.example.gary.newsdemo.bean.NewsBean;
import com.example.gary.newsdemo.ui.news.model.Model;
import com.example.gary.newsdemo.ui.news.observer.NewsObserver;
import com.example.gary.newsdemo.utils.NetWorkUtil;

import java.util.List;

/**
 * Created by Gary on 2017/9/12.
 */

public class Persenter  implements NewsObserver.firstLoadListener,NewsObserver.persenter{
    private NewsObserver.setData view;
    private NewsObserver.model model;
    private Context context;

    public Persenter(Context context,NewsObserver.setData view) {
        this.view = view;
        model= new Model();
        this.context = context;
    }



    @Override
    public void onSuccess(List<NewsBean.NewsListBean> list) {
        view.returnData(list);
    }

    @Override
    public void onFailure(String reason, Throwable e) {
        NetWorkUtil.checkNetWorkState(context);
    }

    @Override
    public void load(int type, int page) {
        model.load(type,this,page);
    }
}