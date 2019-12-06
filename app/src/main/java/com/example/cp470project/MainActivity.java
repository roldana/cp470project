package com.example.cp470project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME =  "MainActivity";
    private Database dbHelper = new Database(this);
    private SQLiteDatabase database;


    EditText mortgageAmountTxt;
    EditText interestRateTxt;
    EditText prepayAmountTxt;
    EditText startWithTxt;
    Spinner amortizationYrs;
    Spinner amortizationMonths;
    Spinner paymentFreqSpn;
    Spinner termYrsSpn;
    Spinner prepayFreqSpn;
    Button calcBtn;

    ArrayAdapter<CharSequence> adapter;

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
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        mortgageAmountTxt = findViewById(R.id.mortgage_amount_etext);
        interestRateTxt = findViewById(R.id.interest_rate_etext);
        prepayAmountTxt = findViewById(R.id.prepay_amount_etext);
        startWithTxt = findViewById(R.id.start_with_etext);

        amortizationYrs = findViewById(R.id.amortization_years_spinner);
        amortizationMonths = findViewById(R.id.amortization_months_spinner);
        paymentFreqSpn = findViewById(R.id.payment_freq_spinner);
        termYrsSpn = findViewById(R.id.term_years_spinner);
        prepayFreqSpn = findViewById(R.id.prepay_freq_spinner);
        calcBtn = findViewById(R.id.calculate_btn);

        dbHelper = new Database(this);
        database = dbHelper.getWritableDatabase();

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
        paymentFreqSpn.setAdapter(adapter);

        //set dropdown menu for the term length (years)
        adapter = ArrayAdapter.createFromResource(this,
                R.array.term_yrs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termYrsSpn.setAdapter(adapter);

        //set dropdown menu for the prepayment frequency
        adapter = ArrayAdapter.createFromResource(this,
                R.array.prepayment_freq, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prepayFreqSpn.setAdapter(adapter);;

        calcBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // calculate button pressed
                if (getInputs()) {
                    Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                    intent.putExtra("MORTGAGE_AMOUNT", mortgageAmount);
                    intent.putExtra("INTEREST_RATE", interestRate);
                    intent.putExtra("AMORTIZATION_PERIOD_YRS", amortizationPeriodY);
                    intent.putExtra("AMORTIZATION_PERIOD_MONTHS", amortizationPeriodM);
                    intent.putExtra("PAYMENT_FREQUENCY", paymentFreq);
                    intent.putExtra("TERM", termYrs);
                    intent.putExtra("PREPAYMENT_AMOUNT", prepaymentAmount);
                    intent.putExtra("PREPAYMENT_FREQ", prepaymentFreq);
                    intent.putExtra("START_WITH", startWithPayment);
                    ContentValues cValues = new ContentValues();
                    cValues.put(dbHelper.Morgage_AMT, mortgageAmount);
                    cValues.put(dbHelper.Interest_Rate, interestRate);
                    cValues.put(dbHelper.Amortization_Period_Years, amortizationPeriodY);
                    cValues.put(dbHelper.Term, termYrs);
                    cValues.put(dbHelper.Prepayment_Amt, prepaymentAmount);
                    cValues.put(dbHelper.Starting_Payment, startWithPayment);
                    database.insert(dbHelper.TABLE_NAME, null, cValues);
                    startActivity(intent);
                } else {
                    CharSequence text = "Please check inputs";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                }
            }
        });

    }

    boolean getInputs() {

        Boolean valid = true;

        //mortgage amount
        String mortgageAmountInput = mortgageAmountTxt.getText().toString();
        if (!mortgageAmountInput.isEmpty()) {
            try {
                mortgageAmount = Double.parseDouble(mortgageAmountInput);
                // it means it is double
            } catch (Exception e1) {
                // this means it is not double
                e1.printStackTrace();
            }
        } else { valid = false; }

        //get amortization period yrs
        Integer amortYPos = amortizationYrs.getSelectedItemPosition();
        String[] amortizationYValues = getResources().getStringArray(R.array.amortization_periods_yrs_values);
        amortizationPeriodY = Integer.valueOf(amortizationYValues[amortYPos]);

        //get amortization period months
        Integer amortMPos = amortizationMonths.getSelectedItemPosition();
        String[] amortizationMValues = getResources().getStringArray(R.array.amortization_periods_months_values);
        amortizationPeriodM = Integer.valueOf(amortizationMValues[amortMPos]);

        //payment freq
        Integer payFreqPos = paymentFreqSpn.getSelectedItemPosition();
        String[] payFreqValues = getResources().getStringArray(R.array.payment_freq_values);
        paymentFreq = payFreqValues[payFreqPos];

        //term
        Integer termPos = termYrsSpn.getSelectedItemPosition();
        String[] termYrsValues = getResources().getStringArray(R.array.term_yrs_values);
        termYrs = Integer.valueOf(termYrsValues[termPos]);

        //prepayment freq
        Integer prepayFreqPos = prepayFreqSpn.getSelectedItemPosition();
        String[] prepayFreqValues = getResources().getStringArray(R.array.prepayment_freq_values);
        prepaymentFreq = prepayFreqValues[prepayFreqPos];

        //interest rate
        String interestRateInput = interestRateTxt.getText().toString();
        if (!interestRateInput.isEmpty()) {
            try {
                interestRate = Double.parseDouble(interestRateInput);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else { valid = false; }

        // prepayment amount
        String prepaymentAmountInput = interestRateTxt.getText().toString();
        if (!prepaymentAmountInput.isEmpty()) {
            try {
                prepaymentAmount = Double.parseDouble(prepaymentAmountInput);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else { prepaymentAmount = 0.0; }

        //start with payment
        String startWithPaymentInput = interestRateTxt.getText().toString();
        if (!startWithPaymentInput.isEmpty()) {
            try {
                startWithPayment = Integer.parseInt(startWithPaymentInput);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else { startWithPayment = 1; }

        //
//        valid = true;
        //

        return valid;
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
        database.close(); // close database
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}
