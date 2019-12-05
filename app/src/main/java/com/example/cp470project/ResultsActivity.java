package com.example.cp470project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ResultsActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME =  "ResultsActivity";

    Button summaryReportBtn;

    Double mortgageAmount;
    Double interestRate;
    Integer amortizationPeriodY;
    Integer amortizationPeriodM;
    String paymentFreq;
    Integer termYrs;
    Double prepaymentAmount;
    String prepaymentFreq;
    Integer startWithPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        summaryReportBtn = findViewById(R.id.summary_report_btn);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            mortgageAmount = extras.getDouble("MORTGAGE_AMOUNT");
            interestRate = extras.getDouble("INTEREST_RATE");
            amortizationPeriodY = extras.getInt("AMORTIZATION_PERIOD_YRS");
            amortizationPeriodM = extras.getInt("AMORTIZATION_PERIOD_MONTHS");
            paymentFreq = extras.getString("PAYMENT_FREQUENCY");
            termYrs = extras.getInt("TERM");
            prepaymentAmount = extras.getDouble("PREPAYMENT_AMOUNT");
            prepaymentFreq = extras.getString("PREPAYMENT_FREQ");
            startWithPayment = extras.getInt("START_WITH");
        } else {
            mortgageAmount = (Double) savedInstanceState.getSerializable("MORTGAGE_AMOUNT");
            interestRate = (Double) savedInstanceState.getSerializable("INTEREST_RATE");
            amortizationPeriodY = (Integer) savedInstanceState.getSerializable("AMORTIZATION_PERIOD_YRS");
            amortizationPeriodM = (Integer) savedInstanceState.getSerializable("AMORTIZATION_PERIOD_MONTHS");
            paymentFreq = (String) savedInstanceState.getSerializable("PAYMENT_FREQUENCY");
            termYrs = (Integer) savedInstanceState.getSerializable("TERM");
            prepaymentAmount = (Double) savedInstanceState.getSerializable("PREPAYMENT_AMOUNT");
            prepaymentFreq = (String) savedInstanceState.getSerializable("PREPAYMENT_FREQ");
            startWithPayment = (Integer) savedInstanceState.getSerializable("START_WITH");
        }

        // go to summary report activity
        summaryReportBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // calculate button pressed
                Intent intent = new Intent(ResultsActivity.this, SummaryActivity.class);
                intent.putExtra("MORTGAGE_AMOUNT", mortgageAmount);
                intent.putExtra("INTEREST_RATE", interestRate);
                intent.putExtra("TERM", termYrs);
                startActivity(intent);
            }
        });
    }

    public void calculations() {
        // to do

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
