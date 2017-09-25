package com.example.gary.newstext.ui.news.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gary.newstext.R;
import com.example.gary.newstext.gson.NewsBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Gary on 2017/9/24.
 */

public class NewsViewHolder extends BaseViewHolder<NewsBean.NewsListBean> {
    private ImageView mImg;
    private TextView mTitle;
    private TextView mTime;

    public NewsViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_news_fragment);
        mImg = $(R.id.img_ic_news);
        mTitle = $(R.id.tv_title_news);
        mTime = $(R.id.tv_time_news);
    }

    @Override
    public void setData(NewsBean.NewsListBean data) {
        mTitle.setText(data.getTitle());
        mTime.setText(data.getCtime());
        Glide.with(getContext())
                .load(data.getPicUrl())
                .centerCrop()
                .placeholder(R.mipmap.default_pic)
                .into(mImg);
    }
}
