package com.example.project1213;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebViewClient;

public class WebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent intent=getIntent();
        String website_address=intent.getStringExtra("website_address");
        String website_name=intent.getStringExtra("website_name");
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(website_name);
        android.webkit.WebView webView=(android.webkit.WebView)findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(website_address);


    }
}
