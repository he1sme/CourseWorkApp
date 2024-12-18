package com.example.courseworkapp;

import java.util.Date;

public class Book {
    private int id;
    private String name;
    private Author author;
    private String description;
    private String isbn;
    private Date public_date;

    public Book (int id, String name, String desc, Author author, String isbn, Date date) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.author = author;
        this.isbn = isbn;
        this.public_date = date;
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public Author getAuthor() { return this.author; }
    public String getDescription() { return this.description; }
    public String getIsbn() { return this.isbn; }
    public Date getDate() { return this.public_date; }
}
