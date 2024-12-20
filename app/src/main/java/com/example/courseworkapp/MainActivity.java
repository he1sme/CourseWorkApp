package com.example.courseworkapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Database db = new Database(getApplicationContext());

        db.getBooks();


//        GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 2);
//        Adapter adapter = new Adapter(MainActivity.this, images, titles, ids);
//
//        RecyclerView recyclerView = findViewById(R.id.mainRecycler);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setAdapter(adapter);
    }
}