package com.example.courseworkapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button enterButton = findViewById(R.id.loginEnterButton);
        Button goRegButton = findViewById(R.id.loginRegisterButton);

        db = new Database(getApplicationContext());
//        db.drop();


        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.loginEmail);
                EditText password = findViewById(R.id.loginPassword);

                String em = email.getText().toString();
                String passwd = password.getText().toString();

                if (em != null && passwd != null) {
                    User us = db.getUser(null, em);

                    if (us != null) {
                        if (us.getPassword().equals(passwd)) {
                            Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(loginIntent);
                        }
                    }
                }
            }
        });

        goRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterAcitivity.class);
                startActivity(registerIntent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
