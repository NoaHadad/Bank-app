package com.example.noa.arielibank;

public class CurrentClerk {

    static int employeeNum;

    CurrentClerk(int employeeNum){
        this.employeeNum=employeeNum;
    }

    public static int getEmployeeNum(){return employeeNum;}

    public static void setEmployeeNum(int num){ employeeNum=num;}
}
