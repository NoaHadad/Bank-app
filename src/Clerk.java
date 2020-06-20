package com.example.noa.arielibank;

import java.util.Date;

public class Clerk {
    int id;
    int employeeNum;
    String firstName;
    String lastName;
    String familyStatus;
    String address;
    String password;
    String workerStatus;
    Date startDate;
    String departmentName;

    Clerk(int id,int employeeNum,String firstName,  String lastName, String familyStatus, String address,String password,String departmentName){
        this.id=id;
        this.employeeNum=employeeNum;
        this.firstName=firstName;
        this.lastName=lastName;
        this.familyStatus=familyStatus;
        this.address=address;
        this.workerStatus="active";
        this.departmentName=departmentName;
        this.password=password;
        this.startDate=new Date();
        startDate.setYear(startDate.getYear()+1900);
    }

    public int getId(){return id;}
    public int getEmployeeNum(){return id;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getWorkerStatus(){return workerStatus;}
    public String getAddress(){return address;}
    public String getFamilyStatus(){return familyStatus;}
    public Date getStartDate(){return startDate;}
    public String getDepartmentName(){return departmentName;}
    public String getPassword(){return password;}



}
