package com.derekkier.androidreference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Toshiba on 9/11/2015.
 */
public class ActivityScreenTwo extends Activity {
    private SharedObject mySharedObj;
    GlobalState gs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gs = (GlobalState) getApplication();
        setContentView(R.layout.activity_screen_two);

        Intent activityThatCalled= getIntent();
        mySharedObj= (SharedObject) activityThatCalled.getSerializableExtra("passedSharedObject");

        Toast.makeText(ActivityScreenTwo.this,"Seat on this is"+mySharedObj.getSelectedSeat(),Toast.LENGTH_LONG).show();
    }

    public void backToMainActivity(View view) {
        Intent sendScreenIntent = new Intent(ActivityScreenTwo.this, MainActivity.class);
        //final int result = 1;
        //sendScreenIntent.putExtra("passedSharedObject", mySharedObj);
        startActivity(sendScreenIntent);
    }

    public void getTableName(View view)
    {
        Toast.makeText(ActivityScreenTwo.this,"TableName"+gs.getTableSelected(),Toast.LENGTH_LONG).show();
    }

    public void setTableName(View view) {
        EditText etTableName = (EditText) findViewById(R.id.etTableName);

        gs.setTableSelected(etTableName.getText().toString());
    }
}
