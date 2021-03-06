package com.example.filip.gymcompanion;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

public class WorkoutActivity extends AppCompatActivity {
    int workoutId;
    LayoutInflater inflater;
    LinearLayout layout1;
    ImageView newSet;
    ImageView deleteSet, editSet;

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
        newSet = (ImageView) findViewById(R.id.newSet);
        Database db = new Database(this);
        final SetRepository sets = new SetRepository(db);
        for(final Set s : sets.getSets(workoutId)) {

            ConstraintLayout item = (ConstraintLayout) inflater.inflate(R.layout.workout_set, null);
            String workoutText = String.format("%s      x      %s kg", s.getReps(),s.getWeight());
            ((TextView) item.findViewById(R.id.idWeight)).setText(workoutText);
            layout1.addView(item);
            deleteSet = (ImageView) item.findViewById(R.id.deleteSet);
            deleteSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sets.deleteSet(s.getId());
                    clearViews();
                    init();
                }
            });
        }
        newSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WorkoutActivity.this, PopUp.class);
                i.putExtra("workoutid", workoutId);
                startActivity(i);
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        clearViews();
        init();
    }

    private void clearViews() {
        layout1.removeAllViews();
    }

}
