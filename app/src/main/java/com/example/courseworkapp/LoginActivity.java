package com.example.courseworkapp;

import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    DatabaseUser dbUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button enterButton = findViewById(R.id.loginEnterButton);
        Button goRegButton = findViewById(R.id.loginRegisterButton);

        dbUser = new DatabaseUser(getApplicationContext());
//        dbUser.drop();


        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.loginEmail);
                EditText password = findViewById(R.id.loginPassword);

                String em = email.getText().toString();
                String passwd = password.getText().toString();

                if (!em.isEmpty() && !passwd.isEmpty()) {
                    User us = dbUser.getUser(null, em);
                    if (us != null) {
                        if (us.getPassword().equals(passwd)) {
                            Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                            loginIntent.putExtra("user_id", us.getId());
                            startActivity(loginIntent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
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
    }
}
