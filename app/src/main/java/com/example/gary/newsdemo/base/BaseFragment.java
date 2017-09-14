package com.example.gary.newsdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gary.newsdemo.utils.PixUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

/**
 * Created by Gary on 2017/9/12.
 */

public class BaseFragment  extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    public void addDecoration(EasyRecyclerView rv){
        SpaceDecoration sd = new SpaceDecoration(PixUtil.convertDp2Px(8,getContext()));
        sd.setPaddingHeaderFooter(false);
        sd.setPaddingEdgeSide(true);
        sd.setPaddingStart(true);
        rv.addItemDecoration(sd);
    }
}
