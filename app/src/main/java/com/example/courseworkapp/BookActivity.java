package com.example.courseworkapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookActivity extends BaseActivity {
    DatabaseBook dbBooks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent values = getIntent();
        int bookId = values.getIntExtra("bookId", -1);

        dbBooks = new DatabaseBook(getApplicationContext());

        Book bk = dbBooks.getBook(bookId);

        TextView title = findViewById(R.id.bookTitle);
        TextView desc = findViewById(R.id.bookDescription);
        ImageView image = findViewById(R.id.bookImage);

        title.setText(bk.getName());
        desc.setText(bk.getDescription());
        image.setImageResource(R.drawable.book);

        Button addOrderButton = findViewById(R.id.bookEnterButton);
        Button removeOrderButton = findViewById(R.id.bookRemoveButton);

        addOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int result = cart.add(bk.getId());
                if (result != -1) {
                    Log.d("CART", getCart().getOrderList().toString());
                    Toast.makeText(getApplicationContext(), "Товар добавлен в коризну", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Товар уже находится в корзине", Toast.LENGTH_SHORT).show();
                }
            }
        });

        removeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = getCart().remove(
                        bk.getId()
                );
                if (result != -1) {
                    Toast.makeText(getApplicationContext(), "Товар удален из корзины", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Товар отсутствует в корзине", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
