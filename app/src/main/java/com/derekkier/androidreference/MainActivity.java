package com.derekkier.androidreference;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
    public Toast toast;
    public int intResumeCount = 0;
    //private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //RelativeLayout RL = (RelativeLayout)findViewById(R.id.RL);
        //WebView webview = new WebView(this);
        WebView webview = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new  WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                //super.onPageFinished(view, url);
                view.loadUrl("javascript:(function(){ if( window.intervalSpecialInstructions === undefined ) { console.log('intervalSpecialInstructions is undefined'); var strTable = 'Table9'; var elementSpecialInstructions = document.getElementById('special_instructions'); var specialInstructionsText; var regExpForTable = new RegExp('^'+strTable, 'gi');	window.intervalSpecialInstructions = setInterval( function() { elementSpecialInstructions = document.getElementById('special_instructions'); console.log( typeof(elementSpecialInstructions ) ); console.log( elementSpecialInstructions  ); 	if( typeof( elementSpecialInstructions ) == 'object' &&  elementSpecialInstructions === null ) { console.log('elementSpecialInstructions does not exist yet.');		} else { specialInstructionsText = elementSpecialInstructions.value; if( specialInstructionsText.match(regExpForTable) ===null) { elementSpecialInstructions.value=strTable+' '+specialInstructionsText; } console.log('elementSpecialInstructions exists');		} } , 2000); } })()");
                //view.loadUrl("javascript:(function(){var val='default'; if( window.intervalSpecialInstructions === undefined ){val='intervalSpecialInstructions =undefined';} document.getElementsByTagName('body')[0].innerHTML=val;})()");
               //Log.d("onPageFinished", "The Page has finished loading");

            }
        });
        if (savedInstanceState != null)
            webview.restoreState(savedInstanceState);
        else
            webview.loadUrl("http://globalapp.zuppler.com/show.html?channel=skinnyfats&permalink=skinnyfats2");
        //setContentView(webview);
        //webview.loadUrl("http://globalapp.zuppler.com/show.html?channel=skinnyfats&permalink=skinnyfats2");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        toast.setText(R.string.onPause_message);
        toast.show();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if( intResumeCount > 0 ) {
            toast.setText(R.string.onResume_message);
            toast.show();
        }
        intResumeCount++;
    }
    /*
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
