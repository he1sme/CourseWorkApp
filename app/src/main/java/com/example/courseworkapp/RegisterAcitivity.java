package com.example.courseworkapp;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterAcitivity extends AppCompatActivity {
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_register);

        Button regButton = findViewById(R.id.regEnterButton);
//        db = new Database(getApplicationContext());
//        db.drop();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.regUsername);
                EditText email = findViewById(R.id.regEmail);
                EditText password = findViewById(R.id.regPassword);

                db = new Database(getApplicationContext());

                String us = username.getText().toString();
                String em = email.getText().toString();
                String passwd = password.getText().toString();

                if (!us.isEmpty() && !us.isEmpty() && !us.isEmpty()) {
                    User user = new User(
                        -1, us, 0, em, passwd
                    );
                    try {
                        User result = db.insertUser(user);
                        if (result != null) {
                            Log.d("USER ADDED", result.getEmail());
                        }

                        Intent mainIntent = new Intent(RegisterAcitivity.this, MainActivity.class);
                        startActivity(mainIntent);
                    } catch (SQLiteConstraintException sql_err) {
                        Log.e("Sql exception", sql_err.getMessage().toString());
                    }

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
