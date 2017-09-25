package com.example.gary.newstext.ui.news.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.gary.newstext.gson.NewsBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Gary on 2017/9/24.
 */

public class NewsAdapter extends RecyclerArrayAdapter<NewsBean.NewsListBean> {
    public NewsAdapter(Context context) {
        super(context);
    }


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(parent);
    }
}
