package com.app.mealan;

public class User {
    String number;
    String address;


    String food;
    String id;

    public User()
    {

    }
    public User(String number, String address, String food, String id) {
        this.number = number;
        this.address = address;
        this.food = food;
        this.id = id;
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

    public String getId() {
        return id;
    }


}
