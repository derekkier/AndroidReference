package com.derekkier.androidreference;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.FileObserver;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class MainActivity extends Activity {
    private Context context = MainActivity.this;
    //private String strTable;
    private FileObserver fileObserver;//I couldn't get FileObserver to work properly as a listenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fileName = "myFile3";
        //makeText(str + "|is contents of "+fileName);


        File path = context.getFilesDir();
        File file = new File( path, fileName );
        makeText(file.getPath());
        /*
        fileObserver = new FileObserver(path.toString(),FileObserver.ALL_EVENTS) {
            @Override
            public void onEvent(int event, String path) {
                makeText("path "+path+" changed "+event);
            }
        };

        fileObserver.startWatching();
        */


        writeFile(fileName, "new contents");
        String str = readFileAsString(fileName);

        readFileAsString(fileName);
    }

    public String readFileAsString(String fileName) {
        //Context context = App.instance.getApplicationContext();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(new File(context.getFilesDir(), fileName)));
            while ((line = in.readLine()) != null) stringBuilder.append(line);

        } catch (FileNotFoundException e) {
            //Logger.logError(TAG, e);
            makeText("file doesn't exist");
        } catch (IOException e) {
            //Logger.logError(TAG, e);
        }

        makeText("Reading file contents:" + stringBuilder.toString());
        return stringBuilder.toString();
    }

    private void writeFile(String fileName,String contents)
    {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(contents);
            outputStreamWriter.close();
            //makeText("Created file");
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
            makeText("File write failed: " + e.toString());
        }

        //isFileInExistence(fileName);
    }

    private boolean isFileInExistence(String fileName)
    {
        File path = context.getFilesDir();
        File file = new File( path, fileName );
        makeText("path:" + path);
        if(file.exists())
        {
            makeText("myFile exists.");
            return true;
        }
        else
        {
            makeText("myFile does not exist.");
            return false;
        }
    }

    public void updateFileContents(View view)
    {
        EditText etFileContents = (EditText) findViewById(R.id.etFileContents);
        EditText etFileName = (EditText) findViewById(R.id.etFileName);
        String fileContents = etFileContents.getText().toString();
        String fileName = etFileName.getText().toString();
        writeFile(fileName, fileContents);
        //makeText("updateFile|" + etFileContents.getText().toString() + " fname" + etFileName.getText().toString());
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

    private void makeText(String str)
    {
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG).show();
    }
}
