package com.example.donationapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    TextView msgText;
    ListView simpleList;
    ListView donationList;
    ArrayList<Donation> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        String msg = getIntent().getExtras().getString("myMSG");
        Donation t =  getIntent().getExtras().getParcelable("lastDonation");
        if (getIntent().hasExtra("allDonation"))
              list  = getIntent().getExtras().getParcelableArrayList("allDonation");


        msgText = findViewById(R.id.thankyou_msg);
        msgText.setText(msg);

        donationList = findViewById(R.id.donationList);
         DonationsBaseAdapter baseAdapter =
                 new DonationsBaseAdapter(((MyApp)getApplication()).allDonations,this);
        donationList.setAdapter(baseAdapter);

        simpleList = findViewById(R.id.simple_list);
        String[] collegesArray = {"Seneca College", "Humber College", "Centennial College"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.simple_list_row,
                R.id.simple_list_text, collegesArray);
        simpleList.setAdapter(adapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ReportActivity.this,
                        "The selected college is " + collegesArray[i] ,
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}