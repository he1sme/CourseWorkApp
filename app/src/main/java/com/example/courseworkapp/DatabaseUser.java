package com.example.courseworkapp;

import android.content.ContentValues;
import android.content.Context;

public class DatabaseUser extends Database {
    public DatabaseUser(Context ctx) {
        super(ctx);

    }

    public User getUser(String id, String email) {
        if (id != null) {
            this.cursor = this.db.rawQuery("SELECT * FROM " + DatabaseHelper.USER_TABLE + " WHERE id = ?;", new String[]{id});
        }
        if (email != null) {
            this.cursor = this.db.rawQuery("SELECT * FROM " + DatabaseHelper.USER_TABLE +  " WHERE email = ?;", new String[] {email});
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

    public User updateUser(int id, User user) {
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("balance", user.getBalance());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());

        long result = this.db.update(DatabaseHelper.USER_TABLE, values, Integer.toString(id), null);

        if (result != -1) {
            return user;
        }
        return null;
    }
}
