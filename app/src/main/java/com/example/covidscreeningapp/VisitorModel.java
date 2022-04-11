package com.example.covidscreeningapp;

public class VisitorModel {

    private String Firstname;
    private String Lastname;
    private String mobile;
    private String destination;
    private String checkin;
    private String checkout;
    private String color;


    // constructor
    public VisitorModel(String firstname, String lastname, String mobile, String destination, String checkin, String checkout, String color) {
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.mobile = mobile;
        this.destination = destination;
        this.checkin = checkin;
        this.checkout = checkout;
        this.color=color;

    }
    public VisitorModel() { }

    // getter setter
    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public String getCheckin() { return checkin; }

    public void setCheckin(String checkin) { this.checkin = checkin; }

    public String getCheckout() { return checkout; }

    public void setCheckout(String checkout) { this.checkout = checkout; }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


}
