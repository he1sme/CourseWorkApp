package com.example.courseworkapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "Books.db";
    static final int DATABASE_VERSION = 1;

    static final String USER_TABLE = "users";
    static final String RATING_TABLE = "ratings";
    static final String BOOK_TABLE = "books";
    static final String REVIEW_TABLE = "reviews";
    static final String AUTHOR_TABLE = "authors";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_authors_query = "CREATE TABLE IF NOT EXISTS " + AUTHOR_TABLE + "("
                + "id integer primary key autoincrement,"
                + "name varchar(80),"
                + "surname varchar(80));";

        String create_books_query = "CREATE TABLE IF NOT EXISTS " + BOOK_TABLE + "("
                + "id integer primary key autoincrement,"
                + "name varchar(60) not null,"
                + "description text,"
                + "isbn varchar(90),"
                + "public_date integer,"
                + "cost integer,"
                + "author_id integer,"
                + "FOREIGN KEY (author_id) REFERENCES " + AUTHOR_TABLE + "(id));";

        String create_users_query = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + "("
                + "id integer primary key autoincrement,"
                + "username varchar(80) not null unique,"
                + "balance integer,"
                + "password varchar(128) not null,"
                + "email varchar(120) not null unique);";

        String create_reviews_query = "CREATE TABLE IF NOT EXISTS " + REVIEW_TABLE + "("
                + "id integer primary key autoincrement,"
                + "user_id integer,"
                + "book_id integer,"
                + "review_text text not null,"
                + "FOREIGN KEY (user_id) REFERENCES " + USER_TABLE + "(id),"
                + "FOREIGN KEY (book_id) REFERENCES " + BOOK_TABLE + "(id));";

        String create_ratings_query = "CREATE TABLE IF NOT EXISTS " + RATING_TABLE + "("
                + "id integer primary key autoincrement,"
                + "user_id integer,"
                + "book_id integer,"
                + "rating integer,"
                + "created_at date,"
                + "FOREIGN KEY (user_id) REFERENCES " + USER_TABLE + "(id),"
                + "FOREIGN KEY (book_id) REFERENCES " + BOOK_TABLE + "(id));";

        db.execSQL(create_authors_query);
        db.execSQL(create_books_query);
        db.execSQL(create_users_query);
        db.execSQL(create_reviews_query);
        db.execSQL(create_ratings_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS ";
        db.execSQL(drop + RATING_TABLE);
        db.execSQL(drop + REVIEW_TABLE);
        db.execSQL(drop + USER_TABLE);
        db.execSQL(drop + BOOK_TABLE);
        db.execSQL(drop + AUTHOR_TABLE);
        onCreate(db);
    }
}
