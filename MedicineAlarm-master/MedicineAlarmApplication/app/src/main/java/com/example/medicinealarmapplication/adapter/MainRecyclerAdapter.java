package com.example.medicinealarmapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.medicinealarmapplication.R;
import com.example.medicinealarmapplication.contract.MainRecyclerDelegate;
import com.example.medicinealarmapplication.model.medicine_schedule;
import com.example.medicinealarmapplication.utils.BackgroundUtil;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MainRecyclerAdapter extends
        RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>
{

    private List<medicine_schedule> deliveryInfo;
    MainRecyclerDelegate mainRecylerDelegate;
    medicine_schedule deliveryDetails;
    private Context mContext;

    public void setClickListener(MainRecyclerDelegate itemClickListener) {
        this.mainRecylerDelegate= itemClickListener;
    }
    public void populateData(List<medicine_schedule> deliveries){
        this.deliveryInfo = deliveries;
        notifyDataSetChanged();
    }

    public MainRecyclerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.main_alarm_listitem, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final medicine_schedule deliver = deliveryInfo.get(position);
        deliveryDetails = deliveryInfo.get(position);

        SimpleDateFormat formatOutput = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH);

        String timeLbl = formatOutput.format(deliver.getAlarm_time());

        viewHolder.txtLbl.setText(timeLbl);
        viewHolder.txtMedLbl.setText(deliver.getMedicine_name());

        String theme = "#"+deliver.getAlarm_color();
        viewHolder.viewColor.setBackgroundColor(Color.parseColor(theme));

        String dailyTheme = "#40"+deliver.getAlarm_color();

        if (deliver.getAtMonday().equals("Active"))
            BackgroundUtil.setTextViewDrawableColor(viewHolder.txtM, Color.parseColor(dailyTheme));

        if (deliver.getAtTuesday().equals("Active"))
            BackgroundUtil.setTextViewDrawableColor(viewHolder.txtT, Color.parseColor(dailyTheme));

        if (deliver.getAtWednesday().equals("Active"))
            BackgroundUtil.setTextViewDrawableColor(viewHolder.txtW, Color.parseColor(dailyTheme));

        if (deliver.getAtThursday().equals("Active"))
            BackgroundUtil.setTextViewDrawableColor(viewHolder.txtTh, Color.parseColor(dailyTheme));

        if (deliver.getAtFriday().equals("Active"))
            BackgroundUtil.setTextViewDrawableColor(viewHolder.txtF, Color.parseColor(dailyTheme));

        if (deliver.getAtSaturday().equals("Active"))
            BackgroundUtil.setTextViewDrawableColor(viewHolder.txtS, Color.parseColor(dailyTheme));

        if (deliver.getAtSunday().equals("Active"))
            BackgroundUtil.setTextViewDrawableColor(viewHolder.txtSu, Color.parseColor(dailyTheme));
    }

    @Override
    public int getItemCount() {
        return deliveryInfo == null ? 0 : deliveryInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private View viewColor;
        private TextView txtLbl;
        private TextView txtMedLbl;
        private TextView txtM;
        private TextView txtT;
        private TextView txtW;
        private TextView txtTh;
        private TextView txtF;
        private TextView txtS;
        private TextView txtSu;
        private ConstraintLayout constraint;
        private CardView cardView;

        public ViewHolder(View view) {
            super(view);
            viewColor = view.findViewById(R.id.viewColor);
            txtLbl = view.findViewById(R.id.txtLbl);
            txtMedLbl = view.findViewById(R.id.txtMedLbl);
            txtM = view.findViewById(R.id.txtM);
            txtT = view.findViewById(R.id.txtT);
            txtW = view.findViewById(R.id.txtW);
            txtTh = view.findViewById(R.id.txtTh);
            txtF = view.findViewById(R.id.txtF);
            txtS = view.findViewById(R.id.txtS);
            txtSu = view.findViewById(R.id.txtSu);
            constraint = view.findViewById(R.id.constraint);
            cardView = view.findViewById(R.id.cardView);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                /*case R.id.constraint:{
                    mainRecylerDelegate.onItemClick(deliveryInfo.get(getAdapterPosition()));
                    break;
                }*/
                case R.id.cardView:{
                    mainRecylerDelegate.onItemClick(deliveryInfo.get(getAdapterPosition()));
                    break;
                }
            }
        }
    }
}
