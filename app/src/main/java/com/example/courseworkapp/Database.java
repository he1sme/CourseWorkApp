package com.example.courseworkapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class Database {
    Cursor cursor;
    SQLiteDatabase db;
    DatabaseHelper helper;

    Database(Context ctx) {
        this.helper = new DatabaseHelper(ctx);
        this.db = helper.getReadableDatabase();
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
                    cursor.getInt(1),

            );
        }

    }

    public void close() {
        this.db.close();
        this.cursor.close();
    }
}
