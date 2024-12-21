package com.example.courseworkapp;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    static Cart cart = new Cart();

    public static Cart getCart() {
        return cart;
    }
}
