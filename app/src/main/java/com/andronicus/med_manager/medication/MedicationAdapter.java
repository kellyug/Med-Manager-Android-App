package com.andronicus.med_manager.medication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andronicus.med_manager.R;
import com.andronicus.med_manager.editmedication.EditMedicationActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by andronicus on 3/25/2018.
 */

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder>{

    private int[] colors = new int[]{
            R.color.card_background1,
            R.color.card_background2,
            R.color.card_background3,
            R.color.card_background4,
            R.color.card_background5,
            R.color.card_background6,
            R.color.card_background7,
            R.color.card_background8,
            R.color.card_background9,
            R.color.card_background10,
            R.color.card_background11,
    };

    private Context mContext;
    private List<String> mStrings;
    public MedicationAdapter(List<String> strings){
        mStrings = strings;
    }

    @Override
    public MedicationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medication,parent,false);
        return new MedicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MedicationViewHolder holder, int position) {
        holder.bind(mStrings.get(position));
    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    public void filter(@NonNull List<String> strings){
        mStrings = new ArrayList<>();
        mStrings.addAll(strings);
        notifyDataSetChanged();
    }

    public class MedicationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,PopupMenu.OnMenuItemClickListener{

        TextView mMedicationInitial;
        TextView mMedicationName;
        TextView mPrescription;
        TextView mEndDate;
        ImageView mEditMedication;
        public MedicationViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            mMedicationInitial = view.findViewById(R.id.tv_medication_initial);
            mMedicationName = view.findViewById(R.id.tv_medication_name);
            mPrescription = view.findViewById(R.id.tv_prescription);
            mEndDate = view.findViewById(R.id.tv_end_date);
            mEditMedication = view.findViewById(R.id.imageview_edit_medication);
            mEditMedication.setOnClickListener(v -> {
                PopupMenu popupMenu = new PopupMenu(mContext,mEditMedication);
                popupMenu.getMenuInflater().inflate(R.menu.medication_pop_up,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(MedicationViewHolder.this);
                popupMenu.show();
            });
        }
        private void bind(String name){
            String medicationInital = name.substring(0,1);
            Random random = new Random();
            int position = random.nextInt(colors.length);
            mMedicationInitial.setBackgroundColor(mContext.getResources().getColor(colors[position]));
            mMedicationInitial.setText(medicationInital);
            mMedicationName.setText(name);
            mPrescription.setText("1 * 3");
            mEndDate.setText("26/04/2018");
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_edit_medication :
                    mContext.startActivity(EditMedicationActivity.newIntent(mContext));
                    break;
                case R.id.action_delete_medication :
                    Toast.makeText(mContext, "Delete Medication", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }

        @Override
        public void onClick(View v) {
            Random random = new Random();
            int position = random.nextInt(colors.length);
            String name = mStrings.get(getAdapterPosition());
            mContext.startActivity(MedicationPopupActivity.newIntent(mContext,name,position));
        }
    }
}
