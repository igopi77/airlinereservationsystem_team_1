package com.example.airlinereservastionsystem_team_1;

public class Flight {
    private int flightNo;
    private String flightName;
    private String flightTime;
    private int duration;
    private int price;
    private String departure;
    private String destination;

    // Constructors, getters, and setters

    public Flight(int flightNo, String flightName, String flightTime, int duration, int price,String departure,String destination) {
        this.flightNo = flightNo;
        this.flightName = flightName;
        this.flightTime = flightTime;
        this.duration = duration;
        this.price = price;
        this.departure = departure;
        this.destination = destination;
    }

    public int getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(int flightNo) {
        this.flightNo = flightNo;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public String getDeparture(){ return departure;}
    public void setDeparture(String departure){this.departure=departure;}
    public String getDestination(){ return destination;}
    public void setDestination(String destination){this.destination=destination;}

}
