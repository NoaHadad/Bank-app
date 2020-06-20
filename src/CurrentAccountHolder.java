package com.example.noa.arielibank;

public class CurrentAccountHolder{
    static String id="";
    static String firstName="";
    static String lastName="";
    static String password="";
    static String address="";
    static int yearOfBirth=0;
    static String maritalStatus="";
    static int yearOfJoin=0;

    CurrentAccountHolder(String id,String firstName,String lastName,String password,String address,int yearOfBirth,String maritalStatus,int yearOfJoin){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=password;
        this.address=address;
        this.yearOfBirth=yearOfBirth;
        this.maritalStatus=maritalStatus;
        this.yearOfJoin=yearOfJoin;
    }
    public static String getID(){
        return id;
    }
    public static String getFirstName(){
        return firstName;
    }
    public static String getLastName(){
        return lastName;
    }
    public static String getPassword(){
        return password;
    }
    public static String getAddress(){return address;}
    public static int getYearOfBirth(){return yearOfBirth;}
    public static String getMaritalStatus (){return maritalStatus;}
    public static int getYearOfJoin(){return yearOfJoin;}
}
