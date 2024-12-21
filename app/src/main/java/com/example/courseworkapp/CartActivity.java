package com.example.courseworkapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartActivity extends BaseActivity {
    DatabaseBook dbBook;
    DatabaseUser dbUser;
    int totalCost = 0;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();
        int userId = intent.getIntExtra("user_id", -1);

        dbBook = new DatabaseBook(getApplicationContext());

        ArrayList<Book> booksToCart = new ArrayList<Book>();

        for (Integer id : getCart().getOrderList()) {
            Book book = dbBook.getBook(id);
            booksToCart.add(book);
            totalCost += book.getCost();
        }

        TextView sumView = findViewById(R.id.cartPriceView);
        sumView.setText(Integer.toString(totalCost));

        RecyclerView recycler = findViewById(R.id.cartRecyler);
        LinearLayoutManager manager = new LinearLayoutManager(CartActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        CartAdapter adapter = new CartAdapter(booksToCart);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(manager);

        Button confirmOrderButton = findViewById(R.id.cartConfirmButton);
        confirmOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbUser = new DatabaseUser(getApplicationContext());
                User user = dbUser.getUser(Integer.toString(userId), null);
                if (user.getBalance() >= totalCost) {
                    getCart().clear();
                    user.setBalance(user.getBalance() - totalCost);
                    dbUser.updateUser(user.getId(), user);
                    Toast.makeText(getApplicationContext(), "Заказ отправлен в обработку!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "На балансе недостаточно средств", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
