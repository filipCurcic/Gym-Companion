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

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExerciseActivity extends AppCompatActivity {

    LayoutInflater inflater;
    LinearLayout layout1;
    int exId;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        init();
    }

    private void init() {
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        exId = extras.getInt("id");
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout1 = (LinearLayout) findViewById(R.id.exerciseMainScroll);
        add = (Button) findViewById(R.id.idAdd);

        Database db = new Database(this);
        final WorkoutRepository wr = new WorkoutRepository(db);
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        final String strDate = dateFormat.format(date);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wr.addWorkout(strDate);
                wr.addWorkoutInExercise(exId);
                finish();
                startActivity(getIntent());
            }
        });


        for (Workout w : wr.getSpecificWorkouts(exId)) {
            ConstraintLayout item = (ConstraintLayout) inflater.inflate(R.layout.workout, null);
            ((TextView) item.findViewById(R.id.idDate)).setText(w.getDate());
            int indeks = wr.getSpecificWorkouts(exId).indexOf(w);
            ((TextView) item.findViewById(R.id.wn)).setText(Integer.toString(indeks));
            layout1.addView(item);

            final int wId = w.getId();
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(ExerciseActivity.this, WorkoutActivity.class);
                    Bundle extras = new Bundle();
                    extras.putInt("id", wId);
                    myIntent.putExtras(extras);
                    ExerciseActivity.this.startActivity(myIntent);
                }
            });
        }
    }
}