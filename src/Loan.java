package com.example.noa.arielibank;

import java.lang.Object;
import java.util.Date;

public class Loan {
    int id;
    int accountId;
    double remainingFund;
    double monthlyPayment;
    int remainingPayments;
    double yearlyInterest;
    Date startDate;
    Date endDate;
    String status;

    Loan(int accountId,double remainingFund, int remainingPayments,int id){
        this.accountId=accountId;
        this.remainingFund=remainingFund;
        this.remainingPayments=remainingPayments;
        double i=getInterestRate(remainingFund,remainingPayments);
        int i1=(int)(i*100);
        double i2=(double)i1/100;
        yearlyInterest=i2;
        double m=getMonthlyPayment(remainingFund,remainingPayments,i2/100);
        int m1=(int)m*100;
        monthlyPayment=(double)m1/100;
        this.startDate=new Date();
        startDate.setYear(startDate.getYear()+1900);
        startDate.setMonth(startDate.getMonth()+1);
        int years=remainingPayments/12;
        int months=(remainingPayments+startDate.getMonth())%12;
        int years1=0;
        if (months<startDate.getMonth())years1=1;
        this.endDate=new Date(startDate.getYear()+years+years1,months,startDate.getDate());
        status="waiting";
        this.id=id;

    }

   Loan(int id,int accountId,double remainingFund,double monthlyPayment,int remainingPayments,double yearlyInterest,Date startDate, Date endDate,String status){
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

    public static double getInterestRate(double sum,int months){
        return (sum/30000+(double)months/20);
    }

    public static double getMonthlyPayment(double sum,int months,double yearly_interest){
        double x=Math.pow(1+yearly_interest,months);
        double payment=sum*(yearly_interest/(1-1/x));
        return payment;
    }

    public int getId(){return id;}
    public int getAccountId(){return accountId;}
    public double getRemainingFund(){return remainingFund;}
    public int getRemainingPayments(){return remainingPayments;}
    public double getYearlyInterest(){return yearlyInterest;}
    public Date getStartDate(){return startDate;}
    public Date getEndDate(){return endDate;}
    public String getStatus(){return status;}

    public void setStatus(String status){this.status= status;}
    }

