package com.example.mastermind.testapp;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kostas on 7/5/2018.
 */

public class DetailActivity extends AppCompatActivity {

    SimpleDateFormat format;
    SharedPreferences settingsPreferences;
    String[] paths;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle("Datalabs");

        settingsPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        TextView txt_title = findViewById(R.id.txt_title);
        TextView txt_date = findViewById(R.id.txt_date);
        TextView txt_description = findViewById(R.id.txt_description);
        TextView txt_link = findViewById(R.id.txt_link);
        format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        JobOffer jobOffer = (JobOffer) getIntent().getSerializableExtra("jobOffer");


        txt_title.setText(jobOffer.getTitle());
        txt_date.setText("Δημοσιεύτηκε: " +format.format(jobOffer.getDate()));
        txt_description.setText(String.valueOf(jobOffer.getDesc()));
        txt_link.setText(String.valueOf(jobOffer.getLink()));

        if (settingsPreferences.getInt("numberOfImages",0)>0) {
            paths = new String[settingsPreferences.getInt("numberOfImages", 0)];
            for (int i = 1; i <= paths.length; i++) {
                paths[i - 1] = settingsPreferences.getString("imageUri" + i, "");
            }
            loadImageFromStorage(paths);
        }


    }

    private void loadImageFromStorage(String[] paths)
    {
        ArrayList<Bitmap> bitmaps = new ArrayList<>();
        for(String path : paths) {


            try {
                File d = new File(path);
                System.out.println("This is the path to upload: " + d.toString());
                bitmaps.add(BitmapFactory.decodeStream(new FileInputStream(d)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        Random r = new Random();

        int rnum =r.nextInt(paths.length);
        ImageButton img = findViewById(R.id.imgBtn_ad);
        img.setVisibility(View.VISIBLE);
        img.setImageBitmap(bitmaps.get(rnum));

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
