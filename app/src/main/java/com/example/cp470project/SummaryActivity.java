package com.example.cp470project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private TableLayout tl;

    double principal;          //loan amount
    double paymentFormula;
    double interestRate;                  //initializes value for interestRate
    double monthlyPayment;                   //initializes value for monthlyPayment
    double monthlyPrincipal;
    double monthlyInterest;                   //initialize value for monthlyInterest
    int term;
    double loanBalance = principal;                //initialize value for loanBalance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            principal = extras.getDouble("MORTGAGE_AMOUNT");
            interestRate = extras.getDouble("INTEREST_RATE");
            term = extras.getInt("TERM");
        } else {
            principal = (Double) savedInstanceState.getSerializable("MORTGAGE_AMOUNT");
            interestRate = (Double) savedInstanceState.getSerializable("INTEREST_RATE");
            term = (Integer) savedInstanceState.getSerializable("TERM");
        }

        tl = findViewById(R.id.table_layout);
        TableLayout.LayoutParams tlparams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);

        Integer totalMonths = term*12;
        TextView tv;
        TableRow tr;
        for(int i = 0; i < totalMonths; i++){
            tr = (TableRow) getLayoutInflater().inflate(R.layout.summary_table_row, null);

            //formula to calculate paymentFormula
            paymentFormula = (Math.pow((1 + interestRate), term) -1)/
                    (interestRate * Math.pow((1 + interestRate), term));

            //formula to calculate the monthly payment
            monthlyPayment = principal / paymentFormula;

            monthlyInterest = loanBalance * interestRate;
            monthlyPrincipal = monthlyPayment - monthlyInterest;
            loanBalance = loanBalance - monthlyPrincipal;

            tv = tr.findViewById(R.id.tableCell1);
            tv.setText("Month " + i+1);

            tv = tr.findViewById(R.id.tableCell2);
            tv.setText("$ " + (monthlyPayment - monthlyInterest));

            tv = tr.findViewById(R.id.tableCell3);
            tv.setText("$ " + monthlyInterest);

            tv = tr.findViewById(R.id.tableCell4);
            tv.setText("$ " + monthlyPayment);

            tv = tr.findViewById(R.id.tableCell5);
            tv.setText("$ " + loanBalance);


            tl.addView(tr, tlparams);
        }
    }
}
