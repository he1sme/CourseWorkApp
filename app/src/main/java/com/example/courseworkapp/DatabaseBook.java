package com.example.courseworkapp;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseBook extends Database {
    DatabaseBook(Context ctx) {
        super(ctx);
    }

    public Book insertBook(Book book) {
        ContentValues values = new ContentValues();
        values.put("name", book.getName());
        values.put("description", book.getDescription());
        values.put("isbn", book.getIsbn());
        values.put("public_date", book.getDate());
        values.put("cost", book.getCost());
        int authorId = book.getAuthor().getId();
        values.put("author_id", authorId);

        long result = this.db.insert(DatabaseHelper.BOOK_TABLE, null, values);

        if (result != -1) {
            return book;
        }
        return null;
    }

    public Book getBook(int id) {
        String query = "SELECT books.id as bk_id, books.name as bk_name, books.description, books.isbn, books.public_date, books.cost, authors.id as auth_id, authors.name as auth_name, authors.surname FROM " + DatabaseHelper.BOOK_TABLE + " INNER JOIN authors ON books.author_id = authors.id WHERE books.id = ?;";
        this.cursor = this.db.rawQuery(query, new String[] {Integer.toString(id)});

        if (this.cursor.moveToNext()) {
            return new Book(
                    this.cursor.getInt(0),
                    this.cursor.getString(1),
                    this.cursor.getString(2),
                    this.cursor.getInt(5),
                    new Author(this.cursor.getInt(6), this.cursor.getString(7), this.cursor.getString(8)),
                    this.cursor.getString(3),
                    this.cursor.getInt(4)
            );

        }
        return null;
    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> results = new ArrayList<Book>();
        String query = "SELECT books.id as bk_id, books.name as bk_name, books.description as description, books.isbn as isbn, books.public_date as date, books.cost, authors.id as auth_id, authors.name as auth_name, authors.surname as surname FROM books INNER JOIN authors ON books.author_id = authors.id;";
        this.cursor = this.db.rawQuery(query,null);
        while (this.cursor.moveToNext()) {
            Book current_book = new Book(
                    this.cursor.getInt(0),
                    this.cursor.getString(1),
                    this.cursor.getString(2),
                    this.cursor.getInt(5),
                    new Author(this.cursor.getInt(6), this.cursor.getString(7), this.cursor.getString(8)),
                    this.cursor.getString(3),
                    this.cursor.getInt(4)
            );
            results.add(current_book);
        }
        return results;
    }
}
