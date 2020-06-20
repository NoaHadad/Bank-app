package com.example.noa.arielibank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.lang.Integer.parseInt;


public class AccountHolderLoginActivity extends AppCompatActivity {
    Button login;
    EditText id;
    EditText password1;
    FirebaseDatabase db;
    DatabaseReference accountHolders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_holder_login);
        db = FirebaseDatabase.getInstance();
        accountHolders = db.getReference("accountHolders");

       // accountHolders.child("305486544").setValue(new AccountHolder("305486544","Stav","Brown","gA67A","Hanegev 7/5, Carmiel",1984,"single",2014));
       // accountHolders.child("314786321").setValue(new AccountHolder("314786321","Or","Goral","9hbA2","Hagalil 22, Ramla",1990,"married",2014));
       // accountHolders.child("314786432").setValue(new AccountHolder("314786432","Yosef","Cohen","8lb5A","Mishmar 3, Haifa",1971,"married",2014));

        login=(Button)findViewById(R.id.loginButton);
        id=(EditText)findViewById(R.id.idEditText);
        password1=(EditText)findViewById(R.id.passEditText);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



              accountHolders.addListenerForSingleValueEvent(new ValueEventListener() {

                 //   @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean flag=true;
                        String id1=id.getText().toString().trim();
                        if (!id1.equals("")) {
                            DataSnapshot x = dataSnapshot.child(id.getText().toString().trim());
                            if (x.exists()) {
                                if (x.child("password").getValue(String.class).equals(password1.getText().toString().trim())) {
                                    flag = false;
                                    CurrentAccountHolder c1 = new CurrentAccountHolder (x.child("id").getValue(String.class),x.child("firstName").getValue(String.class), x.child("lastName").getValue(String.class),x.child("password").getValue(String.class), x.child("address").getValue(String.class), x.child("yearOfBirth").getValue(Integer.class),x.child("maritalStatus").getValue(String.class),x.child("yearOfJoin").getValue(Integer.class));
                                    Intent intent = new Intent(AccountHolderLoginActivity.this, customerAccounts.class);
                                    startActivity(intent);
                                }
                            }
                        }
                        if (flag) {
                            id.setText("");
                            password1.setText("");
                            Toast.makeText(AccountHolderLoginActivity.this, "יש לנסות להתחבר מחדש", Toast.LENGTH_SHORT).show();
                       }
                    }

                public void onCancelled(@NonNull DatabaseError databaseError) {

                    }



            } );}});
    }

}
