package com.example.dynamicworkscheduler;

public class SyncHelper
{
    static boolean isDataAvailable ;
    static TaskHelper task;

    SyncHelper(TaskHelper task, boolean flag)
    {
        this.task = task;
        this.isDataAvailable = flag;
    }

    public static boolean isDataAvailable() {
        return isDataAvailable;
    }

    public static TaskHelper getTask() {
        return task;
    }
//
//    @Override
//    public static String toString() {
//        return "SyncHelper{" +
//                "isDataAvailable=" + isDataAvailable +
//                ", task=" + task +
//                '}';
//    }
}

