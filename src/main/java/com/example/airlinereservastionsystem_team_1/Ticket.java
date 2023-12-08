package com.example.airlinereservastionsystem_team_1;

public class Ticket {

    private int ticketId;
    private String noOfPerson;
    private String emailId;
    private String name;
    private String phoneNumber;

    public Ticket(int ticketId, String noOfPerson, String emailId, String name, String phoneNumber) {
        this.ticketId = ticketId;
        this.noOfPerson = noOfPerson;
        this.emailId = emailId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getNoOfPerson() {
        return noOfPerson;
    }

    public void setNoOfPerson(String noOfPerson) {
        this.noOfPerson = noOfPerson;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
