package com.example.gary.newsdemo.ui.news.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.gary.newsdemo.bean.NewsBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by Gary on 2017/9/12.
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
