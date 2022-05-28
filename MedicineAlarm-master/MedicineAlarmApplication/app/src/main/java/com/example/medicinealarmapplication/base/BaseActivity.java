package com.example.medicinealarmapplication.base;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    public Context getViewContext() {
        return this;
    }

    public void showProgressDialog(Context context, String title, String message) {
        try {
            if (!isFinishing() && mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(context);
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setCancelable(false);
                mProgressDialog.setInverseBackgroundForced(true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setMessage(title + "\n" + message);
                mProgressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideProgressDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing())
                if (mProgressDialog != null && !isFinishing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
