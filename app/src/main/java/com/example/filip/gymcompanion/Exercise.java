package com.example.filip.gymcompanion;

public class Exercise {
    public static final String TABLE_NAME="exercise";
    public static final String FIELD_EXERCISE_ID="id";
    public static final String FIELD_EXERCISE_NAME="name";
    public static final String TABLE_1_ID="exercise.id";
    public static final String FIELD_CATEGORY="category";

    private String name, imageResourceName;
    private int id, category;

    public Exercise(int id, String name, int category, String imageResourceName){
        this.id = id;
        this.name = name;
        this.category = category;
        this.imageResourceName = imageResourceName;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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
