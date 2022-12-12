package com.example.dynamicworkscheduler;

import android.os.Parcel;
import android.os.Parcelable;


public class TaskHelper implements Parcelable {
    String title, date, start_time, end_time, description, category;
    boolean isDeadline;

    public TaskHelper(String title, String date, String start_time, String end_time, String description, String category, boolean isDeadline) {
        this.title = title;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.description = description;
        this.category = category;
        this.isDeadline = isDeadline;
    }

    protected TaskHelper(Parcel in) {
        title = in.readString();
        date = in.readString();
        start_time = in.readString();
        end_time = in.readString();
        description = in.readString();
        category = in.readString();
        isDeadline = in.readByte() != 0;
    }

    public static final Creator<TaskHelper> CREATOR = new Creator<TaskHelper>() {
        @Override
        public TaskHelper createFromParcel(Parcel in) {
            return new TaskHelper(in);
        }

        @Override
        public TaskHelper[] newArray(int size) {
            return new TaskHelper[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "TaskHelper{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", isDeadline=" + isDeadline +
                '}';
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isDeadline() {
        return isDeadline;
    }

    public void setDeadline(boolean deadline) {
        isDeadline = deadline;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(date);
        parcel.writeString(start_time);
        parcel.writeString(end_time);
        parcel.writeString(description);
        parcel.writeString(category);
        parcel.writeByte((byte) (isDeadline ? 1 : 0));
    }
}
