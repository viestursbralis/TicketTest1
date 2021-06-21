package com.example.Ticket1;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Passenger {
    String type  ="";
    double countOfBags = 0;

    public Passenger(){}

    public Passenger(String t, double c){
        this.type = t;
        this.countOfBags = c;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCountOfBags() {
        return countOfBags;
    }

    public void setCountOfBags(double countOfBags) {
        this.countOfBags = countOfBags;
    }
}
