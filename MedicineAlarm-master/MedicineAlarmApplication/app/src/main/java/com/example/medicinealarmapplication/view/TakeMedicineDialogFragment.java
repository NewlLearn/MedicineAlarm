package com.example.medicinealarmapplication.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import com.example.medicinealarmapplication.R;
import com.example.medicinealarmapplication.contract.MainActivtyContract;
import com.example.medicinealarmapplication.contract.MainInteractorImpl;
import com.example.medicinealarmapplication.contract.RefreshDelegate;
import com.example.medicinealarmapplication.model.medicine_schedule;
import com.example.medicinealarmapplication.presenter.MainPresenterImpl;

import java.util.List;
import java.util.Objects;


public class TakeMedicineDialogFragment extends DialogFragment
        implements MainActivtyContract.MainView, View.OnClickListener{

    private MainActivtyContract.Presenter presenter;
    public static String TAG = "TakeMedicineDialogFragment";
    private RefreshDelegate delegate;
    private medicine_schedule medicineSched;
    private TextView btnClose;
    private TextView txtRefill;
    private TextView txtViewLabel;
    private ProgressDialogFragment newFragment;

    public void setDelegate(RefreshDelegate del) {
        delegate = del;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_take_medicine_dialog, container, false);

        presenter = new MainPresenterImpl(new MainInteractorImpl(), this,
                requireContext());

        init(view);
        return view;
    }

    private void init(View view){

        btnClose = view.findViewById(R.id.btnClose);
        txtRefill = view.findViewById(R.id.txtRefillLabel);
        txtViewLabel = view.findViewById(R.id.textViewLabel);

        showLoader();

        Bundle bundle = getArguments();
        assert bundle != null;
        medicineSched = bundle.getParcelable("MEDICINE_DETAILS");

        if (medicineSched.getTotal_count() == 0)
        {
            hideProgress();
            txtViewLabel.setVisibility(View.GONE);
            txtRefill.setVisibility(View.VISIBLE);
        }
        else if(medicineSched.getTotal_count() <= medicineSched.getMin_count())
        {
            txtRefill.setVisibility(View.VISIBLE);
            updateMedicine();
        }
        else if (medicineSched.getTotal_count() > 0)
            updateMedicine();


        btnClose.setOnClickListener(this);
    }

    private void updateMedicine()
    {
        int medicine = medicineSched.getTotal_count() - 1;
        presenter.updateMedicineCount(requireContext(), medicineSched.getAlarm_id(), medicine);
    }

    public static TakeMedicineDialogFragment newInstance() {
        return new TakeMedicineDialogFragment();
    }

    @Override
    public void showLoader() {
        newFragment = new ProgressDialogFragment();
        newFragment.show(requireActivity().getSupportFragmentManager(), ProgressDialogFragment.TAG);
    }

    @Override
    public void hideProgress() {
        newFragment.dismissDialog();
    }

    @Override
    public void onFailed(String errMessage) {
        hideProgress();
        txtRefill.setText(errMessage);
        txtViewLabel.setVisibility(View.GONE);
        txtRefill.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAlarmSchedule(List<medicine_schedule> deliveryInfo) {

    }

    @Override
    public void updateMedicineCount(int count) {
        delegate.setRefresh(count);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnClose:{
                dismiss();
            }
        }
    }
}