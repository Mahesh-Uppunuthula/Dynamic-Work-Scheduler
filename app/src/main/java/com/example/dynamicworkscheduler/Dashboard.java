package com.example.dynamicworkscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.IntBinaryOperator;

public class Dashboard extends AppCompatActivity  {

    String activityCallingFlag;
    LinearLayout t;
    boolean collectTaskData = false;
    TextView mTaskDate, mWeek_day1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        Log.d("TVID" , String.valueOf(findViewById(R.id.day1).getId()));
        Toast.makeText(this, String.valueOf(findViewById(R.id.day1).getId()), Toast.LENGTH_SHORT).show();
        mTaskDate = findViewById(R.id.TaskDate);
        mWeek_day1 = findViewById(R.id.Week_day1);

        mWeek_day1.setOnClickListener(view -> {
            Toast.makeText(this, mWeek_day1.getText(), Toast.LENGTH_SHORT).show();
        });

        Log.d("TEST", "on create dashboard");

        findViewById(R.id.add_task).setOnClickListener(view -> {
            Log.d("TEST", "on call create task");
            activityCallingFlag = "createTask";
            startActivity(new Intent(Dashboard.this,CreateTask.class));
            finish();
        });

        Log.d("TEST", "flag in create "+collectTaskData);

//        if(collectTaskData)
//        {
//            TaskHelper newObj =   getIntent().getParcelableExtra("NewTaskObj");
////            String temp = getIntent().getStringExtra("title");
//            Log.d("TEST",newObj.toString());
//
//        }
//        if(temp == null)
//            Log.d("title1", "null");
//        else
//            Log.d("title1", temp);
//        Log.d("NewTask",newObj.toString());



        boolean isDataAvailable = SyncHelper.isDataAvailable;
        if(isDataAvailable)
            mTaskDate.setText(SyncHelper.getTask().toString());

    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//
//        Log.d("TEST","on stop dash");
//        if(activityCallingFlag.equals("createTask"))
//            collectTaskData = true;
//
//        Log.d("TEST","flag in stop  "+collectTaskData);
//        if(collectTaskData)
//        {
//            String test = getIntent().getStringExtra("TEST");
//            Log.d("TESTTEXT", test);
//        }
//
//
//    }

    //    @Override
//    protected void onPostResume() {
//        super.onPostResume();

//        Log.d("TEST","on resume dashboard");
//        TaskHelper newObj =   getIntent().getParcelableExtra("NewTaskObj");
////        String temp = getIntent().getStringExtra("title");
////        if(temp == null)
////            Log.d("title1", "null");
////        else
////            Log.d("title1", temp);
//        Log.d("NewTask",newObj.toString());
//        Toast.makeText(this, newObj.toString(), Toast.LENGTH_LONG).show();
//    }

    //    public void backToDashboard(View view){
//        startActivity(new Intent(Dashboard.this, MainActivity.class));
//    }

//
//    public void openDateSpecificSchedule(View view){
////        Log.d("VIEWID" , String.valueOf(view.getId()));
//
//
//
//    }

}