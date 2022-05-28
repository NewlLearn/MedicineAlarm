package com.example.medicinealarmapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.medicinealarmapplication.base.BaseActivity;
import com.example.medicinealarmapplication.view.MainActivity;
import com.google.firebase.messaging.FirebaseMessaging;

public class WelcomePageActivity extends BaseActivity {

    ImageView imgLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        imgLogo = findViewById(R.id.imgLogo);

        Glide.with(getViewContext())
                .load(R.drawable.medicine_logo)
                .into(imgLogo);
        FirebaseMessaging.getInstance().subscribeToTopic("test");
        //FirebaseInstanceId.getInstance().getToken();
        final Handler handler = new Handler();
        handler.postDelayed(() -> {

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("SAMPLE", "SAMPLE");
            startActivity(intent);

        }, 3000);
    }
}