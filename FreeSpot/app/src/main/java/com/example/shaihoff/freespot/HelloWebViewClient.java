package com.example.shaihoff.freespot;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by shaihoff on 4/30/15.
 */
public class HelloWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}