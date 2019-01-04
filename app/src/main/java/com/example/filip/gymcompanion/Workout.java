package com.example.filip.gymcompanion;

import java.util.Date;

public class Workout {
    public static final String TABLE_NAME="workout";
    public static final String FIELD_WORKOUT_ID="id";
    public static final String FIELD_WORKOUT_DATE="date";
    public static final String TABLE_1_ID="workout.id";

    private int id;
    private String date;

    public Workout(int id, String date) {
        this.id = id;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
