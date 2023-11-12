package com.cs407.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ActivityWater extends AppCompatActivity {
    private double currrent_total = 0;
    private int current_progtress = 0;

    private ImageView add_btn;
    private NonScrollListView recordsListView;
    private ProgressBar progressBar;
    private TextView progessText;
    private TextView intake;
    private TextView remaining;
    private void initializeViews(){
        add_btn =findViewById(R.id.manual_button);
        recordsListView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.circularProgressbar);
        progessText = findViewById(R.id.progess_text);
        intake = findViewById(R.id.tv_intake_value);
        remaining = findViewById(R.id.tv_remaining);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_main);
        initializeViews();
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("records", Context.MODE_PRIVATE, null);
        WaterDBHelper dbHelper = new WaterDBHelper(sqLiteDatabase, getApplicationContext());
        // readRecords
        ArrayList<Record> records = dbHelper.readRecords("username1");
        ArrayAdapter adapter = new CustomListAdapter(this, records);
        recordsListView.setAdapter(adapter);
        // ListView onClickListener
        recordsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Record selected_item = (Record) parent.getItemAtPosition(position);
                Intent intent = new Intent(context, WaterAdd.class);
                Log.i("Information", "selected_item.getAmount(): " + selected_item.getAmount());
                Log.i("Information", "selected_item.getTitle(): " + selected_item.getTitle());
                Log.i("Information", "selected_item.getId(): " + selected_item.getId());
                intent.putExtra("recordId", position);
                intent.putExtra("recordIndex", selected_item.getId());
                startActivity(intent);
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ActivityWater.this, WaterAdd.class);
                startActivity(intent);
            }
        });
        // Progress Bar, percentage text
        for (Record record: records){
            currrent_total += Double.parseDouble(record.getAmount());
        }
        int total = Math.min((int) currrent_total, 69);
        current_progtress = Math.min((int) ((currrent_total/69.00)*100), 100);
        int remain = 69 - total;
        progressBar.setProgress(current_progtress);
        progessText.setText(current_progtress+"%");
        intake.setText(((int) currrent_total)+" fl oz");
        remaining.setText("Today I still need to drink " + remain + " oz");
    }

}