package com.example.covidscreeningapp;

public class VisitorModel {

    private String Firstname, Lastname, mobile, destination;
    private boolean Seconddose, Thirddose,pass;

    // constructor
    public VisitorModel(String firstname, String lastname, String mobile, String destination) {
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.mobile = mobile;
        this.destination = destination;

    }
    public VisitorModel() { }

    // getter setter

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

    public boolean isSeconddose() {
        return Seconddose;
    }

    public void setSeconddose(boolean seconddose) {
        Seconddose = seconddose;
    }

    public boolean isThirddose() {
        return Thirddose;
    }

    public void setThirddose(boolean thirddose) {
        Thirddose = thirddose;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
    // toString is for printing the contents of this class
    @Override
    public String toString() {
        return "VisitorModel{" +
                "Firstname='" + Firstname + '\'' +
                ", Lastname='" + Lastname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", destination='" + destination + '\'' +
                ", Seconddose=" + Seconddose +
                ", Thirddose=" + Thirddose +
                ", pass=" + pass +
                '}';
    }
}
