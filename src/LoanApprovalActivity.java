package com.example.noa.arielibank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoanApprovalActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference loans;
    DatabaseReference accounts;
    DatabaseReference log;
    TextView fund;
    TextView months;
    TextView payment;
    TextView balance;
    ListView listViewLoanList;
    ArrayList<LoanIdFund> loanList;
    Button accept;
    Button reject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_approval);
        db = FirebaseDatabase.getInstance();
        loans = db.getReference("loans");
        accounts = db.getReference("accounts");
        log = db.getReference("log");

        fund = (TextView) findViewById(R.id.requstedFUND);
        months = (TextView) findViewById(R.id.requestedMONTH);
        payment = (TextView) findViewById(R.id.requestedPAYMENT);
        balance = (TextView) findViewById(R.id.clientBALANCE);
        accept = (Button) findViewById(R.id.buttonAPPROVAL1);
        reject = (Button) findViewById(R.id.buttonREJECT2);

        listViewLoanList = (ListView) findViewById(R.id.activeLOANS);
        loanList = new ArrayList<LoanIdFund>();


        loans.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int loanId = CurrentLoanIdFund.getId();
                final String loanId1 = Integer.toString(loanId);
                DataSnapshot x = dataSnapshot.child(loanId1);
                double fund1 = x.child("remainingFund").getValue(Double.class);
                fund.setText(Double.toString(fund1));
                int months1 = x.child("remainingPayments").getValue(Integer.class);
                months.setText(Integer.toString(months1));
                double payment1 = x.child("monthlyPayment").getValue(Double.class);
                payment.setText(Double.toString(payment1));
                int account = x.child("accountId").getValue(Integer.class);
                final String account1 = Integer.toString(account);

                for (DataSnapshot x1 : dataSnapshot.getChildren()) {
                    if (x1.child("accountId").exists() && x1.child("status").exists()) {
                        String stat = x1.child("status").getValue(String.class);
                        if ((x1.child("accountId").getValue(Integer.class) == account) && (x1.child("id").getValue(Integer.class) != loanId) && (stat.equals("active"))) {
                            LoanIdFund a = new LoanIdFund(x1.child("id").getValue(Integer.class), x1.child("remainingFund").getValue(Double.class));
                            loanList.add(a);
                        }
                    }

                }
                LoanList adapter = new LoanList(LoanApprovalActivity.this, loanList);
                listViewLoanList.setAdapter(adapter);
                accounts.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        double balance1 = dataSnapshot1.child(account1).child("balance").getValue(Double.class);
                        String balance2 = Double.toString(balance1);
                        balance.setText(balance2);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

        listViewLoanList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CurrentLoanIdFund a=new  CurrentLoanIdFund(loanList.get(position));
                Intent intent = new Intent(LoanApprovalActivity.this, LoanDetailsActivity.class);
                startActivity(intent);
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int loanId = CurrentLoanIdFund.getId();
                final String loanId1 = Integer.toString(loanId);
                loans.child(loanId1).child("status").setValue("rejected");
                Intent intent = new Intent(LoanApprovalActivity.this,ClerkActivity.class);
                startActivity(intent);
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int loanId = CurrentLoanIdFund.getId();
                final String loanId1 = Integer.toString(loanId);
                loans.child(loanId1).child("status").setValue("active");
                loans.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot x) {
                        int account = x.child(loanId1).child("accountId").getValue(Integer.class);
                        final String account1 = Integer.toString(account);
                        accounts.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot x1) {
                                final double sum = Double.parseDouble((String) fund.getText());
                                accounts.child(account1).child("balance").setValue(x1.child(account1).child("balance").getValue(Double.class) + sum);
                                log.addListenerForSingleValueEvent(new ValueEventListener() {
                                    public void onDataChange(@NonNull DataSnapshot x) {
                                        DatabaseReference log_account = log.child(account1);
                                        DataSnapshot x1 = x.child(account1);
                                        log_account.child(Integer.toString(x1.child("nextId").getValue(Integer.class))).setValue(sum);
                                        log_account.child("nextId").setValue(x1.child("nextId").getValue(Integer.class) + 1);
                                        Current_account.increaseBalance(sum);
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }

                                });
                                Intent intent = new Intent(LoanApprovalActivity.this,ClerkActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }

                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });


            }
        });

    }
}