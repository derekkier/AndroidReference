package com.derekkier.androidreference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {
    String locations;
    private static Context context;
    TextView tvLocations;

    static String urlJsonLocations = "http://estimotemanager.derekkier.com/locations?format=json";
    static String urlJsonEstimotes;// = "http://estimotemanager.derekkier.com/estimotes?format=json";
    //static String urlJsonLocations = "http://estimotemanager.derekkier.com/locations?format=json";
    EditText etResponse;
    TextView tvIsConnected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context =this;

        // get reference to the views
        etResponse = (EditText) findViewById(R.id.etResponse);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);
        tvLocations = (TextView) findViewById(R.id.tvLocations);

        // check if you are connected or not
        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are conncted");
        }
        else{
            tvIsConnected.setText("You are NOT conncted");
        }

        // call AsynTask to perform network operation on separate thread
        new HttpAsyncTask().execute(urlJsonLocations);
    }

    public  String GET(String url){
        InputStream inputStream     = null;
        String result               = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // Holds Key Value pairs from a JSON source
        JSONArray jsonArray;
        try {

            // Delete cbfunc( and ); from the results
            //result = result.substring(7);
            //result = result.substring(0, result.length()-2);

            // Print out all the data read in
            // Log.v("JSONParser RESULT ", result);

            // Get the root JSONObject
            //jsonObject = new JSONObject(result);

            jsonArray               = new JSONArray(result);
            JSONObject jsonDataRow  = jsonArray.getJSONObject(0);
            String location_name    = jsonDataRow.getString("name");

            //determine what JSON data this function is parsing
            if( url.contains("locations?") )
            {
                locations+=location_name;
            }
            else
            {

            }

            //tvLocations.append(location_name);
            /*
            jsonObject.getJSONArray()

            // Get the JSON object named query
            JSONObject queryJSONObject = jsonObject.getJSONObject("query");

            // Get the JSON object named results inside of the query object
            JSONObject resultsJSONObject = queryJSONObject.getJSONObject("results");

            // Get the JSON object named quote inside of the results object
            JSONObject quoteJSONObject = resultsJSONObject.getJSONObject("quote");


            // Get the JSON Strings in the quote object
            stockSymbol = quoteJSONObject.getString("symbol");
            stockDaysLow = quoteJSONObject.getString("DaysLow");
            stockDaysHigh = quoteJSONObject.getString("DaysHigh");
            stockChange = quoteJSONObject.getString("Change");

            // EXTRA STUFF THAT HAS NOTHING TO DO WITH THE PROGRAM

            Log.v("SYMBOL ", stockSymbol);
            Log.v("Days Low ", stockDaysLow);
            Log.v("Days High ", stockDaysHigh);
            Log.v("Change ", stockChange);

            // GET ARRAY DATA
            JSONArray queryArray = quoteJSONObject.names();

            List<String> list = new ArrayList<String>();
            for (int i=0; i<queryArray.length(); i++) {
                list.add( queryArray.getString(i) );
            }

            for(String item : list){

                Log.v("JSON ARRAY ITEMS ", item);

            }
            // END OF GET ARRAY DATA

            // Gets the first item in the JSONObject
            JSONArray objectArray = resultsJSONObject.names();

            // Prints out that first item in the JSONObject
            Log.v("JSON NEXT NODE ", objectArray.getString(0));

            */
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            etResponse.setText(result);
            tvLocations.append(locations);
        }
    }
}
