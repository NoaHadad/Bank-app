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
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ClerkActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference loans;
    DatabaseReference accounts;
    ListView listViewLoanList;
    List<LoanIdFund> loanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerk);

        db = FirebaseDatabase.getInstance();
        loans = db.getReference("loans");
        accounts = db.getReference("accounts");

        listViewLoanList = (ListView) findViewById(R.id._dynamic33);
        loanList = new ArrayList<LoanIdFund>();

    }

    protected void onStart(){
        super.onStart();


        loans.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                loanList.clear();
                for (DataSnapshot x : dataSnapshot.getChildren()) {

                    if (x.child("accountId").exists()) {
                        String status1 = x.child("status").getValue(String.class);
                        if (status1.equals("waiting")) {
                            int account = x.child("accountId").getValue(Integer.class);
                            final String account1 = Integer.toString(account);
                            final DataSnapshot x1 = x;
                            accounts.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                    int p = dataSnapshot1.child(account1).child("employeeNum").getValue(Integer.class);
                                    if (p == CurrentClerk.getEmployeeNum()) {
                                        LoanIdFund a = new LoanIdFund(x1.child("id").getValue(Integer.class), x1.child("remainingFund").getValue(Double.class));
                                        loanList.add(a);
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                }   LoanList adapter = new LoanList(ClerkActivity.this, loanList);
                listViewLoanList.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        listViewLoanList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CurrentLoanIdFund l = new CurrentLoanIdFund(loanList.get(position));
                        Intent intent = new Intent(ClerkActivity.this, LoanApprovalActivity.class);
                        startActivity(intent);
                    }

                });




    }
}
