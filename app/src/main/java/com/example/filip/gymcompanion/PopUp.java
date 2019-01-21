package com.example.filip.gymcompanion;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;

public class PopUp extends AppCompatActivity {

    NumberPicker picker1, picker2;
    Button addSet;
    int workoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.6), (int)(height*0.6));
        init();


    }

    private void init() {
        Bundle extras = getIntent().getExtras();
        workoutId = extras.getInt("workoutid");
        picker1 = (NumberPicker) findViewById(R.id.picker1);
        picker1.setMinValue(1);
        picker1.setMaxValue(100);
        picker2 = (NumberPicker) findViewById(R.id.picker2);


        final String[] weightValues = new String[999];
        double j = 0;
        for (int i = 0; i < weightValues.length; i++) {
            j+= 2.5;
            String number = Double.toString(j);
            weightValues[i] = number;
        }

        picker2.setMinValue(0);
        picker1.setDisplayedValues(weightValues);
        picker2.setMaxValue(100);

        addSet = (Button) findViewById(R.id.buttonAdd);
        Database db = new Database(this);
        final SetRepository sr = new SetRepository(db);
        final double finalWeight = picker1.getWeightSum()*2.5;
        addSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sr.addSet((picker1.getValue()*2.5), picker2.getValue(), workoutId);
                setResult(RESULT_OK, null);
                finish();

            }
        });






    }
}
