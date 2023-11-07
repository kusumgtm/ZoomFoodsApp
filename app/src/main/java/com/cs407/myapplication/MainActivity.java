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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialize views
        ImageView add_btn =findViewById(R.id.manual_button);
        ListView recordsListView = (ListView) findViewById(R.id.recyclerview);
        // SharedPreference. Get SQLiteDatabase Instance
        SharedPreferences sharedPreferences = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("records", Context.MODE_PRIVATE, null);
        WaterDBHelper dbHelper = new WaterDBHelper(sqLiteDatabase, getApplicationContext());
        // readRecords
        ArrayList<ListAdapter> records = dbHelper.readRecords();
        // Create an ArrayList<String> object for iterating over records
        ArrayList<String> displayRecords = new ArrayList<>();
        for(ListAdapter record: records){
            displayRecords.add(String.format("Time:%s\nTitle:%s\n", record.getTime(), record.getTitle()));
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, displayRecords);
        recordsListView.setAdapter(adapter);

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

        List<ListAdapter> items = new ArrayList<ListAdapter>();

    }
}