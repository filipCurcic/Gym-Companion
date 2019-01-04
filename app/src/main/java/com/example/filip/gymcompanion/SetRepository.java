package com.example.filip.gymcompanion;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SetRepository {

    private Database database;

    public SetRepository(Database db) {
        this.database = db;
    }

    public void addSet(int weight, int reps) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Set.FIELD_SET_WEIGHT, weight);
        cv.put(Set.FIELD_SET_REPS, reps);
        db.insert(Set.TABLE_NAME, null, cv);
    }

    public ArrayList<Set> getSets(int workoutId) {
        SQLiteDatabase db = database.getReadableDatabase();
        String query = String.format("SELECT * FROM %s INNER JOIN %s ON %s = %s ",
                Database.TABLE_1, Workout.TABLE_NAME, "exercises_and_workouts.idworkout", Workout.TABLE_1_ID);
        Cursor result = db.rawQuery(query, null);
        result.moveToFirst();
        ArrayList<Set> list = new ArrayList<Set>(result.getCount());
        while(!result.isAfterLast()){
            int setId = result.getInt(result.getColumnIndex(Set.FIELD_SET_ID));
            int setWeight = result.getInt(result.getColumnIndex(Set.FIELD_SET_WEIGHT));
            int setReps = result.getInt(result.getColumnIndex(Set.FIELD_SET_REPS));
            list.add(new Set(setId, setWeight, setReps));
            result.moveToNext();

        }
        result.close();
        return list;
    }

}
