package com.example.noa.arielibank;

import java.util.Date;

public class Current_account {

    static int id;
    static int employeeNum;
    static String clientId;
    static double balance;
    static int openingYear;
    static String status;


    Current_account(int id,  int employeeNum, String clientId, double balance,int openingYear,String status){
        this.id=id;
        this.employeeNum=employeeNum;
        this.clientId=clientId;
        this.balance=balance;
        this.openingYear=openingYear;
        this.status=status;
    }

    Current_account(Account other){
        this.id=other.id;
        this.employeeNum=other.employeeNum;
        this.clientId=other.clientId;
        this.balance=other.balance;
        this.openingYear=other.openingYear;
        this.status=status;
    }

    Current_account(){}


    public static int getID(){ return id; }
    public static int getEmployeeNum(){ return employeeNum;}
    public static String clientID(){ return clientId; }
    public static double getBalance(){ return balance; }
    public static int getOpeningYear(){ return openingYear;}
    public static String getStatus(){return status;}
    public static void decreaseBalance(double x){balance-=x;}
    public static void increaseBalance(double x){balance+=x;}
}


