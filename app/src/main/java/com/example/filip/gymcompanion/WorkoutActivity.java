package com.example.filip.gymcompanion;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import java.util.zip.Inflater;

public class WorkoutActivity extends AppCompatActivity {
    int workoutId;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
    }

    private void init() {
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        workoutId = extras.getInt("id");
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
}
