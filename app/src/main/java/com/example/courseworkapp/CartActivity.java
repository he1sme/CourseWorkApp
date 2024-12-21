package com.example.courseworkapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartActivity extends BaseActivity {
    DatabaseBook dbBook;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();
        int userId = intent.getIntExtra("user_id", -1);

        dbBook = new DatabaseBook(getApplicationContext());

        Log.d("CART", getCart().getOrderList().toString());

        ArrayList<Book> booksToCart = new ArrayList<Book>();

        for (Integer id : getCart().getOrderList()) {
            booksToCart.add(dbBook.getBook(id));
        }

        RecyclerView recycler = findViewById(R.id.cartRecyler);
        LinearLayoutManager manager = new LinearLayoutManager(CartActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        CartAdapter adapter = new CartAdapter(booksToCart);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(manager);
    }
}
