package com.example.courseworkapp;

import android.content.ContentValues;
import android.content.Context;

public class DatabaseAuthor extends Database{
    DatabaseAuthor(Context ctx) {
        super(ctx);
    }

    public Author insertAuthor(Author author) {
        ContentValues values = new ContentValues();
        values.put("name", author.getName());
        values.put("surname", author.getSurname());

        long result = this.db.insert(DatabaseHelper.AUTHOR_TABLE, null, values);
        if (result != -1){
            return author;
        }
        return null;
    }

    public Author getAuthor(int id) {
        this.cursor = this.db.rawQuery("SELECT * FROM " + DatabaseHelper.AUTHOR_TABLE + " WHERE id = ?;", new String[]{Integer.toString(id)});
        if (cursor.moveToNext()) {
            return new Author(
                    this.cursor.getInt(0),
                    this.cursor.getString(1),
                    this.cursor.getString(2)
            );
        }
        return null;
    }
}
