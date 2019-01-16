package com.example.filip.gymcompanion;

import java.util.Date;

public class Workout {
    public static final String TABLE_NAME="workout";
    public static final String FIELD_WORKOUT_ID="id";
    public static final String FIELD_WORKOUT_DATE="date";
    public static final String TABLE_EXERCISE_ID="idexercise";

    private int id;
    private Date date;

    public Workout(int id, Date date) {
        this.id = id;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
