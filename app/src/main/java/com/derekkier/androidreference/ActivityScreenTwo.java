package com.derekkier.androidreference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Toshiba on 9/11/2015.
 */
public class ActivityScreenTwo extends Activity {
    private SharedObject mySharedObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_two);

        Intent activityThatCalled= getIntent();
        mySharedObj= (SharedObject) activityThatCalled.getSerializableExtra("passedSharedObject");

        Toast.makeText(ActivityScreenTwo.this,"Seat on this is"+mySharedObj.getSelectedSeat(),Toast.LENGTH_LONG).show();
    }
}
