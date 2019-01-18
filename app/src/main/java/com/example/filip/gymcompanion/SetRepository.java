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

    public void addSet(double weight, int reps, int workoutId) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Set.FIELD_SET_WEIGHT, weight);
        cv.put(Set.FIELD_SET_REPS, reps);
        cv.put(Set.FIELD_WORKOUTID, workoutId);
        db.insert(Set.TABLE_NAME, null, cv);
    }

    public ArrayList<Set> getSets(int workoutId) {
        SQLiteDatabase db = database.getReadableDatabase();
        String query = String.format("SELECT * FROM %s WHERE %s = %s",
                Set.TABLE_NAME, "idworkout", workoutId);
        Cursor result = db.rawQuery(query, null);
        result.moveToFirst();
        ArrayList<Set> list = new ArrayList<Set>(result.getCount());
        while(!result.isAfterLast()){
            int setId = result.getInt(result.getColumnIndex(Set.FIELD_SET_ID));
            double setWeight = result.getDouble(result.getColumnIndex(Set.FIELD_SET_WEIGHT));
            int setReps = result.getInt(result.getColumnIndex(Set.FIELD_SET_REPS));
            list.add(new Set(setId, setWeight, setReps));
            result.moveToNext();

        }
        result.close();
        return list;
    }

    public void deleteSet(int setId) {
        SQLiteDatabase db = database.getWritableDatabase();
        String query = String.format("DELETE FROM %s WHERE %s = %s", Set.TABLE_NAME, Set.FIELD_SET_ID, setId);
        db.execSQL(query);
    }

}
