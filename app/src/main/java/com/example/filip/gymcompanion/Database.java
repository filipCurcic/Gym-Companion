package com.example.filip.gymcompanion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="gcdb";
    public static final String TABLE_1 = "exercises_and_workouts";
    public static final String TABLE_2 = "sets_and_workouts";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String t1 = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT);",
                Exercise.TABLE_NAME, Exercise.FIELD_EXERCISE_ID, Exercise.FIELD_EXERCISE_NAME);


        String t2 = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER, %s INTEGER, FOREIGN KEY(%s) REFERENCES exercise(id));",
                Workout.TABLE_NAME, Workout.FIELD_WORKOUT_ID, Workout.FIELD_WORKOUT_DATE, "idexercise", "idexercise");

        String t3 = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER, %s INTEGER, %s INTEGER, FOREIGN KEY(%s) REFERENCES workout(id));",
                Set.TABLE_NAME, Set.FIELD_SET_ID, Set.FIELD_SET_WEIGHT, Set.FIELD_SET_REPS, "idworkout", "idworkout");

        /*String t4 = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER, %s INTEGER, FOREIGN KEY(%s) REFERENCES workout(id), FOREIGN KEY(%s) REFERENCES exercise(id));",
                TABLE_1, "idworkout", "idexercise", "idworkout", "idexercise"); */

        //String t5 = String.format("CREATE TABLE IF NOT EXISTS %s (INTEGER FOREIGN KEY(idworkout) REFERENCES workout(id), INTEGER FOREIGN KEY(idset) REFERENCES setTable(id));",
          //      TABLE_2);

        db.execSQL(t1);
        db.execSQL(t2);
        db.execSQL(t3);
        //db.execSQL(t4);
        //db.execSQL(t5);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(String.format("DROP TABLE IF EXISTS %s;", Exercise.TABLE_NAME));

        onCreate(db);
    }

}
