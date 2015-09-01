package com.derekkier.androidreference;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
    public Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create and show a toast message.
        Context context = getApplicationContext();
        CharSequence text = "Hello Amily! Wo ai ni!";
        int duration = Toast.LENGTH_SHORT;
        toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        toast.setText("Don't leave please!");
        toast.show();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        toast.setText("That's better! Wo ai ni!");
        toast.show();
    }

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
