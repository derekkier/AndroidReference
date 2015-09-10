package com.derekkier.androidreference;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
    public Toast toast;
    public int intResumeCount = 0;
    int increment = 4;
    MyLocation myLocation = new MyLocation();

    // private ProgressDialog dialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLocation.getLocation(getApplicationContext(), locationResult);

        boolean r = myLocation.getLocation(getApplicationContext(),
            locationResult);

        /*
        startActivity(new Intent(LocationFinder.this,
        // Nearbyhotelfinder.class));
            GPSMyListView.class));
        finish();
        */
    }

    public MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {

        @Override
        public void gotLocation(Location location) {
            // TODO Auto-generated method stub
            double Longitude = location.getLongitude();
            double Latitude = location.getLatitude();
            DistanceUsingLatitudeLongitude distLatLong =  new DistanceUsingLatitudeLongitude();
            double distanceFromSkinnyFats1 = distLatLong.getDistance(Longitude,Latitude,36.075947,-115.181811,'M');
            //8680 W Warm Springs Rd, Las Vegas, NV 89148 Lat and long = 36.056123, -115.281297
            //meters/1609.344 converts to miles
/*
            Toast.makeText(getApplicationContext(), "Got Location",
                Toast.LENGTH_LONG).show();
*/
            Toast.makeText(getApplicationContext(), "Longitude = " + Longitude+", Latitude = " + Latitude, Toast.LENGTH_LONG).show();
            try {
                Toast.makeText(getApplicationContext(), "Distance to Dean Martin = "+distanceFromSkinnyFats1+" miles", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                //Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    };

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
