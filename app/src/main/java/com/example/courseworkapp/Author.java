package com.example.courseworkapp;

import java.util.Date;

public class Author {
    private int id;
    private String name;
    private String surname;
    private Date birthday;

    public Author (int id, String name, String surname, Date birth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birth;
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public String getSurname() { return this.surname; }
    public Date getBirthday() { return this.birthday; }
}
