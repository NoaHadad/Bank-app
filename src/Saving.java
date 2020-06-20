package com.example.noa.arielibank;

import java.util.Date;
import java.lang.Object;

public class Saving {

    int id;
    int accountId;
    double sum;
    int remainingMonths;
    double yearlyInterest;
    Date startDate;
    Date endDate;
    String status;

    Saving(int accountId,double sum,int years,int next_id){
            this.accountId=accountId;
            this.sum=sum;
            this.remainingMonths=years*12;
            double i=getInterestRate(sum,years);
            int i1=(int)(i*100);
            double i2=(double)i1/100;
            yearlyInterest=i2;
            id=next_id;
            this.startDate=new Date();
            startDate.setMonth(startDate.getMonth()+1);
            startDate.setYear(startDate.getYear()+1900);
            this.endDate=new Date(startDate.getYear()+years,startDate.getMonth(),startDate.getDate());
            this.status="active";
    }

    public static double getInterestRate(double sum,int years){
            return (sum/100000+years)*0.005;
    }

    public static double getFinalSum(double sum,int years,double monthly_interest){
            double monthlyInterest=monthly_interest;
            double finalSum=sum*Math.pow(1+monthlyInterest,years*12);
            return finalSum;
    }


    public int getId(){ return id;}
    public int getAccountId (){return accountId;}
    public double getSum(){return sum;}
    public int getRemainingMonths(){return remainingMonths;}
    public double getYearlyInterest(){return yearlyInterest; }
    public Date getStartDate(){return startDate;}
    public Date getEndDate(){return endDate;}
    public String getStatus(){return status;}
    }



