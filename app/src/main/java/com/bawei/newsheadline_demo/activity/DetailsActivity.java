package com.bawei.newsheadline_demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawei.newsheadline_demo.R;

/**
 *
 */

public class DetailsActivity extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        web = (WebView)findViewById(R.id.webviews);

        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDefaultTextEncodingName("utf-8");

        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                web.loadUrl(url);
                return true;
            }
        });
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        web.loadUrl(url);
    }
}
