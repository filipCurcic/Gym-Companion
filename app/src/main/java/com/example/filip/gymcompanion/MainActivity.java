package com.example.filip.gymcompanion;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    LayoutInflater inflater;
    LinearLayout layout1;
    ImageView newExercise, deleteExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        clearViews();
        init();
    }

    private void clearViews() {
        layout1.removeAllViews();
    }

    private void init() {
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout1 = (LinearLayout) findViewById(R.id.mainScroll);
        newExercise = (ImageView) findViewById(R.id.addNewExercise);

        Database db = new Database(this);
        final ExerciseRepository er = new ExerciseRepository(db);

        newExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PopUp2.class));
            }
        });





        for (Exercise e : er.getAllExercises()) {
            ConstraintLayout item = (ConstraintLayout) inflater.inflate(R.layout.exercise, null);
            ((TextView) item.findViewById(R.id.exName)).setText(e.getName());
            ((ImageView) item.findViewById(R.id.imageView2)).setImageResource(e.getImageResource());
            try {
                ((TextView) item.findViewById(R.id.lastWorkout))
                        .setText("Last workout: " +
                                DbUtils.formatDate(
                        (DbUtils.convertDate(
                                er.getLastExercise(e.getId())
                        ))));
            } catch (Exception e2) {
                ((TextView) item.findViewById(R.id.lastWorkout)).setText("No workouts");
            }


            layout1.addView(item);
            final int exId = e.getId();
            ((TextView) item.findViewById(R.id.exName)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(MainActivity.this, ExerciseActivity.class);
                    Bundle extras = new Bundle();
                    extras.putInt("id", exId);
                    myIntent.putExtras(extras);
                    MainActivity.this.startActivity(myIntent);
                }
            });

            ((ImageView) item.findViewById(R.id.imageView2)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(MainActivity.this, ExerciseActivity.class);
                    Bundle extras = new Bundle();
                    extras.putInt("id", exId);
                    myIntent.putExtras(extras);
                    MainActivity.this.startActivity(myIntent);
                }
            });



            ((ImageView) item.findViewById(R.id.deleteExercise)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    er.deleteExercise(exId);
                    clearViews();
                    init();
                }
            });
        }
    }


}
