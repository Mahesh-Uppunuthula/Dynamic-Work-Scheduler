package com.example.dynamicworkscheduler;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

public class CreateTask extends AppCompatActivity {


    ExtendedFloatingActionButton mDone_FAB;
//    TaskHelper task_data;
    String customDateFormat;
    Button mSelectDateBTN, mStartSelectTimeBTN, mEndSelectedTimeBTN;
    int startHours, startMinute, endHours, endMinute, DD, MM, YYYY;
    String start_selected_time, end_selected_time, selected_date, selected_time;

    String task_title, task_description;
    EditText mTitle_ET, mDescription_ET;
    AutoCompleteTextView mPriorityTV;
    ArrayAdapter<String> priority_array_adapter;
    String[] strs = {"1", "2", "3", "4", "5"};
    Calendar calendar;
    int datePickerYear, datePickerMonth, datePickerDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);


        // hooks
        mSelectDateBTN = findViewById(R.id.SelectDateBTN);
        mStartSelectTimeBTN = findViewById(R.id.SelectStartTimeBTN);
        mEndSelectedTimeBTN = findViewById(R.id.SelectEndTimeBTN);
        mTitle_ET = findViewById(R.id.Title_ET);
        mDescription_ET = findViewById(R.id.Description_ET);
        mDone_FAB = findViewById(R.id.Done_FAB);

//        mPriorityTV = findViewById(R.id.mPriorityTV);
//        mPriorityTV.setInputType(0);
//        priority_array_adapter = new ArrayAdapter<>(this, R.layout.priority_dropdown_itemm, strs);
//        mPriorityTV.setAdapter(priority_array_adapter);

        // date picker calender obj
        calendar = Calendar.getInstance();


        // initialize globals
//        selected_date = new StringBuilder("");
//        start_selected_time= new StringBuilder("");
//        end_selected_time= new StringBuilder("");

        // select date for task
        mSelectDateBTN.setOnClickListener(view -> {
            datePickerDay = calendar.get(Calendar.DATE);
            datePickerMonth = calendar.get(Calendar.MONDAY);
            datePickerYear = calendar.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(CreateTask.this, new DatePickerDialog.OnDateSetListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                    DD = i2;
                    MM = i1 + 1;
                    YYYY = i;

//                    selected_date = (i + "-" + (i1 + 1) + "-" + i2);
                    selected_date = String.format(Locale.getDefault(), "%02d-%02d-%02d", i, i1 + 1, i2);
                    Toast.makeText(CreateTask.this, selected_date, Toast.LENGTH_SHORT).show();

                    customDateFormat = getSelectedDateCustom(DD, MM, YYYY, selected_date);
                    Toast.makeText(CreateTask.this, selected_date, Toast.LENGTH_SHORT).show();
                    mSelectDateBTN.setText(customDateFormat);
                }
            }, datePickerYear, datePickerMonth, datePickerDay);
            datePickerDialog.setTitle("Select Date");
            datePickerDialog.show();
        });

//        Log.d("AFTSEL",customDateFormat);

        // select start time
        mStartSelectTimeBTN.setOnClickListener(view -> {
            TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selected_start_hour, selected_startMinute) -> {
                startHours = selected_start_hour;
                startMinute = selected_startMinute;

                start_selected_time = (String.format(Locale.getDefault(), "%02d : %02d", startHours, startMinute));
                mStartSelectTimeBTN.setText(start_selected_time);
                Toast.makeText(CreateTask.this, start_selected_time, Toast.LENGTH_SHORT).show();

            };
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, startHours, startMinute, true);
            timePickerDialog.setTitle("Select time");
            timePickerDialog.show();
        });

        // select end time
        mEndSelectedTimeBTN.setOnClickListener(view -> {
            TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selected_end_hour, int selectedEndMinute) {
                    endHours = selected_end_hour;
                    endMinute = selectedEndMinute;

                    end_selected_time = (String.format(Locale.getDefault(), "%02d : %02d", endHours, endMinute));
                    mEndSelectedTimeBTN.setText(end_selected_time);
                    Toast.makeText(CreateTask.this, end_selected_time, Toast.LENGTH_SHORT).show();
                    mEndSelectedTimeBTN.setText(String.format(Locale.getDefault(), "%02d : %02d", endHours, endMinute));
                }
            };

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, endHours, endMinute, true);
            timePickerDialog.setTitle("Select time");
            timePickerDialog.show();
        });

        Log.d("TEST", "on create create task");

        mDone_FAB.setOnClickListener(view -> {

            // send Data
            task_title = mTitle_ET.getText().toString();
            task_description = mDescription_ET.getText().toString();

            Toast.makeText(this, "Title " + mTitle_ET.getText().toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Des " + mDescription_ET.getText().toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Date " + selected_date, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "start time " + start_selected_time, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "end time " + end_selected_time, Toast.LENGTH_SHORT).show();


            Log.d("TEST", "on done create task");


//            System.out.println(task_data.toString());
            TaskHelper newTaskObj = new TaskHelper(task_title,
                    selected_date,
                    start_selected_time,
                    end_selected_time,
                    task_description,
                    "idk",
                    true);


//            Log.d("PREVTITLE", newTaskObj.getTitle());
//            Log.d("PREVDATE ", newTaskObj.getDate());
//            Log.d("PREVST", newTaskObj.getStart_time());
//            Log.d("PREVET", newTaskObj.getEnd_time());
//            Log.d("PREVDES", newTaskObj.getDescription());
            TaskHelper task_data = new TaskHelper(task_title, selected_date, start_selected_time, end_selected_time, task_description, "idk", true);

            Intent new_task = new Intent(CreateTask.this, MainActivity.class);
//            new_task.putExtra("title",newTaskObj.getTitle());
//            Log.d("title",newTaskObj.getTitle());
//            new_task.putExtra("NewTaskObj", newTaskObj);
//            Log.d("NEWOBJ", newTaskObj.toString());
//            new_task.putExtra("TEST","TEXT");
            new SyncHelper(task_data, true);
            startActivity(new_task);
            finish();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getSelectedDateCustom(int dd, int mm, int yyyy, String selected_date) {
        LocalDate date = LocalDate.parse(selected_date);
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        String temp = String.valueOf(dayOfWeek).toLowerCase(Locale.ENGLISH);
        String res = temp.substring(0, 1).toUpperCase(Locale.ENGLISH) + temp.substring(1) + ", " + dd + " " + getCustomMonth(mm) + " " + yyyy;

        return res;
    }

    private String getCustomMonth(int m) {
        switch (m) {
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
        }
        return "";
    }

    public void backToDashboard(View view) {
        startActivity(new Intent(CreateTask.this, MainActivity.class));
        finish();
    }
}