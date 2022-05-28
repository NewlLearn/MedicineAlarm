package com.example.medicinealarmapplication.utils;

import android.graphics.PorterDuff;
import android.widget.TextView;

public class BackgroundUtil {
    public static void setTextViewDrawableColor(TextView textView, int color) {
       textView.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

}

