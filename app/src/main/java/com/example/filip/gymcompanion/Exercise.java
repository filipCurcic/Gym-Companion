package com.example.filip.gymcompanion;

public class Exercise {
    public static final String TABLE_NAME="exercise";
    public static final String FIELD_EXERCISE_ID="id";
    public static final String FIELD_EXERCISE_NAME="name";
    public static final String TABLE_1_ID="exercise.id";

    private String name;
    private int id;

    public Exercise(int id, String name){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }
}
