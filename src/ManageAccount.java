package com.example.noa.arielibank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class ManageAccount extends AppCompatActivity {

    Button loan;
    Button investment;
    Button transfer;
    FirebaseDatabase db1;
    DatabaseReference loans;
    TextView money;
    ListView listViewLoanList;
    List<LoanIdFund> loanList;
    DatabaseReference savings;
    ListView listViewSavingsList;
    List<SavingIdSum> savingList;
    ListView listViewTransferList;
    DatabaseReference transfers ;
    List<TransferToSum> transferList;
    ListView listViewLogList;
    DatabaseReference logs ;
    List<log> logList;
    DatabaseReference accounts ;
    ListView listViewTransferList1;
    List<TransferToSum> transferList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        money = findViewById(R.id.money);

        loan = findViewById(R.id.loanButton);
        investment = findViewById(R.id.investButton);
        transfer = findViewById(R.id.transferButton);

        db1 = FirebaseDatabase.getInstance();

        listViewLoanList = findViewById(R.id.loan_dynamic);
        loans = db1.getReference("loans");
        loanList = new ArrayList<LoanIdFund>();

        listViewSavingsList = findViewById(R.id.saving_dynamic);
        savings = db1.getReference("savings");
        savingList = new ArrayList<SavingIdSum>();

        listViewTransferList = findViewById(R.id.dynamic20);
        transfers = db1.getReference("transfers");
        transferList = new ArrayList<TransferToSum>();

        listViewTransferList1 = findViewById(R.id.dynamic88);
        transferList1 = new ArrayList<TransferToSum>();

        listViewLogList = findViewById(R.id.logs9);
        logs = db1.getReference("log");
        logList = new ArrayList<log>();

        accounts = db1.getReference("accounts");
    }
        protected void onStart(){
            super.onStart();


            accounts.addValueEventListener(new ValueEventListener(){
                @Override
                public void onDataChange(@NonNull DataSnapshot x) {
                    money.setText(Double.toString(x.child(Integer.toString(Current_account.getID())).child("balance").getValue(Double.class)));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

            transfers.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    transferList.clear();
                    transferList1.clear();
                    for (DataSnapshot x: dataSnapshot.getChildren()) {
                        if (x.child("from").exists()) {
                            if (x.child("from").getValue(Integer.class) == Current_account.getID()) {
                                TransferToSum a = new TransferToSum(x.child("to").getValue(Integer.class), x.child("sum").getValue(Double.class));
                                transferList.add(a);
                            }
                            if (x.child("to").getValue(Integer.class)==Current_account.getID()){
                                TransferToSum a=new TransferToSum(x.child("from").getValue(Integer.class),x.child("sum").getValue(Double.class));
                                transferList1.add(a);
                            }
                        }
                    }
                    TransferList adapter =new  TransferList(ManageAccount.this,transferList);
                    listViewTransferList.setAdapter(adapter);
                    TransferList1 adapter1 =new  TransferList1(ManageAccount.this,transferList1);
                    listViewTransferList1.setAdapter(adapter1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

            logs.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot x) {
                    logList.clear();
                    DataSnapshot x1 = x.child(Integer.toString(Current_account.getID()));
                    int num = x1.child("nextId").getValue(Integer.class);
                    for (int i = 1; i < num; i++) {
                        logList.add(new log(x1.child(Integer.toString(i)).getValue(Double.class)));
                    }



                LogList adapter = new LogList(ManageAccount.this, logList);
                    listViewLogList.setAdapter(adapter);
            }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

            loans.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    loanList.clear();
                    for (DataSnapshot x: dataSnapshot.getChildren()) {
                        if (x.child("accountId").exists())
                        if (x.child("accountId").getValue(Integer.class)==Current_account.getID()){
                            LoanIdFund a=new LoanIdFund(x.child("id").getValue(Integer.class),x.child("remainingFund").getValue(Double.class));
                            loanList.add(a);
                        }

                    }
                    LoanList adapter =new LoanList(ManageAccount.this,loanList);
                    listViewLoanList.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

            savings.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    savingList.clear();
                    for (DataSnapshot x: dataSnapshot.getChildren()) {
                        if (x.child("accountId").exists())
                        if (x.child("accountId").getValue(Integer.class)==Current_account.getID()){
                            SavingIdSum a=new SavingIdSum(x.child("id").getValue(Integer.class),x.child("sum").getValue(Double.class));
                            savingList.add(a);
                        }

                    }
                    SavingList adapter =new SavingList(ManageAccount.this,savingList);
                    listViewSavingsList.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

         listViewLoanList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CurrentLoanIdFund a=new  CurrentLoanIdFund(loanList.get(position));
                    Intent intent = new Intent(ManageAccount.this, LoanDetailsActivity.class);
                    startActivity(intent);
                }
            });

            listViewSavingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CurrentSavingIdSum a=new  CurrentSavingIdSum(savingList.get(position));
                    Intent intent = new Intent(ManageAccount.this, SavingDetailsActivity.class);
                    startActivity(intent);
                }
            });

       loan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ManageAccount.this, LoanActivity.class);
            startActivity(intent);
        }
    });
        investment.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ManageAccount.this, SavingActivity.class);
            startActivity(intent);
        }
    });

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageAccount.this, TransferFundsActivity.class);
                startActivity(intent);
            }
        });

}}
