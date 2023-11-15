package com.cs407.zoomfoods;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class dailyGoalTable {
    private static final String databaseName = "Zoom Foods Database";
    static SQLiteDatabase database;
    public dailyGoalTable(SQLiteDatabase sqLiteDatabase) {
        this.database = sqLiteDatabase;
    }

    private static final String CREATE_TABLE_DAILYGOAL = "CREATE TABLE dailyGoal ("
            + "user_id INTEGER REFERENCES users(pid),"
            + "date TEXT,"
            + "total_calories REAL,"
            + "goal_calories REAL,"
            + "total_water TEXT,"
            + "goal_water TEXT,"
            + "PRIMARY KEY (user_id, date));";

    public static void createTable(Context context) {
        SQLiteDatabase db = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        db.execSQL(CREATE_TABLE_DAILYGOAL);
        db.close();
    }

    public void addEntry(int user_id, String date, int calories, int goal_calories, String total_water, String goal_water){
        if (database.isOpen()) {
            database.execSQL("INSERT INTO dailyGoal (user_id, date, calories, goal_calories, total_water, goal_water)" +
                            "VALUES (user_id=?, date=?, calories=?, goal_calories=?, total_water=?, goal_water=?)",
                    new Object[]{user_id, date, calories, goal_calories, total_water, goal_water});
        }
    }

    public void updateEntry(int user_id, String date, int calories, int goal_calories, String total_water, String goal_water){
        if (database.isOpen()) {
            database.execSQL("UPDATE dailyGoal SET calories=?, goal_calories=?, total_water=?, goal_water=?" +
                    "WHERE user_id=? AND date=?", new Object[]{calories, goal_calories, total_water, goal_water, user_id, date});
        }
    }

    public void deleteEntry(int user_id, String date){
        String whereClause = "user_id=? AND date=?";
        String[] whereArgs = {String.valueOf(user_id), date};
        if (database.isOpen()) {
            database.delete("dailyGoal", whereClause, whereArgs);
        }
    }

}
