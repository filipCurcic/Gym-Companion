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


    public void addWorkoutInExercise(int exId) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Workout.FIELD_WORKOUT_DATE, DbUtils.getCurrentDate());
        cv.put("idexercise", exId);
        db.insert(Workout.TABLE_NAME, null, cv);
    }


    public void deleteWorkout(int wId) {
        SQLiteDatabase db = database.getWritableDatabase();
        String query = String.format("DELETE FROM %s WHERE %s = %s", Workout.TABLE_NAME, Workout.FIELD_WORKOUT_ID, wId);
        db.execSQL(query);
    }

    public ArrayList<Workout> getSpecificWorkouts(int exId) {
        SQLiteDatabase db = database.getReadableDatabase();
        String query = String.format("SELECT * FROM %s WHERE %s = %s",
                Workout.TABLE_NAME, Workout.TABLE_EXERCISE_ID, exId);
        Cursor result = db.rawQuery(query, null);
        result.moveToFirst();
        ArrayList<Workout> list = new ArrayList<Workout>(result.getCount());
        while(!result.isAfterLast()){
            int workoutId = result.getInt(result.getColumnIndex(Workout.FIELD_WORKOUT_ID));
            long workoutDate = result.getLong(result.getColumnIndex(Workout.FIELD_WORKOUT_DATE));
            list.add(new Workout(workoutId, DbUtils.convertDate(workoutDate)));
            result.moveToNext();
        }
        result.close();
        return list;
    }
}
