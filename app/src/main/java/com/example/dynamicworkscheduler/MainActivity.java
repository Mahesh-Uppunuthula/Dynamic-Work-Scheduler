package com.example.dynamicworkscheduler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView mDate_TV;
    Button mBtn;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDate_TV = findViewById(R.id.date_TV);
        mBtn = findViewById(R.id.btn);

        /* IGNORE THIS JUNK

        LocalDate date = LocalDate.parse("2022-12-07");
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        mDate_TV.setText(String.valueOf(dayOfWeek));

        String temp = getIntent().getStringExtra("title");
        if(temp == null)
            Log.d("title1", "null");
        Log.d("title1", temp);

        Till here */
    }
    @Override
    protected void onStart() {
        super.onStart();
//        startActivity(new Intent(MainActivity.this, Dashboard.class));
        startActivity(new Intent(MainActivity.this, Dashboard.class));
        finish();
    }
}