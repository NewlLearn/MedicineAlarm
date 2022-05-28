package com.example.medicinealarmapplication.presenter;

import android.content.Context;
import com.example.medicinealarmapplication.contract.MainActivtyContract;
import com.example.medicinealarmapplication.model.medicine_schedule;
import java.util.List;

public class MainPresenterImpl implements
        MainActivtyContract.Presenter,
        MainActivtyContract.MainInteractor.onMainResultListener{

    private MainActivtyContract.MainView view;
    private final MainActivtyContract.MainInteractor interactor;
    Context context;

    public MainPresenterImpl(MainActivtyContract.MainInteractor interactor,
                             MainActivtyContract.MainView view, Context context) {
        this.context = context;
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void getAlarmSchedule(Context context) {
        if (view != null)
            interactor.fetchAlarmSchedule(context, this);

    }

    @Override
    public void updateMedicineCount(Context context, String alarmID, int count) {
        if (view != null)
            interactor.updateMedicineCount(context, alarmID, count, this);

    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onSuccess() {
        if (view != null)
        {
            view.hideProgress();
        }
    }

    @Override
    public void onFailed(String errMessage) {
        if (view != null) {
            view.hideProgress();
            view.onFailed(errMessage);
        }
    }

    @Override
    public void onDeliveriesInfo(List<medicine_schedule> deliveryInfo) {
        if (view != null) {
            view.hideProgress();
            view.onAlarmSchedule(deliveryInfo);
        }
    }

    @Override
    public void updateMedicineCount(int count) {
        if (view != null) {
            view.hideProgress();
            view.updateMedicineCount(count);
        }
    }
}
