package com.example.dynamicworkscheduler;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class Report_Screen extends AppCompatActivity {

    ListView mTaskActivity_LV;
    ArrayList<TaskActivity> act_task_list;
    private int[] chart_colors;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_screen);

        pieChart = findViewById(R.id.pie_chart);
        mTaskActivity_LV = findViewById(R.id.TaskActivity_LV);

        setUpPieChart();
        initPieChart();

        initTaskActivityList();
    }

    private void initTaskActivityList() {

        String[] titles =  {"Create wireframes","Do Epic Shit", "Keep it Low", "Catch me If you can", "Lionel Messi", "Create wireframes","Do Epic Shit", "Keep it Low"};
        String[] start_times = {"10:00", "12:00", "15:00", "17:00", "18:00", "10:00", "12:00", "15:00"};
        String[] end_times = {"11:30", "14:00", "16:00", "17:30", "20:00", "11:30", "14:00", "16:00"};
        int[] states = {R.drawable.ic_suspended, R.drawable.ic_pending, R.drawable.ic_finished, R.drawable.ic_suspended, R.drawable.ic_pending, R.drawable.ic_finished, R.drawable.ic_pending, R.drawable.ic_finished};
        int[] DynamicBg = {R.drawable.suspended_task_bg , R.drawable.pending_task_bg, R.drawable.finished_task_bg , R.drawable.suspended_task_bg, R.drawable.pending_task_bg , R.drawable.finished_task_bg,R.drawable.pending_task_bg , R.drawable.finished_task_bg,};


        act_task_list = new ArrayList<>();
        for(int i = 0; i < titles.length; i++)
        {
            act_task_list.add(new TaskActivity(start_times[i], titles[i], start_times[i], end_times[i], states[i], DynamicBg[i]));
        }

        TaskActivityAdapter taskActivityAdapter = new TaskActivityAdapter(Report_Screen.this, act_task_list);
        mTaskActivity_LV.setAdapter(taskActivityAdapter);
        mTaskActivity_LV.setClickable(true);

    }

    private void initPieChart() {

        chart_colors = new int[3];
        chart_colors[0] = getResources().getColor(R.color.finished_task_color); //#47B39C
        chart_colors[1] = getResources().getColor(R.color.pending_task_color); // #FFC154
        chart_colors[2] = getResources().getColor(R.color.suspended_task_color); // #EC6B56

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(6f, "tasks"));
        pieEntries.add(new PieEntry(3f, "tasks"));
        pieEntries.add(new PieEntry(1f, "tasks"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Report");
        pieDataSet.setColors(chart_colors);
        pieDataSet.setDrawIcons(false);
        PieData pieData = new PieData(pieDataSet);
//        Toast.makeText(this, String.format("%.0f" ,(pieEntries.get(1).getValue())), Toast.LENGTH_SHORT).show();
        pieData.setValueTypeface(Typeface.DEFAULT_BOLD);
        pieChart.setUsePercentValues(false);
        pieData.setValueTextSize(14);
        pieChart.setData(pieData);
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
//                Toast.makeText(Report_Screen.this, e.get, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected() {}
        });

    }

    private void setUpPieChart() {
         pieChart.setUsePercentValues(true);
        pieChart.animateXY(1000, 1000);
        pieChart.setHapticFeedbackEnabled(true);
        pieChart.getDescription().setTextColor(getResources().getColor(R.color.black));
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleRadius(30f);
        pieChart.setEntryLabelTextSize(13);
        pieChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD);
        pieChart.setTransparentCircleRadius(0f);
        pieChart.setRotationEnabled(false);

        pieChart.setCenterText("14\ntasks");
        pieChart.setCenterTextSize(16);
        pieChart.setCenterTextColor(R.color.black);
        pieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD);
        pieChart.getLegend().setEnabled(false);
        pieChart.invalidate();

    }

    public void backToDashboard(View view) {
        onBackPressed();
    }
}