package com.example.filip.gymcompanion;

public class Exercise {
    public static final String TABLE_NAME="exercise";
    public static final String FIELD_EXERCISE_ID="id";
    public static final String FIELD_EXERCISE_NAME="name";
    public static final String FIELD_IMAGE_RESOURCE="image";

    private String name;
    private int id, imageResource;

    public Exercise(int id, String name, int imageResource){
        this.id = id;
        this.name = name;
        this.imageResource = imageResource;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }


}
