package com.example.filip.gymcompanion;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkoutRepository {

    private Database database;
    private DbUtils utils;

    public WorkoutRepository(Database db) {
        this.database = db;
    }

    public void addWorkout(String date) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Workout.FIELD_WORKOUT_DATE, date);
        db.insert(Workout.TABLE_NAME, null, cv);
    }

    public void addWorkoutInExercise(int exId) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("idworkout", getLastAddedRowId());
        cv.put("idexercise", exId);
        db.insert(Database.TABLE_1, null, cv);

    }

    public int getLastAddedRowId() {
        SQLiteDatabase db = database.getWritableDatabase();
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

    public ArrayList<Workout> getAllWorkouts() {
        SQLiteDatabase db = database.getReadableDatabase();
        String query = String.format("SELECT * FROM %s", Workout.TABLE_NAME);
        Cursor result = db.rawQuery(query, null);
        result.moveToFirst();
        ArrayList<Workout> list = new ArrayList<Workout>(result.getCount());
        while (!result.isAfterLast()) {
            int workoutId = result.getInt(result.getColumnIndex(Workout.FIELD_WORKOUT_ID));
            String workoutDate = result.getString(result.getColumnIndex(Workout.FIELD_WORKOUT_DATE));
            list.add(new Workout(workoutId, workoutDate));
            result.moveToNext();

        }
        result.close();
        return list;
    }



    public ArrayList<Workout> getSpecificWorkouts(int exId) {
        SQLiteDatabase db = database.getReadableDatabase();
        String query = String.format("SELECT * FROM %s INNER JOIN %s ON %s = %s INNER JOIN %s ON %s = %s",
                Database.TABLE_1, Workout.TABLE_NAME, "exercises_and_workouts.idworkout", Workout.TABLE_1_ID, Exercise.TABLE_NAME, "exercises_and_workouts.idexercise", exId);
        Cursor result = db.rawQuery(query, null);
        result.moveToFirst();
        ArrayList<Workout> list = new ArrayList<Workout>(result.getCount());
        while(!result.isAfterLast()){
            int workoutId = result.getInt(result.getColumnIndex(Workout.FIELD_WORKOUT_ID));
            String workoutDate = result.getString(result.getColumnIndex(Workout.FIELD_WORKOUT_DATE));
            list.add(new Workout(workoutId, workoutDate));
            result.moveToNext();

        }
        result.close();
        return list;
    }
}
