package com.example.donationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DonationsBaseAdapter extends BaseAdapter {

    ArrayList<Donation> donations;
    Context context;

    public DonationsBaseAdapter(ArrayList<Donation> donations, Context context) {
        this.donations = donations;
        this.context = context;
    }

    @Override
    public int getCount() {
        return donations.size();
    }

    @Override
    public Donation getItem(int i) {
        return donations.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = LayoutInflater.from(context).inflate(R.layout.donation_list_row,
              viewGroup,false);
        TextView amount_tv = v.findViewById(R.id.dl_amount_text);
        TextView pm_tv = v.findViewById(R.id.dl_pm_text);
        ImageView sharing_iv = v.findViewById(R.id.dl_sharing_image);

        amount_tv.setText(donations.get(i).amount + "$");
        pm_tv.setText(donations.get(i).paymentMethod);

        if (donations.get(i).isShared)
            sharing_iv.setImageResource(R.drawable.forsharing);
        else
            sharing_iv.setImageResource(R.drawable.notforshared);


        return v;// create a view for every single row in the list.
    }
}
