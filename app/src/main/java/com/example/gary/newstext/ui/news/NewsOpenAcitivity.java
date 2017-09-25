package com.example.gary.newstext.ui.news;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.gary.newstext.R;
import com.example.gary.newstext.bean.BeanActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gary on 2017/9/24.
 */

public class NewsOpenAcitivity extends BeanActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_pic_news)
    ImageView mImg;
    @BindView(R.id.webview)
    WebView webview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_news);
        ButterKnife.bind(this);
        toolbar.setTitle("新闻详情");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Bundle bundle = getIntent().getExtras();
        String picurl =bundle.getString("picurl");
        String url = bundle.getString("url");
        Glide.with(this).load(picurl).fitCenter().into(mImg);
        WebSettings settings = webview.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient());
    }
}
