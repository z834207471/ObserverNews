package com.example.gary.newstext.ui.meinv.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gary.newstext.R;
import com.example.gary.newstext.bean.BeanFragment;
import com.example.gary.newstext.gson.MeinvBean;
import com.example.gary.newstext.ui.meinv.PicOpenActivity;
import com.example.gary.newstext.ui.meinv.adapter.MeinvAdapter;
import com.example.gary.newstext.ui.meinv.meinvobserver.MeinvObserver;
import com.example.gary.newstext.ui.meinv.presenter.Persenter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gary on 2017/9/24.
 */

public class MeinvFragment extends BeanFragment implements MeinvObserver.setData {
    @BindView(R.id.recyclerview)
    EasyRecyclerView recyclerView;
    private boolean isViewPrepared;
    private boolean hasFetchData;
    private MeinvAdapter adapter;
    private int pageIndex = 1;
    private Persenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_news, container, false);
        ButterKnife.bind(this,view);
        presenter = new Persenter(getContext(),this);
        adapter = new MeinvAdapter(getContext());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        super.setRecyclerDecoration(recyclerView);
        recyclerView.setAdapter(adapter);
        adapter.setMore(R.layout.item_view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                lazyFetchData();
            }

            @Override
            public void onMoreClick() {

            }
        });
        adapter.setNoMore(R.layout.item_view_nomore);

        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        pageIndex = 1;
                        presenter.load(pageIndex);
                    }
                },1000);
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), PicOpenActivity.class);
                intent.putExtra("picurl",adapter.getAllData().get(position).getPicUrl());
                getContext().startActivity(intent);
            }
        });
        isViewPrepared = true;
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        onLazyFetchDataIfViewPrepared();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onLazyFetchDataIfViewPrepared();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hasFetchData = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hasFetchData = false;
        isViewPrepared = false;
    }

    public void onLazyFetchDataIfViewPrepared() {
        if (isViewPrepared && getUserVisibleHint() && !hasFetchData) {
            hasFetchData = true;
            lazyFetchData();
        }
    }

    private void lazyFetchData() {
        presenter.load(pageIndex);
        pageIndex++;
    }

    @Override
    public void addData(List<MeinvBean.MeinvListBean> list) {
        adapter.addAll(list);
    }
}
