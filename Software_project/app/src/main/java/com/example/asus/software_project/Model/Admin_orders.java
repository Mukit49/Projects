package com.example.asus.software_project.Model;

public class Admin_orders {
    private String name,phone,date,address,city,state,time,totalamount;


    public Admin_orders() {

    }

    public Admin_orders(String name, String phone, String date, String address, String city, String state, String time, String totalamount) {
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.address = address;
        this.city = city;
        this.state = state;
        this.time = time;
        this.totalamount = totalamount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }
}
