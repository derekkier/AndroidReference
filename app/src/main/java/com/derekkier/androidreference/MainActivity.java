package com.derekkier.androidreference;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.app.NotificationCompat;

import java.util.GregorianCalendar;

public class MainActivity extends Activity {
    Button btnShowNotification, btnStopNotification, btnSetAlarm;

    NotificationManager notificationManager;

    boolean isNotificationActive = false;
    int notificationID = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowNotification     = (Button) findViewById(R.id.btnShowNotification);
        btnStopNotification     = (Button) findViewById(R.id.btnStopNotification);
        btnSetAlarm             = (Button) findViewById(R.id.btnSetAlarm);

    }

    public void showNotification(View view)
    {
        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(this)
                .setContentTitle("Content Title")
                .setContentText("Content Text")
                .setTicker("Ticker Text")
                .setSmallIcon(R.drawable.asterisk_orange);

        Intent moreInfoIntent = new Intent(this, MoreInfoNotification.class);

        //for use when person goes back or back button
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MoreInfoNotification.class);
        taskStackBuilder.addNextIntent(moreInfoIntent);

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        notificationBuilder.setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID, notificationBuilder.build());
        isNotificationActive=true;
    }

    public void stopNotification(View view)
    {
        if( isNotificationActive )
        {
            notificationManager.cancel(notificationID);

        }
    }

    public void setAlarm(View view)
    {
        Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;

        Intent alarmIntent = new Intent(this, AlertReceiver.class);

        AlarmManager alarmManager = (AlarmManager)
                getSystemService( Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime,
                PendingIntent.getBroadcast(this, 1, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT));

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
