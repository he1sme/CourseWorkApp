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

    public void close() {
        if (this.db.isOpen()) {
            this.db.close();
        }
    }

    public void drop() {
        this.helper.onUpgrade(this.db, 0, 0);
    }

}
