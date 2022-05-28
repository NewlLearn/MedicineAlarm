package com.example.medicinealarmapplication.contract;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.medicinealarmapplication.model.medicine_schedule;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Objects;

public class MainInteractorImpl implements MainActivtyContract.MainInteractor {

    private onMainResultListener listener = null;
    private Context context;
    FirebaseFirestore firestore;

    @Override
    public void fetchAlarmSchedule(Context context, onMainResultListener onFinishedListener) {

        this.context = context;
        this.listener = onFinishedListener;

        firestore = FirebaseFirestore.getInstance();
        fetchAlarmScheduleDatabase(firestore);
    }

    private void fetchAlarmScheduleDatabase(FirebaseFirestore firestore)
    {
        firestore.collection("medicine_schedule")
                .orderBy("alarm_number", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(task ->
                {
                    if (task.isSuccessful())
                    {
                        List<medicine_schedule> userDetails;
                        QuerySnapshot documentSnapshots = task.getResult();
                        if (documentSnapshots != null && !documentSnapshots.isEmpty())
                        {
                            userDetails = documentSnapshots.toObjects(medicine_schedule.class);
                            Log.d("BERNA", userDetails.toString());

                            listener.onDeliveriesInfo(userDetails);
                        }

                    } else {
                        Log.d("TAG", "Error getting documents: ", task.getException());
                        listener.onFailed(task.getException().toString());
                    }
                }).addOnFailureListener(e ->{
                    listener.onFailed(e.toString());
                } );
    }

    @Override
    public void updateMedicineCount(Context context, String alarmID, int count,
                                    onMainResultListener onFinishedListener)
    {
        this.context = context;
        this.listener = onFinishedListener;

        firestore = FirebaseFirestore.getInstance();
        updateMedicineCountDatabase(firestore, alarmID, count);
    }

    private void updateMedicineCountDatabase(FirebaseFirestore firestore, String alarmID, int count)
    {
        DocumentReference medicineRef = firestore.collection("medicine_schedule")
                .document(alarmID);

        medicineRef.update("total_count", count)
                .addOnSuccessListener(aVoid -> {
                    listener.updateMedicineCount(count);
                })
                .addOnFailureListener(e -> {
                    listener.onFailed("Please check your Internet connection.");
                });
    }
}
