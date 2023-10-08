package com.example.shoppingcartreservationsystem.models;

import jakarta.persistence.Id;

public class User {
    @Id
    private String id;

    public String userName;
    public String firstName;
    public String lastName;
    public String email;
    private String password;

    public User(String userName, String firstName, String lastName, String email, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getId(){
        return this.id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
