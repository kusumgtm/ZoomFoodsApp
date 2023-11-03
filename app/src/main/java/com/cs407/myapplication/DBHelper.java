package com.cs407.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/*
* Perform all the CRUD operations related to database required for our application
* */
public class DBHelper {
    static SQLiteDatabase sqLiteDatabase;
    public DBHelper (SQLiteDatabase sqLiteDatabase) { this.sqLiteDatabase = sqLiteDatabase; }

    public static void createTable(){
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS records "+
                "(id INTEGER PRIMARY KEY, time TEXT, amount TEXT, title TEXT)");
    }

    public ArrayList<ListAdapter> readRecords(){
        createTable();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM records", null);
        int timeIndex = c.getColumnIndex("time");
        int amountIndex = c.getColumnIndex("amount");
        int titleIndex = c.getColumnIndex("title");
        c.moveToFirst();
        ArrayList<ListAdapter> recordList = new ArrayList<>();
        while(!c.isAfterLast()){
            String time = c.getString(timeIndex);
            String title = c.getString(titleIndex);
            String amount = c.getString(amountIndex);

            ListAdapter record = new ListAdapter(time, amount,  title);
            recordList.add(record);
        }
        c.close();
        return recordList;
    }

    public void saveRecord(String time, String amount, String title){
        createTable();
        sqLiteDatabase.execSQL("INSERT INTO records (time, amount, title) VALUES (?, ?, ?)",
                new String[]{time, amount, title});
    }

    public void updateRecord(String time, String amount, String title, String original_time, String original_amount, String original_title){
        createTable();
        sqLiteDatabase.execSQL("UPDATE records set time=?, amount=?, title=? where time=? AND amount=? AND title=?",
                new String[]{time, amount, title, original_time, original_amount, original_title});
    }

    public void deleteRecord(String time, String amount, String title){
        createTable();
        sqLiteDatabase.execSQL("DELETE FROM records WHERE time=? AND amount=? AND title=?",
            new String[]{time, amount, title});
    }
}
