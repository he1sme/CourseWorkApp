package com.example.courseworkapp;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

public class User {
    private int id;
    private String username;
    private int balance;
    private String email;
    private String password;

    User(int id, String username, int balance, String email, String password) {
        this.id = id;
        this.username = username;
        this.balance = balance;
        this.email = email;
        this.password = password;
    }

    int getId() { return this.id; }
    String getUsername() { return this.username; }
    int getBalance() { return this.balance; }
    String getEmail() { return this.email; }
    String getPassword() { return this.password; }

    public static boolean checkData(Context ctx, String email, String password) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length() >= 8) {
            Toast.makeText(ctx, "Email подтвержден", Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(ctx, "Ошибка в электронном адресе или пароле(минимум 8 символов)", Toast.LENGTH_SHORT).show();
        return false;
    }

}
