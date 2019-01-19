package com.example.filip.gymcompanion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class PopUp2 extends AppCompatActivity {

    public final static String SPINNER_CATEGORY_1 = "1 - Legs";
    public final static String SPINNER_CATEGORY_2 = "2 - Chest";
    public final static String SPINNER_CATEGORY_3 = "3 - Back";
    public final static String SPINNER_CATEGORY_4 = "4 - Shoulders";
    public final static String SPINNER_CATEGORY_5 = "5 - Arms";

    EditText exerciseName;
    Spinner exerciseCategory;
    ImageView exerciseIcon;
    Button addExercise;
    int imageValue;

    String exerciseNameValue, exerciseCategoryValue, exerciseIconResourceName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up2);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.9), (int)(height*0.8));

        init();

    }

    private void init() {
        exerciseName = (EditText) findViewById(R.id.exerciseName);
        exerciseCategory = (Spinner) findViewById(R.id.exerciseCategory);
        exerciseIcon = (ImageView) findViewById(R.id.exerciseIcon);
        addExercise = (Button) findViewById(R.id.addNewExc);

        Database db = new Database(this);
        final ExerciseRepository er = new ExerciseRepository(db);



        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add(PopUp2.SPINNER_CATEGORY_1);
        spinnerArray.add(PopUp2.SPINNER_CATEGORY_2);
        spinnerArray.add(PopUp2.SPINNER_CATEGORY_3);
        spinnerArray.add(PopUp2.SPINNER_CATEGORY_4);
        spinnerArray.add(PopUp2.SPINNER_CATEGORY_5);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseCategory.setAdapter(adapter);



        exerciseIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                exerciseCategoryValue = exerciseCategory.getSelectedItem().toString();
                extras.putString("Category", exerciseCategoryValue);
                Intent i = new Intent(PopUp2.this, ImagePicker.class);
                i.putExtras(extras);
                startActivityForResult(i, 1);

            }
        });

        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                er.addExercise(exerciseName.getText().toString(), imageValue);
                finish();
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                imageValue = data.getIntExtra("ImageIntValue", 0);
                exerciseIcon.setImageResource(imageValue);

            }
        }
    }



}
