package com.example.noa.arielibank;

public class LoanIdFund {
    int  id;
    double  fund;

    LoanIdFund(int id,double fund){
        this.id=id;
        this.fund=fund;
    }

    LoanIdFund(LoanIdFund other){
        this.id=other.id;
        this.fund=other.fund;
    }

    public  int getId(){return id; }
    public  double getFund(){return fund; }
}
