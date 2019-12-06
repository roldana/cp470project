package com.example.cp470project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "inputDB.db";
    public static final String COLUMN_ID = "id"; //not needed?
    public static final String Morgage_AMT = "MortgageAmount";
    public static final String Interest_Rate = "InterestRate";
    public static final String Amortization_Period_Years = "AmortizationPeriod";
    public static final String Term = "Term";
    public static final String Prepayment_Amt = "PrepaymentAmount";
    public static final String Starting_Payment = "StartingPayment";

    private static final String DATABASE_CREATE = "create table " + DB_NAME + "(" + COLUMN_ID +
            " integer primary key autoincrement, "+ Morgage_AMT + " double not null, " + Interest_Rate +
            " double not null, " + Amortization_Period_Years + " integer not null, " + Term + " integer not null, " +
            Prepayment_Amt + " double not null, " + Starting_Payment + " double not null);";

    Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Database.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + Morgage_AMT);
        onCreate(db);
    }
}
