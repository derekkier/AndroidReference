package com.derekkier.androidreference;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int REQUEST_ENABLE_BT = 1;
    private Boolean userWantsBluetoothOff = false;
    TextView stateBluetooth;
    BluetoothAdapter bluetoothAdapter;

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
        stateBluetooth = (TextView)findViewById(R.id.bluetoothstate);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        CheckBlueToothState();
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

        if( !userWantsBluetoothOff )
        {
            CheckBlueToothState();
        }
        //BluetoothAdapter.STATE_ON
        //Toast.makeText(MainActivity.this,"is bluetooth state on?"+REQUEST_ENABLE_BT,Toast.LENGTH_LONG ).show();
    }

    private void CheckBlueToothState(){
    if (bluetoothAdapter == null){
        stateBluetooth.setText("Bluetooth NOT support");
       }else{
        if (bluetoothAdapter.isEnabled()){
         if(bluetoothAdapter.isDiscovering()){
          stateBluetooth.setText("Bluetooth is currently in device discovery process.");
         }else{
          stateBluetooth.setText("Bluetooth is Enabled.");
         }
        }else{
         stateBluetooth.setText("Bluetooth is NOT Enabled!");
         Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
       }
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Toast.makeText(MainActivity.this, "requestCode" + requestCode, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "resultCode" + resultCode, Toast.LENGTH_SHORT).show();
        if(requestCode == REQUEST_ENABLE_BT ){
            if( resultCode ==0 ) {
                userWantsBluetoothOff = true;
                Toast.makeText(MainActivity.this, "Wants it off" + resultCode, Toast.LENGTH_LONG).show();
            }
            else
            {
                CheckBlueToothState();
            }
        }
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
