package com.example.dynamicworkscheduler;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    Button mCancel_dialog_YES_BTN, mCancel_dialog_NO_BTN, mUpdate_dialog_YES_BTN, mUpdate_dialog_NO_BTN;
    Dialog task_activity_cancel_dialog, task_activity_update_dialog;
    ConstraintLayout mInProgress_Layout;
    ConstraintLayout mExpandable_pane;
    LinearLayout mExpandable_pane_LL;
    ConstraintLayout mLower_pane;
    ImageView mExpand_upNext_IV;
    int[] chart_colors;
    PieChart pieChart;
    List<String> category_list;
    CategoryListAdapter categoryListAdapter;
    RecyclerView mCategoryRV;
    LinearLayoutManager linearLayoutManager;
    TextView mTaskDate, mWeek_day1, mSeeFullReport, mUp_next_external_TV,mIn_progress_Tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        mIn_progress_Tv = findViewById(R.id.in_progress_Tv);
        mInProgress_Layout = findViewById(R.id.InProgress_Layout);
//        mUp_next_external_TV = findViewById(R.id.up_next_external_TV);
        mExpandable_pane = findViewById(R.id.lower_pane);
        pieChart = findViewById(R.id.pie_chart);
        mExpandable_pane_LL = findViewById(R.id.Expandable_layout);
        mLower_pane = findViewById(R.id.lower_pane);
//        mExpand_upNext_IV = findViewById(R.id.expand_upNext_IV);

        setUpPieChart();
        initPieChart();

        findViewById(R.id.today_task_TV).setOnClickListener(view -> startActivity(new Intent(Dashboard.this, ScheduleOverview.class)));


        findViewById(R.id.add_task).setOnClickListener(view -> {
            Log.d("TEST", "on call create task");
//            activityCallingFlag = "createTask";
            startActivity(new Intent(Dashboard.this, CreateTask.class));
//            finish();
        });

        mInProgress_Layout.setOnClickListener(view -> {
            Toast.makeText(this, "clicked layout", Toast.LENGTH_SHORT).show();
            task_activity_update_dialog = new Dialog(Dashboard.this);
            task_activity_update_dialog.setContentView(R.layout.task_activity_dialog);
            task_activity_update_dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            task_activity_update_dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.all_rounded_corners_big));
            task_activity_update_dialog.show();

            mUpdate_dialog_YES_BTN = task_activity_update_dialog.findViewById(R.id.Update_dialog_YES_BTN);
            mUpdate_dialog_NO_BTN = task_activity_update_dialog.findViewById(R.id.Update_dialog_NO_BTN);

            mUpdate_dialog_YES_BTN.setOnClickListener(view1 -> {
                Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
                task_activity_update_dialog.dismiss();
            });
            mUpdate_dialog_NO_BTN.setOnClickListener(view1 -> task_activity_update_dialog.dismiss());

        });

        mIn_progress_Tv.setOnClickListener(view -> {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
            task_activity_cancel_dialog = new Dialog(Dashboard.this);
            task_activity_cancel_dialog.setContentView(R.layout.task_activity_cancel_dialog);
            task_activity_cancel_dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            task_activity_cancel_dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.all_rounded_corners_big));
            task_activity_cancel_dialog.show();

            mCancel_dialog_YES_BTN = task_activity_cancel_dialog.findViewById(R.id.Cancel_dialog_YES_BTN);
            mCancel_dialog_NO_BTN = task_activity_cancel_dialog.findViewById(R.id.Cancel_dialog_NO_BTN);

            mCancel_dialog_NO_BTN.setOnClickListener(view1 -> task_activity_cancel_dialog.dismiss());
            mCancel_dialog_YES_BTN.setOnClickListener(view1 -> {
                Toast.makeText(Dashboard.this, "YES Cancel", Toast.LENGTH_SHORT).show();
                task_activity_cancel_dialog.dismiss();
            });


        });

//        Log.d("TEST", "flag in create "+collectTaskData);

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


//        boolean isDataAvailable = SyncHelper.isDataAvailable;
//        if(isDataAvailable)
//            mTaskDate.setText(SyncHelper.getTask().toString());

    }

    private void initPieChart() {

        chart_colors = new int[2];
        chart_colors[0] = getResources().getColor(R.color.compli_royal_blue);
        chart_colors[1] = getResources().getColor(R.color.orange);


        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(0.78f, ""));
        pieEntries.add(new PieEntry(0.22f, ""));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setColors(chart_colors);
        pieDataSet.setDrawIcons(false);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTypeface(Typeface.DEFAULT_BOLD);
        pieData.setValueTextSize(0);
        pieChart.setData(pieData);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
//                Toast.makeText(Report_Screen.this, e.get, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {
            }
        });

    }

    private void setUpPieChart() {
        pieChart.setRotationAngle(90);
        pieChart.setUsePercentValues(true);
        pieChart.animateXY(1000, 1000);
        pieChart.setHapticFeedbackEnabled(true);
//        pieChart.getDescription().setTextColor(getResources().getColor(R.color.black));
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleRadius(85f);
//        pieChart.setHoleColor(R.color.white);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setEntryLabelTextSize(16);
        pieChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD);
        pieChart.setTransparentCircleRadius(0f);
        pieChart.setRotationEnabled(false);

        pieChart.setCenterText("78%");
        pieChart.setCenterTextSize(21);
        pieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD);
        pieChart.getLegend().setEnabled(false);
        pieChart.invalidate();


    }

//
//    private void intiCategoryData() {
//        String[] s = new String[]{"Design", "Develop", "Blog", "Sales", "Backend", "FrontEnd", "Business"};
//        category_list = Arrays.asList(s);
//    }



    public void expand(View view) {
        int visibility = mExpandable_pane_LL.getVisibility() == View.GONE ? View.VISIBLE : View.GONE;
        TransitionManager.beginDelayedTransition(mLower_pane, new AutoTransition());

        // if visible
        if (visibility == 0) {
//            mExpand_upNext_IV.setRotation(180f);
//            getWindow().setStatusBarColor(Color.parseColor("#e1b941"));
        }
        // if gone
        if (visibility == 8) {
//            mExpand_upNext_IV.setRotation(360f);
//            getWindow().setStatusBarColor(Color.parseColor("#ffffff"));
        }
        mExpandable_pane_LL.setVisibility(visibility);
    }

    public void openReportScreen(View view) {
        startActivity(new Intent(Dashboard.this, Report_Screen.class));
    }
}