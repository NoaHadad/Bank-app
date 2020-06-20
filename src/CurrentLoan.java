package com.example.noa.arielibank;

public class CurrentLoan {
    public void setStartDate(String startDate) {
    }

    static int id;
    static int accountId;
    static double remainingFund;
    static double monthlyPayment;
    static int remainingPayments;
    static double yearlyInterest;
    static String startDate;
    static String endDate;
    static String status;

    CurrentLoan(int id,int accountId,double remainingFund,double monthlyPayment,int remainingPayments,double yearlyInterest,String startDate,String endDate,String status){
        this.id=id;
        this.accountId=accountId;
        this.remainingFund=remainingFund;
        this.monthlyPayment=monthlyPayment;
        this.remainingPayments=remainingPayments;
        this.yearlyInterest=yearlyInterest;
        this.startDate=startDate;
        this.endDate=endDate;
        this.status=status;

    }


    public static int getId(){return id;}
    public static int getAccountId(){return accountId;}
    public static double getRemainingFund(){return remainingFund;}
    public static int getRemainingPayments(){return remainingPayments;}
    public static double getYearlyInterest(){return yearlyInterest;}
    public static String getStartDate(){return startDate;}
    public static String getEndDate(){return endDate;}
    public static String getStatus(){return status;}
}


