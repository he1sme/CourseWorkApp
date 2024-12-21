package com.example.courseworkapp;

import java.util.Date;

public class Book {
    private int id;
    private String name;
    private Author author;
    private String description;
    private String isbn;
    private int public_date;
    private int cost;

    public Book (int id, String name, String desc, int cost, Author author, String isbn, int date) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.author = author;
        this.isbn = isbn;
        this.public_date = date;
        this.cost = cost;
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public Author getAuthor() { return this.author; }
    public String getDescription() { return this.description; }
    public String getIsbn() { return this.isbn; }
    public int getDate() { return this.public_date; }
    public int getCost() { return this.cost; }

    public void setAuthor(Author new_author) { this.author = new_author; }
}
