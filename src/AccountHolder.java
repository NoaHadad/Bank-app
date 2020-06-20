package com.example.noa.arielibank;

public class AccountHolder {
    String id;
    String firstName;
    String lastName;
    String password;
    String address;
    int yearOfBirth;
    String maritalStatus;
    int yearOfJoin;

    AccountHolder(String id,String firstName,String lastName,String password,String address,int yearOfBirth,String maritalStatus,int yearOfJoin){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=password;
        this.address=address;
        this.yearOfBirth=yearOfBirth;
        this.maritalStatus=maritalStatus;
        this.yearOfJoin=yearOfJoin;
    }

    public String getID(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getPassword(){
        return password;
    }
    public String getAddress(){return address;}
    public int getYearOfBirth(){return yearOfBirth;}
    public String getMaritalStatus (){return maritalStatus;}
    public int getYearOfJoin(){return yearOfJoin;}

}
