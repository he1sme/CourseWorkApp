package com.example.courseworkapp;

import java.util.Date;

public class Author {
    private int id;
    private String name;
    private String surname;

    public Author (int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public String getSurname() { return this.surname; }
}
