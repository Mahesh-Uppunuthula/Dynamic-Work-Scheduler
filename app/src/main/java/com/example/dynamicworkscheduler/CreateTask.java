package com.example.dynamicworkscheduler;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class CreateTask extends AppCompatActivity {

    Spinner mDuration_Spinner;
    int selected_date_index = -1;
    String category = "Select Category of Task", priority = "+";
    LinearLayout cat1_LL, cat2_LL, cat3_LL, mPriority_List_LL;
    TextView cat1_title, cat2_title, cat3_title, cat1_des, cat2_des, cat3_des, pri_1, pri_2, pri_3, pri_4, pri_5;
    TextView mSelectCategory_TV, mAdd_priority_TV;
    Dialog mShowTaskCategoryListDialog;
    ExtendedFloatingActionButton mDone_FAB;
    //    TaskHelper task_data;
    String customDateFormat;
    ListView mCategoryLV;
    Button mSelectDateBTN, mStartSelectTimeBTN, mEndSelectedTimeBTN;
    int startHours, startMinute, endHours, endMinute, DD, MM, YYYY;
    String start_selected_time, end_selected_time, selected_date, selected_time;
    ArrayAdapter<String> arrayAdapter;
    String task_title, task_description;
    EditText mTitle_ET, mDescription_ET;
    AutoCompleteTextView mPriorityTV;
    ArrayAdapter<String> duration_array_adapter;
    String[] duration_time = {"< 30 mins", "< 1hr", "1hrs - 2 hrs", "2hrs - 3hrs"};
    String[] category_list = new String[]{"Design", "Develop", "Blog", "Sales", "Backend", "FrontEnd", "Business"};
    Calendar calendar;
    int datePickerYear, datePickerMonth, datePickerDay;
    LinearLayout mDay1, mDay2, mDay3, mDay4, mDay5, mDay6, mDay7;
    TextView mDay_day1, mDay_day2, mDay_day3, mDay_day4, mDay_day5, mDay_day6, mDay_day7;
    TextView mDay_date1, mDay_date2, mDay_date3, mDay_date4, mDay_date5, mDay_date6, mDay_date7;


    Button mAssign_end_time_BTN, mAssign_start_time_BTN;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);


        // hooks
//        mSelectDateBTN = findViewById(R.id.SelectDateBTN);
//        mStartSelectTimeBTN = findViewById(R.id.SelectStartTimeBTN);
//        mEndSelectedTimeBTN = findViewById(R.id.SelectEndTimeBTN);
//        mTitle_ET = findViewById(R.id.Title_ET);
//        mDescription_ET = findViewById(R.id.Description_ET);
//        mDone_FAB = findViewById(R.id.Done_FAB);
//        mCategoryLV = findViewById(R.id.category_LV);


        mDuration_Spinner = findViewById(R.id.Duration_Spinner);
        duration_array_adapter = new ArrayAdapter<>(this, R.layout.priority_dropdown_itemm, duration_time);
        mDuration_Spinner.setAdapter(duration_array_adapter);


        mAssign_start_time_BTN = findViewById(R.id.Assign_start_time_BTN);
        mAssign_end_time_BTN = findViewById(R.id.Assign_end_time_BTN);
        mSelectCategory_TV = findViewById(R.id.SelectCategory_TV);
        mAdd_priority_TV = findViewById(R.id.Add_priority_TV);
        mPriority_List_LL = findViewById(R.id.Priority_List_LL);
        pri_1 = findViewById(R.id.Priority_1_TV);
        pri_2 = findViewById(R.id.Priority_2_TV);
        pri_3 = findViewById(R.id.Priority_3_TV);
        pri_4 = findViewById(R.id.Priority_4_TV);
        pri_5 = findViewById(R.id.Priority_5_TV);

        mDay1 = findViewById(R.id.day1);
        mDay2 = findViewById(R.id.day2);
        mDay3 = findViewById(R.id.day3);
        mDay4 = findViewById(R.id.day4);
        mDay5 = findViewById(R.id.day5);
        mDay6 = findViewById(R.id.day6);
        mDay7 = findViewById(R.id.day7);

        mDay_day1 = findViewById(R.id.Week_day1);
        mDay_day2 = findViewById(R.id.Week_day2);
        mDay_day3 = findViewById(R.id.Week_day3);
        mDay_day4 = findViewById(R.id.Week_day4);
        mDay_day5 = findViewById(R.id.Week_day5);
        mDay_day6 = findViewById(R.id.Week_day6);
        mDay_day7 = findViewById(R.id.Week_day7);

        mDay_date1 = findViewById(R.id.Week_date1);
        mDay_date2 = findViewById(R.id.Week_date2);
        mDay_date3 = findViewById(R.id.Week_date3);
        mDay_date4 = findViewById(R.id.Week_date4);
        mDay_date5 = findViewById(R.id.Week_date5);
        mDay_date6 = findViewById(R.id.Week_date6);
        mDay_date7 = findViewById(R.id.Week_date7);

        /* SOME BASIC INITIALISATIONS */
        calendar = Calendar.getInstance();





        // initialize globals
//        selected_date = new StringBuilder("");
//        start_selected_time= new StringBuilder("");
//        end_selected_time= new StringBuilder("");

        // select date for task

//        mSelectDateBTN.setOnClickListener(view -> {
//            datePickerDay = calendar.get(Calendar.DATE);
//            datePickerMonth = calendar.get(Calendar.MONDAY);
//            datePickerYear = calendar.get(Calendar.YEAR);
//            DatePickerDialog datePickerDialog = new DatePickerDialog(CreateTask.this, new DatePickerDialog.OnDateSetListener() {
//                @RequiresApi(api = Build.VERSION_CODES.O)
//                @Override
//                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//
//                    DD = i2;
//                    MM = i1 + 1;
//                    YYYY = i;
//
////                    selected_date = (i + "-" + (i1 + 1) + "-" + i2);
//                    selected_date = String.format(Locale.getDefault(), "%02d-%02d-%02d", i, i1 + 1, i2);
//                    Toast.makeText(CreateTask.this, selected_date, Toast.LENGTH_SHORT).show();
//
//                    customDateFormat = getSelectedDateCustom(DD, MM, YYYY, selected_date);
//                    Toast.makeText(CreateTask.this, selected_date, Toast.LENGTH_SHORT).show();
//                    mSelectDateBTN.setText(customDateFormat);
//                }
//            }, datePickerYear, datePickerMonth, datePickerDay);
//            datePickerDialog.setTitle("Select Date");
//            datePickerDialog.show();
//        });

////        Log.d("AFTSEL",customDateFormat);


//        final Calendar myCalendar = Calendar.getInstance();
        //code for datepicker Dialog
//        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                myCalendar.set(Calendar.YEAR, year);
//                myCalendar.set(Calendar.MONTH, month);
//                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateDate();
//            }
//        };

//        @Override
//                mDay1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                DatePickerDialog datePicker;
//                int year = myCalendar.get(Calendar.YEAR);
//                int month = myCalendar.get(Calendar.MONTH);
//                int day = myCalendar.get(Calendar.DAY_OF_MONTH);
//                datePicker = new DatePickerDialog(CreateTask.this, date, year, month, day);
//                // This code is used for disable previous date but you can select the date
//                datePicker.getDatePicker().setMinDate(System.currentTimeMillis());
//                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() +);
//                // This code is used to prevent the date selection
//                datePicker.getDatePicker().setCalendarViewShown(false);
//                datePicker.show();
//            }
//        });


//*/



        /* INIT() CATEGORY DIALOG */

        mShowTaskCategoryListDialog = new Dialog(CreateTask.this);
        mShowTaskCategoryListDialog.setContentView(R.layout.task_category_selection_dialog);
        mShowTaskCategoryListDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mShowTaskCategoryListDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.all_rounded_corners_big));


        /* TASK PRIORITY SELECTION */

        mAdd_priority_TV.setOnClickListener(view -> {
            mPriority_List_LL.setVisibility(View.VISIBLE);

            pri_1.setOnClickListener(v -> {
                priority = (String) pri_1.getText();
                mAdd_priority_TV.setText(priority);
                mPriority_List_LL.setVisibility(View.GONE);
            });

            pri_2.setOnClickListener(v -> {
                priority = (String) pri_2.getText();
                mAdd_priority_TV.setText(priority);
                mPriority_List_LL.setVisibility(View.GONE);
            });
            pri_3.setOnClickListener(v -> {
                priority = (String) pri_3.getText();
                mAdd_priority_TV.setText(priority);
                mPriority_List_LL.setVisibility(View.GONE);
            });
            pri_4.setOnClickListener(v -> {
                priority = (String) pri_4.getText();
                mAdd_priority_TV.setText(priority);
                mPriority_List_LL.setVisibility(View.GONE);
            });
            pri_5.setOnClickListener(v -> {
                priority = (String) pri_5.getText();
                mAdd_priority_TV.setText(priority);
                mPriority_List_LL.setVisibility(View.GONE);
            });

        });


        /* TASK CATEGORY SELECTION */
        mSelectCategory_TV.setOnClickListener(view -> {

            // hooking the elements
            cat1_LL = mShowTaskCategoryListDialog.findViewById(R.id.category_1_LL);
            cat2_LL = mShowTaskCategoryListDialog.findViewById(R.id.category_2_LL);
            cat3_LL = mShowTaskCategoryListDialog.findViewById(R.id.category_3_LL);

            cat1_title = mShowTaskCategoryListDialog.findViewById(R.id.category_1_title_TV);
            cat2_title = mShowTaskCategoryListDialog.findViewById(R.id.category_2_title_TV);
            cat3_title = mShowTaskCategoryListDialog.findViewById(R.id.category_3_title_TV);

            cat1_des = mShowTaskCategoryListDialog.findViewById(R.id.category_1_des_TV);
            cat2_des = mShowTaskCategoryListDialog.findViewById(R.id.category_2_des_TV);
            cat3_des = mShowTaskCategoryListDialog.findViewById(R.id.category_3_des_TV);

            // popup

            mShowTaskCategoryListDialog.show();

            cat1_LL.setOnClickListener(v1 -> {

                // change this layout and its element's tint to royal blue and soft black and others to white and soft black
                cat1_LL.setBackground(getResources().getDrawable(R.drawable.tinted_all_rounded_corners));
                cat1_title.setTextColor(getResources().getColor(R.color.white));
                cat1_des.setTextColor(getResources().getColor(R.color.white));


                //
                cat2_LL.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners));
                cat2_title.setTextColor(getResources().getColor(R.color.soft_black));
                cat2_des.setTextColor(getResources().getColor(R.color.soft_black));

                cat3_LL.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners));
                cat3_title.setTextColor(getResources().getColor(R.color.soft_black));
                cat3_des.setTextColor(getResources().getColor(R.color.soft_black));

                category = "Category-1";
            });
            cat2_LL.setOnClickListener(v1 -> {

                // change this layout and its element's tint to royal blue and soft black and others to white and soft black
                cat2_LL.setBackground(getResources().getDrawable(R.drawable.tinted_all_rounded_corners));
                cat2_title.setTextColor(getResources().getColor(R.color.white));
                cat2_des.setTextColor(getResources().getColor(R.color.white));


                //
                cat1_LL.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners));
                cat1_title.setTextColor(getResources().getColor(R.color.soft_black));
                cat1_des.setTextColor(getResources().getColor(R.color.soft_black));

                cat3_LL.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners));
                cat3_title.setTextColor(getResources().getColor(R.color.soft_black));
                cat3_des.setTextColor(getResources().getColor(R.color.soft_black));

                category = "Category-2";
            });
            cat3_LL.setOnClickListener(v1 -> {

                // change this layout and its element's tint to royal blue and soft black and others to white and soft black
                cat3_LL.setBackground(getResources().getDrawable(R.drawable.tinted_all_rounded_corners));
                cat3_title.setTextColor(getResources().getColor(R.color.white));
                cat3_des.setTextColor(getResources().getColor(R.color.white));


                //
                cat1_LL.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners));
                cat1_title.setTextColor(getResources().getColor(R.color.soft_black));
                cat1_des.setTextColor(getResources().getColor(R.color.soft_black));

                cat2_LL.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners));
                cat2_title.setTextColor(getResources().getColor(R.color.soft_black));
                cat2_des.setTextColor(getResources().getColor(R.color.soft_black));

                category = "Category-3";
            });

            Button mOk_BTN = mShowTaskCategoryListDialog.findViewById(R.id.Ok_BTN);
            mOk_BTN.setOnClickListener(view1 -> {
                mSelectCategory_TV.setText(category);
                mShowTaskCategoryListDialog.dismiss();
            });
        });

        /* TASK DEADLINE SELECTION */

        /* TASK INIT() DEADLINE DATES */
//        initWeekLayout();

        /* Add Listeners and change the colors accordingly*/


        mDay1.setOnClickListener(v -> {

            // change background of selected layout to tinted background and change all other layouts to non-tinted background
            mDay1.setBackground(getResources().getDrawable(R.drawable.tinted_all_rounded_corners_small_btn));
            mDay_day1.setTextColor(getResources().getColor(R.color.white));
            mDay_date1.setTextColor(getResources().getColor(R.color.white));

            mDay2.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day2.setTextColor(getResources().getColor(R.color.black));
            mDay_date2.setTextColor(getResources().getColor(R.color.black));

            mDay3.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day3.setTextColor(getResources().getColor(R.color.black));
            mDay_date3.setTextColor(getResources().getColor(R.color.black));

            mDay4.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day4.setTextColor(getResources().getColor(R.color.black));
            mDay_date4.setTextColor(getResources().getColor(R.color.black));

            mDay5.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day5.setTextColor(getResources().getColor(R.color.black));
            mDay_date5.setTextColor(getResources().getColor(R.color.black));

            mDay6.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day6.setTextColor(getResources().getColor(R.color.black));
            mDay_date6.setTextColor(getResources().getColor(R.color.black));

            mDay7.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day7.setTextColor(getResources().getColor(R.color.black));
            mDay_date7.setTextColor(getResources().getColor(R.color.black));
            selected_date_index = 1;


        });

        mDay2.setOnClickListener(v -> {

            // change background of selected layout to tinted background and change all other layouts to non-tinted background
            mDay2.setBackground(getResources().getDrawable(R.drawable.tinted_all_rounded_corners_small_btn));
            mDay_day2.setTextColor(getResources().getColor(R.color.white));
            mDay_date2.setTextColor(getResources().getColor(R.color.white));

            mDay1.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day1.setTextColor(getResources().getColor(R.color.black));
            mDay_date1.setTextColor(getResources().getColor(R.color.black));

            mDay3.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day3.setTextColor(getResources().getColor(R.color.black));
            mDay_date3.setTextColor(getResources().getColor(R.color.black));

            mDay4.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day4.setTextColor(getResources().getColor(R.color.black));
            mDay_date4.setTextColor(getResources().getColor(R.color.black));

            mDay5.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day5.setTextColor(getResources().getColor(R.color.black));
            mDay_date5.setTextColor(getResources().getColor(R.color.black));

            mDay6.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day6.setTextColor(getResources().getColor(R.color.black));
            mDay_date6.setTextColor(getResources().getColor(R.color.black));

            mDay7.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day7.setTextColor(getResources().getColor(R.color.black));
            mDay_date7.setTextColor(getResources().getColor(R.color.black));
            selected_date_index = 2;
        });

        mDay3.setOnClickListener(v -> {

            // change background of selected layout to tinted background and change all other layouts to non-tinted background
            mDay3.setBackground(getResources().getDrawable(R.drawable.tinted_all_rounded_corners_small_btn));
            mDay_day3.setTextColor(getResources().getColor(R.color.white));
            mDay_date3.setTextColor(getResources().getColor(R.color.white));

            mDay2.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day2.setTextColor(getResources().getColor(R.color.black));
            mDay_date2.setTextColor(getResources().getColor(R.color.black));

            mDay1.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day1.setTextColor(getResources().getColor(R.color.black));
            mDay_date1.setTextColor(getResources().getColor(R.color.black));

            mDay4.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day4.setTextColor(getResources().getColor(R.color.black));
            mDay_date4.setTextColor(getResources().getColor(R.color.black));

            mDay5.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day5.setTextColor(getResources().getColor(R.color.black));
            mDay_date5.setTextColor(getResources().getColor(R.color.black));

            mDay6.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day6.setTextColor(getResources().getColor(R.color.black));
            mDay_date6.setTextColor(getResources().getColor(R.color.black));

            mDay7.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day7.setTextColor(getResources().getColor(R.color.black));
            mDay_date7.setTextColor(getResources().getColor(R.color.black));
            selected_date_index = 3;
        });

        mDay4.setOnClickListener(v -> {

            // change background of selected layout to tinted background and change all other layouts to non-tinted background
            mDay4.setBackground(getResources().getDrawable(R.drawable.tinted_all_rounded_corners_small_btn));
            mDay_day4.setTextColor(getResources().getColor(R.color.white));
            mDay_date4.setTextColor(getResources().getColor(R.color.white));

            mDay2.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day2.setTextColor(getResources().getColor(R.color.black));
            mDay_date2.setTextColor(getResources().getColor(R.color.black));

            mDay3.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day3.setTextColor(getResources().getColor(R.color.black));
            mDay_date3.setTextColor(getResources().getColor(R.color.black));

            mDay1.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day1.setTextColor(getResources().getColor(R.color.black));
            mDay_date1.setTextColor(getResources().getColor(R.color.black));

            mDay5.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day5.setTextColor(getResources().getColor(R.color.black));
            mDay_date5.setTextColor(getResources().getColor(R.color.black));

            mDay6.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day6.setTextColor(getResources().getColor(R.color.black));
            mDay_date6.setTextColor(getResources().getColor(R.color.black));

            mDay7.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day7.setTextColor(getResources().getColor(R.color.black));
            mDay_date7.setTextColor(getResources().getColor(R.color.black));
            selected_date_index = 4;
        });

        mDay5.setOnClickListener(v -> {

            // change background of selected layout to tinted background and change all other layouts to non-tinted background
            mDay5.setBackground(getResources().getDrawable(R.drawable.tinted_all_rounded_corners_small_btn));
            mDay_day5.setTextColor(getResources().getColor(R.color.white));
            mDay_date5.setTextColor(getResources().getColor(R.color.white));

            mDay2.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day2.setTextColor(getResources().getColor(R.color.black));
            mDay_date2.setTextColor(getResources().getColor(R.color.black));

            mDay3.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day3.setTextColor(getResources().getColor(R.color.black));
            mDay_date3.setTextColor(getResources().getColor(R.color.black));

            mDay1.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day1.setTextColor(getResources().getColor(R.color.black));
            mDay_date1.setTextColor(getResources().getColor(R.color.black));

            mDay4.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day4.setTextColor(getResources().getColor(R.color.black));
            mDay_date4.setTextColor(getResources().getColor(R.color.black));

            mDay6.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day6.setTextColor(getResources().getColor(R.color.black));
            mDay_date6.setTextColor(getResources().getColor(R.color.black));

            mDay7.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day7.setTextColor(getResources().getColor(R.color.black));
            mDay_date7.setTextColor(getResources().getColor(R.color.black));
            selected_date_index = 5;
        });

        mDay6.setOnClickListener(v -> {

            // change background of selected layout to tinted background and change all other layouts to non-tinted background
            mDay6.setBackground(getResources().getDrawable(R.drawable.tinted_all_rounded_corners_small_btn));
            mDay_day6.setTextColor(getResources().getColor(R.color.white));
            mDay_date6.setTextColor(getResources().getColor(R.color.white));

            mDay2.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day2.setTextColor(getResources().getColor(R.color.black));
            mDay_date2.setTextColor(getResources().getColor(R.color.black));

            mDay3.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day3.setTextColor(getResources().getColor(R.color.black));
            mDay_date3.setTextColor(getResources().getColor(R.color.black));

            mDay4.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day4.setTextColor(getResources().getColor(R.color.black));
            mDay_date4.setTextColor(getResources().getColor(R.color.black));

            mDay5.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day5.setTextColor(getResources().getColor(R.color.black));
            mDay_date5.setTextColor(getResources().getColor(R.color.black));

            mDay1.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day1.setTextColor(getResources().getColor(R.color.black));
            mDay_date1.setTextColor(getResources().getColor(R.color.black));

            mDay7.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day7.setTextColor(getResources().getColor(R.color.black));
            mDay_date7.setTextColor(getResources().getColor(R.color.black));
            selected_date_index = 6;
        });


        mDay7.setOnClickListener(v -> {

            // change background of selected layout to tinted background and change all other layouts to non-tinted background
            mDay7.setBackground(getResources().getDrawable(R.drawable.tinted_all_rounded_corners_small_btn));
            mDay_day7.setTextColor(getResources().getColor(R.color.white));
            mDay_date7.setTextColor(getResources().getColor(R.color.white));

            mDay2.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day2.setTextColor(getResources().getColor(R.color.black));
            mDay_date2.setTextColor(getResources().getColor(R.color.black));

            mDay3.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day3.setTextColor(getResources().getColor(R.color.black));
            mDay_date3.setTextColor(getResources().getColor(R.color.black));

            mDay4.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day4.setTextColor(getResources().getColor(R.color.black));
            mDay_date4.setTextColor(getResources().getColor(R.color.black));

            mDay5.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day5.setTextColor(getResources().getColor(R.color.black));
            mDay_date5.setTextColor(getResources().getColor(R.color.black));

            mDay6.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day6.setTextColor(getResources().getColor(R.color.black));
            mDay_date6.setTextColor(getResources().getColor(R.color.black));

            mDay1.setBackground(getResources().getDrawable(R.drawable.all_rounded_corners_small_btn));
            mDay_day1.setTextColor(getResources().getColor(R.color.black));
            mDay_date1.setTextColor(getResources().getColor(R.color.black));
            selected_date_index = 7;
        });

        Toast.makeText(this, String.valueOf(selected_date_index), Toast.LENGTH_SHORT).show();




        /* TASK TIME SELECTION */

        /* TASK START TIME SELECTION */
        mAssign_start_time_BTN.setOnClickListener(view -> {
            TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selected_start_hour, selected_startMinute) -> {
                startHours = selected_start_hour;
                startMinute = selected_startMinute;

                start_selected_time = (String.format(Locale.getDefault(), "%02d : %02d", startHours, startMinute));
                mAssign_start_time_BTN.setText(start_selected_time);
                Toast.makeText(CreateTask.this, start_selected_time, Toast.LENGTH_SHORT).show();

            };
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, startHours, startMinute, true);
            timePickerDialog.setTitle("Select time");
            timePickerDialog.show();
        });

        /* TASK END TIME SELECTION */
        mAssign_end_time_BTN.setOnClickListener(view -> {
            TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selected_end_hour, int selectedEndMinute) {
                    endHours = selected_end_hour;
                    endMinute = selectedEndMinute;

                    end_selected_time = (String.format(Locale.getDefault(), "%02d : %02d", endHours, endMinute));
                    Toast.makeText(CreateTask.this, end_selected_time, Toast.LENGTH_SHORT).show();
                    mAssign_end_time_BTN.setText(String.format(Locale.getDefault(), "%02d : %02d", endHours, endMinute));
                }
            };

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, endHours, endMinute, true);
            timePickerDialog.setTitle("Select time");
            timePickerDialog.show();
        });


//        Log.d("TEST", "on create create task");

        // category list
//        arrayAdapter = new ArrayAdapter<>(this, R.layout.catrgory_list_item, category_list);
//        mCategoryLV.setAdapter(arrayAdapter);
//
//        mDone_FAB.setOnClickListener(view -> {
//
//            // send Data
//            task_title = mTitle_ET.getText().toString();
//            task_description = mDescription_ET.getText().toString();
//
//            Toast.makeText(this, "Title " + mTitle_ET.getText().toString(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "Des " + mDescription_ET.getText().toString(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "Date " + selected_date, Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "start time " + start_selected_time, Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "end time " + end_selected_time, Toast.LENGTH_SHORT).show();
//
//
//            Log.d("TEST", "on done create task");
//
//
////            System.out.println(task_data.toString());
//            TaskHelper newTaskObj = new TaskHelper(task_title,
//                    selected_date,
//                    start_selected_time,
//                    end_selected_time,
//                    task_description,
//                    "idk",
//                    true);
//
//
////            Log.d("PREVTITLE", newTaskObj.getTitle());
////            Log.d("PREVDATE ", newTaskObj.getDate());
////            Log.d("PREVST", newTaskObj.getStart_time());
////            Log.d("PREVET", newTaskObj.getEnd_time());
////            Log.d("PREVDES", newTaskObj.getDescription());
//            TaskHelper task_data = new TaskHelper(task_title, selected_date, start_selected_time, end_selected_time, task_description, "idk", true);
//
//            Intent new_task = new Intent(CreateTask.this, MainActivity.class);
////            new_task.putExtra("title",newTaskObj.getTitle());
////            Log.d("title",newTaskObj.getTitle());
////            new_task.putExtra("NewTaskObj", newTaskObj);
////            Log.d("NEWOBJ", newTaskObj.toString());
////            new_task.putExtra("TEST","TEXT");
//            new SyncHelper(task_data, true);
//            startActivity(new_task);
//            finish();
//        });
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private String getSelectedDateCustom(int dd, int mm, int yyyy, String selected_date) {
//        LocalDate date = LocalDate.parse(selected_date);
//        DayOfWeek dayOfWeek = date.getDayOfWeek();
//
//        String temp = String.valueOf(dayOfWeek).toLowerCase(Locale.ENGLISH);
//        String res = temp.substring(0, 1).toUpperCase(Locale.ENGLISH) + temp.substring(1) + ", " + dd + " " + getCustomMonth(mm) + " " + yyyy;
//
//        return res;
//    }

//    private String getCustomMonth(int m) {
//        switch (m) {
//            case 1:
//                return "Jan";
//            case 2:
//                return "Feb";
//            case 3:
//                return "Mar";
//            case 4:
//                return "Apr";
//            case 5:
//                return "May";
//            case 6:
//                return "Jun";
//            case 7:
//                return "Jul";
//            case 8:
//                return "Aug";
//            case 9:
//                return "Sep";
//            case 10:
//                return "Oct";
//            case 11:
//                return "Nov";
//            case 12:
//                return "Dec";
//        }
//        return "";

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initWeekLayout() {
        String[] trimmed_dates = new String[7];
        String[] trimmed_days = new String[7];

        String[] days_of_week = new String[7];
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatDayOfWeek = new SimpleDateFormat("EE");


        String str = String.valueOf(LocalDate.now());
        String[] temp = str.split("-");
        calendar.set(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]) - 1, Integer.parseInt(temp[2]));
        Toast.makeText(this, String.valueOf(calendar.getFirstDayOfWeek()), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, str+" "+String.valueOf(calendar.get(calendar.DAY_OF_WEEK)), Toast.LENGTH_SHORT).show();


        for (int i = 0; i < 7; i++) {

            days_of_week[i] = formatDate.format(calendar.getTime());
            trimmed_dates[i] = days_of_week[i].substring(8);
//                trimmed_days[i] = formatDayOfWeek.format(calendar.getTime()).substring(0,1);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        mDay_date1.setText(trimmed_dates[0]);
        mDay_date2.setText(trimmed_dates[1]);
        mDay_date3.setText(trimmed_dates[2]);
        mDay_date4.setText(trimmed_dates[3]);
        mDay_date5.setText(trimmed_dates[4]);
        mDay_date6.setText(trimmed_dates[5]);
        mDay_date7.setText(trimmed_dates[6]);

        Log.d("TEST_WEEK_DAYS", Arrays.toString(days_of_week));
        Log.d("TEST_WEEK_DAYS", Arrays.toString(trimmed_dates));
        Log.d("TEST_WEEK_DAYS", Arrays.toString(trimmed_days));

    }


    public void backToDashboard(View view) {
        startActivity(new Intent(CreateTask.this, MainActivity.class));
        finish();
    }

//    private void updateDate() {
//        String myFormat = "dd/MM/yy"; //put your date format in which you need to display
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
//        etDate.setText(sdf.format(myCalendar.getTime()));
//    }
}