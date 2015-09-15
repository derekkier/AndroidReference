package com.derekkier.androidreference;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class ActivityWeb extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://globalapp.zuppler.com/show.html?channel=skinnyfats&permalink=skinnyfats2");
        //webView.loadUrl("http://google.com");
    }

}