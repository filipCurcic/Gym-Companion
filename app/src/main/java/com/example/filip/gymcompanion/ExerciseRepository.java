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


    public void addExercise(String name, int imageValue){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Exercise.FIELD_EXERCISE_NAME, name);
        cv.put(Exercise.FIELD_IMAGE_RESOURCE, imageValue);
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
            int imageResource = result.getInt(result.getColumnIndex(Exercise.FIELD_IMAGE_RESOURCE));
            list.add(new Exercise(exerciseId, exerciseName, imageResource));
            result.moveToNext();

        }
        result.close();
        return list;
    }

    public void deleteExercise(int exId) {
        SQLiteDatabase db = database.getWritableDatabase();
        String query = String.format("DELETE FROM %s WHERE %s = %s", Exercise.TABLE_NAME, Exercise.FIELD_EXERCISE_ID, exId);
        String query1 = String.format("DELETE FROM %s WHERE %s = %s", Workout.TABLE_NAME, Workout.TABLE_EXERCISE_ID, exId);
        //String query2 = String.format("DELETE FROM %s WHERE (SELECT ) = %s");
        db.execSQL(query);
        db.execSQL(query1);

    }

    public int getLastExercise(int exId) {
        SQLiteDatabase db = database.getReadableDatabase();
        String query = String.format("SELECT * FROM %s WHERE %s = %s ORDER BY %s DESC LIMIT 1", Workout.TABLE_NAME, Workout.TABLE_EXERCISE_ID, exId, Workout.FIELD_WORKOUT_DATE);
        Cursor result = db.rawQuery(query, null);
        result.moveToFirst();
        int workoutDate = result.getInt(result.getColumnIndex(Workout.FIELD_WORKOUT_DATE));
        return workoutDate;
    }


    public void dropTables() {
        SQLiteDatabase db = database.getWritableDatabase();
        String query = String.format("DROP TABLE IF EXISTS %s", Exercise.TABLE_NAME);
        db.execSQL(query);
    }
}
