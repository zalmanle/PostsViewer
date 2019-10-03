package com.example.postsviewer.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.postsviewer.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    public static final String LINK_KEY = "link";
    private String url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.webViewPost);
        webView.getSettings().setJavaScriptEnabled(true);
        Intent intent = getIntent();

        if(intent != null){

            try {

                url = intent.getStringExtra(LINK_KEY);
                webView.loadUrl(url);
            }
            catch (Exception e){

                webView.loadUrl("http://www.google.com");
            }
        }
        else {

            webView.loadUrl("http://www.google.com");
        }



    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LINK_KEY, url);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        url = savedInstanceState.getString(LINK_KEY);
    }
}
