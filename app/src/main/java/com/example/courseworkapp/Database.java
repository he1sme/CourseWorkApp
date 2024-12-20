package com.example.courseworkapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class Database {
    Cursor cursor;
    SQLiteDatabase db;
    DatabaseHelper helper;

    Database(Context ctx) {
        this.helper = new DatabaseHelper(ctx);
        this.db = this.helper.getReadableDatabase();
    }

    public User getUser(String id, String email) {
        if (id != null) {
            this.cursor = this.db.rawQuery("SELECT * FROM users WHERE id = ?", new String[]{id});
        }
        if (email != null) {
            this.cursor = this.db.rawQuery("SELECT * FROM users WHERE email = ?", new String[] {email});
        }
        if (cursor.moveToNext()) {
            return new User(
                    this.cursor.getInt(0),
                    this.cursor.getString(1),
                    this.cursor.getInt(2),
                    this.cursor.getString(4),
                    this.cursor.getString(3)
            );
        }
        return null;
    }

    public User insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("balance", user.getBalance());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());

        long result = this.db.insert(DatabaseHelper.USER_TABLE, null, values);

        if (result != -1) {
            return user;
        }
        return null;
    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> results = new ArrayList<Book>();
        this.cursor = this.db.rawQuery("SELECT books.id, books.name, author.id, author.name, books.author_id FROM books, authors INNER JOIN books WHERE books.author_id = authors.id;", null);
        while (this.cursor.moveToNext()) {
            Log.d("id ?", Integer.toString(this.cursor.getInt(1)));
            Log.d("username ?", this.cursor.getString(2));
            Log.d("balance ?", Integer.toString(this.cursor.getInt(3)));
            Log.d("email ?", this.cursor.getString(4));
        }
        return results;
    }

    public void close() {
        if (this.db.isOpen()) {
            this.db.close();
        }
    }

    public void drop() {
        this.helper.onUpgrade(this.db, 0, 0);
    }
}
