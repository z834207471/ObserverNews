package com.example.gary.newsdemo.ui.news.adapter;


import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gary.newsdemo.R;
import com.example.gary.newsdemo.bean.NewsBean;
import com.example.gary.newsdemo.utils.PictureUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Gary on 2017/9/12.
 */

public class NewsViewHolder extends BaseViewHolder<NewsBean.NewsListBean> {
    TextView mTitle;
    ImageView iv;
    TextView mTime;

    public NewsViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_framgnet_news);
        mTitle = $(R.id.tv_item_titel);
        iv = $(R.id.iv_item_ic);
        mTime = $(R.id.tv_item_time);
    }

    @Override
    public void setData(NewsBean.NewsListBean data) {
        mTime.setText(data.getCtime());
        mTitle.setText(data.getTitle());
        PictureUtil.showPic(getContext(),data.getPicUrl(),iv);
    }
}
