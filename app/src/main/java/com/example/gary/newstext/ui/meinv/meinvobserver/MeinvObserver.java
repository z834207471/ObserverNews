package com.example.gary.newstext.ui.meinv.meinvobserver;

import com.example.gary.newstext.gson.MeinvBean;

import java.util.List;

/**
 * Created by Gary on 2017/9/24.
 */

public interface MeinvObserver {
    interface setData{
        void addData(List<MeinvBean.MeinvListBean> list);
    }
    interface firstLoadListener{
        void onSuccess(List<MeinvBean.MeinvListBean> list);
        void onFailed(String reason,Throwable e);
    }
    interface model{
        void load(firstLoadListener listener,int page);
    }
    interface presenter{
        void load(int page);
    }
}
