package com.example.filip.gymcompanion;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {

    private TextView exercises;
    LayoutInflater inflater;
    LinearLayout layout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout1 = (LinearLayout) findViewById(R.id.mainScroll);
        Database db = new Database(this);
        ExerciseRepository er = new ExerciseRepository(db);





        for (Exercise e : er.getAllExercises()) {
            ConstraintLayout item = (ConstraintLayout) inflater.inflate(R.layout.exercise, null);
            ((TextView) item.findViewById(R.id.e1)).setText(String.valueOf(e.getId()));
            ((TextView) item.findViewById(R.id.exName)).setText(e.getName());
            switch(e.getName()) {
                case "Squat":
                    ((ImageView) item.findViewById(R.id.imageView2)).setImageResource(R.drawable.sqt);
                    break;
                case "Bench Press":
                    ((ImageView) item.findViewById(R.id.imageView2)).setImageResource(R.drawable.bp);
                    break;
                case "Deadlift":
                    ((ImageView) item.findViewById(R.id.imageView2)).setImageResource(R.drawable.dlft);
                    break;
            }
            layout1.addView(item);
            final int exId = e.getId();
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(MainActivity.this, ExerciseActivity.class);
                    Bundle extras = new Bundle();
                    extras.putInt("id", exId);
                    myIntent.putExtras(extras);
                    MainActivity.this.startActivity(myIntent);

                }
            });

        }


    }
}
