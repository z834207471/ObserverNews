package com.example.gary.newsdemo.ui.meinv.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.gary.newsdemo.bean.MeinvBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Gary on 2017/9/12.
 */

public class MeinvAdapter extends RecyclerArrayAdapter<MeinvBean.MeinvListBean> {
    public MeinvAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MeinvViewHolder(parent);
    }
}
