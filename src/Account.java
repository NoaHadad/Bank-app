package com.example.noa.arielibank;

import java.util.Date;

public class Account {
    int id;
    int employeeNum;
    String clientId;
    double balance;
    int openingYear;
    String status;


    Account( int employeeNum, String clientId, double balance,int openingYear, int id,String status){
        this.id=id;
        this.employeeNum=employeeNum;
        this.clientId=clientId;
        this.balance=balance;
        this.openingYear=openingYear;
        this.status=status;
    }

    Account( Account other){
        this.id=other.id;
        this.employeeNum=other.employeeNum;
        this.clientId=other.clientId;
        this.balance=other.balance;
        this.openingYear=other.openingYear;
        this.status=other.status;
    }

    Account(Current_account other){
        this.id=other.id;
        this.employeeNum=other.employeeNum;
        this.clientId=other.clientId;
        this.balance=other.balance;
        this.openingYear=other.openingYear;
        this.status=other.status;
    }

    public  int getID(){
        return id;
    }
    public int getEmployeeNum(){ return employeeNum;}
    public String clientID(){
        return clientId;
    }
    public double getBalance(){
        return balance;
    }
    public int getOpeningYear(){ return openingYear;}
    public String getStatus(){return status;}



    public void decreaseBalance(double x){balance-=x;}


}
