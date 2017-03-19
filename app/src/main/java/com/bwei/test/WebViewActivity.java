package com.bwei.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 姓名:胡文帅
 * 时间:2017/3/18
 * 邮箱：
 * 备注：
 */

public class WebViewActivity extends Activity{

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        wv = (WebView) findViewById(R.id.wv);
        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        if(url!=null){
            wv.loadUrl(url);
        }
    }
}
