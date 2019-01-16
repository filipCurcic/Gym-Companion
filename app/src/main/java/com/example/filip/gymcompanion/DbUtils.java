package com.example.filip.gymcompanion;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DbUtils {
    private Database database;

    public DbUtils(Database db) {
        this.database = db;
    }

    public static int getLastAddedRowId(Database dbs) {
        SQLiteDatabase db = dbs.getWritableDatabase();
        String queryLastRowInserted = "select last_insert_rowid()";
        final Cursor cursor = db.rawQuery(queryLastRowInserted, null);
        int lastInsertedId = 0;
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    lastInsertedId = cursor.getInt(0);
                }
            } finally {
                cursor.close();
            }
        }

        return lastInsertedId;

    }

    public static long getCurrentDate() {
        long secs = (new Date().getTime())/1000;
        return secs;
    }

    public static Date convertDate(long s) {
        long m = s*1000;
        return new Date(m);
    }

    public static String formatDate(Date d) {
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        return df.format(d);
    }

}
