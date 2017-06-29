package com.mercury.permission;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    WebView mWebView;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = (WebView) findViewById(R.id.wv);

        mWebView.loadUrl("file:///android_asset/wx.html");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(this, "wx");

    }

    @JavascriptInterface
    public void actionFromJs() {
        Log.v("js", "action");
        Toast.makeText(this, "hha", Toast.LENGTH_SHORT).show();
    }
}
