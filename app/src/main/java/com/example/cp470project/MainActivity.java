package com.example.cp470project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME =  "MainActivity";

    EditText mortgageAmountTxt;
    EditText interestRateTxt;
    EditText prepayAmountTxt;
    EditText startWithTxt;
    Spinner amortizationYrs;
    Spinner amortizationMonths;
    Spinner paymentFreq;
    Spinner termYrs;
    Spinner prepayFreq;
    Button calcBtn;

    ArrayAdapter<CharSequence> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        mortgageAmountTxt = findViewById(R.id.mortgage_amount);
        interestRateTxt = findViewById(R.id.interest_rate);
        prepayAmountTxt = findViewById(R.id.prepay_amount);
        startWithTxt = findViewById(R.id.start_with);

        amortizationYrs = findViewById(R.id.amortization_years);
        amortizationMonths = findViewById(R.id.amortization_months);
        paymentFreq = findViewById(R.id.payment_freq);
        termYrs = findViewById(R.id.term_years);
        prepayFreq = findViewById(R.id.prepay_freq);
        calcBtn = findViewById(R.id.calculateBtn);

        //set dropdown menu for the amortization period (years)
        adapter = ArrayAdapter.createFromResource(this,
                R.array.amortization_periods_yrs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amortizationYrs.setAdapter(adapter);

        //set dropdown menu for the amortization period (months)
        adapter = ArrayAdapter.createFromResource(this,
                R.array.amortization_periods_months, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amortizationMonths.setAdapter(adapter);

        //set dropdown menu for the payment frequency
        adapter = ArrayAdapter.createFromResource(this,
                R.array.payment_freq, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentFreq.setAdapter(adapter);

        //set dropdown menu for the term length (years)
        adapter = ArrayAdapter.createFromResource(this,
                R.array.term_yrs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termYrs.setAdapter(adapter);

        //set dropdown menu for the prepayment frequency
        adapter = ArrayAdapter.createFromResource(this,
                R.array.prepayment_freq, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prepayFreq.setAdapter(adapter);

        calcBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // calculate button pressed
                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                startActivity(intent);

            }
        });

    }

    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}
