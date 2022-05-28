package com.example.medicinealarmapplication.utils;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public static String convertToTime(String strDate){

        SimpleDateFormat fmtOut = new SimpleDateFormat("hh:mm aa");
        return fmtOut.format(strDate);

    }
}