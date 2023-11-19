package com.cs407.zoomfoods;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class foodNutritionTable {
    private static final String databaseName = "Zoom Foods Database";
    static SQLiteDatabase database;
    public foodNutritionTable(SQLiteDatabase sqLiteDatabase) {
        this.database = sqLiteDatabase;
    }

    private static final String CREATE_TABLE_FOODNUTRITION = "CREATE TABLE foodNutrition ("
            + "name TEXT,"
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

    public void importData(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split("\\|");
                String name = values[0].trim();
                int calories = Integer.parseInt(values[1].trim());
                double protein = Double.parseDouble(values[2].trim());
                double carbohydrates = Double.parseDouble(values[3].trim());
                double fat = Double.parseDouble(values[4].trim());
                double sodium = Double.parseDouble(values[5].trim());
                double potassium = Double.parseDouble(values[6].trim());

                ContentValues contentValues = new ContentValues();
                contentValues.put("name", name);
                contentValues.put("calories", calories);
                contentValues.put("protein", protein);
                contentValues.put("carbohydrates", carbohydrates);
                contentValues.put("fat", fat);
                contentValues.put("sodium", sodium);
                contentValues.put("potassium", potassium);

                database.insert("foodNutrition", null, contentValues);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            database.close();
        }
    }
}