package com.example.courseworkapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button enterButton = findViewById(R.id.loginEnterButton);
        Button goRegButton = findViewById(R.id.loginRegisterButton);

        db = new Database(getApplicationContext());

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.loginEmail);
                EditText password = findViewById(R.id.loginPassword);

                if (email != null && password != null) {
                    User us = db.getUser(null, email.getText().toString());
                    if (us != null) {
                        Log.i("LOGIN", "User found");
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
