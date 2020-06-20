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
public class LoanDetailsActivity extends AppCompatActivity {

    TextView id;
    TextView fund;
    TextView payment;
    TextView numOfPayments;
    TextView startDate;
    TextView endDate;
    TextView status;

    FirebaseDatabase db1;
    DatabaseReference loans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_details);

         id=(TextView)findViewById(R.id.loanNUM);
         fund=(TextView)findViewById(R.id.fundREMAIN);
         payment=(TextView)findViewById(R.id.paymentSUM);
         numOfPayments=(TextView)findViewById(R.id.paymentREMAIN);
         startDate=(TextView)findViewById(R.id.startDATE);
         endDate=(TextView)findViewById(R.id.textView38);
         status=(TextView)findViewById(R.id.status1);

        db1 = FirebaseDatabase.getInstance();
        loans = db1.getReference("loans");

       loans.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot x=dataSnapshot.child(Integer.toString( CurrentLoanIdFund.getId()));
                id.setText(Integer.toString(x.child("id").getValue(Integer.class)));
                fund.setText(Double.toString(x.child("remainingFund").getValue(Double.class)));
                payment.setText(Double.toString(x.child("monthlyPayment").getValue(Double.class)));
                numOfPayments.setText(Integer.toString(x.child("remainingPayments").getValue(Integer.class)));
                String s="";
                s=s+(x.child("startDate").getValue(Date.class)).getDate()+"/"+(x.child("startDate").getValue(Date.class)).getMonth()+"/"+(x.child("startDate").getValue(Date.class)).getYear();
                startDate.setText(s);
                String s1="";
                s1=s1+(x.child("endDate").getValue(Date.class)).getDate()+"/"+(x.child("endDate").getValue(Date.class)).getMonth()+"/"+(x.child("endDate").getValue(Date.class)).getYear();
                endDate.setText(s1);
                String status1=x.child("status").getValue(String.class);
                if (status1.equals("waiting")) status.setText("ממתין לאישור");
                else if (status1.equals("active")) status.setText("פעילה");
                else if (status1.equals("rejected")) status.setText("הבקשה נדחתה");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
