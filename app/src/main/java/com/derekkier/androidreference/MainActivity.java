package com.derekkier.androidreference;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View.OnClickListener;

import java.io.Serializable;

public class MainActivity extends Activity {
    public int intResumeCount = 0;
    SharedObject mySharedObj = new SharedObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySharedObj.setSelectedSeat(5);
        Toast.makeText(MainActivity.this, mySharedObj.getSelectedSeat(), Toast.LENGTH_LONG).show();

        final Spinner spinnerArrSeats = (Spinner) findViewById(R.id.spinnerArrSeats);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                mySharedObj.arrSeats);
        spinnerArrSeats.setAdapter(adapter);


        //startActivityForResult(sendScreenIntent, result);
        spinnerArrSeats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                int sid = spinnerArrSeats.getSelectedItemPosition();
                mySharedObj.setSelectedSeat(sid);
                Toast.makeText(getBaseContext(), "You have selected  : " + mySharedObj.arrSeats[sid],
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if( intResumeCount > 0 ) {
            Toast.makeText(MainActivity.this,"Welcome back!",Toast.LENGTH_LONG).show();
        }
        intResumeCount++;

        //mySharedObj.setSelectedSeat(0);
        //Toast.makeText(MainActivity.this, mySharedObj.getSelectedSeat(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        Button getActivityScreenTwo = (Button) findViewById(R.id.get_activity_screen_two);
        getActivityScreenTwo.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"click",Toast.LENGTH_LONG).show();
                    Intent sendScreenIntent = new Intent(MainActivity.this, ActivityScreenTwo.class);
                    final int result = 1;
                    sendScreenIntent.putExtra("passedSharedObject", mySharedObj);
                    startActivity(sendScreenIntent);
                }
            }
        );
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
