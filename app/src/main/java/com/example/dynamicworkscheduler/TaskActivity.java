package com.example.dynamicworkscheduler;

public class TaskActivity {
    String item_start_time, item_title, item_inside_startTime, item_inside_EndTime;
    int item_state_image, item_dynamic_bg;

    public TaskActivity(String item_start_time, String item_title, String item_inside_startTime, String item_inside_EndTime, int item_state_image, int item_dynamic_bg) {
        this.item_start_time = item_start_time;
        this.item_title = item_title;
        this.item_inside_startTime = item_inside_startTime;
        this.item_inside_EndTime = item_inside_EndTime;
        this.item_state_image = item_state_image;
        this.item_dynamic_bg = item_dynamic_bg;
    }
}
