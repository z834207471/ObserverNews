package com.example.gary.newsdemo.ui.meinv.observer;

import com.example.gary.newsdemo.bean.MeinvBean;
import com.example.gary.newsdemo.bean.NewsBean;

import java.util.List;

/**
 * Created by Gary on 2017/9/12.
 */

public interface MeinvObserver {
    interface setData{
        void returnData(List<MeinvBean.MeinvListBean> list);
    }
    interface firstLoadListener{
        void onSuccess(List<MeinvBean.MeinvListBean> list);
        void onFailure(String reason, Throwable e);
    }
    interface persenter{
        void load(int type, int page);
    }
    interface model{
        void load( firstLoadListener listener, int page);
    }
}
