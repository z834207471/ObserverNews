package com.example.gary.newsdemo.ui.meinv.presenter;

import android.content.Context;
import android.util.Log;

import com.example.gary.newsdemo.bean.MeinvBean;
import com.example.gary.newsdemo.ui.meinv.model.MeinvModel;
import com.example.gary.newsdemo.ui.meinv.observer.MeinvObserver;
import com.example.gary.newsdemo.utils.NetWorkUtil;

import java.util.List;

/**
 * Created by Gary on 2017/9/12.
 */

public class MeinvPersenter implements MeinvObserver.firstLoadListener,MeinvObserver.persenter{
    private MeinvObserver.setData view;
    private MeinvObserver.model meinvModel;
    private Context context;

    public MeinvPersenter(Context context, MeinvObserver.setData view) {
        this.view = view;
        meinvModel = new MeinvModel();
        this.context = context;
        Log.e("grgrgr", "MeinvPersenter: ");
    }



    @Override
    public void onSuccess(List<MeinvBean.MeinvListBean> list) {
        view.returnData(list);
    }

    @Override
    public void onFailure(String reason, Throwable e) {
        NetWorkUtil.checkNetWorkState(context);
    }

    @Override
    public void load(int type, int page) {
        meinvModel.load(this,page);
    }
}