package com.example.medicinealarmapplication.view;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.format.DateFormat;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.example.medicinealarmapplication.R;
import com.example.medicinealarmapplication.enums.ScheduleTime;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;
import java.util.Objects;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String title = Objects.requireNonNull(remoteMessage.getNotification()).getTitle();
        String message = Objects.requireNonNull(remoteMessage.getNotification()).getBody();

        long time = remoteMessage.getSentTime();
        String dateString = DateFormat.format("HH", new Date(time)).toString();
        Log.i("BERNALOO",dateString);
        //showNotification(title, message);
        searchSchedule(dateString, title, message);
    }

    private void searchSchedule(String time, String title, String message)
    {
        if(time.equals(ScheduleTime.AM6))
            showNotification(ScheduleTime.alarm_number1, title, message);
        else if(time.equals(ScheduleTime.NN12))
            showNotification(ScheduleTime.alarm_number2, title, message);
        else if(time.equals(ScheduleTime.PM6))
            showNotification(ScheduleTime.alarm_number3, title, message);
        else
            showNotification(0, title, message);
    }

    private void showNotification(int alarmNumber, String title, String message) {

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra(ScheduleTime.notification, true);
        i.putExtra(ScheduleTime.notification_alarm_number, alarmNumber);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(message)
                .setSound(soundUri)
                .setSmallIcon(R.drawable.welcome_page)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
}