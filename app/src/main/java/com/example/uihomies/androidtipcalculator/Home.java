package com.example.uihomies.androidtipcalculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class Home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    public void calculateButtonClick(View view) {
        // Calculate tip amount & total
        EditText amountText = (EditText) findViewById(R.id.billText);
        double billAmount = Double.parseDouble(amountText.getText().toString());

        EditText billText = (EditText) findViewById(R.id.tipText);
        double tipPercentage = Double.parseDouble(billText.getText().toString());

        double tipAmount = round((billAmount * tipPercentage / 100.0), 2);
        double totalAmount = billAmount + tipAmount;

        // Save values
        Intent intent = new Intent(Home.this, BillSummaryActivity.class);
        intent.putExtra("billAmount", billAmount);
        intent.putExtra("tipAmount", tipAmount);
        intent.putExtra("totalAmount", totalAmount);

        // Calculate tip per person
        EditText personText = (EditText) findViewById(R.id.peopleText);
        double numberOfPeople = Double.parseDouble(personText.getText().toString());

        double tipPerPerson = tipAmount / numberOfPeople;
        intent.putExtra("tipPerPerson", tipPerPerson);

        // Calculate how much each person needs to pay
        double eachPersonPays = totalAmount / numberOfPeople;
        intent.putExtra("eachPersonPays", eachPersonPays);

        startActivity(intent);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
