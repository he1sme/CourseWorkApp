package com.example.courseworkapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5)
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

    public void close() {
        if (this.db.isOpen()) {
            this.db.close();
        }
    }

    public void drop() {
        this.helper.onUpgrade(this.db, 0, 0);
    }
}
