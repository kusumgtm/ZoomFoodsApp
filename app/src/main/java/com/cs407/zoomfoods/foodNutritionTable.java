package com.cs407.zoomfoods;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class foodNutritionTable {
    private static final String databaseName = "Zoom Foods Database";

    private static final String CREATE_TABLE_FOODNUTRITION = "CREATE TABLE foodNutrition ("
            + "calories REAL,"
            + "protein REAL,"
            + "fat REAL,"
            + "carbs REAL,"
            + "sodium REAL,"
            + "potassium REAL);";


    public static void createTable(Context context) {
        SQLiteDatabase db = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        db.execSQL(CREATE_TABLE_FOODNUTRITION);
        db.close();
    }
}