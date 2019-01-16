package com.example.filip.gymcompanion;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

public class WorkoutActivity extends AppCompatActivity {
    int workoutId;
    LayoutInflater inflater;
    LinearLayout layout1;
    Button newSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        init();
    }

    private void init() {
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        workoutId = extras.getInt("id");
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout1 = (LinearLayout) findViewById(R.id.workoutsScrollElement);
        newSet = (Button) findViewById(R.id.newSet);
        Database db = new Database(this);
        SetRepository sets = new SetRepository(db);
        for(Set s : sets.getSets(workoutId)) {
            ConstraintLayout item = (ConstraintLayout) inflater.inflate(R.layout.workout_set, null);
            String workoutText = String.format("%s      x      %s kg", s.getWeight(), s.getReps());
            ((TextView) item.findViewById(R.id.idWeight)).setText(workoutText);
            layout1.addView(item);
        }

        newSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
