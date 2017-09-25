package com.example.gary.newstext.ui.news.presenter;

import android.content.Context;

import com.example.gary.newstext.gson.NewsBean;
import com.example.gary.newstext.ui.news.model.Model;
import com.example.gary.newstext.ui.news.observer.NewsObserver;
import com.example.gary.newstext.utils.NetWorkUtil;

import java.util.List;

/**
 * Created by Gary on 2017/9/24.
 */

public class Presenter implements NewsObserver.firstLoadListener,NewsObserver.Presenter {
    private NewsObserver.Model model;
    private NewsObserver.setData view;
    private Context context;
    public Presenter(Context context,NewsObserver.setData view) {
        super();
        this.context = context;
        this.view = view;
        model = new Model();
    }

    @Override
    public void onSuccess(List<NewsBean.NewsListBean> list) {
        view.addData(list);
    }

    @Override
    public void onFailed(String reason, Throwable e) {
        NetWorkUtil.onFailedRequest(context);
    }

    @Override
    public void load(int type, int page) {
        model.load(type,this,page);
    }
}
