package com.example.medicinealarmapplication.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;
import java.util.Date;

@Entity(tableName = "medicine_schedule")
public class medicine_schedule implements Parcelable {
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "alarm_id")
    private String alarm_id;

    @ColumnInfo(name = "alarm_number")
    private int alarm_number;

    @ColumnInfo(name = "alarm_color")
    private String alarm_color;

    @ColumnInfo(name = "alarm_time")
    //@TypeConverters(DateConverter.class)
    private Date alarm_time;
    //private Timestamp alarm_time;

    @ColumnInfo(name = "atMonday")
    private String atMonday;

    @ColumnInfo(name = "atTuesday")
    private String atTuesday;

    @ColumnInfo(name = "atWednesday")
    private String atWednesday;

    @ColumnInfo(name = "atThursday")
    private String atThursday;

    @ColumnInfo(name = "atFriday")
    private String atFriday;

    @ColumnInfo(name = "atSaturday")
    private String atSaturday;

    @ColumnInfo(name = "atSunday")
    private String atSunday;

    @ColumnInfo(name = "max_count")
    private int max_count;

    @ColumnInfo(name = "medicine_name")
    private String medicine_name;

    @ColumnInfo(name = "min_count")
    private int min_count;

    @ColumnInfo(name = "total_count")
    private int total_count;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "medicine_count")
    private String medicine_count;


    public medicine_schedule(@NonNull String alarm_id, int alarm_number, String alarm_color,
                             Date alarm_time, String atMonday, String atTuesday,
                             String atWednesday, String atThursday, String atFriday,
                             String atSaturday, String atSunday, int max_count,
                             String medicine_name, int min_count, int total_count,
                             String status, String medicine_count) {
        this.alarm_id = alarm_id;
        this.alarm_number = alarm_number;
        this.alarm_color = alarm_color;
        this.alarm_time = alarm_time;
        this.atMonday = atMonday;
        this.atTuesday = atTuesday;
        this.atWednesday = atWednesday;
        this.atThursday = atThursday;
        this.atFriday = atFriday;
        this.atSaturday = atSaturday;
        this.atSunday = atSunday;
        this.max_count = max_count;
        this.medicine_name = medicine_name;
        this.min_count = min_count;
        this.total_count = total_count;
        this.status = status;
        this.medicine_count = medicine_count;
    }

    public medicine_schedule() {

    }

    protected medicine_schedule(Parcel in) {
        alarm_id = in.readString();
        alarm_number = in.readInt();
        alarm_color = in.readString();
        atMonday = in.readString();
        atTuesday = in.readString();
        atWednesday = in.readString();
        atThursday = in.readString();
        atFriday = in.readString();
        atSaturday = in.readString();
        atSunday = in.readString();
        max_count = in.readInt();
        medicine_name = in.readString();
        min_count = in.readInt();
        total_count = in.readInt();
        status = in.readString();
        medicine_count = in.readString();
        alarm_time = new Date(in.readLong());
    }

    public static final Creator<medicine_schedule> CREATOR = new Creator<medicine_schedule>() {
        @Override
        public medicine_schedule createFromParcel(Parcel in) {
            return new medicine_schedule(in);
        }

        @Override
        public medicine_schedule[] newArray(int size) {
            return new medicine_schedule[size];
        }
    };

    public int getAlarm_number() {
        return alarm_number;
    }

    public void setAlarm_number(int alarm_number) {
        this.alarm_number = alarm_number;
    }

    public String getAlarm_color() {
        return alarm_color;
    }

    public void setAlarm_color(String alarm_color) {
        this.alarm_color = alarm_color;
    }

    public Date getAlarm_time() {
        return alarm_time;
    }

    public void setAlarm_time(Date alarm_time) {
        this.alarm_time = alarm_time;
    }

    public int getMax_count() {
        return max_count;
    }

    public void setMax_count(int max_count) {
        this.max_count = max_count;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public int getMin_count() {
        return min_count;
    }

    public void setMin_count(int min_count) {
        this.min_count = min_count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAtMonday() {
        return atMonday;
    }

    public void setAtMonday(String atMonday) {
        this.atMonday = atMonday;
    }

    public String getAtTuesday() {
        return atTuesday;
    }

    public void setAtTuesday(String atTuesday) {
        this.atTuesday = atTuesday;
    }

    public String getAtWednesday() {
        return atWednesday;
    }

    public void setAtWednesday(String atWednesday) {
        this.atWednesday = atWednesday;
    }

    public String getAtThursday() {
        return atThursday;
    }

    public void setAtThursday(String atThursday) {
        this.atThursday = atThursday;
    }

    public String getAtFriday() {
        return atFriday;
    }

    public void setAtFriday(String atFriday) {
        this.atFriday = atFriday;
    }

    public String getAtSaturday() {
        return atSaturday;
    }

    public void setAtSaturday(String atSaturday) {
        this.atSaturday = atSaturday;
    }

    public String getAtSunday() {
        return atSunday;
    }

    public void setAtSunday(String atSunday) {
        this.atSunday = atSunday;
    }

    public String getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(String alarm_id) {
        this.alarm_id = alarm_id;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public String getMedicine_count() {
        return medicine_count;
    }

    public void setMedicine_count(String medicine_count) {
        this.medicine_count = medicine_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(alarm_id);
        parcel.writeInt(alarm_number);
        parcel.writeString(alarm_color);
        parcel.writeString(atMonday);
        parcel.writeString(atTuesday);
        parcel.writeString(atWednesday);
        parcel.writeString(atThursday);
        parcel.writeString(atFriday);
        parcel.writeString(atSaturday);
        parcel.writeString(atSunday);
        parcel.writeInt(max_count);
        parcel.writeString(medicine_name);
        parcel.writeInt(min_count);
        parcel.writeInt(total_count);
        parcel.writeString(status);
        parcel.writeString(medicine_count);
        parcel.writeLong(alarm_time.getTime());
    }
}
