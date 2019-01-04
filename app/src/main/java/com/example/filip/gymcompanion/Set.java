package com.example.filip.gymcompanion;

public class Set {
    public static final String TABLE_NAME="setTable";
    public static final String FIELD_SET_ID="id";
    public static final String FIELD_SET_WEIGHT="weight";
    public static final String FIELD_SET_REPS="reps";

    private int id;
    private int weight;
    private int reps;

    public Set(int id, int weight, int reps) {
        this.id = id;
        this.weight = weight;
        this.reps = reps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    @Override
    public String toString() {
        return "Set{" +
                "id=" + id +
                ", weight=" + weight +
                ", reps=" + reps +
                '}';
    }
}
