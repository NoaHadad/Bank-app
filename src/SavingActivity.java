package com.example.noa.arielibank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class SavingActivity extends AppCompatActivity {

    TextView sum;
    TextView years;
    TextView interest;
    TextView finalSum;
    SeekBar sum1;
    SeekBar years1;
    Button approval2;
    FirebaseDatabase db;
    DatabaseReference savings;
    DatabaseReference accounts;
    DatabaseReference log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving);

        sum = (TextView) findViewById(R.id.summTextView);
        years = (TextView) findViewById(R.id.yearsTextView);
        interest = (TextView) findViewById(R.id.interestTextView);
        finalSum = (TextView) findViewById(R.id.finalSumTextView);
        sum1 = (SeekBar) findViewById(R.id.seekBar3);
        years1 = (SeekBar) findViewById(R.id.seekBar4);
        approval2 = (Button) findViewById(R.id.makeInvestButton);

        db = FirebaseDatabase.getInstance();
        savings = db.getReference("savings");
        accounts = db.getReference("accounts");
        log = db.getReference("log");

        sum1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sum.setText(Integer.toString(progress * 900000 / 100 + 100000));
                double sum2 = progress * 900000 / 100 + 100000;
                String s = years.getText().toString().trim();
                double i = Saving.getInterestRate(sum2, Integer.parseInt(s));
                int i1 = (int) (i * 1000);
                double i2 = (double) i1 / 1000;
                String i3 = Double.toString(i2);
                i3 = i3 + "%";
                interest.setText(i3);
                double m = Saving.getFinalSum(sum2, Integer.parseInt(s), (double) i2 / 100);
                int m1 = (int) (m * 100);
                double m2 = (double) m1 / 100;
                String m3 = Double.toString(m2);
                finalSum.setText(m3);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        years1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int x = progress * 9 / 100 + 1;
                String s = Integer.toString(x);
                years.setText(s);
                String s1 = sum.getText().toString().trim();
                double i = Saving.getInterestRate(Integer.parseInt(s1), x);
                int i1 = (int) (i * 1000);
                double i2 = (double) i1 / 1000;
                String i3 = Double.toString(i2);
                i3 = i3 + "%";
                interest.setText(i3);
                double m = Saving.getFinalSum(Integer.parseInt(s1), x, i2 / 100);
                int m1 = (int) (m * 100);
                double m2 = (double) m1 / 100;
                String m3 = Double.toString(m2);
                finalSum.setText(m3);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        approval2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(sum.getText().toString().trim()) > Current_account.getBalance())
                    Toast.makeText(SavingActivity.this, "אין ברשותך את הסכום המבוקש", Toast.LENGTH_SHORT).show();
                else {
                    savings.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Saving i = new Saving(Current_account.getID(), Double.parseDouble(sum.getText().toString().trim()), Integer.parseInt(years.getText().toString().trim()), dataSnapshot.child("nextId").getValue(Integer.class));
                            savings.child("nextId").setValue(dataSnapshot.child("nextId").getValue(Integer.class)+1);
                            savings.child(Integer.toString(i.getId())).setValue(i);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                    accounts.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Current_account.decreaseBalance(Integer.parseInt(sum.getText().toString().trim()));
                            Account a = new Account(new Current_account());
                            accounts.child(Integer.toString(a.getID())).setValue(a);
                            log.addListenerForSingleValueEvent(new ValueEventListener() {
                                public void onDataChange(@NonNull DataSnapshot x) {
                                    DatabaseReference log_account = log.child(Integer.toString(Current_account.getID()));
                                    DataSnapshot x1 = x.child(Integer.toString(Current_account.getID()));
                                    log_account.child(Integer.toString(x1.child("nextId").getValue(Integer.class))).setValue(-1*Double.parseDouble(sum.getText().toString().trim()));
                                    log_account.child("nextId").setValue(x1.child("nextId").getValue(Integer.class) + 1);
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }

                            });

                            Toast.makeText(SavingActivity.this, "הכסף הופקד לחיסכון", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SavingActivity.this, ManageAccount.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                }

            }
        });
    }}



