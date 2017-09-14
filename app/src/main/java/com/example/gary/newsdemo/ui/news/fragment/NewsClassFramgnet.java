package com.example.gary.newsdemo.ui.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gary.newsdemo.R;
import com.example.gary.newsdemo.base.BaseFragment;
import com.example.gary.newsdemo.bean.NewsBean;
import com.example.gary.newsdemo.ui.news.NewsDetailsActivity;
import com.example.gary.newsdemo.ui.news.adapter.NewsAdapter;
import com.example.gary.newsdemo.ui.news.observer.NewsObserver;
import com.example.gary.newsdemo.ui.news.presenter.Persenter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gary on 2017/9/12.
 */

public class NewsClassFramgnet extends BaseFragment implements NewsObserver.setData{
    private NewsAdapter adapter;
    private Persenter persenter;
    private boolean hasFatchData;
    private boolean isViewPrepared;
    private int type;
    private int pageIndex=1;

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    public static NewsClassFramgnet newInstance(int type){
        NewsClassFramgnet framgnet = new NewsClassFramgnet();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        framgnet.setArguments(bundle);
        return framgnet;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        ButterKnife.bind(this,view);
        persenter = new Persenter(getContext(),this);
        adapter = new NewsAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
                        persenter.load(type,pageIndex);
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
                bundle.putString("picurl",adapter.getAllData().get(position).getPicUrl());
                bundle.putString("url",adapter.getAllData().get(position).getUrl());
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
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
        persenter.load(type,pageIndex);
        pageIndex++;
    }

    @Override
    public void returnData(List<NewsBean.NewsListBean> list) {
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
