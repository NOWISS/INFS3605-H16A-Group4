package com.example.covidscreeningapp;

public class Employee {

    String Firstname, Lastname, mobile, destination, vax, result;
    String checkin,checkout;

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }


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

    public String getVax() {
        return vax;
    }

    public void setVax(String vax) {
        this.vax = vax;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Employee(String firstname, String lastname, String mobile, String destination, String vax, String result, String checkin, String checkout) {
        Firstname = firstname;
        Lastname = lastname;
        this.mobile = mobile;
        this.destination = destination;
        this.vax = vax;
        this.result = result;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Employee() {
    }
}
