package com.example.medicinealarmapplication.view;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicinealarmapplication.R;
import com.example.medicinealarmapplication.contract.MainActivtyContract;
import com.example.medicinealarmapplication.contract.MainInteractorImpl;
import com.example.medicinealarmapplication.contract.RefreshDelegate;
import com.example.medicinealarmapplication.model.medicine_schedule;
import com.example.medicinealarmapplication.presenter.MainPresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RefillMedicineDialogFragment extends DialogFragment implements
        View.OnClickListener, MainActivtyContract.MainView {

    public static String TAG = "RefillMedicineDialogFragment";
    private medicine_schedule medicineSched;
    private TextView txtMedicineName;
    List<Integer> availableLst= new ArrayList<>();
    Spinner spinnerRefill;
    String refillNo;
    Button btnAdd;
    private ImageView imageViewBack;
    MainPresenterImpl presenter;
    private RefreshDelegate delegate;
    ProgressDialogFragment newFragment;
    int totalCount;

    public void setDelegate(RefreshDelegate del) {
        delegate = del;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_refill_medicine_dialog, container, false);

        init(view);

        return view;
    }

    private void init(View view){

        presenter = new MainPresenterImpl(new MainInteractorImpl(), this, requireContext());
        Bundle bundle = getArguments();
        assert bundle != null;
        medicineSched = bundle.getParcelable("MEDICINE_DETAILS");

        txtMedicineName = view.findViewById(R.id.txtMedicineName);
        btnAdd = view.findViewById(R.id.btnAdd);
        imageViewBack = view.findViewById(R.id.imageViewBack);

        spinnerRefill = view.findViewById(R.id.spinnerRefill);
        int maxCount = medicineSched.getMax_count();
        totalCount = medicineSched.getTotal_count();

        int available = maxCount - totalCount;

        for (int i=1; i<=available; i++){
            availableLst.add(i);
        }

        txtMedicineName.setText(String.valueOf(totalCount));

        // Creating adapter for spinner
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, availableLst);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerRefill.setAdapter(dataAdapter);

        spinnerRefill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                refillNo = String.valueOf(availableLst.get(position));
                String item = parent.getItemAtPosition(position).toString();
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        btnAdd.setOnClickListener(this);
        imageViewBack.setOnClickListener(this);
    }

    public static RefillMedicineDialogFragment newInstance() {
        return new RefillMedicineDialogFragment();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnAdd:{

                if(refillNo != null)
                {
                    showLoader();
                    String alarmID = medicineSched.getAlarm_id();
                    int refill = Integer.parseInt(refillNo);

                    int total = refill + totalCount;

                    presenter.updateMedicineCount(requireContext(), alarmID, total);
                }
                else
                    Toast.makeText(requireContext(), "Please choose how many ro refill.", Toast.LENGTH_LONG).show();

                break;
            }
            case R.id.imageViewBack:{
                dismiss();
                break;
            }
        }
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
        Toast.makeText(getActivity(), "Please check your Internet connection",
                Toast.LENGTH_LONG).show();
    }


    @Override
    public void onAlarmSchedule(List<medicine_schedule> deliveryInfo) {

    }

    @Override
    public void updateMedicineCount(int count) {
        delegate.setRefresh(count);
        dismiss();
    }
}