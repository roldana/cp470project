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

    private TableLayout mTableLayout;

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


        mTableLayout = findViewById(R.id.table_layout);
        mTableLayout.setStretchAllColumns(true);

        TextView text_Spacer = null;

        mTableLayout.removeAllViews();

        final TextView period = new TextView(this);
        period.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        period.setText("Period");

        final TextView principle_payment = new TextView(this);
        principle_payment.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        principle_payment.setText("Principle Payment");

        final TextView int_payment = new TextView(this);
        int_payment.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        int_payment.setText("Interest Payment");

        final TextView tot_payment = new TextView(this);
        tot_payment.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        tot_payment.setText("Total Payment");

        final TextView loan_bal = new TextView(this);
        loan_bal.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        loan_bal.setText("Loan Balance");

        final TableRow tr = new TableRow(this);
        tr.setId(0);

        TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);

        tr.addView(period);
        tr.addView(principle_payment);
        tr.addView(int_payment);
        tr.addView(tot_payment);
        tr.addView(loan_bal);


        for(int i = 0; i < term; i++){
            //formula to calculate paymentFormula
            paymentFormula = (Math.pow((1 + interestRate), term) -1)/
                    (interestRate * Math.pow((1 + interestRate), term));

            //formula to calculate the monthly payment
            monthlyPayment = principal / paymentFormula;

            monthlyInterest = loanBalance * interestRate;
            monthlyPrincipal = monthlyPayment - monthlyInterest;
            loanBalance = loanBalance - monthlyPrincipal;

            period.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            period.setText("Month " + i);

            principle_payment.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            principle_payment.setText("$ " + (monthlyPayment - monthlyInterest));

            int_payment.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            int_payment.setText("$ " + monthlyInterest);

            tot_payment.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tot_payment.setText("$ " + monthlyPayment);

            loan_bal.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            loan_bal.setText("$ " + loanBalance);


            tr.setId(i + 1);

            if (period.getParent() != null) {
                ((ViewGroup)period.getParent()).removeView(period);
            }
            tr.addView(period);

            if (principle_payment.getParent() != null) {
                ((ViewGroup)principle_payment.getParent()).removeView(principle_payment);
            }
            tr.addView(principle_payment);

            if (int_payment.getParent() != null) {
                ((ViewGroup)int_payment.getParent()).removeView(int_payment);
            }
            tr.addView(int_payment);

            if (tot_payment.getParent() != null) {
                ((ViewGroup)tot_payment.getParent()).removeView(tot_payment);
            }
            tr.addView(tot_payment);

            if (loan_bal.getParent() != null) {
                ((ViewGroup)loan_bal.getParent()).removeView(loan_bal);
            }
            tr.addView(loan_bal);
        }
    }
}
