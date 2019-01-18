package com.example.filip.gymcompanion;

import java.util.ArrayList;
import java.util.HashMap;

public  class ImageUtils {

    public static HashMap<String, Integer> getImages(String category) {
        HashMap<String, Integer> images = new HashMap<String, Integer>();
        switch(category) {
            case PopUp2.SPINNER_CATEGORY_1:
                images.put("Squat", Integer.valueOf( R.drawable.sqt));
                images.put("Front Squat", Integer.valueOf( R.drawable.frntsqt));
                break;

            case PopUp2.SPINNER_CATEGORY_2:
                images.put("Bench Press", Integer.valueOf( (R.drawable.bp) ));
                images.put("Dumbbell BP", Integer.valueOf( (R.drawable.dbp) ));
                break;

            case PopUp2.SPINNER_CATEGORY_3:
                images.put("Deadlift", Integer.valueOf( (R.drawable.dlft) ));
                images.put("Row", Integer.valueOf( (R.drawable.row) ));
                images.put("Pullup", Integer.valueOf( (R.drawable.pull) ));
                break;

            case PopUp2.SPINNER_CATEGORY_4:
                images.put("OHP", Integer.valueOf( (R.drawable.ohp) ));
                images.put("Dumbbell OHP", Integer.valueOf( (R.drawable.dohp) ));
                break;

            case PopUp2.SPINNER_CATEGORY_5:
                images.put("Bicep curl", Integer.valueOf( (R.drawable.bcp) ));
                images.put("Tricep extension", Integer.valueOf( (R.drawable.trcp) ));
                break;

        }
        return images;

    }
}
