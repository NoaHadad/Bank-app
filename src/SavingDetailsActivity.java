package com.example.noa.arielibank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class SavingDetailsActivity extends AppCompatActivity {
    TextView id;
    TextView sum;
    TextView remainingMonths;
    TextView interest;
    TextView startDate;
    TextView endDate;

    FirebaseDatabase db1;
    DatabaseReference savings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_details);

        id=(TextView)findViewById(R.id.savingID);
        sum=(TextView)findViewById(R.id.currentSUM);
        remainingMonths=(TextView)findViewById(R.id.remainingMONTH);
        interest=(TextView)findViewById(R.id.yearlyINTEREST);
        startDate=(TextView)findViewById(R.id.startDATEE);
        endDate=(TextView)findViewById(R.id.endDATE);


        db1 = FirebaseDatabase.getInstance();
        savings = db1.getReference("savings");

        savings.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot x=dataSnapshot.child(Integer.toString( CurrentSavingIdSum.getId()));
                id.setText(Integer.toString(x.child("id").getValue(Integer.class)));
                sum.setText(Double.toString(x.child("sum").getValue(Double.class)));
                remainingMonths.setText(Integer.toString(x.child("remainingMonths").getValue(Integer.class)));
                interest.setText(Double.toString(x.child("yearlyInterest").getValue(Double.class)));
                String s="";
                s=s+(x.child("startDate").getValue(Date.class)).getDate()+"/"+(x.child("startDate").getValue(Date.class)).getMonth()+"/"+(x.child("startDate").getValue(Date.class)).getYear();
                startDate.setText(s);
                String s1="";
                s1=s1+(x.child("endDate").getValue(Date.class)).getDate()+"/"+(x.child("endDate").getValue(Date.class)).getMonth()+"/"+(x.child("endDate").getValue(Date.class)).getYear();
                endDate.setText(s1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
