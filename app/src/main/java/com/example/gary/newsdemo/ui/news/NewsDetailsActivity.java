package com.example.gary.newsdemo.ui.news;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.gary.newsdemo.R;
import com.example.gary.newsdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gary on 2017/9/12.
 */

public class NewsDetailsActivity extends BaseActivity {
    @BindView(R.id.toobar)
    Toolbar toobar;
    @BindView(R.id.cltoolbl)
    CollapsingToolbarLayout cltoolbl;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.ivdetail)
    ImageView ivdetail;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        toobar.setTitle("新闻详情");

        setSupportActionBar(toobar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Bundle bundle = this.getIntent().getExtras();
        String picurl = bundle.getString("picurl");
        String url = bundle.getString("url");
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);  //设置支持javascript脚本
        settings.setUseWideViewPort(true);      ///将图片调整到适合webview的大小  meta标签 viewport的缩放设置也没有关系。
        settings.setLoadWithOverviewMode(true); //缩放至屏幕的大小
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        Glide.with(this).load(picurl).error(R.mipmap.default_pic)
                .centerCrop().into(ivdetail);

    }
}
