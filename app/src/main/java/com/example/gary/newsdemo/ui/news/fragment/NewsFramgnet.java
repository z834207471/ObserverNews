package com.example.gary.newsdemo.ui.news.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gary.newsdemo.R;
import com.example.gary.newsdemo.base.BaseFragment;
import com.example.gary.newsdemo.base.TabAdapter;
import com.example.gary.newsdemo.ui.meinv.fragment.MeinvFramgnet;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gary on 2017/9/12.
 */

public class NewsFramgnet extends BaseFragment {
    private NewsClassFramgnet[] framgnets;
    private String[] mTitles;
    private TabAdapter adapter;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tably)
    TabLayout tably;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    public static NewsFramgnet newInstance(){
        NewsFramgnet framgnet = new NewsFramgnet();
        return framgnet;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_bar_main,container,false);

        ButterKnife.bind(this,view);
        mTitles = getResources().getStringArray(R.array.newsTitles);
        framgnets = new NewsClassFramgnet[mTitles.length];
        for (int i = 0;i < mTitles.length;i++){
            framgnets[i] = NewsClassFramgnet.newInstance(i);
        }
        adapter = new TabAdapter(getChildFragmentManager(),framgnets);
        tably.setTabMode(TabLayout.MODE_SCROLLABLE);
        adapter.setTitles(mTitles);
        viewpager.setAdapter(adapter);
        tably.setupWithViewPager(viewpager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }

    private void showDialog() {
        AlertDialog.Builder bl = new AlertDialog.Builder(getContext());
        bl.setTitle("友情提示");
        bl.setMessage("是否前往转载者的git");
        bl.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("https://github.com/HuRuWo/YiLan");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        }).create().show();
    }
}
