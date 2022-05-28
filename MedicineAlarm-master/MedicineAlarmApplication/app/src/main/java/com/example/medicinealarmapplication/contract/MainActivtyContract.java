package com.example.medicinealarmapplication.contract;

import android.content.Context;
import com.example.medicinealarmapplication.model.medicine_schedule;
import java.util.List;

public interface MainActivtyContract {

    interface Presenter {
        void getAlarmSchedule(Context context);
        void updateMedicineCount(Context context, String alarmID, int count);
        void onDestroy();
    }

    interface MainView {
        void showLoader();
        void hideProgress();
        void onFailed(String errMessage);
        void onAlarmSchedule(List<medicine_schedule> deliveryInfo);
        void updateMedicineCount(int count);
    }

    interface MainInteractor {

        interface onMainResultListener {
            void onSuccess();
            void onFailed(String errMessage);
            void onDeliveriesInfo(List<medicine_schedule> deliveryInfo);
            void updateMedicineCount(int count);
        }

        void fetchAlarmSchedule(Context context, onMainResultListener onFinishedListener);
        void updateMedicineCount(Context context, String alarmID, int count,
                                 onMainResultListener onFinishedListener);
    }
}
