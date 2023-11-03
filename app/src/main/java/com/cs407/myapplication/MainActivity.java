package com.cs407.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // SharedPreference
        SharedPreferences sharedPreferences = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        Context context = getApplicationContext();
        //SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase();
        //SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("")
        // add button
        ImageView add_btn =findViewById(R.id.manual_button);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, WaterAdd.class);
                startActivity(intent);
            }
        });
        // Progress Bar
        ProgressBar progressBar = findViewById(R.id.circularProgressbar);
        progressBar.setProgress(63);
        // Recycler
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        List<ListAdapter> items = new ArrayList<ListAdapter>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));
    }
}