package com.derekkier.androidreference;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    public Toast toast;
    public int intResumeCount = 0;
    //private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Simple array with a list TV shows
        String[] favoriteTVShows = {"Walking Dead","Orange Is The New Black","Sherlock Holmes","Lie To Me"};

        // The ListAdapter acts as a bridge between the data and each ListItem
        // You fill the ListView with a ListAdapter. You pass it a context represented by
        // this. A Context provides access to resources you need.
        // android.R.layout.simple_list_item_1 is one of the resources needed.
        // It is a predefined layout provided by Android that stands in as a default
        ListAdapter theAdapter = new MyAdapter(this,favoriteTVShows);

        //new ArrayAdapter<String>(this, R.layout.row_layout,R.id.textView1, favoriteTVShows);

        // ListViews display data in a scrollable list
        ListView theListView = (ListView) findViewById(R.id.theListView);
        theListView.setAdapter(theAdapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String tvShowPicked = "You selected " +
                        String.valueOf(adapterView.getItemAtPosition(i));
                        //TextView currentTextView = (TextView) adapterView.getItemAtPosition(i);
                        //adapterView.findViewById(R.id.textView1);
                        //currentTextView.setTextColor(Color.parseColor("#000000"));

                Toast.makeText(MainActivity.this, tvShowPicked, Toast.LENGTH_SHORT).show();

            }
        });
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
