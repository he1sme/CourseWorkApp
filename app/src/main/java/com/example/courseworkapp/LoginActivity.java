package com.example.courseworkapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button enter_button = findViewById(R.id.loginEnterButton);
        Button go_reg_button = findViewById(R.id.loginRegisterButton);

        dbHelper = new DatabaseHelper(getApplicationContext());
        db = dbHelper.getReadableDatabase();

        enter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.loginEmail);
                EditText password = findViewById(R.id.loginPassword);

                if (email != null && password != null) {
//                    String q = "SELECT email, password FROM " + DatabaseHelper.USER_TABLE + " WHERE password = ?;";
//                    db.rawQuery(q);
                    userCursor = db.rawQuery("SELECT email, password FROM " + DatabaseHelper.USER_TABLE + " WHERE email = email;", null);
                    userCursor.moveToNext()


                    Log.i("LOGIN", "Email and Password received");
                }
            }
        });

        go_reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterAcitivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        userCursor.close();
    }
}
