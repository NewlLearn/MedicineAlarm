package com.example.medicinealarmapplication.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.medicinealarmapplication.R;
import com.example.medicinealarmapplication.contract.RefreshDelegate;
import com.example.medicinealarmapplication.model.medicine_schedule;
import com.example.medicinealarmapplication.utils.BackgroundUtil;
import com.example.medicinealarmapplication.utils.DateConverter;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MedicineAlarmDetailsActivity extends AppCompatActivity
        implements View.OnClickListener, RefreshDelegate {

    private View viewTheme;
    private TextView txtAlarmTime;
    private TextView txtDetailsM;
    private TextView txtDetailsT;
    private TextView txtDetailsW;
    private TextView txtDetailsTh;
    private TextView txtDetailsF;
    private TextView txtDetailsS;
    private TextView txtDetailsSu;
    private TextView txtMedicineName;
    private TextView txtTotalMed;
    private TextView txtMedMax;
    private TextView txtMedMin;
    private Button btnTakeMedicine;
    private TextView txtRefill;
    private ImageView imgBack;

    private medicine_schedule medicineSched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_alarm_details);

        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);

        init();
        populate();
    }

    private void init(){

        Bundle bundle = getIntent().getExtras();
        medicineSched = bundle.getParcelable("MEDICINE_DETAILS");

        viewTheme = findViewById(R.id.view3);
        txtAlarmTime = findViewById(R.id.txtAlarmTime);
        txtDetailsM = findViewById(R.id.txtDetailsM);
        txtDetailsT = findViewById(R.id.txtDetailsT);
        txtDetailsW = findViewById(R.id.txtDetailsW);
        txtDetailsTh = findViewById(R.id.txtDetailsTh);
        txtDetailsF = findViewById(R.id.txtDetailsF);
        txtDetailsS = findViewById(R.id.txtDetailsS);
        txtDetailsSu = findViewById(R.id.txtDetailsSu);
        txtMedicineName = findViewById(R.id.txtMedicineName);
        txtTotalMed = findViewById(R.id.txtTotalMed);
        txtMedMax = findViewById(R.id.txtMedMax);
        txtMedMin = findViewById(R.id.txtMedMin);
        btnTakeMedicine = findViewById(R.id.btnTakeMedicine);
        txtRefill = findViewById(R.id.txtRefill);
        imgBack = findViewById(R.id.imgBack);

        btnTakeMedicine.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        txtRefill.setOnClickListener(this);

    }

    private void populate()
    {

        SimpleDateFormat formatOutput = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH);

        String timeLbl = formatOutput.format(medicineSched.getAlarm_time());
        txtAlarmTime.setText(timeLbl);

        txtMedicineName.setText(medicineSched.getMedicine_name());
        txtMedMax.setText(String.valueOf(medicineSched.getMax_count()));
        txtMedMin.setText(String.valueOf(medicineSched.getMin_count()));
        txtTotalMed.setText(String.valueOf(medicineSched.getTotal_count()));

        String theme = "#"+medicineSched.getAlarm_color();
        viewTheme.setBackgroundColor(Color.parseColor(theme));

        String dailyTheme = "#40"+medicineSched.getAlarm_color();

        if (medicineSched.getAtMonday().equals("Active"))
            BackgroundUtil.setTextViewDrawableColor(txtDetailsM, Color.parseColor(dailyTheme));

        if (medicineSched.getAtTuesday().equals("Active"))
            BackgroundUtil.setTextViewDrawableColor(txtDetailsT, Color.parseColor(dailyTheme));

        if (medicineSched.getAtWednesday().equals("Active"))
            BackgroundUtil.setTextViewDrawableColor(txtDetailsW, Color.parseColor(dailyTheme));

        if (medicineSched.getAtThursday().equals("Active"))
            BackgroundUtil.setTextViewDrawableColor(txtDetailsTh, Color.parseColor(dailyTheme));

        if (medicineSched.getAtFriday().equals("Active"))
            BackgroundUtil.setTextViewDrawableColor(txtDetailsF, Color.parseColor(dailyTheme));

        if (medicineSched.getAtSaturday().equals("Active"))
            BackgroundUtil.setTextViewDrawableColor(txtDetailsS, Color.parseColor(dailyTheme));

        if (medicineSched.getAtSunday().equals("Active"))
            BackgroundUtil.setTextViewDrawableColor(txtDetailsSu, Color.parseColor(dailyTheme));
    }

    @Override
    public void setRefresh(int medicine_count) {

        medicineSched.setTotal_count(medicine_count);
        txtTotalMed.setText(String.valueOf(medicineSched.getTotal_count()));

    }

    @Override
    public void onClick(View view) {

        Bundle bundle = new Bundle();
        bundle.putParcelable("MEDICINE_DETAILS", medicineSched);

        switch (view.getId()){
            case R.id.imgBack:{
                onBackPressed();

                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
                break;
            }
            case R.id.btnTakeMedicine:{

                TakeMedicineDialogFragment newFragment = new TakeMedicineDialogFragment();
                newFragment.setArguments(bundle);
                newFragment.setDelegate(this);
                newFragment.show(getSupportFragmentManager(),
                        TakeMedicineDialogFragment.TAG);
                break;
            }
            case R.id.txtRefill:{
                RefillMedicineDialogFragment newFragment = new RefillMedicineDialogFragment();
                newFragment.setArguments(bundle);
                newFragment.setDelegate(this);
                newFragment.show(getSupportFragmentManager(),
                        RefillMedicineDialogFragment.TAG);
                break;
            }
        }
    }
}