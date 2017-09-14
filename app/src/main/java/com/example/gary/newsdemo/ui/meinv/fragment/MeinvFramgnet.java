package com.example.gary.newsdemo.ui.meinv.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gary.newsdemo.R;
import com.example.gary.newsdemo.base.BaseFragment;
import com.example.gary.newsdemo.bean.MeinvBean;
import com.example.gary.newsdemo.ui.meinv.PictureActivity;
import com.example.gary.newsdemo.ui.meinv.adapter.MeinvAdapter;
import com.example.gary.newsdemo.ui.meinv.observer.MeinvObserver;
import com.example.gary.newsdemo.ui.meinv.presenter.MeinvPersenter;
import com.example.gary.newsdemo.ui.news.NewsDetailsActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gary on 2017/9/12.
 */

public class MeinvFramgnet extends BaseFragment implements MeinvObserver.setData{
    private MeinvAdapter adapter;
    private MeinvPersenter meinvPersenter;
    private boolean hasFatchData;
    private boolean isViewPrepared;
    private int type;
    private int pageIndex=1;

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    public static MeinvFramgnet newInstance(){
        MeinvFramgnet framgnet = new MeinvFramgnet();
        return framgnet;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        Log.e("grgrgr", "MeinvOnCreate: ");
        ButterKnife.bind(this,view);
        meinvPersenter = new MeinvPersenter(getContext(),this);
        adapter = new MeinvAdapter(getContext());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        super.addDecoration(recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        pageIndex = 0;
                        meinvPersenter.load(type,pageIndex);
                    }
                },1000);

            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                lazyLoad();
            }

            @Override
            public void onMoreClick() {

            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("mnpicurl",adapter.getAllData().get(position).getPicUrl());
                bundle.putString("mnurl",adapter.getAllData().get(position).getUrl());
                Intent intent = new Intent(getActivity(), PictureActivity.class);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
        isViewPrepared =true;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lazyFatchDataIfPrepared();
    }

    private void lazyFatchDataIfPrepared() {
        if (isViewPrepared && getUserVisibleHint() && !hasFatchData){
            lazyLoad();
        }
    }

    private void lazyLoad() {
        meinvPersenter.load(type,pageIndex);
        pageIndex++;
    }

    @Override
    public void returnData(List<MeinvBean.MeinvListBean> list) {
        adapter.addAll(list);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewPrepared =false;
        hasFatchData = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyFatchDataIfPrepared();
    }
}
