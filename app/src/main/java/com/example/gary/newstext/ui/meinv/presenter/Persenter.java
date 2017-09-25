package com.example.gary.newstext.ui.meinv.presenter;

import android.content.Context;

import com.example.gary.newstext.gson.MeinvBean;
import com.example.gary.newstext.ui.meinv.meinvobserver.MeinvObserver;
import com.example.gary.newstext.ui.meinv.model.Model;
import com.example.gary.newstext.utils.NetWorkUtil;

import java.util.List;

/**
 * Created by Gary on 2017/9/24.
 */

public class Persenter implements MeinvObserver.firstLoadListener,  MeinvObserver.presenter {
    private Context context;
    private MeinvObserver.setData view;
    private Model model;

    public Persenter(Context context, MeinvObserver.setData view) {
        super();
        this.view = view;
        this.context = context;
        this.model = new Model();
    }

    @Override
    public void onSuccess(List<MeinvBean.MeinvListBean> list) {
        view.addData(list);
    }

    @Override
    public void onFailed(String reason, Throwable e) {
        NetWorkUtil.onFailedRequest(context);
    }

    @Override
    public void load(int page) {
        model.load(this,page);
    }
}
