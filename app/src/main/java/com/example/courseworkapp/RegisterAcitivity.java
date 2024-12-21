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
    DatabaseUser dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_register);

        Button regButton = findViewById(R.id.regEnterButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.regUsername);
                EditText email = findViewById(R.id.regEmail);
                EditText password = findViewById(R.id.regPassword);

                dbUser = new DatabaseUser(getApplicationContext());

                String us = username.getText().toString();
                String em = email.getText().toString();
                String passwd = password.getText().toString();

                if (!us.isEmpty() && !em.isEmpty() && !passwd.isEmpty()) {
                    User user = new User(-1, us, 0, em, passwd);
                    try {
                        if (User.checkData(getApplicationContext(), em, passwd)) {
                            dbUser.insertUser(user);
                            Intent mainIntent = new Intent(RegisterAcitivity.this, MainActivity.class);
                            startActivity(mainIntent);
                        }
                    } catch (SQLiteConstraintException sql_err) {
                        Log.e("[REGISTER] SQL EXCEPTION", sql_err.getMessage().toString());
                    }

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
