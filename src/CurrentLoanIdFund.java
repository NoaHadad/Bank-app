package com.example.noa.arielibank;

public class CurrentLoanIdFund {
    static int   id;
    static double  fund;

    CurrentLoanIdFund(int id,double fund){
        this.id=id;
        this.fund=fund;
    }

    CurrentLoanIdFund(LoanIdFund other){
        this.id=other.id;
        this.fund=other.fund;
    }

    public  static int getId(){return id; }
    public  static double getFund(){return fund; }
}
