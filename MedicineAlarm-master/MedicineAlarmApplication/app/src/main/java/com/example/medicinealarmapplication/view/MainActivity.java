package com.example.medicinealarmapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medicinealarmapplication.R;
import com.example.medicinealarmapplication.adapter.MainRecyclerAdapter;
import com.example.medicinealarmapplication.base.BaseActivity;
import com.example.medicinealarmapplication.contract.MainActivtyContract;
import com.example.medicinealarmapplication.contract.MainInteractorImpl;
import com.example.medicinealarmapplication.contract.MainRecyclerDelegate;
import com.example.medicinealarmapplication.enums.ScheduleTime;
import com.example.medicinealarmapplication.model.medicine_schedule;
import com.example.medicinealarmapplication.presenter.MainPresenterImpl;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

public class MainActivity extends BaseActivity implements
        MainActivtyContract.MainView, MainRecyclerDelegate {

    private MainActivtyContract.Presenter presenter;
    private MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecyclerView;
    LinearLayout linearLayout;
    ImageView imgLogo;
    ProgressDialogFragment newFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("test");
        //FirebaseInstanceId.getInstance().getToken();
        presenter = new MainPresenterImpl(new MainInteractorImpl(), this,
                getApplicationContext());

        init();
        showLoader();
        presenter.getAlarmSchedule(getApplicationContext());

    }

    private void init(){

        mainRecyclerView = findViewById(R.id.mainRecyclerView);
        linearLayout = findViewById(R.id.linearLayout);
        imgLogo = findViewById(R.id.imgLogo);

        mainRecyclerAdapter = new MainRecyclerAdapter(this);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setAdapter(mainRecyclerAdapter);
        mainRecyclerAdapter.setClickListener(this);

        Glide.with(getViewContext())
                .load(R.drawable.medicine_logo)
                .into(imgLogo);
    }

    private void display(Boolean isError){
        if (isError)
        {
            linearLayout.setVisibility(View.VISIBLE);
            mainRecyclerView.setVisibility(View.GONE);
        }
        else {
            linearLayout.setVisibility(View.GONE);
            mainRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showLoader() {
        newFragment = new ProgressDialogFragment();
        newFragment.show(getSupportFragmentManager(), ProgressDialogFragment.TAG);
    }

    @Override
    public void hideProgress() {
        newFragment.dismissDialog();
    }

    @Override
    public void onFailed(String errMessage) {
        display(true);
    }

    @Override
    public void onAlarmSchedule(List<medicine_schedule> deliveryInfo) {

        if (deliveryInfo.size() > 0)
        {
            Intent intent= getIntent();

            Boolean notification = getIntent().getExtras().getBoolean(ScheduleTime.notification);

            if (notification)
            {
                int alarmNumber = getIntent().getExtras().getInt(ScheduleTime.notification_alarm_number);

                if (alarmNumber != 0)
                {
                    for (int i=0; i<= deliveryInfo.size(); i++)
                    {
                        medicine_schedule schedule = deliveryInfo.get(i);
                        if (schedule.getAlarm_number() == alarmNumber)
                        {
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("MEDICINE_DETAILS", schedule);

                            Intent intent1 = new Intent(this, MedicineAlarmDetailsActivity.class);
                            intent.putExtra("MEDICINE_DETAILS", schedule);
                            startActivity(intent1);
                        }

                    }
                }
                else
                {
                    mainRecyclerAdapter.populateData(deliveryInfo);
                    display(false);
                    Toast.makeText(this, "Error finding notification information", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                mainRecyclerAdapter.populateData(deliveryInfo);
                display(false);
            }
        }
        else
            display(true);
    }

    @Override
    public void updateMedicineCount(int count) { }

    @Override
    public void onItemClick(medicine_schedule schedule) {

        Bundle bundle = new Bundle();
        bundle.putParcelable("MEDICINE_DETAILS", schedule);

        Intent intent = new Intent(this, MedicineAlarmDetailsActivity.class);
        intent.putExtra("MEDICINE_DETAILS", schedule);
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        presenter.getAlarmSchedule(getApplicationContext());
    }
}