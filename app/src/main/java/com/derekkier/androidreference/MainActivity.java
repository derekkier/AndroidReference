package com.derekkier.androidreference;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends Activity {
    private static final String TOTAL_BILL = "TOTAL_BILL";//the value acts a key to be stored later
    private static final String CURRENT_TIP = "CURRENT_TIP";
    private static final String BILL_WITHOUT_TIP = "BILL_WITHOUT_TIP";

    private double billBeforeTip;
    private double tipAmount;
    private double finalBill;

    EditText billBeforeTipET;
    EditText tipAmountET;
    EditText finalBillET;
    SeekBar tipSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //App started for first time?
        if( savedInstanceState == null)
        {
            billBeforeTip       = 0.0;
            tipAmount           = .15;
            finalBill           = 0.0;
        }else
        {
            billBeforeTip       = savedInstanceState.getDouble(BILL_WITHOUT_TIP);
            tipAmount           = savedInstanceState.getDouble(CURRENT_TIP);
            finalBill           = savedInstanceState.getDouble(TOTAL_BILL);
        }

        billBeforeTipET = (EditText) findViewById(R.id.billEditText);
        tipAmountET = (EditText) findViewById(R.id.tipEditText);
        finalBillET = (EditText) findViewById(R.id.finalBillEditText);
        tipSeekBar = (SeekBar) findViewById(R.id.changeTipSeekBar);
        tipSeekBar.setOnSeekBarChangeListener(tipSeekBarListener);
        billBeforeTipET.addTextChangedListener(billBeforeTipListener);
        //tipAmountET.addTextChangedListener(billBeforeTipListener);
    }
    private SeekBar.OnSeekBarChangeListener tipSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            tipAmount       = tipSeekBar.getProgress() *.01;
            tipAmountET.setText(String.format("%.02f",tipAmount));

            updateTipAndFinalBill();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private TextWatcher billBeforeTipListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try{
                billBeforeTip = Double.parseDouble(s.toString());
            }
            catch(NumberFormatException e)
            {
                billBeforeTip = 0.0;
            }

            updateTipAndFinalBill();
        }
    };

    private void updateTipAndFinalBill()
    {
        double tipAmount = Double.parseDouble(tipAmountET.getText().toString());
        double finalBill = tipAmount*billBeforeTip+billBeforeTip;

        finalBillET.setText(String.format("%.02f",finalBill));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putDouble(TOTAL_BILL, finalBill);
        outState.putDouble(CURRENT_TIP, tipAmount);
        outState.putDouble(BILL_WITHOUT_TIP, billBeforeTip);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present. <tag>text</tag>
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
