package com.example.cp470project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME =  "ResultsActivity";

    Button summaryReportBtn;
    TextView mortgageSummaryTxt0, mortgageSummaryTxt1, mortgageSummaryTxt2, mortgageSummaryTxt3, mortgageSummaryTxt4, mortgageSummaryTxt5;
    TextView numPaymentsTermTxt, numPaymentsPeriodTxt, monthlyPaymentTermTxt, monthlyPaymentPeriodTxt, prepayTermTxt, prepayPeriodTxt,
            principlePaymentsTermTxt, principlePaymentsPeriodTxt, interestPaymentsTermTxt, interestPaymentsPeriodTxt, totalTermTxt, totalPeriodTxt;

    Double mortgageAmount;
    Double interestRate;
    Integer amortizationPeriodY;
    String paymentFreq;
    Integer termYrs;
    Double prepaymentAmount;
    Integer startWithPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        summaryReportBtn = findViewById(R.id.summary_report_btn);
        mortgageSummaryTxt0 = findViewById(R.id.mortgage_summary_txt0);
        mortgageSummaryTxt1 = findViewById(R.id.mortgage_summary_txt1);
        mortgageSummaryTxt2 = findViewById(R.id.mortgage_summary_txt2);
        mortgageSummaryTxt3 = findViewById(R.id.mortgage_summary_txt3);
        mortgageSummaryTxt4 = findViewById(R.id.mortgage_summary_txt4);
        mortgageSummaryTxt5 = findViewById(R.id.mortgage_summary_txt5);

        numPaymentsTermTxt = findViewById(R.id.num_pay_term);
        numPaymentsPeriodTxt = findViewById(R.id.num_pay_period);
        monthlyPaymentTermTxt = findViewById(R.id.monthly_payment_term);
        monthlyPaymentPeriodTxt = findViewById(R.id.monthly_payment_period);
        prepayTermTxt = findViewById(R.id.prepay_term);
        prepayPeriodTxt = findViewById(R.id.prepay_period);
        principlePaymentsTermTxt = findViewById(R.id.principle_payments_term);
        principlePaymentsPeriodTxt = findViewById(R.id.principle_payments_period);
        interestPaymentsTermTxt = findViewById(R.id.interest_payments_term);
        interestPaymentsPeriodTxt = findViewById(R.id.interest_payments_period);
        totalTermTxt = findViewById(R.id.total_term);
        totalPeriodTxt = findViewById(R.id.total_period);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            mortgageAmount = extras.getDouble("MORTGAGE_AMOUNT");
            interestRate = extras.getDouble("INTEREST_RATE");
            amortizationPeriodY = extras.getInt("AMORTIZATION_PERIOD_YRS");
            paymentFreq = extras.getString("PAYMENT_FREQUENCY");
            termYrs = extras.getInt("TERM");
            prepaymentAmount = extras.getDouble("PREPAYMENT_AMOUNT");
            startWithPayment = extras.getInt("START_WITH");
        } else {
            mortgageAmount = (Double) savedInstanceState.getSerializable("MORTGAGE_AMOUNT");
            interestRate = (Double) savedInstanceState.getSerializable("INTEREST_RATE");
            amortizationPeriodY = (Integer) savedInstanceState.getSerializable("AMORTIZATION_PERIOD_YRS");
            paymentFreq = (String) savedInstanceState.getSerializable("PAYMENT_FREQUENCY");
            termYrs = (Integer) savedInstanceState.getSerializable("TERM");
            prepaymentAmount = (Double) savedInstanceState.getSerializable("PREPAYMENT_AMOUNT");
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

        calculations();
    }

    public void calculations() {
        Integer totalMonths = amortizationPeriodY * 12;
        Integer termMonths = termYrs * 12;
        Double monthlyInterestRate = interestRate * 0.01 / 12;

        Boolean monthlyPay = true;
        if (paymentFreq.equals("bi_weekly")) {
            monthlyPay = false;
        }

        Double monthlyPayments = mortgageAmount * ((monthlyInterestRate * (Math.pow(( 1 + monthlyInterestRate),totalMonths))) / (Math.pow(( 1 + monthlyInterestRate), totalMonths) - 1));

        Double totalPaid = monthlyPayments * totalMonths;
        Double interestPaid = totalPaid - mortgageAmount;

        Double totalPaidTerm = monthlyPayments * termMonths;
        Double interestPaidTerm = mortgageAmount * termMonths * monthlyInterestRate;
        Double principalPaidTerm = totalPaidTerm - interestPaidTerm;
        Double balanceTerm = mortgageAmount - principalPaidTerm;

        String txt;
        // set text for calculation summary section

        if (monthlyPay) {txt = String.format("%d", termMonths);
        } else { txt = String.format("%d", termYrs*26); }
        numPaymentsTermTxt.setText(txt);
        if (monthlyPay) {txt = String.format("%d", totalMonths);
        } else { txt = String.format("%d", amortizationPeriodY*26); }
        numPaymentsPeriodTxt.setText(txt);
        if (monthlyPay) { txt = String.format("$%,.2f", monthlyPayments);
        } else { txt = String.format("$%,.2f", (monthlyPayments*12)/26); }
        monthlyPaymentTermTxt.setText(txt);
        monthlyPaymentPeriodTxt.setText(txt);
        txt = String.format("$%,.2f", prepaymentAmount);
//        prepayTermTxt.setText(txt);
//        prepayPeriodTxt.setText(txt);
        txt = String.format("$%,.2f", principalPaidTerm);
        principlePaymentsTermTxt.setText(txt);
        txt = String.format("$%,.2f", mortgageAmount);
        principlePaymentsPeriodTxt.setText(txt);
        txt = String.format("$%,.2f", interestPaidTerm);
        interestPaymentsTermTxt.setText(txt);
        txt = String.format("$%,.2f", interestPaid);
        interestPaymentsPeriodTxt.setText(txt);
        txt = String.format("$%,.2f", totalPaidTerm);
        totalTermTxt.setText(txt);
        txt = String.format("$%,.2f", totalPaid);
        totalPeriodTxt.setText(txt);

        // set text for mortgage summary section
        txt = String.format("Over the %d-year amortization period, you will:", amortizationPeriodY);
        mortgageSummaryTxt0.setText(txt);
        if (monthlyPay) {
            txt = String.format("Have made %d monthly (12x per year) payments of $%,.2f. Have paid $%,.2f in principal, $%,.2f in interest, for a total of $%,.2f.", totalMonths, monthlyPayments, mortgageAmount, interestPaid, totalPaid);
        } else {
            txt = String.format("Have made %d bi-weekly (every two weeks) payments of $%,.2f. Have paid $%,.2f in principal, $%,.2f in interest, for a total of $%,.2f.", amortizationPeriodY*26, (monthlyPayments*12)/26, mortgageAmount, interestPaid, totalPaid);
        }
        mortgageSummaryTxt1.setText(txt);
        txt = String.format("Over the %d-year term, you will", termYrs);
        mortgageSummaryTxt2.setText(txt);
        if (monthlyPay) {
            txt = String.format("Have made %d monthly (12x per year) payments of $%,.2f. Have paid $%,.2f in principal, $%,.2f in interest, for a total of $%,.2f.", termMonths, monthlyPayments, principalPaidTerm, interestPaidTerm, totalPaidTerm);
        } else {
            txt = String.format("Have made %d bi-weekly (every two weeks) payments of $%,.2f. Have paid $%,.2f in principal, $%,.2f in interest, for a total of $%,.2f.", termYrs*26, (monthlyPayments*12)/26, principalPaidTerm, interestPaidTerm, totalPaidTerm);
        }
        mortgageSummaryTxt3.setText(txt);
        txt = String.format("At the end of your %d-year term, you will:", termYrs);
        mortgageSummaryTxt4.setText(txt);
        txt = String.format("Have a balance of $%,.2f", balanceTerm);
        mortgageSummaryTxt5.setText(txt);

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
