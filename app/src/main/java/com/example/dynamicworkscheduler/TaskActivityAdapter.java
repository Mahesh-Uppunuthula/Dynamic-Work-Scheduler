package com.example.dynamicworkscheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class TaskActivityAdapter extends ArrayAdapter<TaskActivity> {

    Report_Screen report_screen;

    public TaskActivityAdapter(@NonNull Context context, @NonNull ArrayList<TaskActivity> objects) {
        super(context, R.layout.user_task_activity_list_item, objects);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TaskActivity taskActivity = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_task_activity_list_item, parent, false);
        }

        TextView item_start_time = convertView.findViewById(R.id.Item_StartTime);
        TextView item_inside_start_time = convertView.findViewById(R.id.Item_inside_StartTime);
        TextView item_inside_end_time = convertView.findViewById(R.id.Item_inside_EndTime);
        TextView title_TV = convertView.findViewById(R.id.Item_Title);
        ImageView state_IV = convertView.findViewById(R.id.Item_state);
        LinearLayout item_dynamic_ll = convertView.findViewById(R.id.Dynamic_layout_LL);

        item_start_time.setText(taskActivity.item_start_time);
        item_inside_start_time.setText(taskActivity.item_inside_startTime);
        item_inside_end_time.setText(taskActivity.item_inside_EndTime);
        title_TV.setText(taskActivity.item_title);
        state_IV.setImageResource(taskActivity.item_state_image);
        item_dynamic_ll.setBackground(ContextCompat.getDrawable(getContext(), taskActivity.item_dynamic_bg));


        return convertView;
    }
}
