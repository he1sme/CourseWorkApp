package com.example.courseworkapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseBook dbBook;
    DatabaseAuthor dbAuthor;
    ArrayList<Book> books;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent values = getIntent();
        int loggedId = values.getIntExtra("user_id", -1);

        dbBook = new DatabaseBook(getApplicationContext());
        dbAuthor = new DatabaseAuthor(getApplicationContext());

//        dbAuthor.insertAuthor(new Author(-1, "Fyodor", "Dostoevsky"));
//        dbAuthor.insertAuthor(new Author(-1, "Dazai", "Osamu"));

        Author fyodor = dbAuthor.getAuthor(1);
        Author dazai = dbAuthor.getAuthor(2);

//        dbBook.insertBook(new Book(1, "Crime and punishment", "test", 120, fyodor, "123", 1865));
//        dbBook.insertBook(new Book(2, "No Longer Human", "test", 160, dazai, "1234", 1948));
//
//        dbBook.insertBook(new Book(3, "Idiot",
//                "Главный герой романа Фёдора Достоевского «Идиот» — князь Лев Николаевич Мышкин. 12 Это последний представитель древнего обедневшего рода. В юности у него проявилась эпилепсия, и его отправили на лечение в Швейцарию, где он провёл четыре года.",
//                220, fyodor, "12345", 1867));

        books = dbBook.getBooks();

        ImageButton cartButton = findViewById(R.id.mainCartButton);
        ImageButton profileButton = findViewById(R.id.mainProfileButton);
        profileButton.setImageResource(R.drawable.profile);

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(MainActivity.this, CartActivity.class);
                cartIntent.putExtra("user_id", loggedId);
                startActivity(cartIntent);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                profileIntent.putExtra("userId", loggedId);
                startActivity(profileIntent);
            }
        });

        GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 2);
        adapter = new Adapter(MainActivity.this, loggedId, books);

        RecyclerView recyclerView = findViewById(R.id.mainRecycler);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        SearchView search = findViewById(R.id.mainSearch);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                queryFilter(newText);
                return true;
            }
        });
    }

    private void queryFilter(String text) {
        ArrayList<Book> filteredBooks = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredBooks.add(book);
            }
        }
        adapter.setList(filteredBooks);
    }
}