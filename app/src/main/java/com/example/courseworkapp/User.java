package com.example.courseworkapp;

public class User {
    private int id;
    private String username;
    private int balance;
    private String email;
    private String password;

    User(int id, String username, int balance, String email, String password) {
        this.id = id;
        this.username = username;
        this.balance = balance;
        this.email = email;
        this.password = password;
    }

    int getId() { return this.id; }
    String getUsername() { return this.username; }
    int getBalance() { return this.balance; }
    String getEmail() { return this.email; }
    String getPassword() { return this.password; }

}
