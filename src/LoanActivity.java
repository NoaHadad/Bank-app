package com.example.noa.arielibank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoanActivity extends AppCompatActivity {

    TextView sum;
    TextView months;
    TextView interest;
    TextView payment;
    SeekBar sum1;
    SeekBar months1;
    Button loanRequest;
    FirebaseDatabase db;
    DatabaseReference loans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        sum = (TextView) findViewById(R.id.sumTextView);
        months = (TextView) findViewById(R.id.months8TextView);
        interest = (TextView) findViewById(R.id.interest1TextView);
        payment = (TextView) findViewById(R.id.paymentTextView45);
        sum1 = (SeekBar) findViewById(R.id.seekBar);
        months1 = (SeekBar) findViewById(R.id.seekBar2);
        loanRequest = (Button) findViewById(R.id.loanRequestButton);

        db = FirebaseDatabase.getInstance();
        loans = db.getReference("loans");

        sum1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sum.setText(Integer.toString(progress * 180000 / 100 + 20000));
                double sum2 = progress * 180000 / 100 + 20000;
                String s = months.getText().toString().trim();
                double i = Loan.getInterestRate(sum2, Integer.parseInt(s));
                int i1 = (int) (i * 100);
                double i2 = (double) i1 / 100;
                String i3 = Double.toString(i2);
                i3 = i3 + "%";
                interest.setText(i3);
                double m = Loan.getMonthlyPayment(sum2, Integer.parseInt(s), (double) i2 / 100);
                int m1 = (int) (m * 100);
                double m2 = (double) m1 / 100;
                String m3 = Double.toString(m2);
                payment.setText(m3);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        months1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int x = progress * 114 / 100 + 6;
                String s = Integer.toString(x);
                months.setText(s);
                String s1 = sum.getText().toString().trim();
                double i = Loan.getInterestRate(Integer.parseInt(s1), x);
                int i1 = (int) (i * 100);
                double i2 = (double) i1 / 100;
                String i3 = Double.toString(i2);
                i3 = i3 + "%";
                interest.setText(i3);
                double m = Loan.getMonthlyPayment(Integer.parseInt(s1), x, i2 / 100);
                int m1 = (int) (m * 100);
                double m2 = (double) m1 / 100;
                String m3 = Double.toString(m2);
                payment.setText(m3);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        loanRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loans.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Loan l = new Loan(Current_account.getID(), Double.parseDouble(sum.getText().toString().trim()), Integer.parseInt(months.getText().toString().trim()), dataSnapshot.child("nextId").getValue(Integer.class));
                        loans.child(Integer.toString(l.getId())).setValue(l);
                        loans.child("nextId").setValue(dataSnapshot.child("nextId").getValue(Integer.class) + 1);
                        Toast.makeText(LoanActivity.this, "בקשתך נקלטה במערכת", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoanActivity.this,ManageAccount.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        });
    }
}
