package com.example.medicinealarmapplication.view;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicinealarmapplication.R;

public class ProgressDialogFragment extends DialogFragment {

    public static String TAG = "ProgressDialogFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progress_dialog, container, false);

        this.setCancelable(false);
        return view;
    }

    public static ProgressDialogFragment newInstance() {
        return new ProgressDialogFragment();
    }

    public void dismissDialog()
    {
        this.dismiss();
    }
}