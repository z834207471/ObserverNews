package com.example.gary.newstext.ui.news.observer;

import com.example.gary.newstext.gson.NewsBean;

import java.util.List;

/**
 * Created by Gary on 2017/9/24.
 */

public interface NewsObserver {
    interface setData{
        void addData(List<NewsBean.NewsListBean> list);
    }
    interface firstLoadListener{
        void onSuccess(List<NewsBean.NewsListBean> list);
        void onFailed(String reason,Throwable e);
    }
    interface Model{
        void load(int type,firstLoadListener listener,int page);
    }
    interface Presenter{
        void load(int type,int page);
    }
}
