package com.example.Ticket1;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement
public class Buss {
    public String destination = "";
    public List<Passenger> passengers;
    public Buss(){}
    public Buss(String dest, List<Passenger>pass){
        this.destination = dest;
        this.passengers = pass;


    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
