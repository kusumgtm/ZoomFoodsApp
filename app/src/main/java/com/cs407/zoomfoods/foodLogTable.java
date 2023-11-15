package com.cs407.zoomfoods;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class foodLogTable {
    private static final String databaseName = "Zoom Foods Database";
    static SQLiteDatabase database;
    public foodLogTable(SQLiteDatabase sqLiteDatabase) {
        this.database = sqLiteDatabase;
    }

    private static final String CREATE_TABLE_FOODLOG = "CREATE TABLE foodLog ("
            + "user_id INTEGER REFERENCES users(pid),"
            + "log_id INTEGER PRIMARY KEY,"
            + "meal TEXT,"
            + "calories REAL,"
            + "protein REAL,"
            + "fat REAL,"
            + "carbs REAL,"
            + "sodium REAL,"
            + "potassium REAL,"
            + "date TEXT);";

    public static void createTable(Context context) {
        SQLiteDatabase db = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        db.execSQL(CREATE_TABLE_FOODLOG);
        db.close();
    }

    public void addFood(int user_id, int log_id, String date, String meal, String name, int calories, double protein, double carbohydrates,
                        double fat, int sodium, int potassium) {
        if (database.isOpen()) {
            database.execSQL("INSERT INTO foodLog (user_id, log_id, date, meal, name, calories, protein, carbohydrates, fat, sat_Fat, sodium, potassium)" +
                            "VALUES (user_id=?, log_id=?, date=?, meal=?, protein=?, carbohydrates=?, fat=?, sodium=?, potassium=?)",
                    new Object[]{user_id, log_id, date, meal, name, calories, protein, carbohydrates, fat, sodium, potassium});
        }
    }
    public void updateFood(int user_id, int log_id, String date, String meal, int calories, double protein, double carbohydrates,
                           double fat, int sodium, int potassium) {
        if (database.isOpen()) {
            database.execSQL("UPDATE user SET date=?, meal=?, calories=?, protein=?, carbohydrates=?, fat=?, sodium=?, potassium=? WHERE user_id=? AND log_id=?",
                    new Object[]{date, meal, calories, protein, carbohydrates, fat, sodium, potassium, user_id, log_id});
        }
    }

    public void deleteFood(int user_id, int log_id){
        String whereClause = "user_id=? AND log_id=?";
        String[] whereArgs = {String.valueOf(user_id), String.valueOf(log_id)};
        database.delete("foodLog", whereClause, whereArgs);
    }
}