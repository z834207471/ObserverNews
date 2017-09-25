package com.example.gary.newstext.ui.news.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gary.newstext.R;
import com.example.gary.newstext.adapter.TapAdapter;
import com.example.gary.newstext.bean.BeanFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gary on 2017/9/24.
 */

public class NewsFragment extends BeanFragment {
    private TapAdapter adapter;
    private String[] titles;
    private NewsClassFragment[] fragments;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_bar_main,container,false);
        ButterKnife.bind(this,view);
        int n = 0;
        titles = getResources().getStringArray(R.array.titles);
        if (titles != null&&titles.length!=0){
            n = titles.length;
            fragments = new NewsClassFragment[n];
        }
        for (int i = 0 ; i < n ;i++){
            fragments[i] = NewsClassFragment.newInstance(i);
        }
        adapter = new TapAdapter(getChildFragmentManager(),fragments);
        adapter.setmTitles(titles);
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("喜欢？")
                .setMessage("跳转到高瑞主页")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Uri uri = Uri.parse("https://github.com/z834207471/");
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        getContext().startActivity(intent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();
    }
}
