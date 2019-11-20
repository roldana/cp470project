package com.example.cp470project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME =  "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        final EditText mortgageAmountTxt = findViewById(R.id.mortgage_amount);
        final EditText interestRateTxt = findViewById(R.id.interest_rate);
        final EditText prepayAmountTxt = findViewById(R.id.prepay_amount);
        final EditText startWithTxt = findViewById(R.id.start_with);

        String mortgageAmount = mortgageAmountTxt.getText().toString();
        String interestRate = interestRateTxt.getText().toString();
        String prepayAmount = prepayAmountTxt.getText().toString();
        String startWith = startWithTxt.getText().toString();

        final Spinner amortizationYrs = findViewById(R.id.amortization_years);
        final Spinner amortizationMonths = findViewById(R.id.amortization_months);
        final Spinner paymentFreq = findViewById(R.id.payment_freq);
        final Spinner termYrs = findViewById(R.id.term_years);
        final Spinner prepayFreq = findViewById(R.id.prepay_freq);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.amortization_periods_yrs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amortizationYrs.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.amortization_periods_months, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amortizationMonths.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.amortization_periods_yrs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amortizationYrs.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.payment_freq, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentFreq.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.term_yrs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termYrs.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.prepayment_freq, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prepayFreq.setAdapter(adapter);

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
