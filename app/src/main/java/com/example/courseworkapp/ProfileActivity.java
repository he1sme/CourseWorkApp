package com.example.courseworkapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    DatabaseUser dbUser;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dbUser = new DatabaseUser(getApplicationContext());

        Intent values = getIntent();
        int userId = values.getIntExtra("userId", -1);

        User user = dbUser.getUser(Integer.toString(userId), null);

        TextView username = findViewById(R.id.profileUsername);
        TextView balance = findViewById(R.id.profileBalance);
        ImageView avatar = findViewById(R.id.profileAvatar);

        username.setText(user.getUsername());
        avatar.setImageResource(R.drawable.profile);
        balance.setText(Integer.toString(user.getBalance()) + "₽");
    }
}