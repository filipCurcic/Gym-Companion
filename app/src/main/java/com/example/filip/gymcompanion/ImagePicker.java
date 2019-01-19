package com.example.filip.gymcompanion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Map;

public class ImagePicker extends Activity {

    LinearLayout imageScroll;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.7), (int)(height*0.25));

        init();


    }

    private void init() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageScroll = (LinearLayout) findViewById(R.id.mainImageScrollElement);

        Bundle extras = getIntent().getExtras();

        for (final Map.Entry<String, Integer> entry : ImageUtils.getImages(extras.getString("Category")).entrySet()) {
            ConstraintLayout item = (ConstraintLayout) inflater.inflate(R.layout.exercise_image, null);
            ((ImageView) item.findViewById(R.id.exImage)).setImageResource(entry.getValue().intValue());
            imageScroll.addView(item);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("ImageIntValue", entry.getValue().intValue());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }


    }
}
