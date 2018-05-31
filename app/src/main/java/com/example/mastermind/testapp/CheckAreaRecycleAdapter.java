package com.example.mastermind.testapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mastermind.testapp.OfferCategory;

import java.util.ArrayList;


public class CheckAreaRecycleAdapter extends
        RecyclerView.Adapter<CheckAreaRecycleAdapter.ViewHolder> {

    private ArrayList<OfferArea> filterList;
    private Context context;
    SharedPreferences settingsPreferences;

    public CheckAreaRecycleAdapter(ArrayList<OfferArea> filterModelList
            , Context ctx) {
        filterList = filterModelList;
        context = ctx;
        this.settingsPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public CheckAreaRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkbox_list_item,parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OfferArea filterM = filterList.get(position);
        holder.checkBox.setText(filterM.getTitle());
        for (int j = 0; j < settingsPreferences.getInt("numberOfCheckedAreas",0); j++) {
            System.out.println(holder.checkBox.getText() + "In the checkboxadapter");
            System.out.println(settingsPreferences.getString("checkedAreaTitle " + j, ""));
            if (holder.checkBox.getText().equals(settingsPreferences.getString("checkedAreaTitle " + j, ""))) {
                holder.checkBox.setChecked(true);
            }
        }
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            checkBox = view.findViewById(R.id.chbox_category);


        }

    }

}
