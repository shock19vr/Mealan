package com.app.mealan;

public class User {
    String number;
    String address;


    String food;

    public User()
    {

    }
    public User(String number, String address, String food) {
        this.number = number;
        this.address = address;
        this.food = food;
    }


    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    public String getFood() {
        return food;
    }

}
