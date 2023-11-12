package com.cs407.cs407zoomfoods;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class usersTable {
    private static final String databaseName = "Zoom Foods Database";
    private int pid = 0;
    static SQLiteDatabase database;
    public usersTable(SQLiteDatabase sqLiteDatabase) {
        this.database = sqLiteDatabase;
    }

    private static final String CREATE_TABLE_USERS = "CREATE TABLE users ("
            + "pid INTEGER PRIMARY KEY,"
            + "username TEXT,"
            + "email TEXT);";

    public static void createTable(Context context) {
        SQLiteDatabase db = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        db.execSQL(CREATE_TABLE_USERS);
    }

    public void addUser(String username, String email) {
        if (database.isOpen()) {
            database.execSQL("INSERT INTO users (pid, username, email)" +
                    "VALUES (pid=?, username=?, email=?)", new Object[]{pid++, username, email});
        }
    }

    public void deleteUser(String username, String email) {
        String whereClause = "username=? AND email=?";
        String[] whereArgs = {username, email};
        if (database.isOpen()) {
            database.delete("users", whereClause, whereArgs);
        }
    }
}


