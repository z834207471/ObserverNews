package com.example.gary.newstext.ui.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gary.newstext.R;
import com.example.gary.newstext.bean.BeanFragment;
import com.example.gary.newstext.gson.NewsBean;
import com.example.gary.newstext.ui.news.NewsOpenAcitivity;
import com.example.gary.newstext.ui.news.adapter.NewsAdapter;
import com.example.gary.newstext.ui.news.observer.NewsObserver;
import com.example.gary.newstext.ui.news.presenter.Presenter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gary on 2017/9/24.
 */

public class NewsClassFragment extends BeanFragment implements NewsObserver.setData{
    private int type;
    private NewsAdapter adapter;
    private Presenter presenter;
    private int pageIndex = 1; //加载页数
    private boolean isViewPrepared; //视图创建完毕
    private boolean hasFetchData;  //是否已经使用懒人加载加载数据
    @BindView(R.id.recyclerview)
    EasyRecyclerView recyclerview;

    public static NewsClassFragment newInstance(int type){
     NewsClassFragment fragment = new NewsClassFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        fragment.setArguments(bundle );
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_news,container,false);
        ButterKnife.bind(this,view);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsAdapter(getContext());
        presenter = new Presenter(getContext(),this);
        super.setRecyclerDecoration(recyclerview);
        recyclerview.setAdapter(adapter);
        adapter.setMore(R.layout.item_view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                lazyOnFetchData();
            }

            @Override
            public void onMoreClick() {

            }
        });
        recyclerview.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        pageIndex = 1;
                        presenter.load(type,pageIndex);
                    }
                },1000);
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), NewsOpenAcitivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("picurl",adapter.getAllData().get(position).getPicUrl());
                bundle.putString("url",adapter.getAllData().get(position).getUrl());
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });
        isViewPrepared = true;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onLazyFetchDataIfViewPrepared();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        onLazyFetchDataIfViewPrepared();
    }

    public void onLazyFetchDataIfViewPrepared(){
        if (getUserVisibleHint() && isViewPrepared && !hasFetchData){
            lazyOnFetchData();
            hasFetchData = true;
        }
    }
    private void lazyOnFetchData() {
        presenter.load(type,pageIndex);
        pageIndex++;
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

    @Override
    public void addData(List<NewsBean.NewsListBean> list) {
        adapter.addAll(list);
    }
}
