package com.derekkier.androidreference;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
    public Toast toast;
    public int intResumeCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create and show a toast message.
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        toast = Toast.makeText(context, R.string.onCreate_message, duration);
        toast.show();
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
            DialogFragment myFragment = new MyDialogFragment();
            myFragment.show(getFragmentManager(),"theDialog");
            return true;
        }
        else if(id == R.id.exit_the_app)
        {
           finish();
           return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
