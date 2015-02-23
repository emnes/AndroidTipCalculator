package com.example.uihomies.androidtipcalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;


public class BillSummaryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_summary);

        DecimalFormat myFormatter = new DecimalFormat("#.00");
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String currency;
            currency = prefs.getString("currency_list", "$");
            // Bill Amount

            TextView billAmountText = (TextView) findViewById(R.id.billAmount);
            billAmountText.setText(currency + myFormatter.format(extras.getDouble("billAmount")));

            // Tip Amount
            TextView tipAmountText = (TextView) findViewById(R.id.tipAmount);
            tipAmountText.setText(currency + myFormatter.format(extras.getDouble("tipAmount")));

            // Total Amount
            TextView totalAmountText = (TextView) findViewById(R.id.totalAmount);
            totalAmountText.setText(currency + myFormatter.format(extras.getDouble("totalAmount")));

            // Tip per person
            TextView tipPerPersonText = (TextView) findViewById(R.id.tipPerPerson);
            tipPerPersonText.setText(currency + myFormatter.format(extras.getDouble("tipPerPerson")));

            // Each person pays
            TextView eachPersonPaysText = (TextView) findViewById(R.id.eachPersonPays);
            eachPersonPaysText.setText(currency + myFormatter.format(extras.getDouble("eachPersonPays")));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bill_summary, menu);
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
            startActivity(new Intent(BillSummaryActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
