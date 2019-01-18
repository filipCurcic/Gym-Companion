package com.example.filip.gymcompanion;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ExerciseRepository {

    private Database database;

    public ExerciseRepository(Database db) {
        this.database = db;
    }
    public void addExercise(String name){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Exercise.FIELD_EXERCISE_NAME, name);
        db.insert(Exercise.TABLE_NAME, null, cv);
    }

    public List<Exercise> getAllExercises(){
        SQLiteDatabase db = database.getReadableDatabase();
        String query = String.format("SELECT * FROM %s", Exercise.TABLE_NAME);
        Cursor result = db.rawQuery(query, null);
        result.moveToFirst();
        List<Exercise> list = new ArrayList<Exercise>(result.getCount());
        while(!result.isAfterLast()){
            int exerciseId = result.getInt(result.getColumnIndex(Exercise.FIELD_EXERCISE_ID));
            String exerciseName = result.getString(result.getColumnIndex(Exercise.FIELD_EXERCISE_NAME));
            list.add(new Exercise(exerciseId, exerciseName, 0, null));
            result.moveToNext();

        }
        result.close();
        return list;
    }

    public void addToTables() {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();


        db.insert(Database.TABLE_1, null, cv);
    }

    public void dropTables() {
        SQLiteDatabase db = database.getWritableDatabase();
        String query = String.format("DROP TABLE IF EXISTS %s", Exercise.TABLE_NAME);
        db.execSQL(query);
    }
}
