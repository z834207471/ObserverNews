package com.example.gary.newsdemo.ui.news.observer;

import com.example.gary.newsdemo.bean.NewsBean;

import java.util.List;

/**
 * Created by Gary on 2017/9/12.
 */

public interface NewsObserver {
    interface setData{
        void returnData(List<NewsBean.NewsListBean> list);
    }
    interface firstLoadListener{
        void onSuccess(List<NewsBean.NewsListBean> list);
        void onFailure(String reason,Throwable e);
    }
    interface persenter{
        void load(int type,int page);
    }
    interface model{
        void load(int type,firstLoadListener listener,int page);
    }
}
