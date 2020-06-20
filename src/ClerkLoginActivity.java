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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClerkLoginActivity extends AppCompatActivity {

    Button login1;
    EditText employeeNum;
    EditText password1;
    FirebaseDatabase db;
    DatabaseReference clerks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerk_login);

        db = FirebaseDatabase.getInstance();
        clerks = db.getReference("clerks");

        login1 = (Button) findViewById(R.id.loginButton2);
        employeeNum = (EditText) findViewById(R.id.employeeNUM);
        password1 = (EditText) findViewById(R.id.password2);

            /*   clerks.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Integer x=dataSnapshot.child("nextId").getValue(Integer.class);
                        clerks.child(Integer.toString(x)).setValue(new Clerk (305134694,x,"Rony","Mor","single","Or 5, Rehovot","g","Business"));
                        clerks.child("nextId").setValue(x + 1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/


        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clerks.addListenerForSingleValueEvent(new ValueEventListener() {

                    //   @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean flag = true;
                        String employeeNum1 = employeeNum.getText().toString().trim();
                        if (!employeeNum1.equals("")) {
                            DataSnapshot x = dataSnapshot.child(employeeNum1);
                            if (x.exists()) {
                                if (x.child("password").getValue(String.class).equals(password1.getText().toString().trim())) {
                                    flag = false;
                                    CurrentClerk c1 = new CurrentClerk(Integer.parseInt( employeeNum1));
                                    Intent intent = new Intent(ClerkLoginActivity.this, ClerkActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }
                        if (flag) {
                            employeeNum.setText("");
                            password1.setText("");
                            Toast.makeText(ClerkLoginActivity.this, "יש לנסות להתחבר מחדש", Toast.LENGTH_SHORT).show();
                        }
                    }

                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });
            }
        });
    }
}
