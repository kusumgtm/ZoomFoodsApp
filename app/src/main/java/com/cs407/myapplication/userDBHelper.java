package com.cs407.zoomfoods;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

public class userDBHelper {
    static SQLiteDatabase sqLiteDatabase;

    public userDBHelper(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public static void createTable() {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS user " +
                "(date INTEGER PRIMARY KEY, name TEXT, calories INTEGER, protein REAL, carbohydrates REAL, fat REAL, sat_Fat REAL," +
                "sodium INTEGER, potassium INTEGER)");
    }

    public void addFood(int date, String name, int calories, double protein, double carbohydrates,
                        double fat, double sat_Fat, int sodium, int potassium) {
        if (sqLiteDatabase.isOpen()) {
            sqLiteDatabase.execSQL("INSERT INTO user (date, name, calories, protein, carbohydrates, fat, sat_Fat, sodium, potassium)" +
                            "VALUES (date=?, name=?, protein=?, carbohydrates=?, fat=?, sat_Fat=?, sodium=?, potassium=?)",
                    new Object[]{date, name, calories, protein, carbohydrates, fat, sat_Fat, sodium, potassium});
        }
    }

    public void updateFood(int date, String name, int calories, double protein, double carbohydrates,
                           double fat, double sat_Fat, int sodium, int potassium) {
        if (sqLiteDatabase.isOpen()) {
            sqLiteDatabase.execSQL("UPDATE user SET calories=?, protein=?,carbohydrates=?, fat=?, sat_fat=?, sodium=?, potassium=? WHERE name=? AND date=?",
                    new Object[]{calories, protein, carbohydrates, fat, sat_Fat, sodium, potassium, name, date});
        }
    }
}