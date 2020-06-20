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
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class customerAccounts extends AppCompatActivity {

    FirebaseDatabase db1;
    DatabaseReference accounts;
    ListView listViewAccountList;
    List<Account> accountList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_accounts);

        listViewAccountList = (ListView) findViewById(R.id.listView);
        db1 = FirebaseDatabase.getInstance();
        accounts = db1.getReference("accounts");
        accountList = new ArrayList<Account>();

    }
        protected void onStart(){
        super.onStart();

        accounts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                accountList.clear();
                for (DataSnapshot x: dataSnapshot.getChildren()) {
                    if(x.child("clientId").exists())
                    if (x.child("clientId").getValue(String.class).equals(CurrentAccountHolder.getID())){
                        Account a=new Account(x.child("employeeNum").getValue(Integer.class),x.child("clientId").getValue(String.class),x.child("balance").getValue(Double.class),x.child("openingYear").getValue(Integer.class),x.child("id").getValue(Integer.class),x.child("status").getValue(String.class));
                        accountList.add(a);
                    }

                }
                AccountList adapter =new AccountList(customerAccounts.this,accountList);
                listViewAccountList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        listViewAccountList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Current_account a=new Current_account(accountList.get(position));
                Intent intent = new Intent(customerAccounts.this, ManageAccount.class);
                startActivity(intent);
            }
        });
    }}


