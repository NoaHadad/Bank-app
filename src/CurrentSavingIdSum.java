package com.example.noa.arielibank;

public class CurrentSavingIdSum {

    static int  id;
    static double sum;

    CurrentSavingIdSum(int id,double sum){
        this.id=id;
        this.sum=sum;
    }

    CurrentSavingIdSum( SavingIdSum other){
        this.id=other.id;
        this.sum=other.sum;
    }

    public static int getId(){return id; }
    public static double getSum(){return sum; }
}
