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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TransferFundsActivity extends AppCompatActivity {
    FirebaseDatabase db1;
    DatabaseReference accounts;
    DatabaseReference transfers;
    DatabaseReference log;
    EditText account;
    EditText sum;
    Button approval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_funds);

        account = (EditText) findViewById(R.id.accountEditText);
        sum = (EditText) findViewById(R.id.sumEditText);
        approval = (Button) findViewById(R.id.ApprovalButton);

        db1 = FirebaseDatabase.getInstance();
        accounts = db1.getReference("accounts");
        transfers = db1.getReference("transfers");
        log = db1.getReference("log");



     // log.child("100000").child("nextId").setValue(1);
      //log.child("100001").child("nextId").setValue(1);


        approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sum1 = sum.getText().toString().trim(); //transfered sum
                String account4 = account.getText().toString().trim();
                if (sum1.equals("") || account.equals(""))
                    Toast.makeText(TransferFundsActivity.this, "לפחות אחד מהשדות ריק", Toast.LENGTH_SHORT).show();
                else {
                    final double sum2 = Double.parseDouble(sum1); //transfered sum
                    if (Current_account.getBalance() < sum2)
                        Toast.makeText(TransferFundsActivity.this, "אין מספיק כסף בחשבונך", Toast.LENGTH_SHORT).show();
                    else {
                        final String account1 = account.getText().toString().trim(); //received account
                        accounts.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.child(account1).exists()) {

                                    double balance = dataSnapshot.child(account1).child("balance").getValue(Double.class);
                                    balance = balance + sum2; //new balance for receiving account
                                    accounts.child(account1).child("balance").setValue(balance);
                                    double balance1 = Current_account.getBalance() - sum2;
                                    accounts.child(Integer.toString(Current_account.getID())).child("balance").setValue(balance1);
                                    Current_account.decreaseBalance(sum2);
                                    Toast.makeText(TransferFundsActivity.this, "ההעברה התבצעה בהצלחה", Toast.LENGTH_SHORT).show();
                                    transfers.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot x) {
                                            int id = x.child("nextId").getValue(Integer.class);
                                            transfers.child(Integer.toString(id)).setValue(new Transfer(Current_account.getID(), Integer.parseInt(account1), sum2));
                                            transfers.child("nextId").setValue(id + 1);

                                        }

                                        @Override

                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                        }
                                    });
                                    log.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot x6) {
                                            DatabaseReference log_account1 = log.child(Integer.toString(Current_account.getID()));//Integer.toString(Current_account.getID())
                                            DataSnapshot x1 = x6.child(Integer.toString(Current_account.getID()));
                                            double sum3 = -sum2;
                                            log_account1.child(Integer.toString(x1.child("nextId").getValue(Integer.class))).setValue(sum3);
                                            log_account1.child("nextId").setValue(x1.child("nextId").getValue(Integer.class) + 1);
                                            DatabaseReference log_account2 = log.child(account1);
                                            DataSnapshot x2 = x6.child(account1);
                                            log_account2.child(Integer.toString(x2.child("nextId").getValue(Integer.class))).setValue(sum2);
                                            log_account2.child("nextId").setValue(x2.child("nextId").getValue(Integer.class) + 1);
                                            Intent intent = new Intent(TransferFundsActivity.this, ManageAccount.class);
                                            startActivity(intent);


                                        }

                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                        }
                                    });
                                } else {
                                    Toast.makeText(TransferFundsActivity.this, "מספר חשבון לא קיים", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                    }

                }
            }
        });
    }}
