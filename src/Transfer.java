package com.example.noa.arielibank;

public class Transfer {
    int from;
    int to;
    double sum;

    Transfer(int from,
            int to,
            double sum){
        this.from=from;
        this.to=to;
        this.sum=sum;
    }

    public int getFrom(){return from;}
    public int getTo(){return to;}
    public double getSum(){return sum;}
}
