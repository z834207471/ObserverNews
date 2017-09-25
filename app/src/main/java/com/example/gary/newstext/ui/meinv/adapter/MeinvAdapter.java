package com.example.gary.newstext.ui.meinv.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.gary.newstext.gson.MeinvBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Gary on 2017/9/24.
 */

public class MeinvAdapter extends RecyclerArrayAdapter<MeinvBean.MeinvListBean> {
    public MeinvAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new MeinvViewHold(parent);
    }
}
