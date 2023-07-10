package com.example.keyword_project;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewsDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        String newsUrl = getIntent().getStringExtra("newsUrl");
        setNewsDetailWebView(newsUrl);
    }

    private void setNewsDetailWebView(String newsUrl){
        WebView webView = findViewById(R.id.news_detail_webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(newsUrl);
    }
}
